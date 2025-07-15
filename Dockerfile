# Используем официальный образ OpenJDK для Java 17
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный jar-файл в контейнер
COPY target/*.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"] 