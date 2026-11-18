# Personal Expense Tracker

A console-based expense tracking application built with **Java, JDBC, PostgreSQL, and Maven**. It allows users to manage personal expenses, organize them into categories, and generate spending summaries.

The project follows a layered architecture with separate model, repository, service, and presentation layers. It also uses Java Generics to create reusable repository abstractions and JUnit 5 for automated testing.

## Features

### Expense Management
- Add new expenses.
- View all expenses.
- Find an expense by ID.
- Update existing expenses.
- Delete expenses.
- Filter expenses by category.

### Category Management
- Create expense categories.
- View all categories.
- Find categories by ID.
- Update category information.
- Delete categories.
- Prevent duplicate category names.
- Prevent deletion of categories that contain expenses.

### Expense Statistics
- Calculate total expenses.
- Calculate expenses by category.
- Calculate total expenses over the last 7 days.
- Calculate total expenses over the last 30 days.

## Technologies

| Technology | Purpose |
|---|---|
| Java 25 | Core application |
| PostgreSQL | Relational database |
| JDBC | Database connectivity |
| HikariCP | Database connection pooling |
| Maven | Dependency and build management |
| JUnit 5 | Automated testing |
| dotenv-java | Environment configuration |

## Project Architecture

The application follows a layered architecture:

```text
com.krmall50
│
├── Main.java
│
├── config/
│   └── DatabaseConfig.java
│
├── model/
│   ├── Expense.java
│   └── Category.java
│
├── repo/
│   ├── Repository.java
│   ├── ExpenseRepository.java
│   └── CategoryRepository.java
│
├── service/
│   ├── ExpenseService.java
│   └── CategoryService.java
│
└── render/
    ├── UserInterface.java
    └── UIFunctions.java
```

### Architecture Layers

- **Model:** Represents application entities (`Expense` and `Category`).
- **Repository:** Handles database operations using JDBC and SQL.
- **Service:** Contains business logic, validation, and expense calculations.
- **Render:** Handles console interaction and user input.
- **Config:** Configures database connections using HikariCP.

### Generic Repository

The project uses a generic repository interface to avoid duplicating common CRUD operations.

```java
interface Repository<T, ID> {

    T findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    int add(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(ID id) throws SQLException;
}
```

It is implemented by both `ExpenseRepository` and `CategoryRepository`.

## Database Design

The application uses PostgreSQL with two tables.

### Categories

| Column | Type | Description |
|---|---|---|
| id | SERIAL | Primary key |
| name | VARCHAR(100) | Unique category name |
| description | TEXT | Category description |

### Expenses

| Column | Type | Description |
|---|---|---|
| id | SERIAL | Primary key |
| description | VARCHAR(255) | Expense description |
| amount | DECIMAL(12,2) | Expense amount |
| date | DATE | Expense date |
| categoryId | INTEGER | Foreign key referencing categories |

**Relationship:** One category can contain multiple expenses. Each expense belongs to one category.

The database schema is available in `src/main/resources/schema.sql`.

## Getting Started

### Prerequisites

Make sure you have installed:

- JDK 25
- Apache Maven
- PostgreSQL
- Git

### 1. Clone the repository

```bash
git clone https://github.com/krmall50/PersonalExpenseTracker.git

cd PersonalExpenseTracker
```

### 2. Create PostgreSQL databases

Create two separate databases:

- `personalExpenseTracker` — application database.
- `personalExpenseTrackerTest` — testing database.

Execute the SQL statements from `src/main/resources/schema.sql` in **both databases** to create the required tables.

Make sure the database names match the names used in your configuration.

### 3. Configure environment variables

Create a `.env` file in the project root:

```env
DB_URL=jdbc:postgresql://localhost:5432/personalExpenseTracker
DB_USERNAME=postgres
DB_PASSWORD=your_password

DB_URL_TEST=jdbc:postgresql://localhost:5432/personalExpenseTrackerTest
```

Replace the username and password with your PostgreSQL credentials.

The `.env` file contains sensitive information and should not be committed to Git.

### 4. Build the project

```bash
mvn clean package
```

### 5. Run the application

Open the project in your IDE and run:

```text
com.krmall50.Main
```

The console menu will appear.

## Running Tests

The project uses JUnit 5 for automated testing.

Tests cover:

- Expense repository operations.
- Category repository operations.
- Expense service operations.
- Category service operations.
- CRUD functionality.
- Validation and error scenarios.
- Expense calculations.

A separate PostgreSQL database is used for testing to avoid modifying application data.

Run all tests using:

```bash
mvn test
```

Run a clean test build:

```bash
mvn clean test
```

## Design Principles

The project demonstrates practical application of:

- Object-Oriented Programming.
- Encapsulation and separation of concerns.
- Java Generics.
- Repository Pattern.
- Service Layer Pattern.
- Dependency Injection through constructors.
- JDBC and PreparedStatements.
- Connection pooling.
- Exception handling.
- Unit and integration testing.


## Author

Developed as a personal Java backend learning project to practice JDBC, PostgreSQL, Maven, generics, software architecture, and automated testing.