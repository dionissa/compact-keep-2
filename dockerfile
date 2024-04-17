FROM ubuntu:latest AS build

# Instalar as dependências necessárias
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml separadamente para evitar reexecutar o download de dependências se o código-fonte não mudar
COPY pom.xml .

# Baixar as dependências do Maven
RUN mvn dependency:go-offline

# Copiar todo o código fonte
COPY . .

# Executar o comando para preparar o frontend Vaadin
RUN mvn vaadin:prepare-frontend

# Compilar o projeto
RUN mvn clean package

# Stage de produção
FROM openjdk:17-jdk-slim

# Configurar a porta de exposição
EXPOSE 8080

# Copiar o arquivo JAR construído a partir do estágio de compilação
COPY --from=build /app/target/compactkeep2.jar /app.jar

# Definir o comando de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app.jar"]
