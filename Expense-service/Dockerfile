# Use a base image with JDK 21
FROM --platform=linux/amd64 amazoncorretto:21

# Set working directory
WORKDIR /Expense-service

# Copy the JAR file from the target directory (Ensure you build your JAR first)
COPY build/libs/Expense-service-0.0.1-SNAPSHOT.jar  app.jar

# Expose the port that your Java service listens on
EXPOSE 9820

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
