# Software Quality (COS482) 2020.2 Course Project

## Time

- [João Henrique Franco](https://github.com/joaohenrifranco)
- [João Pedro Brandao](https://github.com/jpbrs/)
- [Pedro Maciel Xavier](https://github.com/pedromxavier)

## Documentação

- Board do projeto
- Documento de Visão
## Executando o processo BPMN

### Via Docker

- Baixe o container com o engine do camunda

`docker pull camunda/camunda-bpm-platform:run-latest`

- Execute o container

`docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:run-latest`

- [Baixe o camunda modeler](https://camunda.com/download/modeler/)

- Execute uma instancia do processo usando o endpoint `http://localhost:8080/engine-rest`

- Navegue até `http://localhost:8080/`

- Credenciais: `demo`/`demo`

## Via Spring Boot app

- Copie o `bpmn/cinema.bpmn` para a `ingressa-camunda-web/src/main/resources`:

`cp bpmn/cinema.bpmn ingressa-camunda-web/src/main.resources/`

- No diretório `ingressa-camunda-web` instale as dependências:

`mvn install`

- Execute a aplicação: 

`mvn spring-boot:run`
  
- Navegue até `http://localhost:8080/`

- Credenciais: `COS482`/`COS482`