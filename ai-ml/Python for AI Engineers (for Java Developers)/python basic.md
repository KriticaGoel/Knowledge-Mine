## Arithmetic operators
These perform mathematical operations.
Operator	Meaning	Example
+	Addition	10 + 5
-	Subtraction	10 - 5
*	Multiplication	10 * 5
     /	Division	10 / 5
     //	Floor division	11 // 2
     %	Modulus	11 % 2
     **	Exponentiation	2 ** 3


## Comparison operators

price = 1000
print(price > 500)
print(price < 500)
print(price == 1000)
print(price != 1000)
print(price >= 1000)
print(price <= 1000)

## Logical operators
Three important logical operators:
Operator	Meaning
and	Both conditions must be true
or	At least one condition must be true
not	Reverses the Boolean value

## Assignment Operator

total = 100

total += 50
total -= 20
total *= 2
total /= 2

## Condition statement

sales = 150000

if sales >= 200000:
print("Excellent performance")
elif sales >= 100000:
print("Good performance")
elif sales >= 50000:
print("Average performance")
else:
print("Poor performance")

## Ternary operator
stock = 10

status = "Available" if stock > 0 else "Out of stock"

print(status)

List
A list stores multiple values in an ordered, mutable collection.
```
products = ["Keyboard", "Mouse", "Monitor"]

products.append("Laptop")
products.remove("Mouse")

print(products)
```

```
Operation	Purpose
append()	Add an element
insert()	Add at a position
remove()	Remove by value
pop()	Remove by index
sort()	Sort the list
len()	Count elements
```

```
products = ["Keyboard", "Mouse", "Monitor"]

print(products[0])
print(products[-1])
```

Output
```
Keyboard
Monitor
```

Slicing 

```
print(products[0:2])
```

Output first two elements
```
['Keyboard', 'Mouse']
```

## Tuple
A tuple is ordered but immutable.

```
store_location = ("Delhi", "India")
```
When to use it?

When you want to represent a fixed collection of values, such as coordinates or fixed configuration data.

## Set
A set stores unique elements.

```
categories = {"Electronics", "Clothing", "Electronics"}

print(categories)
```

Output:
```
{'Electronics', 'Clothing'}
```
Only unique values remain.

Useful for:
- Removing duplicates.
- Finding unique categories.
- Finding common elements.
- Finding differences between collections.

```
store_a = {"Keyboard", "Mouse", "Monitor"}
store_b = {"Mouse", "Laptop"}

print(store_a.intersection(store_b))
```
Output:
```
{'Mouse'}
```
## Dictionary

A dictionary stores data in key-value pairs.

```
product = {
    "product_id": 101,
    "product_name": "Keyboard",
    "price": 1000,
    "quantity": 10
}
```
Access Value
```
print(product["product_name"])
print(product["price"])
```
Add
```
product["category"] = "Electronics"
print(product)
```
Output:
```
{'product_id': 101, 'product_name': 'Keyboard', 'price': 1000, 'quantity': 10, 'category': 'Electronics'}
```
```
for key, value in product.items():
    print(key, value)
```

Collection comparison
Feature	List	Tuple	Set	Dictionary
Ordered	Yes	Yes	No positional order	Yes
Mutable	Yes	No	Yes	Yes
Duplicates	Yes	Yes	No	Keys unique
Access	Index	Index	No index	Key

## Function

### Multiple return values
```
def calculate_sales(price, quantity=1):
    gross = price * quantity
    tax = gross * 0.18
    return gross, tax

gross, tax = calculate_sales(1000, 2)
```

## Lambda functions

A lambda is a small anonymous function.

```
calculate_tax = lambda amount: amount * 0.18

print(calculate_tax(1000))
```
180

## List comprehension

Traditional approach:

```
prices = [100, 500, 700, 1000, 1500]

expensive = []

for price in prices:
    if price > 500:
        expensive.append(price)
```

List comprehension approach:

```
prices = [100, 500, 700, 1000, 1500]

expensive = [price for price in prices if price > 500]

prices = [100, 200, 300]

increased_prices = [price * 1.10 for price in prices]
```
General structure:
```
[expression for item in collection if condition]
```

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


enumerate() — index and value together

products = ["Laptop", "Mouse", "Keyboard"]

for index, product in enumerate(products):
    print(index, peoduct)


zip() — combine collections

products = ["Laptop", "Mouse", "Keyboard"]
prices = [50000, 800, 1500]

for product, price in zip(products, prices):
    print(product, price)

any() and all()

stock = [10, 0, 5]

print(any(quantity == 0 for quantity in stock))
print(all(quantity > 0 for quantity in stock))

any() is true if at least one condition is true.
all() is true only if every condition is true.


sorted() vs .sort()