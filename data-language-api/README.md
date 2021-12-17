# Data Language API

This module holds the endpoints for the Data Language API.

## Development

### Configuration
Copy the file `api/config/application.properties.example` and paste it as `api/config/application.properties` and change its properties values to the ones for the environment that the application should run. The tables will be automatically created and however **the database schema should be created manually**.

### Run

Execute `./gradlew :api:bootRun` and the application will start, and the endpoints will be available for consumption.
