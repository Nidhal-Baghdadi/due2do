# Due2Do

**Due2Do** is a Spring Boot application designed to assist with task tracking and management based on files, emails, and other data sources. It integrates Google Drive for tracking file uploads and uses Apache Kafka for real-time event processing. This is version **v0** and currently a **work-in-progress**, with future updates planned for expanded third-party integrations and UI enhancements.

## Features

- **User Authentication**: Traditional login via JWT and Google OAuth2 for third-party access.
- **Google Drive Integration**: Tracks file upload events and updates.
- **Event Processing**: Uses Apache Kafka for handling events triggered by file uploads or other user actions.
- **Tech Stack**: Spring Boot, JWT, Google Drive API, Kafka, Docker, and H2 Database for testing.

## Technologies Used

- **Backend**: Spring Boot
- **Security**: OAuth2 (Google), JWT for login sessions
- **File Integration**: Google Drive API v3
- **Event Queue**: Apache Kafka
- **Database**: H2 for local testing
- **Deployment**: Docker (for future containerized deployment)

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/due2do.git

2. Navigate into the project directory:

   ```bash
   cd due2do

3. Run the application with Maven:

   ```bash
   ./mvnw spring-boot:run

Ensure Kafka is running locally on port 9092 to support event handling.

## Configuration

### Google Drive API

- Set up a Google Cloud Project with Drive API enabled and download the `google-credentials.json` file.
- Place `google-credentials.json` in `src/main/resources/` (remember to add it to `.gitignore` for security).
- Configure the `GoogleDriveService` for webhook events and Google OAuth2.

## OAuth2 and JWT Configuration

Update `application.properties` with your Google client credentials:

```properties
spring.security.oauth2.client.registration.google.client-id=<your-client-id>
spring.security.oauth2.client.registration.google.client-secret=<your-client-secret>

## Endpoints

### Authentication

- **POST** `/api/auth/signup`: Registers a new user.
- **POST** `/api/auth/login`: JWT-based login for accessing protected resources.

### Google Drive Integration

- **GET** `/api/oauth2/callback/google`: Handles Google OAuth2 callback.
- **POST** `/api/webhook/drive-upload`: Webhook endpoint for Google Drive file uploads.

### Event Processing

Apache Kafka listens for file events such as Google Drive uploads, allowing the system to handle notifications, task generation, and scheduling.

## Usage

- **Run the Application**: Start the application and access endpoints via tools like Postman.
- **Authenticate Users**: Test JWT-based authentication by signing up and logging in.
- **Connect Google Drive**: Use the OAuth2 flow to link a Google Drive account.
- **Test File Upload Events**: Trigger file uploads on Google Drive and verify event handling via Kafka.

## Future Plans

This is an initial version (v0), and Due2Do will continue to evolve with additional third-party integrations, enhanced task management, and UI/UX improvements.

