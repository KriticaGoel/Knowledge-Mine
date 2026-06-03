## Prototype Design Pattern
The Prototype Design Pattern is a creational pattern where instead of creating objects using new, we create new objects by copying (cloning) an existing object.

```
          Client
             |
             v
      Prototype Interface
             |
       clone()
             |
      Concrete Prototype
```


#### Benefits:
1. Avoids the overhead of creating objects from scratch, especially when the creation process is expensive.
2. Provides a way to create objects without specifying their exact class.
3. Can be used to create complex objects with many configurations by cloning a prototype and then modifying the cloned object.
4. Supports dynamic object creation at runtime.


#### When to use
✅ Object creation is expensive

    Huge object graphs
    
    DB-loaded templates
    
    Config-heavy objects

✅ Many objects differ only slightly

✅ Creation logic is complex

✅ Dynamic object creation at runtime

Examples:

    Form templates
    Notification templates
    Game characters
    Document generators
    Report templates

#### When Not To Use:

❌ Objects are simple

    User user=new User();
    user.setName("John");

No need to clone.

❌ Deep copy becomes nightmare

Example:

    Order
        ->Items
            ->Products
                ->Suppliers

Clone complexity explodes.

Shallow copy bugs appear:

    copy.items.add(...)

Original object changes too.

❌ Object has external resources

Bad:

    class FileConnection{
    Socket socket;
    }

Cloning sockets, DB connections, streams is dangerous.

❌ Immutable objects

    String
    LocalDate
    Money

Copying gives little value.



### Steps to create a Prototype:
1. Define a Prototype interface with a clone method.
```java 
public interface Prototype {
    Prototype clone();
}
```
2. Create Concrete Prototype classes that implement the Prototype interface and override the clone method to return a copy of themselves.
```java 
public class Student implements Prototype {

    int id;
    String name;
    String address;
    int age;

    public Student(int id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    //Deep Copy
    public Student(Student s){
        this.id=s.id;
        this.name=s.name;
        this.address=s.address;
        this.age=s.age;
    }


    @Override
    public Student clone() {
        return new Student(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
} 

``` 

```
 @Override
    public Student clone() {
        return new Student(this);
    }
```

3. Clients can then use the clone method to create new objects based on existing prototypes.
```java
public class Client {

    static void main(String[] args) {
        Student s1= new Student(1,"Kritica","India",18);
        Student s2=s1.clone();
        s2.name="ABC";
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
} 
```


The Registry Pattern works with Prototype by maintaining a collection of pre-created prototypes. Clients ask the registry for an object and get a cloned copy.

Think
```
Create expensive object once
        ↓
Store in Registry
        ↓
Clone whenever needed

```

Without the registry, client need to know all the details of creating and cloning of objects.
```java
public class Client {

    static void main(String[] args) {
        Student s1= new Student(1,"Kritica","India",18,"Student");
        Student s2=s1.clone();
        s2.name="ABC";
        System.out.println(s1.toString());
        System.out.println(s2.toString());

//        Engineering Student
        Student eng = s1.clone();
        eng.name="Engineering Student";
        eng.university = "Engineering";
        System.out.println(eng.toString());
//        Medical Student
        Student medical = s1.clone();
        medical.name="Medical Student";
        medical.university = "Medical";
        System.out.println(medical.toString());
//        MBA Student
        Student mba = s1.clone();
        mba.name="MBA Student";
        mba.university = "MBA";
        System.out.println(mba.toString());
//        School Student
    }
}

```
```
Client
|
+--> clone()
|
+--> clone()
|
+--> clone()
```

Issues:

❌ Client knows every type

❌ Duplicate setup code

❌ Tight coupling

❌ Hard to add new templates

The client becomes responsible for object creation.

With the help of prototype+registry every class has separate responsibility

**Prototype object** → knows how to clone itself

**Registry** → stores and returns clones

**Bootstrap/Loader** → creates templates once during startup

**Client/service layer** → only asks for copies

### Prototype and Registry together

```
Application Startup
        |
        v
RegistryLoader
        |
Creates prototypes once
        |
Registers into Registry
        |
--------------------------------
Runtime
        |
Client → registry.get("eng")
        |
Clone returned
```

Step 1: Prototype interface

```java 
public interface Prototype<T> {

    public Prototype<T> clone();
}
```

Step 2: Domain object

```java 
public class Student implements Prototype<Student> {

    int id;
    String name;
    String field;

    Student(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }

    Student(Student s){
        this.id = s.id;
        this.name = s.name;
        this.field = s.field;
    }

    @Override
    public Student clone(){
        return new Student(this);
    }

    @Override
    public String toString(){
        return "Student [id=" + id + ", name=" + name + ", field=" + field + "]";
    }

}
```

Step 3: Registry

Step 1 : Template Map<String,Object>

Step 2 : register(String name, Object prototype) - add data to map

Step 3 : get(String name) - return clone of object from map

Registry only stores templates and returns copies.

```java 
public class StudentRegistry {

    Map<String, Student> template = new HashMap<String, Student>();

    public void register(String name, Student s){
        template.put(name, s);
    }

    public Student get(String name){
        Student prototype = template.get(name);
        // Use Optional + lambda to throw an exception if template is missing,
        // then clone the found prototype.
        return Optional.ofNullable(prototype)
                .orElseThrow(() -> new RuntimeException("template "+name+" not found"))
                .clone();

    }
}
```

Step 4: Startup Loader

This is where real applications initialize data.

```java 
public class RegisteryLoad {

    public static StudentRegistry load(){
        StudentRegistry registry = new StudentRegistry();

        Student eng = new Student(1,"Rahul","Engineering");
        registry.register(Field.eng.name(), eng);
        Student med = eng.clone();
        med.id = 2;
        med.name = "Medic";
        med.field ="Medical";
        registry.register(Field.med.name(),med);
        Student mba = med.clone();
        mba.id = 3;
        mba.name = "Kritica";
        mba.field ="MBA";
        registry.register(Field.mba.name(),mba);

        return registry;
    }
}
```

Step 5: Client code

```java 
public class Client {

    static void main(String[] args) {
        StudentRegistry registry = RegistryLoad.load();

        Student eng1=registry.get(Field.eng.name());
        eng1.name="Eng1";
        System.out.println(eng1.toString());
        Student med1=registry.get(Field.med.name());
        med1.name="Med1";
        System.out.println(med1.toString());
    }
}
```

Client does not know:
* how Student is initialized 
* default values
* template setup
* cloning logic
* object creation complexity


#### Real Use Cases:

* Email templates
* Resume/document templates
* Payment workflow templates
* Rule-engine configurations
* Form templates
* Game character templates
* Dashboard widget templates
* API connector templates
* ETL job configurations

#### When to Use:
✅ Many reusable templates exist

Examples:

    Email templates
    Notification templates
    Product templates
    Workflow templates

✅ Need runtime registration

Example:

    Admin uploads:    
    WELCOME_EMAIL
    ORDER_CONFIRMATION
    PASSWORD_RESET

✅ Plug-and-play architecture

Very common in Spring:

    Map<String, PaymentProcessor>
    Map<String, Strategy>
    Map<String, Handler>

Spring internally acts similar to a registry.

#### When Not To Use:
❌ Few object types

    Car
    Bike

Registry introduces unnecessary indirection.

❌ Static types never change

    if(payment=="UPI")

Three fixed options don't justify registry.

❌ Registry becomes a God Object

    registry.registerStudent()
    registry.registerInvoice()
    registry.registerPayment()
    registry.registerEmployee()
    registry.registerEverything()

Registry starts knowing too much.

#### Smell

🚨 Smell 1: Repeated initialization

❌ Bad:

    Invoice i=new Invoice();
    i.setCountry("India");
    i.setCurrency("INR");
    i.setTax(18);
    
    Invoice i2=new Invoice();
    i2.setCountry("India");
    i2.setCurrency("INR");
    i2.setTax(18);

You repeatedly create nearly identical objects.

🚨 Smell 2: Huge constructors

❌ Bad:
    new User(
    name,
    email,
    phone,
    role,
    permissions,
    settings,
    preferences
    )

Object creation becoming painful.

🚨 Smell 3: Factory becoming giant

❌ Bad:

    if(type.equals("A"))
    ...
    else if(type.equals("B"))
    ...
    else if(type.equals("C"))

Factory continuously growing.

Registry often replaces this.

🚨 Smell 4: Template duplication

You copy-paste object setup.

❌ Bad:

    Student eng1
    Student eng2
    Student eng3

Same initialization repeated.

❓ Question Ask:

1. Do I repeatedly create very similar objects with expensive setup?
2. Is creation expensive?
3. Are objects mostly similar? 
4. Is initialization duplicated? 
5. Do types change dynamically?

If mostly "No", avoid it.