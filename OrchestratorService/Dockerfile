# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /workspace/app

# Copy the source code into the container
COPY . .

# Build the application
RUN mvn install -DskipTests

# Extract dependencies
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Final stage
FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy the dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app 

# Set the entry point
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "rs.ac.uns.acs.nais.orchestratorservice.OrchestratorServiceApplication"]
