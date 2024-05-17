# Use a base image with OpenJDK installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/your-application.jar /app/your-application.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "your-application.jar"]
