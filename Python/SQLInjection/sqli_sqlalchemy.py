from sqlalchemy import create_engine, text
import sqlite3

# Create a SQLite database
engine = create_engine('sqlite:///:memory:')

# Create a connection
conn = engine.connect()

# Create a table
conn.execute(text('CREATE TABLE users (username TEXT, password TEXT)'))

# Insert users
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'alice', 'password': 'password123'})
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'bob', 'password': 'qwerty123'})
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'ram', 'password': 'test123'})
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'rahim', 'password': 'github123'})
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'singh', 'password': 'rainbow'})
conn.execute(text('INSERT INTO users VALUES (:username, :password)'),
             {'username': 'kalam', 'password': '123456789'})

# Commit changes
conn.commit()

# Parameterized query (secure)
def secure_query(username, password):
    print("Using Secure Query:")
    query = text('SELECT * FROM users WHERE username = :username AND password = :password')    
    result = conn.execute(query, {'username': username, 'password': password})   
    return result.fetchall()

# Parameterized query in a different way (secure)
def another_secure_query(username, password):
    print("\nUsing another secure Query:")  
    result = conn.execute(text(f"SELECT * FROM users WHERE username = :username and password = :password"),
    {"username": username, "password": password })   
    return result.fetchall()

# String concatenation query (insecure)
def insecure_query(username, password):
    print("\nUsing Insecure Query:")   
    result = conn.execute(text(f"SELECT * FROM users WHERE username = '{username}' and password = '{password}'"),
    {"username": username, "password": password })   
    return result.fetchall()


# Test with valid credentials- All the three queries will yield the same results as the input is safe
print("\nWith Valid Inputs:\n")
print(secure_query('alice', 'password123'))
print(another_secure_query('alice', 'password123'))
print(insecure_query('alice', 'password123'))

# Test with malicious input (SQL injection)
# See that the secure queries yield nothing as the inputs doesn't match any of teh database records.
# However, the insecure_query gives the full DB records as the sqlinjection payload works there

print("\nWith Malicious Inputs:\n")
malicious_username = "' or 1=1--"
try:
    # Will not retrun any results
    print(secure_query(malicious_username, 'password123'))
    # Will not retrun any results
    print(another_secure_query(malicious_username, 'password123'))
    # Will return all the DB records as sql injection works here
    print(insecure_query(malicious_username, 'password123'))        
except sqlite3.Error as e:
    print(f"Error: {e}")

# Close the connection
conn.close()