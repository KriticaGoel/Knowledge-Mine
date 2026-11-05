|Mode|	Meaning|
|--|--|
|r|	Read|
|w|	Write and overwrite|
|a|	Append|
|x|	Create a new file|

### Open file
with open() because it automatically closes the file
```
with open("file.txt", "r") as f:
    content = f.read()
```