### Agenda

- [Inversion of control(IOC)](#inversion-of-controlioc)
- [Beans](#beans)
    - [Xml configuration file](#xml-configuration-file)
    - [Lifecycle of bean](#lifecycle-of-bean)
- [Dependency Injection (DI)](#dependency-injection-di)
    - [Constructor Injection](#constructor-injection)
    - [Setter Injection](#setter-injection)
- Autowiring
    - [Autowire by Name](#autowire-by-name)
    - [Autowire by Type](#autowire-by-type)
    - [Autowire by Constructor](#autowire-by-constructor)
- Annotations
    - [Component](#component)
    - [Value]()
    - [Autowired](#autowired)
    - [Qualifier](#using-qualifier)

![SpringRoadMap.jpg](../../Images/SpringRoadMap.jpg)
### Inversion of control(IOC)

> It's a design principle where object creation and lifecycle management transfer from the application code to a framework.

### Beans

> Objects that are managed by framework called beans.

Beans can be defined in various ways:

* Xml configuration file
* Annotation
* Java-based configuration

#### Xml configuration file

Application context file is used to define beans in xml format this file is stored under the resources folder, and
filename needs to be added in the first java file called by compiler

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationBeanContext.xml");

        MyBean myBean = (MyBean) context.getBean("myBean");
        System.out.println(myBean);
    }
}
```

**applictionBeanContext.xml**—Each bean is defined in <bean> tag with attributes specifying **class, properties, and
dependencies**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- bean definitions here -->
    <bean id="myBean" class="example.bean.MyBean">
        <property name="message" value="I am a first bean"/>
    </bean>

</beans>
```

```java
public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage(){
        System.out.println("Message: " + message);
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
```

> Bean can be configured using annotation like @Component, @Service, @Repository, which is scanned by spring and managed
> as bean.

#### Lifecycle of bean

1. Instantiation—Bean instance is created using bean defination and constructor injection
2. Population of Properties—Properties should be set in bean either from setter, constructor or field injection
3. Initialization—Initialization method invokes after properties ae set
4. Ready to use — bean is mark ready to use
5. Destruction—Destroying Bean

[Home](#agenda)

### Dependency Injection (DI)

> Design pattern used in OOPs where dependency of a class is provided externally rather than created in class interface

This can be achieved in two ways

1. Constructor Injection
2. Setter Injection

#### Constructor Injection

> Dependencies are provided to class through constructor

Implementation:

1. **Specification class** -Maintaining some private properties with getter and setter.
2. **Car class** — Contain dependency of previous class Specification and a method which can be used by outside world.
3. **Xml configuration file** responsible for initializing the bean and constructor injection
4. **App Class** - Main class having xml call and method call

* Xml Configuration

```xml
 <bean id="specification" class="com.kritica.contructorInjection.Specification">
  <!--Setter injection -->
        <property name="make" value="Toyata"/>
        <property name="model" value="Jazz"/>
    </bean>

    <bean id="car" class="com.kritica.contructorInjection.Car">
    <!--Constructor Injection-->
        <constructor-arg ref="specification"/>
    </bean>
```

Specification class

```java
public class Specification {
  private String make;
  private String model;

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return "Specification [make=" + make + ", model=" + model + "]";
  }


}
```

```java
public class Car {
  private Specification specification;

  public Car(Specification specification) {
    this.specification = specification;
  }

  public void display() {
    System.out.println(specification.toString());
  }
}
```

Client class

```java
public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car = (Car) context.getBean("car");
        car.display();

    }
```

#### Setter Injection

> Dependencies are provided to class through setter

Implementation:
Same as above only change in Car class instead of constructor we need to use setter
change in xml file

```xml
<bean id="specification" class="com.kritica.setterInjection.Specification">
        <property name="make" value="Toyota"/>
        <property name="model" value="Jazz"/>
    </bean>

    <bean id="car" class="com.kritica.setterInjection.Car">
        <!--Setter injection -->
        <property name="specification" ref="specification"/>
        <!--Constructor Injection-->
<!--        <constructor-arg ref="specification"/>-->
    </bean>
```

```java
public class Car {

  private Specification specification;

  public void setSpecification(Specification specification) {
    this.specification = specification;
  }


  public void display() {
    System.out.println(specification.toString());
  }
}
```

[Home](#agenda)

### Autowiring

> Feature in a spring framework where own will resolve dependencies.

1. We used to create an object and manage the dependency using java classes
2. Then we moved to xml configuration to manage dependencies and object
3. Spring framework has a feature called autowiring. its feature that automatically resolved and injects dependencies
   between beans without requiring explicit definition in xml or java configurations.

Types of Autowiring

#### Autowire by Name

> Setter method is needed to inject bean and
> setter name and object name should be same _carSpecification_

```xml

<bean id="carSpecification" class="com.kritica.autowire.name.Specification">
  <property name="make" value="Toyata"/>
  <property name="model" value="Jazz"/>
</bean>

<bean id="car" class="com.kritica.autowire.name.Car" autowire="byName"/>

```

```java
public class Car {
  private Specification carSpecification;

  //Autowire by name use setter method to set values
  public void setCarSpecification(Specification carSpecification) {
    this.carSpecification = carSpecification;
  }

  public void display() {
    System.out.println(carSpecification.toString());
  }
}
```

#### Autowire by Type

> Setter method is needed to inject bean and
> setter name and object name can be different.
> In xml only one bean define having same type

```xml

<bean id="carSpecification" class="com.kritica.autowire.type.Specification">
  <property name="make" value="Toyata"/>
  <property name="model" value="Jazz"/>
</bean>

<bean id="car" class="com.kritica.autowire.type.Car" autowire="byType"/>

```

```java
public class Car {
  private Specification specification;

  public void setSpecification(Specification specification) {
    this.specification = specification;
  }

  public void display() {
    System.out.println(specification.toString());
  }
}

```

#### Autowire by Constructor

> using constructor instead of setter

```xml

<bean id="carSpecification" class="com.kritica.autowire.constructor.Specification">
  <property name="make" value="Toyata"/>
  <property name="model" value="Jazz"/>
</bean>

<bean id="car" class="com.kritica.autowire.constructor.Car" autowire="constructor"/>
```

```java
public class Car {
  private Specification carSpecification;

  public Car(Specification carSpecification) {
    this.carSpecification = carSpecification;
  }

  public void display() {
    System.out.println(carSpecification.toString());
  }
}
```

[Home](#agenda)

### Annotations

> It provides a way to add metadata to code

Commonly used Spring annotation

1. @[Component](#component) - marks java class as a bean to manage component. We can give bean name as well
2. [ @Autowired](#autowired) - Adding dependency bean
3. @[Qualifier](#using-qualifier) - name to a bean if multiple class have same name in different packages
4. @Value - give default value in java class. setter is not needed
5. @Repository
6. @Service
7. @Controller
8. @RequestMapping
9. @SpringBootApplication

#### Component

> It refers to java class which is managed by spring IOC container

Defining Components in spring

1. [using XMl](#using-xml----)

```
<bean id="car" class="com.kritica.autowire.constructor.Car"/>
```  

2. [using Annotation](#using-annotation)

```java
@Component
public class MyComponent{
    
}
```

#### Component Scanning

> It helps to automatically detect and register the bean rom predefine package paths.

##### Using XML --

Enable component scanning
<context:component-scan base-package=" "/>

Implementation

```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.kritica.componentScan"/>

</beans>

```

```java
import org.springframework.stereotype.Component;

@Component
public class Employee {
    private int id;
    private String name;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}
```

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextComponentScan.xml");
        Employee emp=context.getBean("employee", Employee.class);
        System.out.println(emp.toString());
    }
}

```

##### Using Annotation

Make a new class having all configuration

```java

@Configuration
@ComponentScan(basePackages = "com.kritica.annotation.componentSan")
public class AppConfig {

}

```

Instead of classPathXMlApplicationCOntext use AnnotationConfigApplicationContext and send acpngig class as argumet

```java
public class App {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextComponentScan.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee emp = context.getBean("employees", Employee.class);
        System.out.println(emp.toString());
    }
}

```

#### Autowired

> Adding dependency using annotation autowired instaed of xml file

App class

```java
public class App {
  public static void main(String[] args) {
    ApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);

    Car car = context.getBean("car", Car.class);
    car.display();

  }
}
```

AppConfig class

```java

@Configuration
@ComponentScan(basePackages = "com.kritica.annotation.autowired")
public class AppConfig {

}
```

Class have dependency

```java

@Component
public class Car {
  //autowired by fields
  @Autowired
  private Specification specification;
//Autowired by constructor
//    @Autowired
//   public Car(Specification specification) {
//       this.specification = specification;
//   }

  public void display() {
    System.out.println(specification.toString());
  }
}

```

```java

@Component
public class Specification {
  @Value("Toyata")
  private String make;
  @Value("ABC")
  private String model;

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return "Specification [make=" + make + ", model=" + model + "]";
  }


}
```

##### Using Qualifier

```java

@Component("spec")
public class Specification {

    @Autowired
    @Qualifier("spec")
    private Specification specification;
}
```