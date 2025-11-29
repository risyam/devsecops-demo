# 🛡️ DevSecOps Demo - Expense Manager Application

<p align="left">
  <img alt="gitleaks badge" src="https://img.shields.io/badge/protected%20by-gitleaks-blue">
  <img alt="semgrep badge" src="https://img.shields.io/badge/protected%20by-semgrep-green">
  <img alt="zap badge" src="https://img.shields.io/badge/protected%20by-OWASP ZAP-blue">
  <img alt="java badge" src="https://img.shields.io/badge/Java-21-orange">
  <img alt="spring boot badge" src="https://img.shields.io/badge/Spring%20Boot-3.3.3-green">
</p>

A **DevSecOps demonstration project** showcasing secure CI/CD practices with automated security scanning using **Semgrep SAST**, **OWASP ZAP DAST**, and **Gitleaks** secret detection. Built with Spring Boot, this expense tracking application demonstrates security-first development practices.

---

## TL;DR

This project is a security-focused DevSecOps demonstration showcasing how to secure a modern Spring Boot application using:

- Semgrep SAST with custom rules
- Gitleaks secret scanning
- Secure CI/CD workflows (GitHub Actions)
- Spring Security + Google OAuth2
- Secure coding practices (CSRF, UUID IDs, log masking)
- OWASP ZAP DAST

## Table of Contents

- [What is This Project?](#what-is-this-project)
- [Security Tools Integration](#security-tools-integration)
- [Security Features](#security-features)
- [Technologies Used](#technologies-used)
- [Local Development Setup](#local-development-setup)
- [Viewing Security Findings in GitHub PR](#viewing-security-findings-in-github-pr)
- [Architecture](#architecture)

---

## What is This Project?

This is an **Expense Manager** web application that serves as a **DevSecOps demonstration** showcasing:

- ✅ **Secure coding practices** with Spring Boot
- ✅ **Automated SAST** scanning with custom Semgrep rules
- ✅ **Secret detection** with Gitleaks
- ✅ **DAST testing** with OWASP ZAP (planned)
- ✅ **CI/CD security integration** in GitHub Actions
- ✅ **Security-first development** workflow

### Core Application Features

- ✅ **Google OAuth2 Authentication** - Secure login, no passwords to manage
- ✅ **Expense Management** - Add, edit, delete expenses
- ✅ **Category Management** - Organize expenses by category
- ✅ **Dashboard Analytics** - Pie chart visualization of spending
- ✅ **User Isolation** - Each user's data is completely separate
- ✅ **Responsive UI** - Modern, clean interface with Thymeleaf

---

This project demonstrates how to:

1. **Integrate Security into CI/CD Pipeline**
   - Automated security scans on every PR
   - Block merges when critical vulnerabilities found
   - Security feedback in developer workflow

2. **Custom SAST Rules**
   - Tailored Semgrep rules for Spring Boot applications
   - Detect framework-specific vulnerabilities
   - Reduce false positives

3. **Shift-Left Security**
   - Catch vulnerabilities before code review
   - Automated security testing
   - Fast feedback loop for developers

4. **Real-World Security Practices**
   - UUID v4 for secure IDs
   - CSRF protection on all forms
   - Sensitive data masking in logs
   - OAuth2 authentication
   - Input validation

---

## Quickstart

1. Clone:
   ```bash
   git clone https://github.com/your-username/devsecops-demo.git
   cd devsecops-demo/expense-manager-lite
   ```

2. Build docker image:

    ```bash
   docker build -t expense-manager .
   ```

3. Run (use your Google OAuth creds):

   ```bash
      docker run -p 8085:8085 \
      -e GOOGLE_CLIENT_ID="..." \
      -e GOOGLE_CLIENT_SECRET="..." \
      expense-manager
   ```

4. Open http://localhost:8085

## Security Tools Integration

### **1. Semgrep (SAST)**

**Purpose**: Static analysis for Spring Boot security issues <br>
**Runs on**: Every PR + push to main

**Custom Rules:**
1. ✅ H2 Console Exposure Detection
2. ✅ Insecure Session Cookie Configuration
3. ✅ Missing Authorization Checks (IDOR)
4. ✅ Missing CSRF Tokens in Forms
5. ✅ PII Logging Without Masking

### **2. Gitleaks (Secret Detection)**

**Purpose**: Detects leaked secrets in commits  
**Runs** on: Every PR

**Integration:**
- Runs on every PR
- Checks all commits for leaked credentials
- Prevents accidental secret exposure

### **3. OWASP ZAP (DAST) - Planned**

**Purpose:** Automated dynamic security testing against the running app

**Roadmap:**
- Baseline scans
- Authenticated scans
- API scanning
- Active scan profile for high-risk endpoints

---

## Technologies Used

### Backend
- **Java 21** - Modern Java features
- **Spring Boot 3.3.3** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database abstraction
- **H2 Database** - File-based persistence

### Frontend
- **Thymeleaf** - Server-side templating
- **Chart.js** - Data visualization
- **HTML/CSS/JavaScript** - Modern web standards

### Security & DevOps
- **Semgrep** - Custom SAST rules
- **Gitleaks** - Secret detection
- **GitHub Actions** - CI/CD pipeline
- **Logback** - Structured logging with PII masking

### Build Tools
- **Maven 3.6+** - Dependency management
- **Docker** - Containerization

---

## Local Development Setup

### Prerequisites

- ✅ Java 21 or higher
- ✅ Maven 3.6+ (or use `./mvnw`)
- ✅ Google OAuth2 credentials
- ✅ Git

### Step 1: Clone Repository

```bash
git clone https://github.com/your-username/devsecops-demo.git
cd devsecops-demo
```

### Step 2: Configure Google OAuth2

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing
3. Enable **Google+ API**
4. Go to **Credentials** → **Create Credentials** → **OAuth 2.0 Client ID**
5. Configure OAuth consent screen
6. Add authorized redirect URI:
   ```
   http://localhost:8085/login/oauth2/code/google
   ```
7. Copy **Client ID** and **Client Secret**

### Step 3: Set Environment Variables

```bash
export GOOGLE_CLIENT_ID="your-client-id.apps.googleusercontent.com"
export GOOGLE_CLIENT_SECRET="your-client-secret"
```

### Step 4: Run Application

```bash
cd expense-manager-lite
# Start Colima first (if Docker Desktop is not an option)
colima start --cpu 4 --memory 4 --disk 60 --network-address

# Build Docker image
docker build -t expense-manager .

# Run container
docker run -p 8085:8085 \
  -e GOOGLE_CLIENT_ID="your-client-id" \
  -e GOOGLE_CLIENT_SECRET="your-secret" \
  expense-manager

```

### Step 5: Access Application

1. Open browser: http://localhost:8085
2. Click **"Sign in with Google"**
3. Authorize the application
4. Start managing expenses!

## Architecture

Detailed diagrams and explanations live in [`expense-manager-lite/docs/architecture.md`](expense-manager-lite/docs/architecture.md). This doc covers the browser → Spring Boot → H2 data flow, the local Docker runtime, and how GitHub Actions + security scanners fit into the system.

## Semgrep Custom Rules

The complete catalog of custom checks (IDOR, insecure cookies, CSRF, PII logging, etc.) is maintained in [`expense-manager-lite/docs/semgrep-rules/semgrep-custom-rules.md`](expense-manager-lite/docs/semgrep-rules/semgrep-custom-rules.md), including rationale, Semgrep patterns, and remediation guidance.


## Additional Resources

- [Semgrep Rules Documentation](https://semgrep.dev/docs/)
- [OWASP Top 10 2021](https://owasp.org/Top10/)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [DevSecOps Best Practices](https://www.devsecops.org/)

---

## Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Run security scans locally
4. Submit PR with security check passing

---

## License

This project is for educational and demonstration purposes.

---

**Built with ❤️ for DevSecOps Learning**
