# SysPauta

SysPauta é uma aplicação web para gerenciamento e votação de pautas, desenvolvida em Spring Boot. O sistema permite criar pautas, abrir sessões de votação, registrar votos e consultar resultados, sendo ideal para assembleias, conselhos e organizações que precisam de um processo de votação digital simples, seguro e eficiente.

---

## :star: Funcionalidades

- Cadastro de pautas
- Abertura de sessões de votação
- Registro de votos
- Consulta de resultados
- Interface web para usuários
- API RESTful documentada

---

## :rocket: Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Docker
- Swagger/OpenAPI (documentação da API)

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
   git clone <url-do-repositorio>
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
   [http://localhost:8080](http://localhost:8080)

---

## :whale: Como rodar com Docker

1. **Build da imagem:**
   ```sh
   docker build -t syspauta-app .
   ```

2. **Execute o container:**
   ```sh
   docker run -p 8080:8080 syspauta-app
   ```

3. **Acesse:**  
   [http://localhost:8080](http://localhost:8080)

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
| GET    | /api/v1/pauta/teste         | Endpoint de teste        |

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

---

## :handshake: Como contribuir

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'feat: minha nova feature'`)
4. Faça push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request

---

## :page_facing_up: Licença

Este projeto está sob a licença MIT. 