# 1. Use an image that has Java installed
FROM eclipse-temurin:21-jre-jammy

# 2. Create a folder inside Docker called /app
WORKDIR /app

# 3. Copy your compiled JAR file from your target folder into Docker
# (We assume you ran 'mvn package' already)
COPY target/*.jar app.jar

# 4. Copy your CSV file so your DataLoader.java can find it
# Docker needs this to exist at the same path your code expects
COPY src/main/resources/jlpt_vocab.csv src/main/resources/jlpt_vocab.csv

# 5. Tell Docker to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]