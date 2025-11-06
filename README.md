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

## 📋 Table of Contents

- [What is This Project?](#what-is-this-project)
- [Why This Was Created](#why-this-was-created)
- [Security Tools Integration](#security-tools-integration)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Local Development Setup](#local-development-setup)
- [Testing the Application](#testing-the-application)
- [Viewing Security Findings in GitHub PR](#viewing-security-findings-in-github-pr)
- [Architecture](#architecture)

---

## 🎯 What is This Project?

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

## 🔒 Security Tools Integration

### **1. Semgrep (SAST)**

**What it does:** Static Application Security Testing - scans code for vulnerabilities

**Integration:**
- Custom rules in `semgrep-custom-rules.yaml`
- Runs on every PR and push to main
- Detects: H2 console exposure, insecure cookies, CSRF issues, PII logging, authorization bypass

**Configuration:**
```yaml
# .github/workflows/semgrep.yml
- run: semgrep --config semgrep-custom-rules.yaml
```

**Custom Rules:**
1. ✅ H2 Console Exposure Detection
2. ✅ Insecure Session Cookie Configuration
3. ✅ Missing Authorization Checks (IDOR)
4. ✅ Missing CSRF Tokens in Forms
5. ✅ PII Logging Without Masking

### **2. Gitleaks (Secret Detection)**

**What it does:** Scans for hardcoded secrets, API keys, passwords

**Integration:**
- Runs on every PR
- Checks all commits for leaked credentials
- Prevents accidental secret exposure

**Configuration:**
```yaml
# .github/workflows/gitleaks.yml (to be added)
- uses: gitleaks/gitleaks-action@v2
```

### **3. OWASP ZAP (DAST) - Planned**

**What it does:** Dynamic Application Security Testing - tests running application

**Integration (Planned):**
- Automated penetration testing
- API endpoint security testing
- Authentication bypass testing

---

## Security Features

- ✅ **UUID Primary Keys** - Prevents enumeration attacks
- ✅ **CSRF Protection** - All POST requests protected
- ✅ **Input Validation** - Bean validation on all inputs
- ✅ **Secure Sessions** - HTTP-only, secure cookies (production)
- ✅ **Log Masking** - PII automatically masked in logs
- ✅ **No SQL Injection** - JPA/Hibernate prepared statements
- ✅ **Authorization Checks** - Service-layer ownership validation

---

## 🛠️ Technologies Used

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

## 💻 Local Development Setup

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

## 🔍 Viewing Security Findings in GitHub PR

### Automated Security Checks

When you create a Pull Request, automated security scans run:

### **1. Semgrep SAST Scan**

**Location:** PR Checks → "semgrep/ci" workflow

**What you'll see:**
```
✅ semgrep/ci - No vulnerabilities found
❌ semgrep/ci - 3 vulnerabilities found
```

**View Details:**
1. Click on **"Details"** next to semgrep check
2. See list of findings with:
   - Severity (ERROR, WARNING)
   - File location and line number
   - Vulnerability description
   - Fix recommendations

**Example Finding:**
```
❌ ERROR: h2-console-enabled-production
   File: application.properties:9
   
   H2 console is enabled. This should be disabled in production.
   
   Fix: spring.h2.console.enabled=false
```

### **2. View in GitHub Security Tab**

**Steps:**
1. Go to repository → **Security** tab
2. Click **Code scanning alerts**
3. Filter by:
   - Severity (Critical, High, Medium, Low)
   - Tool (Semgrep)
   - Status (Open, Fixed)

### **3. PR Comments**

Semgrep automatically comments on PR with findings:

```markdown
### 🔒 Semgrep Security Findings

**❌ 2 errors found**

#### insecure-session-cookie (line 40)
Session cookies are not secure. Set to 'true' in production.

#### h2-console-enabled-production (line 9)
H2 console exposed. Disable in production.
```

### **4. Block Merge on Failure**

Configure branch protection:

**Settings → Branches → Branch protection rules**
- ✅ Require status checks to pass
- ✅ Select: `semgrep/ci`
- ✅ Require branches to be up to date

Now PRs with security issues **cannot be merged** until fixed!

### **5. Fix Workflow**

```bash
# 1. Developer creates PR
git checkout -b fix-security-issue
git push origin fix-security-issue

# 2. Semgrep finds issue
# GitHub shows: ❌ semgrep/ci failed

# 3. Developer fixes issue
# Edit file, commit, push

# 4. Semgrep re-runs automatically
# GitHub shows: ✅ semgrep/ci passed

# 5. PR can now be merged
```

---

## 📊 Architecture

### Application Architecture

```
┌─────────────────────────────────────────────────────────┐
│                     Browser                             │
│              (Thymeleaf + Chart.js)                     │
└────────────────────┬────────────────────────────────────┘
                     │ HTTPS + OAuth2
                     ▼
┌─────────────────────────────────────────────────────────┐
│              Spring Security                            │
│  ✓ OAuth2 Login (Google)                              │
│  ✓ CSRF Protection                                     │
│  ✓ Session Management                                  │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│            Controllers Layer                            │
│  • ExpenseUIController (Web)                           │
│  • ExpenseController (REST API)                        │
│  • ExpenseCategoryController (REST API)                │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              Services Layer                             │
│  • ExpenseService                                      │
│  • ExpenseCategoryService                              │
│  • UserService                                         │
│  ✓ Authorization checks                                │
│  ✓ Business logic                                      │
│  ✓ Logging with PII masking                           │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│           Repositories Layer                            │
│  • ExpenseRepository (JPA)                             │
│  • ExpenseCategoryRepository (JPA)                     │
│  • UserRepository (JPA)                                │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              H2 Database                                │
│  File: ~/expensemanager.mv.db                          │
└─────────────────────────────────────────────────────────┘
```

### CI/CD Security Pipeline

```
┌─────────────┐
│  Developer  │
│  git push   │
└──────┬──────┘
       │
       ▼
┌────────────────────────────────────────┐
│      GitHub Actions CI/CD              │
│                                        │
│  1. Gitleaks Secret Scan               │
│     ↓                                  │
│  2. Semgrep SAST                       │
│     • Custom rules                     │
│     • 5 security checks                │
│     ↓                                  │
│  3. Build & Test                       │
│     • Maven compile                    │
│     • Unit tests                       │
│     ↓                                  │
│  4. OWASP ZAP DAST (planned)          │
│     ↓                                  │
│  5. Dependency Check (planned)         │
│                                        │
│  ✅ All passed → Merge allowed         │
│  ❌ Failed → Block merge               │
└────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
devsecops-demo/
├── .github/
│   └── workflows/
│       └── semgrep.yml              # Semgrep CI/CD workflow
├── expense-manager-lite/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/expense/expensemanager/
│   │   │   │       ├── controller/  # REST & UI controllers
│   │   │   │       ├── service/     # Business logic
│   │   │   │       ├── repository/  # Data access
│   │   │   │       ├── model/       # Entities (UUID IDs)
│   │   │   │       ├── security/    # OAuth2 & security config
│   │   │   │       └── exception/   # Error handling
│   │   │   └── resources/
│   │   │       ├── templates/       # Thymeleaf HTML
│   │   │       ├── application.properties
│   │   │       ├── logback-spring.xml  # Log masking config
│   │   │       └── import.sql       # Sample data (UUIDs)
│   │   └── test/                    # Unit tests
│   ├── Dockerfile                   # Container config
│   └── pom.xml                      # Maven dependencies
├── semgrep-custom-rules.yaml        # Custom SAST rules
└── README.md                        # This file
```

---

## 🎯 Custom Semgrep Rules

### Rule 1: H2 Console Exposure
Detects H2 console enabled in production

### Rule 2: Insecure Session Cookies
Finds cookies without secure flag

### Rule 3: Missing Authorization Checks
Detects IDOR vulnerabilities

### Rule 4: Missing CSRF Tokens
Finds forms without CSRF protection

### Rule 5: PII Logging
Detects logging of sensitive user data

**Run Rules:**
```bash
semgrep --config semgrep-custom-rules.yaml expense-manager-lite/
```

---

## 📚 Additional Resources

- [Semgrep Rules Documentation](https://semgrep.dev/docs/)
- [OWASP Top 10 2021](https://owasp.org/Top10/)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [DevSecOps Best Practices](https://www.devsecops.org/)

---

## 🤝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Run security scans locally
4. Submit PR with security check passing

---

## 📝 License

This project is for educational and demonstration purposes.

---

## 🎓 Learning Outcomes

After exploring this project, you'll understand:

✅ How to integrate SAST into CI/CD
✅ How to write custom Semgrep rules
✅ How to implement secure coding practices
✅ How to prevent common vulnerabilities (OWASP Top 10)
✅ How to implement DevSecOps workflows
✅ How to use Spring Security effectively

---

**Built with ❤️ for DevSecOps Learning**
