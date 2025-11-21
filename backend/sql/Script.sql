
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS university (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS academic_year (
    id SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS program (
    id SERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    etu VARCHAR(50) UNIQUE NOT NULL,
    program_id INT NOT NULL,
    FOREIGN KEY (program_id) REFERENCES program(id)
);

CREATE TABLE IF NOT EXISTS year (
    id SERIAL PRIMARY KEY,
    label VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS study_period (
    id SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    year_id INT NOT NULL,
    FOREIGN KEY (year_id) REFERENCES year(id)
);


CREATE TABLE IF NOT EXISTS registration (
    id SERIAL PRIMARY KEY,
    registration_date DATE NOT NULL,
    student_id INT NOT NULL,
    study_period_id INT NOT NULL,
    academic_year_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (study_period_id) REFERENCES study_period(id),
    FOREIGN KEY (academic_year_id) REFERENCES academic_year(id),
    CONSTRAINT uk_student_period_year UNIQUE (student_id, study_period_id, academic_year_id)
);


CREATE TABLE IF NOT EXISTS course_unit (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    label VARCHAR(255) NOT NULL,
    description TEXT,
    credits INT NOT NULL
);

CREATE TABLE IF NOT EXISTS study_period_course_unit (
    id SERIAL PRIMARY KEY,
    study_period_id INT NOT NULL,
    course_unit_id INT NOT NULL,
    FOREIGN KEY (study_period_id) REFERENCES study_period(id),
    FOREIGN KEY (course_unit_id) REFERENCES course_unit(id),
    CONSTRAINT uk_period_unit UNIQUE (study_period_id, course_unit_id)
);


CREATE TABLE IF NOT EXISTS options (
    id SERIAL PRIMARY KEY,
    label VARCHAR(150) NOT NULL,
    responsible_name VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS option_course_unit_period (
    id SERIAL PRIMARY KEY,
    study_period_id INT NOT NULL,
    options_id INT NOT NULL,
    course_unit_id INT NOT NULL,
    FOREIGN KEY (study_period_id) REFERENCES study_period(id),
    FOREIGN KEY (options_id) REFERENCES options(id),
    FOREIGN KEY (course_unit_id) REFERENCES course_unit(id),
    CONSTRAINT uk_period_option_unit UNIQUE (study_period_id, options_id, course_unit_id)
);

CREATE TABLE IF NOT EXISTS option_unit_group (
    id SERIAL PRIMARY KEY,
    options_id INT NOT NULL,
    description TEXT,
    FOREIGN KEY (options_id) REFERENCES options(id)
);


CREATE TABLE IF NOT EXISTS optional_course_unit (
    id SERIAL PRIMARY KEY,
    course_unit_id INT NOT NULL,
    option_unit_group_id INT NOT NULL,
    FOREIGN KEY (course_unit_id) REFERENCES course_unit(id),
    FOREIGN KEY (option_unit_group_id) REFERENCES option_unit_group(id),
    CONSTRAINT uk_unit_group UNIQUE (course_unit_id, option_unit_group_id)
);


CREATE TABLE IF NOT EXISTS exam_session (
    id SERIAL PRIMARY KEY,
    exam_date DATE NOT NULL,
    academic_year_id INT NOT NULL,
    study_period_id INT NOT NULL,
    FOREIGN KEY (academic_year_id) REFERENCES academic_year(id),
    FOREIGN KEY (study_period_id) REFERENCES study_period(id)
);


CREATE TABLE IF NOT EXISTS config_result_grade (
    id SERIAL PRIMARY KEY,
    min_score NUMERIC(5, 2) NOT NULL,
    max_score NUMERIC(5, 2) NOT NULL,
    label VARCHAR(100) NOT NULL,
    application_date DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS config_general_average (
    id SERIAL PRIMARY KEY,
    min_average NUMERIC(5, 2) NOT NULL,
    max_average NUMERIC(5, 2) NOT NULL,
    mention VARCHAR(100) NOT NULL,
    application_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS config_progression (
    id SERIAL PRIMARY KEY,
    min_general_average NUMERIC(5, 2) NOT NULL,
    eliminatory_score NUMERIC(5, 2) NOT NULL,
    max_eliminatory_unit INT NOT NULL,
    application_date DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS student_course_unit_score (
    id SERIAL PRIMARY KEY,
    score NUMERIC(5, 2) NOT NULL,
    student_id INT NOT NULL,
    course_unit_id INT NOT NULL,
    exam_session_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_unit_id) REFERENCES course_unit(id),
    FOREIGN KEY (exam_session_id) REFERENCES exam_session(id),
    CONSTRAINT uk_student_unit_session UNIQUE (student_id, course_unit_id, exam_session_id)
);


INSERT INTO university (id, name, logo) VALUES
(1, 'IT University', 'itu_logo.png')
ON CONFLICT (id) DO NOTHING;

INSERT INTO academic_year (id, label, start_date, end_date) VALUES
(1, '2024-2025', '2024-09-01', '2025-06-30')
ON CONFLICT (id) DO NOTHING;

INSERT INTO program (id, label, description) VALUES
(1, 'Computer Science', 'Program focused on software development and data structures.')
ON CONFLICT (id) DO NOTHING;

INSERT INTO year (id, label) VALUES
(1, 'Year 1'),
(2, 'Year 2')
ON CONFLICT (id) DO NOTHING;

INSERT INTO study_period (id, label, start_date, end_date, year_id) VALUES
(1, 'Semester 1', '2024-09-01', '2024-12-31', 1),
(2, 'Semester 2', '2025-01-01', '2025-06-30', 1),
(3, 'Semester 3', '2025-09-01', '2025-12-31', 2),
(4, 'Semester 4', '2026-01-01', '2026-06-30', 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO course_unit (id, code, label, description, credits) VALUES
(1, 'CS101', 'Introduction to Programming', 'Fundamentals of Java/Python.', 6),
(2, 'MA101', 'Linear Algebra', 'Vector spaces and matrices.', 4),
(3, 'CS102', 'Data Structures', 'Lists, stacks, queues, and trees.', 6),
(4, 'DB101', 'Database Fundamentals', 'SQL and relational model.', 4),
(5, 'CS201', 'Operating Systems', 'Kernel and process management.', 6),
(6, 'NW201', 'Computer Networks', 'OSI model and TCP/IP.', 4),
(7, 'WEB201', 'Web Development', 'Frontend and Backend technologies.', 6),
(8, 'AI201', 'Introduction to AI', 'Search algorithms and basic ML.', 4)
ON CONFLICT (id) DO NOTHING;

INSERT INTO study_period_course_unit (study_period_id, course_unit_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8)
ON CONFLICT DO NOTHING;

INSERT INTO student (id, first_name, last_name, birth_date, etu, program_id) VALUES
(1, 'Alice', 'Smith', '2005-01-15', 'CS24A001', 1),
(2, 'Bob', 'Johnson', '2004-05-20', 'CS24B002', 1),
(3, 'Charlie', 'Williams', '2003-11-03', 'CS24C003', 1),
(4, 'Dana', 'Brown', '2005-08-25', 'CS24D004', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO registration (registration_date, student_id, study_period_id, academic_year_id) VALUES
('2024-09-01', 1, 1, 1), ('2025-01-01', 1, 2, 1), ('2025-09-01', 1, 3, 1), ('2026-01-01', 1, 4, 1),
('2024-09-01', 2, 1, 1), ('2025-01-01', 2, 2, 1), ('2025-09-01', 2, 3, 1), ('2026-01-01', 2, 4, 1),
('2024-09-01', 3, 1, 1), ('2025-01-01', 3, 2, 1), ('2025-09-01', 3, 3, 1), ('2026-01-01', 3, 4, 1),
('2024-09-01', 4, 1, 1), ('2025-01-01', 4, 2, 1), ('2025-09-01', 4, 3, 1), ('2026-01-01', 4, 4, 1)
ON CONFLICT DO NOTHING;

INSERT INTO exam_session (id, exam_date, academic_year_id, study_period_id) VALUES
(1, '2025-01-15', 1, 1),
(2, '2025-06-20', 1, 2),
(3, '2026-01-15', 1, 3),
(4, '2026-06-20', 1, 4)
ON CONFLICT (id) DO NOTHING;

INSERT INTO student_course_unit_score (score, student_id, course_unit_id, exam_session_id) VALUES
(14.50, 1, 1, 1), (12.00, 1, 2, 1), (13.50, 1, 3, 2), (16.00, 1, 4, 2), (15.00, 1, 5, 3), (12.50, 1, 6, 3), (16.00, 1, 7, 4), (17.50, 1, 8, 4),
(10.00, 2, 1, 1), (15.50, 2, 2, 1), (18.00, 2, 3, 2), (10.00, 2, 4, 2), (11.00, 2, 5, 3), (17.00, 2, 6, 3), (13.00, 2, 7, 4), (14.00, 2, 8, 4),
(16.00, 3, 1, 1), (09.50, 3, 2, 1), (07.50, 3, 3, 2), (14.50, 3, 4, 2), (14.00, 3, 5, 3), (13.00, 3, 6, 3), (15.50, 3, 7, 4), (12.00, 3, 8, 4),
(11.50, 4, 1, 1), (13.00, 4, 2, 1), (12.50, 4, 3, 2), (11.00, 4, 4, 2), (08.00, 4, 5, 3), (10.50, 4, 6, 3), (10.00, 4, 7, 4), (09.00, 4, 8, 4)
ON CONFLICT DO NOTHING;

INSERT INTO config_result_grade (min_score, max_score, label, application_date) VALUES
(0.00, 9.99, 'Fail', '2024-09-01'),
(10.00, 20.00, 'Pass', '2024-09-01')
ON CONFLICT DO NOTHING;

INSERT INTO config_general_average (min_average, max_average, mention, application_date) VALUES
(0.00, 9.99, 'Insufficient', '2024-09-01'),
(10.00, 11.99, 'Acceptable', '2024-09-01'),
(12.00, 13.99, 'Good', '2024-09-01'),
(14.00, 20.00, 'Very Good', '2024-09-01')
ON CONFLICT DO NOTHING;

INSERT INTO config_progression (min_general_average, eliminatory_score, max_eliminatory_unit, application_date) VALUES
(10.00, 08.00, 2, '2024-09-01')
ON CONFLICT DO NOTHING;

CREATE TABLE users (
   id_user SERIAL PRIMARY KEY , 
   username VARCHAR (255) UNIQUE , 
   password VARCHAR(255) NOT NULL ,
   role VARCHAR(255) NOT NULL  
);

INSERT INTO users (username, password, role) VALUES
('admin', 'admin', 'ADMIN');