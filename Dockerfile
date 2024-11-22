# Use an official OpenJDK runtime as a base image
FROM amazoncorretto:21-al2023

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local system into the container at /app
COPY build/libs/jerseyAsyncApp.jar /app/jerseyAsyncApp.jar

# Expose the port
EXPOSE 8080

# Define the command to run the JAR file
CMD ["java", "-jar", "jerseyAsyncApp.jar"]
