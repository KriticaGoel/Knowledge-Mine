In Python, config.ini is a simple text file for configuration

It's just a plain text file with Sections and Key = Value pairs.


```
[DATABASE]
host = localhost
port = 5432
user = admin
password = secret123

[PATHS]
input_dir = data/input
output_dir = data/output
log_file = logs/app.log

[LOGGING]
level = INFO
```

Rules:

1. [SECTION] - Section name in square brackets. Must be unique.
2. key = value - Everything after = is a string. Even port = 5432 will be read as "5432", not int.
3. # or ; at start of line = comment.
4. No quotes needed. host = "localhost" will actually read as "localhost" with quotes, so don't use them.
5. Keys are case-insensitive by default. Host = host
6. Section names ARE case-sensitive
7. If you try to read a key that doesn't exist, it throws KeyError
```
# Safe way
port = config.getint('DATABASE', 'port', fallback=5432)
```
8. If you try to read a section that doesn't exist, it throws KeyError

### How does Python read it internally?
Python has a built-in module called configparser. It's like Java's java.util.Properties but section-aware.

main.py
```
import configparser

# 1. Create parser object - like new Properties()
config = configparser.ConfigParser()

# 2. Read file - like properties.load(new FileInputStream(...))
config.read('config.ini')

# 3. Get values - like properties.getProperty("host")
db_host = config['DATABASE']['host']
db_port = config.getint('DATABASE', 'port') # auto converts to int!
db_user = config.get('DATABASE', 'user')

print(db_host) # localhost
print(db_port) # 5432 -> int type
print(type(db_port))
```