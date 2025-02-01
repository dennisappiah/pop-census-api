## Pop Census API

This is a paperless population census application for DBMS project 2025

## About the Application

This is a simple web application that exposes a REST API. This application uses Maven as the build tool and the current
LTS version of Java, 17. I hope to add more functionality to this application in the future but
for now this project uses the following dependencies:

- Spring Web
- Spring Data JDBC
- PostgreSQL Database


## Running the application

You can run this application from your favorite IDE or by running the following command:

```bash
./mvnw spring-boot:run
```

## Testing the application

This application uses Junit 5 and [Tescontainers](https://www.testcontainers.org/). To run the tests you will need Docker
desktop installed and running. You need Docker to execute the tests because this application uses Testcontainers to spin
up PostgreSQL database. This allows us to test as close to production as possible on our development machines as well as
a clean and reproducible testing environment each time.

## Building for Production

If you want to build an artifact that can be used in production you have 2 options. This application uses `JAR` as the
packaging type. This means that you can run the following command to create something that is ready to be used in production.

```bash
./mvnw clean package
```

If you would like to create a Docker Image which can be used on a variety of platforms you can run the following command:

```bash
./mvnw spring-boot:build-image
```

## Spring to Production


### Local Development

When working on this application locally you will need Docker Desktop installed. To start an instance of PostgreSQL run the Docker
Compose file located in the root of the project.

### Azure Spring Apps

Azure Spring Apps is a platform as a service (PaaS) for Spring developers. Manage the lifecycle of your Spring Boot applications with
comprehensive monitoring and diagnostics, configuration management, service discovery, CI/CD integration, and blue-green deployments.

[https://azure.microsoft.com/en-us/products/spring-apps](https://azure.microsoft.com/en-us/products/spring-apps)

#### GitHub Actions

You could create a new artifact each time and deploy it to Azure Spring Apps using the Azure CLI. This can be tedious though and if you want to deploy a new version of your application each time a commit is made or merged into the master branch you can use GitHub actions. The following is a workflow that I am currently using to do that.

```yaml
name: AzureSpringCloud
on: push
env:
  ASC_PACKAGE_PATH: ${{ github.workspace }}
  JAVA_VERSION: 17
  AZURE_SUBSCRIPTION: YOUR_SUBSCRIPTION_ID_HERE

jobs:
  deploy_to_production:
    runs-on: ubuntu-latest
    name: deploy to production with artifact
    steps:
      - name: Checkout Github Action
        uses: actions/checkout@v2
        
      - name: Set up JDK ${{ env.JAVA_VERSION }}
        uses: actions/setup-java@v1
        with:
          java-version: ${{ env.JAVA_VERSION }}

      - name: maven build, clean
        run: |
          mvn clean package -DskipTests

      - name: Login via Azure CLI
        uses: azure/login@v1
        with:
          creds: ${{ secrets.AZURE_CREDENTIALS }}

      - name: deploy to production with artifact
        uses: azure/spring-cloud-deploy@v1
        with:
          azure-subscription: ${{ env.AZURE_SUBSCRIPTION }}
          action: Deploy
          service-name: spring-blog
          app-name: spring-blog
          use-staging-deployment: false
          package: ${{ env.ASC_PACKAGE_PATH }}/**/*.jar
```



```properties
spring_profiles_active=prod
PROD_DB_HOST=HOST_HERE
PROD_DB_PORT=POST_HERE
PROD_DB_NAME=railway
PROD_DB_PASSWORD=PASSWORD_HERE
PROD_DB_USERNAME=postgres
```
