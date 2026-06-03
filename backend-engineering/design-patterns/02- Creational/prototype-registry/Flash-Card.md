# 🎴 Prototype Design Pattern — Flash Cards

<details>
<summary>Q1: What is Prototype Design Pattern?</summary>

Create new objects by copying (cloning) existing objects instead of creating them using `new`.

Think:

```text
Existing Object
      ↓
   clone()
      ↓
 New Object
 ```
</details>
<details> <summary>Q2: When should Prototype be used?</summary>

Use Prototype when:

✔ Object creation is expensive

✔ Initialization logic is complex

✔ Similar objects are repeatedly created

✔ Runtime object creation required

Smell:

Do I repeatedly create similar objects with expensive setup?
</details>
<details> <summary>Q3: Main goal of Prototype?</summary>

Avoid expensive object creation.

Reuse existing configured objects.

Create once
    ↓
Clone many
</details>
<details> <summary>Q4: Basic flow of Prototype Pattern?</summary>
Client
   ↓
Prototype Interface
   ↓
 clone()
   ↓
Concrete Prototype
</details>
<details> <summary>Q5: Prototype interface example?</summary>
public interface Prototype<T>{
    T clone();
}
</details>
<details> <summary>Q6: How is cloning usually implemented?</summary>

Using a copy constructor.

Student(Student s){
 this.id=s.id;
 this.name=s.name;
 this.field=s.field;
}

public Student clone(){
 return new Student(this);
}
</details>
<details> <summary>Q7: Why use copy constructor?</summary>

Helps create a Deep Copy.

Prevents original object modification.

</details>
<details> <summary>Q8: Difference between Deep Copy and Shallow Copy?</summary>

Deep Copy:

Creates separate nested objects

Shallow Copy:

Copies references only

Deep copy avoids shared object issues.

</details>
<details> <summary>Q9: Problem without Registry?</summary>

Client becomes responsible for:

❌ Object setup

❌ Cloning

❌ Template management

❌ Initialization logic

❌ Knowing every object type

</details>
<details> <summary>Q10: Without Registry flow?</summary>
Client
 |
 +--> clone()
 |
 +--> clone()
 |
 +--> clone()

Problem:

Client knows everything.

</details>
<details> <summary>Q11: What does Registry solve?</summary>

Registry stores prototypes and returns copies.

Client asks:

registry.get("ENG")

Registry handles:

find
 ↓
clone
 ↓
return
</details>
<details> <summary>Q12: Responsibilities after Prototype + Registry?</summary>

Prototype:

Knows cloning

Registry:

Stores templates

Loader:

Creates templates once

Client:

Asks for copies
</details>
<details> <summary>Q13: Runtime flow with Registry?</summary>
Application Startup
        ↓
RegistryLoader
        ↓
Create expensive objects
        ↓
Registry stores templates

--------------------------

Runtime

Client
   ↓
registry.get()
   ↓
clone()
   ↓
new copy
</details>
<details> <summary>Q14: Why Registry improves design?</summary>

✔ Removes tight coupling

✔ Removes duplicate code

✔ Centralized template management

✔ Easy to add new templates

</details>
<details> <summary>Q15: Registry code skeleton?</summary>
class StudentRegistry{

Map<String,Student> templates;

void register(String key,Student s)

Student get(String key){
 return templates.get(key).clone();
}

}
</details>
<details> <summary>Q16: Why create templates during startup?</summary>

Create expensive objects once.

Startup
   ↓
Initialize
   ↓
Reuse forever

Improves performance.

</details>
<details> <summary>Q17: Production examples?</summary>

✔ Email templates

✔ Resume templates

✔ Rule engines

✔ Payment workflows

✔ Game characters

✔ Dashboard widgets

✔ ETL jobs

✔ API connectors

</details>
<details> <summary>Q18: Prototype + Registry one line explanation?</summary>
Create once
Store centrally
Clone many times
</details>
<details> <summary>Q19: Common mistakes?</summary>

❌ Shallow copy accidentally

❌ Registry returns original object

❌ Client creates templates

❌ Missing copy constructor

❌ Business logic inside registry

</details>
<details> <summary>Q20: Golden interview answer</summary>

Prototype creates copies of configured objects.

Registry centralizes prototypes and returns clones so clients remain unaware of creation complexity.

</details>
<details> <summary>Q21: Quick memory hook</summary>
Create once
     ↓
Store
     ↓
Clone many
</details> 