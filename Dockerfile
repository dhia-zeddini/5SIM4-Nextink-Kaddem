# Étape de construction
FROM openjdk:17 AS builder

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src
RUN chmod +x mvnw

# Construire le package de l'application
RUN ./mvnw clean package -DskipTests

# Étape finale
FROM openjdk:17
WORKDIR /app

# Copier le jar construit depuis l'étape de construction
COPY --from=builder /app/target/kaddem-0.0.2.jar app.jar

# Exposer le port sur lequel l'application va s'exécuter
EXPOSE 8089

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
