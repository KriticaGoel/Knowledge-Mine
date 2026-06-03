there are approx 23 design pattern

Each design pattern state that remove changing code from static code 


What is changing?

    ├── Parameters only?
    │      → Overloading
    │
    ├── Object identity (IS-A)?
    │      → Inheritance
    │
    ├── Behavior changes at runtime?
    │      → Strategy
    │
    ├── Behavior is small & inline?
    │      → Lambda
    │
    ├── Object creation complex?
    │      → Factory

**Creation patterns**: 

It states that object creation is complex and changing. 

So we remove object creation code from static code and put it in a separate place.

Factory, Builder, Prototype, Registry, Singleton

**Structural patterns**: 

Its states that we want to change the structure of code without changing behavior.

Adapter, Decorator, Facade, Composite, Proxy

**Behavioral patterns**: 

Its states that we want to change the behavior of code without changing structure.

Strategy, State, Observer, Command, Template  

![OverviewOfDesignpattern.jpeg](../../Images/OverviewOfDesignpattern.jpeg)
![HideCreationComplexity.jpeg](../../Images/HideCreationComplexity.jpeg)

![ControlLifecycle.jpeg](../../Images/ControlLifecycle.jpeg)

![ComposeObjects.jpeg](../../Images/ComposeObjects.jpeg)
