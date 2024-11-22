# SQL Injection Prevention Example with SQLAlchemy

This project demonstrates how to securely handle SQL queries using SQLAlchemy and SQLite, with a focus on preventing SQL injection attacks. It includes a simple script to create a database, insert user data, and perform both secure and insecure queries.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Docker](#docker)

## Features

- Creates an in-memory SQLite database.
- Demonstrates secure parameterized queries using SQLAlchemy.
- Shows the risks of insecure SQL queries that are vulnerable to SQL injection.

## Getting Started

### Prerequisites

- Python 3.9 or higher
- Docker (optional, see [Docker](#docker) section for containerized setup)

### Initial setup

1. Clone this repository:
   ```bash
   git clone git@github.com:risyam/security-vulnerability-POCs.git  
2. Navigate to Python/SQLInjection dircetory:
    
       cd Python/SQLInjection
### Docker
To run this application in a Docker container, follow these steps:

1. Build the Docker image:   
    ``` 
    docker build -t sqli_sqlalchemy_example .
2. Run the Docker container:
    ```
    docker run sqli_sqlalchemy_example
