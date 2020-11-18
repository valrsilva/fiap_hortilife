# Dependencias

Baixar e instalar o docker desktop

# Configuração

Abrir o Eclipse e importar os projetos pela opção Import -> Exist Projects -> Maven

# Execução

Ir na pasta 'docker' e executar o comando pelo CMD 'docker-compose up'

Após os serviços iniciarem, criar os bancos de dados:

Rodar os comandos abaixo:

docker exec -it docker_postgres_1 bash

psql -U postgres

create DATABASE compradb;

Após criar os banco de dados, iniciar os projetos pelo Eclipse na ordem:

1-server-config
2-eureka-server
3-zull-server
4-busca
5-compra

# Links

Eureka: http://localhost:8761/

Arquivo do Postman e do Swagger na pasta _files