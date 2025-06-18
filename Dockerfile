# Use uma imagem base do JDK 24 oficial
FROM eclipse-temurin:24-jdk as builder

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o projeto inteiro para dentro do container
COPY . .

# Faz o build do jar usando Maven
RUN ./mvnw clean package -DskipTests

# Segunda etapa: imagem menor, só com o jar pronto
FROM eclipse-temurin:24-jre

WORKDIR /app

# Copia o jar do stage de build
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
