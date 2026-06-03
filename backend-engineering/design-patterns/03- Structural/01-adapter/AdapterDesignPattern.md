## Adapter Design pattern

Structural design pattern

Allows objects with incompatible interfaces to work together.

It acts as a bridge between two incompatible interfaces, enabling them to communicate and collaborate effectively.

**Example**:

Payment Interface  <-> Adapter  <-> Paypal Interface


### Problems:

1. **Interface Incompatibility:**

   When two systems or components have different interfaces, they cannot directly communicate or work together. This can lead to difficulties in integrating new components into existing systems.

2. **Legacy Systems**:

    Many organizations have legacy systems that are still in use but may not be compatible with newer technologies or interfaces. This can create challenges when trying to integrate new features or components.

3. **Third party libraries**:
    When using third-party libraries or APIs, they may have their own interfaces that do not align with the existing system's interfaces. This can make it difficult to utilize these libraries effectively without significant modifications.

### Goal:
Decouple two interfaces so that if anyone change then we need to modify the adapter only not code change.

Here Storage upload in our Code based interface (Strategy design pattern) and AWSUploaded is a adapter which connect AWS API to storage upload interface.

![AdapterDesignPattern.png](../../../../Images/AdapterDesignPattern.png)

Code: https://github.com/KriticaGoel/LLD_JAVA/tree/master/src/designPattern/structural/adapter











