# Hospital Management System Backend

## Project Overview

This is a comprehensive hospital management system backend developed with Spring Boot. The application is designed to manage patient records, medical staff, diagnostics, and various hospital facilities in a unified system.

## Features

- **Nurse Management**: CRUD operations for nurses, including authentication and specialization tracking
- **Patient Management**: Store and retrieve patient information including personal details, medical history, and emergency contacts
- **Staff Management**: Track auxiliary staff with authentication
- **Medical Record Management**: Maintain detailed patient diagnoses, vital signs, and observations
- **Facility Management**: Room allocation and tracking
- **Diet Tracking**: Record and monitor patient diets, dietary requirements, and texture types
- **Mobilization Management**: Track patient mobility, including sedestation and walking assistance
- **Drainage System**: Monitor patient drain outputs and types
- **Hygiene Management**: Record hygiene types and procedures

## Technology Stack

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **Spring Data JPA**: Data persistence
- **Jakarta Persistence**: ORM mapping
- **RESTful API**: Service architecture

  ## Entity Relationship

The system is built around the following key entities:

- **Patient**: Core entity with personal information and medical history
- **Register**: Central record linking patients with various medical data
- **Nurse**: Nursing staff entity
- **Auxiliary**: Medical staff entity
- **Diagnosis**: Patient diagnoses with detailed descriptions
- **DetailDiagnosis**: Specific diagnosis details including dependency levels and equipment
- **VitalSign**: Patient vital statistics tracking
- **Diet**: Nutrition tracking with various types and textures
- **Room**: Facility management
- **Observation**: Additional patient notes
- **Mobilization**: Patient mobility tracking
- **Drain**: Drainage system monitoring

## API Endpoints

### Nurse Management
- `POST /nurse/login`: Nurse authentication
- `GET /nurse/`: Retrieve all nurses
- `GET /nurse/name/{name}`: Find nurse by name
- `GET /nurse/name/{id}`: Find nurse by ID
- `POST /nurse/`: Create a new nurse
- `PUT /nurse/{id}`: Update nurse details
- `DELETE /nurse/{id}`: Delete a nurse

### Auxiliary Management
- `POST /auxiliary/login`: Staff authentication
- `POST /auxiliary`: Create new auxiliary staff
- `GET /auxiliary/{id}`: Retrieve staff information

### Patient Management
- `POST /patient/list`: Batch add patients
- `POST /patient`: Add a single patient
- `GET /patient/{id}`: Retrieve patient data
- `PUT /patient/{id}`: Update patient information

### Register Management
- `POST /register`: Create a new medical register
- `GET /register/{id}`: Get complete register data by vitalsign ID
- `GET /register/vitalSign/{id}`: Get vital sign history
- `GET /register/diagnosis/{id}`: Get the latest diagnosis

### Room Management
- `POST /room`: Create a new room
- `POST /room/list`: Create multiple rooms
- `GET /room`: Get all rooms

## Database Schema

The system uses a relational database with relationships between:
- One-to-many: Diagnosis-DetailDiagnosis, Patient-Observation
- Many-to-many: Diet-DietType
- One-to-one: Register-VitalSign, Register-Diet, Patient-Room

## Setup and Installation

1. Clone the repository
   ```
   git clone https://github.com/nsaladie/MP13-Final-Proyect-BackEnd.git
   ```

2. Build with Maven
   ```
   mvn clean install
   ```

3. Configure your database in `application.properties`

4. Run the application
   ```
   mvn spring-boot:run
   ```

## Development

### Prerequisites
- JDK 17+
- Maven
- MySQL database

### Project Structure
```
src/main/java/com/example/Hospital/Hospital/
├── controller/    # REST controllers
├── entity/        # JPA entities
├── repository/    # Spring Data repositories
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Hospital management systems best practices
- Spring Boot community
