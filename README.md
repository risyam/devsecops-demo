
# Expense Manager Application
<img alt="gitleaks badge" src="https://img.shields.io/badge/protected%20by-gitleaks-blue">
<img alt="semgrep badge" src="https://img.shields.io/badge/protected%20by-semgrep-green">
<img alt="zap badge" src="https://img.shields.io/badge/protected%20by-OWASP ZAP-blue">

A simple Expense Manager web application built with **Spring Boot** and **Thymeleaf**, using an **H2 in-memory database** for data storage. This application allows users to manage their expenses by adding categories, recording expenses, and viewing expense summaries, including a pie chart visualization for better insights.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [Database Configuration](#database-configuration)
- [Usage](#usage)

## Features
- **Add Expense Categories**: Define custom categories for your expenses.
- **Add Expenses**: Record expense details including description, amount, date, and category.
- **View Expenses**: List all recorded expenses with associated categories.
- **Pie Chart Visualization**: View a pie chart summary of expenses, showing the proportion of each category.
- **CRUD Operations**: Manage both expense categories and individual expenses.
- **Responsive UI**: The UI is built with Thymeleaf templates, offering a simple and clean user interface.

## Technologies Used
- **Java 17**: The core programming language.
- **Spring Boot**: For building the backend and managing dependencies.
- **Thymeleaf**: Template engine for dynamic HTML content rendering.
- **H2 Database**: An in-memory database used for easy setup and testing.
- **Chart.js**: JavaScript library for data visualization (pie chart).

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/expense-manager.git
   cd expense-manager
2. **Build the docker file**:
   ```bash
   docker build -t devsecopsdemo .
3. **Run the docker file**:
   ```bash
   docker run -p8085:8085 devsecopsdemo
4. **Access the Application**: Open your browser and go to http://localhost:8085