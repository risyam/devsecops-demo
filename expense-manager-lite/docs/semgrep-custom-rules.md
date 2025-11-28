## Custom Semgrep Rules

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