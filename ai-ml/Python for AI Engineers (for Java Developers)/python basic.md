the difference between modifying an object and reassigning a variable.
your code
def replace_inventory(products):
    products = ["Chair", "Table"]

inventory = ["Laptop", "Mouse"]

replace_inventory(inventory)

print(inventory)
  output

['Laptop', 'Mouse']


Step 1: Create the inventory list

inventory = ["Laptop", "Mouse"]
Python creates a list object in memory.
The variable inventory refers to that object.

Step 2: Call the function

replace_inventory(inventory)
Python passes a reference to the same list object into the function.
Now both names refer to the same object:

Step 3: The critical line

products = ["Chair", "Table"]
What happens here?
Python creates a completely new list object (B), then makes the local variable products refer to it.
It does NOT modify the original list object (A).

Step 4: Function completes
When the function finishes, its local variable products goes out of scope.
The original inventory variable still refers to object A.
That's why the output is:

['Laptop', 'Mouse']

Example A — Reassignment (original does not change)

def replace_inventory(products):
    products = ["Chair", "Table"]

inventory = ["Laptop", "Mouse"]
replace_inventory(inventory)

print(inventory)

Example B — Mutation (original changes)

def replace_inventory(products):
    products.clear()
    products.extend(["Chair", "Table"])

inventory = ["Laptop", "Mouse"]
replace_inventory(inventory)

print(inventory)

output
['Chair', 'Table']
Because clear() and extend() modify the same list object rather than making products point to a different object.


try
a = [10, 20]
b = a

a = a + [30]

print(a)
print(b)

conpare

a = [10, 20]
b = a

a.append(30)

print(a)
print(b)