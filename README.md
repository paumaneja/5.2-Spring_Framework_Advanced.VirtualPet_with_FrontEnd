# VirtualPet Backend

## Introduction

VirtualPet is the final assignment of a bootcamp held at the IT Academy of Barcelona Activa. The goal of the project was to develop a full-stack application divided into two parts: a backend built with Spring Boot and a frontend created using an AI selected to meet the project's needs and characteristics. You can find the frontend repository [here]([https://github.com/your_user/VirtualPetFrontend]). The application combines technical knowledge and creativity to deliver an interactive experience.

## Description

VirtualPet is a web application that allows users to manage and play with virtual pets. The backend handles key functionalities such as JWT-based authentication, pet management, and specific features like weapon changes tailored to pets of "StarWars" and "LordRings" types. Users can perform a variety of actions, including:

- Registering and logging into their accounts.
- Creating, updating, and deleting virtual pets.
- Viewing and managing their pet's attributes.
- Changing weapons for specific pets based on predefined rules.

The backend ensures secure user management and provides robust APIs for seamless integration with the frontend.

## Key Features

- Authentication and authorization with JWT.
- User management.
- Creation, update, and deletion of virtual pets.
- Weapon changes for pets with specific rules based on their type.
- Global exception handling.

## Requirements

- Java 17 or higher
- Maven 3.8.0 or higher
- MySQL 8.0 or higher

## Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your_user/VirtualPet.git
   cd VirtualPet
   ```

2. **Configure the database:**

   - Create a MySQL database named `virtualpetdb`.
   - Update the `src/main/resources/application.properties` file with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/virtualpetdb
     spring.datasource.username=your_user
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Access the backend:**
   The application will be available at `http://localhost:8080`.

## Main Endpoints

### Authentication

- **POST /auth/register:** Register a new user.
- **POST /auth/login:** Log in and obtain a JWT token.

### Pet Management

- **GET /pets:** Retrieve all pets for the current user.
- **POST /pets:** Create a new pet.
- **PUT /pets/{id}:** Update pet information.
- **DELETE /pets/{id}:** Delete a pet.
- **PATCH /pets/{id}/weapon:** Change a pet's weapon (based on type).

## Project Structure

- `src/main/java`: Contains the main source code.
  - `controller`: Handles HTTP requests.
  - `service`: Implements business logic.
  - `repository`: Interacts with the database.
  - `model`: Contains the entities.
  - `config`: Application configurations (security, CORS, etc.).
  - `auth`: Authentication-related classes.
  - `exception`: Custom error handling.
- `src/main/resources`: Includes configuration files.

## Testing

Run unit tests:

```bash
mvn test
```

## Contributions

If you want to contribute to the project:

1. Fork the repository.
2. Create a branch for your feature or bug fix.
3. Submit a pull request with a detailed description.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more information.

