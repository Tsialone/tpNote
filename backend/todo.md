# 📝 TP TODO LIST

**Participants :** Zoara, Nyavo

---

## Docker & Setup
- [ok] Nyavo : `docker run --name tp_mysql -e MYSQL_ROOT_PASSWORD=monmotdepasse -d -p 3305:3305 mysql:8.0`  
- [ok] Zoara : Initialisation stacks : PHP 8.2 + MySQL 8 (Docker Compose)  

---

## Base de données
- [ok] Zoara : Traduction en anglais  
- [ok] Nyavo : Respect du `snake_case`  
- [ok] Nyavo : Création des données  
- [ok] Zoara : Import MySQL  

---

## APIs

### GET /students/{studentId}/studyPeriods/{studyPeriodsId}/scores
- [ok] Nyavo : POSTMAN  
- [ok] Zoara : DATA (Vita)  
    - [ok] Nyavo : student  
    - [ok] Zoara : year  
    - [ok] Nyavo : study_period  
    - [ok] Zoara : registration  
    - [ok] Nyavo : course_unit  
    - [ok] Zoara : study_period_course_unit  
    - [ok] Nyavo : exam_session  
    - [ok] Zoara : student_course_unit_score  
- [ok] Nyavo : SERVICE WP : `GetStudentScoresByStudyPeriod(studentId, studyPeriodId)`  
    - [ok] Zoara : Cas : étudiant n'existe pas  
    - [ok] Nyavo : Cas : problème connexion DB  
    - [ok] Zoara : Cas : autres erreurs imprévues  
- [ok] Nyavo : INTEGRATION  
    - [ok] Zoara : Modèle robuste : status, data, error  
    - [ok] Nyavo : Meta  
    - [ok] Zoara : Codes d’erreurs prévus avec message explicite  

### GET /students/{studentId}/years/{yearsId}/scores
- [ok] Zoara : POSTMAN  
- [ok] Nyavo : DATA : done  
- [ok] Zoara : SERVICE : `GetStudentScoresByYearId(studentId, yearId)`  
    - [ok] Nyavo : Cas : étudiant n'existe pas  
    - [ok] Zoara : Cas : problème connexion DB  
    - [ok] Nyavo : Cas : autres erreurs imprévues  
- [ok] Zoara : INTEGRATION  
    - [ok] Nyavo : Modèle robuste : status, data, error  
    - [ok] Zoara : Meta  
    - [ok] Nyavo : Codes d’erreurs prévus avec message explicite  

### POST /user/login
- [ok] Nyavo : POSTMAN  
- [ok] Zoara : DATA : user(username, pass)  
- [ok] Nyavo : SERVICE : `login(username, pass)`  
    - [ok] Zoara : Cas : utilisateur non authentifié pour tous les REST APIs  
- [ok] Nyavo : INTEGRATION  
    - [ok] Zoara : Modèle robuste : status, data, error  
    - [ok] Nyavo : Meta  
    - [ok] Zoara : Codes d’erreurs prévus avec message explicite  

---

## TP1a
- [ok] Zoara : APIs : `/students/scores`, `/students/`  
- [ok] Nyavo : Ajout des éléments respectant le modèle robuste  
- [ok] Zoara : Collection Postman :  
    - [ok] Nyavo : Création variable d’URL  
    - [ok] Zoara : Création variable token  
    - [ok] Nyavo : Création de tous les fichiers API dans la collection  

# Liste des semestres
Semestre 1
Semestre 2
etc

Lien: liste des etudiants
# liste des etudiants
ex:
Colonnes: S1 S2 S3 S4
ETU001     1  12 13 14 
ETU002     10  12 17 7

- Affichage
    - composant semestres
    - composant etudiants 
    - Composants
        - Semestres
        - Etudiants(DATA: semestres[])
                - EtudiantsMoyennes()
                    - EtudiantMoyennes(etudiant, notes)   
- Base
    -
- Fonction
    [ok]- getMoyenneEtudiantBySemestre(idEtudiant, Semestre)
    ['1']
    - getMoyenneEtudiantBySemestreName(idEtudiant, Semestre)
    [ok]- getMoyennesEtudiants(idEtudiant, listeSemestre)
    {
        studentId,
        etu,
        [12, 13, 7]
    }
    - getListeEtudiantMoyennes(ListeSemestre)
- Integration


# Releve de note Etudiant d'un semestre
- Affichage
    - infos Etudiants
    - composant notesSemestre
    - Composants
        - Releve 
            - infoEtudiant
            - NotesSemestre(liste[NoteUE]) (header)
                - NoteUE(ue, intitule, credits, note, resultat, session)
    
- Base
    -
- Fonction
    [ok]- getNotesEtudiantBySemestre(etudiant, semestre)
    - getUserInfo()
    
- Integration
# fiche etudiant avec moyennes de S4 a S6
[INFOS]
[moyennes]
[lien: L1 na L2]

- affichage
    - composants
        - ficheEtudiantDetaille (data= liste annees)
            - FicheEtudiant
            - EtudiantMoyennes(etudiant, semestres)   
- base
- fonction
- integration 

# Releve de note d'une annee
releve note 2 semestres + moyenne generale
- affichage
    - composants infos etudiants
    - composant NotesSemestre * 2
    - moyenne generale annee

    - composants
        - infoEtudiant
        - NotesSemestre(liste[NoteUE]) (header)
        - ficheEtudiantDetaille (data = liste annees)
            - FicheEtudiant
            - EtudiantMoyennes(etudiant, semestres)   
- base
- fonction
    - get
- integration 






