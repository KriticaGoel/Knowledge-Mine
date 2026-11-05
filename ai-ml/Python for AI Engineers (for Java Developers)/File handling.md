| Mode                              | 	Meaning                                         |
|-----------------------------------|--------------------------------------------------|
| r                                 | 	Read (default) — error if file doesn't exist    |
| w                                 | 	Write — creates file; overwrites existing       |
| a                                 | 	Append — adds to end; creates if not exists     |
| x                                 | 	Create a new file  error if file already exists |
| rb                                | Read binary (images, PDFs, etc.)                 |
| wb                                | Write binary                                     |
| r+                                | Read & write (file must exist)                  |

### Opening & Closing Files
with open() because it automatically closes the file
```
with open('data.txt', 'r') as f:
content = f.read()
print(content)
```

### Read file

```python
with open('sample.txt', 'r') as f:
    # Read entire file as one string
    content = f.read()
    # Read into list of lines
    f.seek(0) # reset to start
    lines = f.readlines()
    # Read line by line (memory-efficient for large files)
    f.seek(0)
    for line in f:
        print(line.strip()) # strip() removes \n
```

### Writing & Appending

```python
with open('output.txt', 'w') as f:
f.write('Hello, File!\n')
f.writelines(['Line 1\n', 'Line 2\n'])

# Append (adds to end)
with open('output.txt', 'a') as f:
    f.write('New line added\n')
```

### Working with CSV & JSON
```python
import csv, json
# CSV read
with open('data.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        print(row['name'], row['score'])
# JSON read / write
data = {'name': 'Alice', 'scores': [90, 85, 92]}
with open('data.json', 'w') as f:
    json.dump(data, f, indent=4)
with open('data.json') as f:
    loaded = json.load(f)
    print(loaded['name']) # Alice
```