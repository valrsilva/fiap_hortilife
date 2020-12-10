# Executar no Play With Docker

git clone https://github.com/valrsilva/fiap_hortilife.git
cd fiap_hortilife/_docker
docker network create -d overlay hortilife
docker stack deploy -c docker-compose-no-net.yml hortilife
docker stack ps hortilife
