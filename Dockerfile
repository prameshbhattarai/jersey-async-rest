# Use an official OpenJDK runtime as a base image
FROM openjdk:17.0-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local system into the container at /app
COPY build/libs/jerseyAsyncApp.jar /app/jerseyAsyncApp.jar

# Expose the port
EXPOSE 8080/tcp

# Define the command to run the JAR file
CMD ["java", "-jar", "jerseyAsyncApp.jar"]
