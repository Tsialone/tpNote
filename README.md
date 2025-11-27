# tpNote

natokako minhintsy ny front sy ny back
efa mande ilay resaka token
efa ao ny models sy ny repo any
fa mbola tsy ao kosa ny:
    -dto
    -mappers
    -services

rehefa hi dev dia 
    -miditra cd prod aloh de docker compose up -d  de rh tsy mety de asio --build
    -backend
        -piana ilay ao @docker, docker stop spring_app
        -miditra cd backend de mvn spring-boot:run
    -front
        -miditra frontend de npm run dev

rehefa ideploie final dia
    -./prod.sh


NB:
 -rehefa dev de soloina localhost ilay url postgres ao @application.propreties

raha hiconnect via psql dia
psql -U admin -h localhost -p 5432 -d itu_db 