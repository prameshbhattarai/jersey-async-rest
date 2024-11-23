# Use an official Amazon Corretto 21 runtime as the base image
FROM amazoncorretto:21-al2023

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/jerseyAsyncApp.jar /app/jerseyAsyncApp.jar

# Expose the application port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "jerseyAsyncApp.jar"]
