# Use a base image with JDK 21
FROM --platform=linux/amd64 amazoncorretto:21

# Set working directory
WORKDIR /app

# Copy the JAR file from the target directory (Ensure you build your JAR first)
COPY app/build/libs/app.jar app.jar

# Expose the port that your Java service listens on
EXPOSE 9810

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
