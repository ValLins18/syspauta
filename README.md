# SysPauta

O SysPauta foi criado como parte de uma avaliação técnica com foco em construção de APIs RESTful robustas, comunicação com aplicações mobile via JSON dinâmico e integração com serviços externos. Toda a lógica de UI é entregue via backend através de layouts JSON, facilitando o consumo em dispositivos móveis.

---

## :triangular_ruler: Arquitetura da Solução

- Backend 100% desacoplado do frontend mobile
- Comunicação baseada em layouts JSON (FORMULARIO e SELECAO)
- API REST com versionamento (ex: /api/v1)
- Persistência em banco de dados MySQL 8
- Integração externa com validação de CPF via API (https://user-info.herokuapp.com)
- Containerização com Docker e Docker Compose
- Deploy simplificado para cloud via VM ou ambientes gerenciados

---

## :star: Funcionalidades

- Cadastro de pautas
- Abertura de sessões de votação
- Registro de votos
- Consulta de resultados
- API RESTful documentada

---

## :rocket: Tecnologias Utilizadas

- Java 24
- Spring Boot
- Maven
- Docker
- Swagger/OpenAPI (documentação da API)

---
## :memo: Observações Técnicas

- Aplicação segue o padrão Clean Code, com separação clara entre controllers, serviços, modelos e repositórios.
- Utilizado RestTemplate (ou WebClient, se atualizou) para integrações externas.
- Possui testes unitários cobrindo o domínio de persistência.
- Dockerfile multistage para otimizar build da aplicação.
---
## :cloud: Deploy em Cloud

A aplicação pode ser facilmente implantada em nuvem utilizando Docker Compose em máquinas virtuais. Exemplo de fluxo:

1. Provisionamento de VM (Google Cloud / AWS / Azure)
2. Instalação de Docker e Docker Compose
3. Clone do projeto via Git
4. Execução de `docker-compose up --build -d`

---
## :iphone: Fluxo de Telas

- Tela inicial de seleção de funcionalidades
- Cadastro de nova pauta
- Abertura de sessão de votação
- Lista de sessões abertas
- Votação de pauta
- Consulta de resultados
- Visualização detalhada de resultado

---

## :file_folder: Estrutura do Projeto

```
src/
  main/
    java/
      com.example.syspauta.syspauta/
        Controller/
          RestController/
          UIController/
        Model/
          DTO/
          Enums/
        Repository/
        UI/
          Layout/
            Components/
          Views/
    resources/
      application.properties
  test/
    java/
      com.example.syspauta.syspauta/
        Model/
        Repository/
```

---

## :gear: Como rodar localmente

1. **Clone o repositório:**
   ```sh
   git clone (https://github.com/ValLins18/syspauta.git)
   cd syspauta
   ```

2. **Build do projeto:**
   ```sh
   mvn clean package
   ```

3. **Execute a aplicação:**
   ```sh
   mvn spring-boot:run
   ```
   ou
   ```sh
   java -jar target/*.jar
   ```

4. **Acesse:**  
   [http://localhost:8080/syspauta/swagger-ui.html](http://localhost:8080/syspauta/swagger-ui.html)

---

## :whale: Como rodar localmente com Docker-Compose (certifique-se que o docker e o docker-compose estão instalados)

1. **Rodando os Containeres:**
   ```sh
   docker-compose up --build
   ```
Dessa forma o docker ja sobe a aplicação junto com uma instancia do Mysql

3. **Acesse:**  
   [http://localhost:8080/syspauta/swagger-ui.html](http://localhost:8080/syspauta/swagger-ui.html)

---

## :wrench: Configuração

As configurações da aplicação podem ser ajustadas em:
```
src/main/resources/application.properties
```
Exemplo de propriedade customizada:
```
my.form.url=http://localhost:8080
```

---

## :link: Principais Endpoints

| Método | Endpoint                    | Descrição                |
|--------|-----------------------------|--------------------------|
| POST   | /api/v1/pauta/criar         | Cria uma nova pauta      |
| POST   | /api/v1/sessao/criar-sessao | Cria uma nova sessão     |
| POST   | /api/v1/voto/votar          | Cria uma novo voto       |

### Exemplos de uso

**Criar uma pauta**
```sh
curl -X POST http://localhost:8080/api/v1/pauta/criar \
  -H "Content-Type: application/json" \
  -d '{"nome":"Pauta Exemplo","descricao":"Descrição da pauta"}'
```

**Testar API**
```sh
curl http://localhost:8080/api/v1/pauta/teste
```

---

## :test_tube: Testes

Para rodar os testes automatizados:
```sh
mvn test
```

Os testes estão localizados em:
```
src/test/java/com/example/syspauta/syspauta/
```
