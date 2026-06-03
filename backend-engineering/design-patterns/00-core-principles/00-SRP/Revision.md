### Definition

👉 A class should have only ONE responsibility and ONE reason to change.

### Formula to remember:

1 Class = 1 Responsibility = 1 Reason to Change

### Problem (SRP Violation)

❌ ShoppingCart was doing multiple jobs:

* Add products
* Calculate discount
* Calculate total price
* Update inventory
* Save to database

So changes in:

* Discount logic
* Inventory logic
* Database logic

all force modifications in the same class.

Result: God Class / Maintenance Nightmare

### Solution (Apply SRP)

Split responsibilities into separate classes:

* Responsibility	Class
* Product Data	SrpProduct
* Cart Management	SrpShoppingCart
* Discount Calculation	SrpDiscountEngine
* Total Price Calculation	SrpCartPricingService
* Inventory Update	SrpInventoryAdjustment
* Database Operations	SrpDataManager

Q: How do you identify SRP violation?

Ask: "Does this class have more than one reason to change?"

If Yes → SRP violated

Before vs After

Before ❌
```
ShoppingCart
├─ addProduct()
├─ calculateDiscount()
├─ getTotalPrice()
├─ updateInventory()
└─ saveToDB()
```
After ✅
```
ShoppingCart       -> Cart operations
DiscountEngine     -> Discount logic
PricingService     -> Price calculation
InventoryService   -> Inventory update
DataManager        -> DB operations
```
### Benefits

✅ Maintainable

✅ Readable

✅ Testable

✅ Reusable

✅ Low Coupling

### Real World Example
Employee Class (Wrong)
```java
class Employee {
calculateSalary();
saveToDatabase();
generateReport();
}
```
3 responsibilities → SRP Violation

Correct
 make different classes
* Employee
* SalaryCalculator
* EmployeeRepository
* ReportGenerator

Each class has one job.

### Memory Trick

Think of a Restaurant

Chef → Cook food

Cashier → Handle payments

Waiter → Serve food

If Chef starts cooking + billing + serving customers, chaos begins.

SRP = One Person → One Job