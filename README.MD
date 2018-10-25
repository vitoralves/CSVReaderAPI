#Projeto
API que lê um arquivo csv e armazena os dados em uma tabela disponibilizando endpoints para consulta.
 
# Configuração
Crie uma nova base de dados para salvar os dados do arquivo

create database csvreader
template = template0
encoding = 'UTF8'
connection limit -1;

As configurações de conexão estão armazenadas no aquivo 'application.properties' a url de conexão deve ser alterada de acordo com as configurações locais:
spring.datasource.url= jdbc:postgresql://localhost:5432/csvreader?user=postgres&password=a


Após configurar o projeto gere um executável utilizando o comando do maven: mvn clean install
Execute  a API com o comando: java -jar CSVReaderAPI-0.0.1-SNAPSHOT.jar

# Utilização
Para popular a base de dados faça o upload do arquivo csv fazendo uma requisição POST no endpoint /api/upload, em caso de sucesso será retornado todos os dados inseridos na base de dados.

# Endpoints
Swagger configurado para facilitar a leitura dos endpoints, acesso através da url localhost:8080/swagger-ui.html





