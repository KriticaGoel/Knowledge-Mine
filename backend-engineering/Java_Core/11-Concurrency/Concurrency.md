# Synchronisation

- Memory Region
    - [Stack Memory Region](#Stack-Memory-Region)
    - [Heap Memory Region](#Heap-Memory-Region)
        - [Reference Variables and Objects](#Reference-Variables-and-Objects)
- [Resource Sharing between Threads and Problem](#Resource-Sharing-between-Threads)
    - [Race Condition](#Race-Conditions)
    - [Data Race](#Data-Race)
    - [Non-atomic Operations](#Non-atomic-Operations)
    - [Added and Substractor Problem](#Increment-value-and-decrement-same-value.)
- [Concurrency Challenges and Solutions](#Concurrency-Challenges-and-Solutions)
    - [Mutex Locks](#Mutex)
        - [Properties of a mutex lock](#Properties-of-a-mutex-lock)
        - [Problem of mutex lock](#Problem-of-a-mutex-lock)
    - [Syncronized keyword](#Synchronized-Keyword)
        - [Synchronized- Monitor](#Synchronized—Monitor)
        - [Problem of a Synchronized - Monitor](#problem-of-a-synchronized---monitor)
    - [Synchronized-Lock](#Synchronized-Lock)
        - [Advantage of a Synchronized-Lock](#advantage-of-a-synchronized-lock)
- [Atomic Operations](#atomic-operations)
- [Concurrent Data structures](#concurrent-data-structures)
- [HashMap Vs HashTable Vs ConcurrentHashMap](#HashMap-Vs-HashTable-Vs-ConcurrentHashMap)
- [String Vs String Builder Vs String Buffer](#string-vs-string-builder-vs-string-buffer)
- [DeadLock](#DeadLock)

## DATA SHARING BETWEEN THREAD

### Stack Memory Region

> **Definition**: The stack is a memory region where methods are called, arguments are passed, and local variables are
> stored.

![Stack1.png](..%2F..%2F..%2Fresources%2FStack1.png)

![Stack2.png](..%2F..%2F..%2Fresources%2FStack2.png)
#### Stack Properties

1. Each stack belongs to a specific thread.
2. Local variables are not accessible by other threads
3. Stack memory is allocated statically when a thread is created.
4. The size of the stack is fixed and cannot change at runtime.
5. A deep memory hierarchy can lead to a StackOverflowException, especially with recursive calls.

### Heap Memory Region

> **Definition**: The heap is a shared memory region used for dynamic memory allocation.

What is allocated over heap?

* Objects-Anything created by new operator like String, Object, collections...
* Class member variables.
* Static variables are the class variables (remain in memory as long as the class is loaded).

  ```
  class MyClass {
     MyObject memberObject; // Class member object reference

     MyClass() {
         memberObject = new MyObject(); // Initializing the member object
     }
  
     void myMethod() {
         MyObject localObject = new MyObject(); // Non-class member object reference
         // localObject can only be used within this method
     }
  }

#### Heap Memory Management

* Managed by Garbage Collector
* Objects stay as long as we have one reference of them.
* Members of class exist as long as parent of class exists.
* Static Variables—Stay Forever.

#### Reference Variables and Objects

1. Reference variables are stored in **STACK**
2. Reference variable of class member stored in **HEAP**
3. Objects always stored in **HEAP**

## Resource Sharing between Threads

**Common Resources**: Variables, objects, file handles, data structures, and message queues.

**Use Case**: In a text editor, one thread may handle the UI while another manages file saving, requiring access to
shared resources.

**Another Use Case**: Database, One db is shared by multiple thread to do read and write operations.

#### Problems with Shared Resources

**Race Conditions**: When multiple threads access and modify shared resources simultaneously, leading to incorrect
results.

**Non-atomic Operations**: Operations like items++ are not atomic, as they involve multiple steps.

```
Atomic Operations
1. Appears at once.
2. Single step operation—all or nothing
3. No intermediate states
```

```
items++ is not atomic since have multiple steps
1. Read current value
2. Increment modify value
3. Store modified value back to variable.
```

**Increment value and decrement same value.**

```java
    public class IncrementDecrement {

        public static void main(String[] args) throws InterruptedException {
            //constructor
            InventoryCounter inventoryCounter = new InventoryCounter();
            IncrementNumber inc = new IncrementNumber(inventoryCounter);
            DecrementNumber dec = new DecrementNumber(inventoryCounter);
    
            inc.start(); //start the thread
            inc.join();//finished the thread
            dec.start();//start the thread
            dec.join();//finished the thread
            System.out.println("Result of sequential threading "+inventoryCounter.getItems());
    
            InventoryCounter inventoryCounter1 = new InventoryCounter();
            IncrementNumber inc1 = new IncrementNumber(inventoryCounter1);
            DecrementNumber dec1 = new DecrementNumber(inventoryCounter1);
    
            inc1.start(); //start the thread
            dec1.start();//start the thread
            inc1.join();//finished the thread
            dec1.join();//finished the thread
    
            System.out.println("Result of multi threading "+inventoryCounter1.getItems());
    
        }
    
        public static class InventoryCounter {
            private int items = 0;
    
            public void increment() {
                items++;
            }
    
            public void decrement() {
                items--;
            }
    
            public int getItems() {
                return items;
            }
        }
    
        public static class IncrementNumber extends Thread{
    
            InventoryCounter increment;
            public IncrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.increment();}
            }
        }
    
        public static class DecrementNumber extends Thread{
    
            InventoryCounter increment;
            public DecrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.decrement();}
            }
        }
    }

```

**Output**:

Result of sequential threading 0
Result of multi threading 36

Problems:

1. Two thread sharing items counter
2. Both threads are reading and modifying the counter at the same time.
3. Operations are not atomic.

## Concurrency Challenges and Solutions

Below is the condition that causes synchronization problem:

1. **Critical Sections**: Parts of code that must be executed by only one thread at a time.

2. **Race Conditions**: Occur when the timing of thread execution causes incorrect results.
   Condition when multiple threads are accessing a shared resource.
   At least one thread is modifying the resource.
   the timing of thread scheduling may cause an incorrect result.
   The core problem is non-atomic operations performed on shared resource.

3. **Preemption**: A thread in a critical section can be paused, leading to inconsistencies

**Data Race**

```java

public class DataRace {

   public static void main(String[] args) {
      RaceCondition raceCondition = new RaceCondition();
      Thread th = new Thread(() -> {
         for (int i = 0; i < Integer.MAX_VALUE; i++) {
            raceCondition.increment();
         }
      });

      Thread th1 = new Thread(() -> {
         for (int i = 0; i < Integer.MAX_VALUE; i++) {
            raceCondition.checkForDataRace();
         }
      });

      th.start();
      th1.start();

   }

   public static class RaceCondition {
      private int x = 0;
      private int y = 0;

      public void increment() {
         x++;
         y++;
      }

      public void checkForDataRace() {
         if (y > x) {
            System.out.println("Data Race condition exists x=" + x + " y=" + y);
         }
      }
   }
}

```

#### Solutions in Java

1. Java provide locking mechanism
2. Used to restrict the entire section or critical section to single thread at a time.

#### Mutex (Mutual Exclusion)

> **Definition:** A lock that restricts access to critical sections.

**Implementation**: Using a lock object around critical code.

```java
Lock lock = new ReentrantLock();
lock.

lock();
try{
        // Critical section
        }finally{
        lock.

unlock();
}
```

Complete code

    public class Mutex {
        public static void main(String[] args) throws InterruptedException {
            //constructor
            InventoryCounter inventoryCounter = new InventoryCounter();
            Lock lock = new ReentrantLock();
            IncrementNumber inc = new IncrementNumber(inventoryCounter,lock);
            DecrementNumber dec = new DecrementNumber(inventoryCounter,lock);
    
            inc.start(); //start the thread
            dec.start();//start the thread
            inc.join();//finished the thread
            dec.join();//finished the thread
            System.out.println("Result of Mutex threading "+inventoryCounter.getItems());
        }
    
        public static class InventoryCounter {
            private int items = 0;
    
            public void increment() {
                items++;
            }
    
            public void decrement() {
                items--;
            }
    
            public int getItems() {
                return items;
            }
        }
    
        public static class IncrementNumber extends Thread{
    
            InventoryCounter increment;
            Lock lock;
            public IncrementNumber(InventoryCounter increment,Lock lock){
                this.increment = increment;
                this.lock = lock;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    lock.lock();
                increment.increment();
                lock.unlock();}
            }
        }
    
        public static class DecrementNumber extends Thread{
    
            InventoryCounter increment;
            Lock lock;
            public DecrementNumber(InventoryCounter increment,Lock lock){
                this.increment = increment;
                this.lock = lock;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    lock.lock();
                increment.decrement();
                lock.unlock();}
            }
        }
    }

#### Properties of a mutex lock:

* Lock - A thread can only access the critical section if it has the lock.
* Only one thread can have the lock at a time.
* Other threads cannot access the critical section if a thread has the lock and thus have to wait.
* Lock will automatically be released when the thread exits the critical section.

#### Problem of a mutex lock:

1. If developer forget to unlock the object
2. If code throws error and object not unlocked properly
3. Always need to put unlocking in the final block so that it will execute always. that means we need to try and final
   block in this scenario.

#### Synchronized Keyword

> There are two ways to use Synchronized Keyword
> * Synchronized - Monitor
> * Synchronized-Lock

1. ##### Synchronized—Monitor

> **Definition**:Locks entire methods, preventing access by other threads.

```java
public synchronized void method() {
   // Critical section
}
```

![objectLock.png](..%2F..%2F..%2Fresources%2FobjectLock.png)
#### Problem of a Synchronized - Monitor:

If thread A accessing method 1 then thread B cannt access any method of that class as both methods are locked.

```java
 public class ABC {
        public synchronized method1(){
        .....
        }

        public synchronized method2(){
        .....
        }
    }
```

Complete code

```java
    public class Monitor {

        public static void main(String[] args) throws InterruptedException {
            //constructor
            InventoryCounter inventoryCounter = new InventoryCounter();
            IncrementNumber inc = new IncrementNumber(inventoryCounter);
            DecrementNumber dec = new DecrementNumber(inventoryCounter);
    
            inc.start(); //start the thread
            dec.start();//start the thread
            inc.join();//finished the thread
            dec.join();//finished the thread
            System.out.println("Result of Mutiple threading in sychronized way "+inventoryCounter.getItems());
    
    
        }
    
        public static class InventoryCounter {
            private int items = 0;
    
            public synchronized void increment() {
                items++;
            }
    
            public synchronized void decrement() {
                items--;
            }
    
            public synchronized int getItems() {
                return items;
            }
        }
    
        public static class IncrementNumber extends Thread{
    
            InventoryCounter increment;
            public IncrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.increment();}
            }
        }
    
        public static class DecrementNumber extends Thread{
    
            InventoryCounter increment;
            public DecrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.decrement();}
            }
        }
    }

```

2. ##### Synchronized-Lock

> **Definition**: Locks specific sections of code, allowing other methods to run concurrently.

```java
Object lock = new Object();
synchronized (lock){
        // Critical section
        }
```

#### Advantage of a Synchronized-Lock:

* Two threads can access two different methods having a critical section without blocking all methods which we are doing
  in the previous case.
* We are locking critical code only rest code can be accessed by muti threads
* Increase performance.

 ![2.jpg](..%2F..%2F..%2Fresources%2F2.jpg)
![3.jpg](..%2F..%2F..%2Fresources%2F3.jpg)
Complete code:

```java

    public class Lock {

        public static void main(String[] args) throws InterruptedException {
            //constructor
            InventoryCounter inventoryCounter = new InventoryCounter();
            IncrementNumber inc = new IncrementNumber(inventoryCounter);
            DecrementNumber dec = new DecrementNumber(inventoryCounter);
    
            inc.start(); //start the thread
            dec.start();//start the thread
            inc.join();//finished the thread
            dec.join();//finished the thread
            System.out.println("Result of Mutiple threading in sychronized way "+inventoryCounter.getItems());
    
    
        }
    
        public static class InventoryCounter {
            private int items = 0;
    
            Object lock = new Object();
            public  void increment() {
                System.out.println("Incrementing");
                synchronized (lock) {items++;}
    
            }
    
            public  void decrement() {
                System.out.println("Decrementing");
                synchronized (lock) {items--;}
            }
    
            public  int getItems() {
                return items;
            }
        }
    
        public static class IncrementNumber extends Thread{
    
            InventoryCounter increment;
            public IncrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.increment();}
            }
        }
    
        public static class DecrementNumber extends Thread{
    
            InventoryCounter increment;
            public DecrementNumber(InventoryCounter increment){
                this.increment = increment;
            }
    
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                increment.decrement();}
            }
        }
    }

```

![objectLock.png](..%2F..%2F..%2Fresources%2FobjectLock.png)
#### Atomic Operations

1. All assignments are atomic.
2. All getters and setters are atomic. Since we are assigning values only.
3. All primitive types are atomic except double and long.
4. Double and long also became atomic after declaring a volatile keyword
   volatile double x=1.0;
5. We have Atomic Datatype like Atomic Integer. that means operation happen on this function are atomic in nature and
   much faster

```java
AtomicInteger counter = new AtomicInteger(0);

public void increment() {
counter.incrementAndGet();
}
```

6. Atomic use case Implement Metric

```java
public class Atomic {

        public static void main(String[] args) {
            Metric metric = new Metric();
            BusinessLogic businessLogic = new BusinessLogic(metric);
            BusinessLogic businessLogic2 = new BusinessLogic(metric);
            MetricPrinter metricPrinter = new MetricPrinter(metric);
    
            businessLogic.start();
            businessLogic2.start();
            metricPrinter.start();
        }
    
        public static class MetricPrinter extends Thread {
            Metric metric;
    
            public MetricPrinter(Metric metric){
                this.metric = metric;
            }
    
            @Override
            public void run() {
             while(true){
                 System.out.println(metric.getAverage());
             }
            }
    
        }
    
        public static class BusinessLogic extends Thread {
            Metric metric;
            Random random= new Random();
    
            public BusinessLogic(Metric metric) {
                this.metric=metric;
            }
    
            @Override
            public void run() {
                while (true) {
                    long startTime = System.currentTimeMillis();
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long endTime = System.currentTimeMillis();
                    metric.addSample(endTime - startTime);
                }
            }
        }
    
    
        public static class Metric {
            private volatile long count=0;
            private volatile double average=0.0;
    
            public synchronized void addSample(long sample){
                double currentSample= average*count;
                count++;
                average=(currentSample+sample)/count;
            }
    
            public double getAverage(){
                return average;
            }
    
    
        }
    }

```

Use case
A stock trading application that keeps track of the minimum and maximum price of the stock daily.

## Concurrent Data structures

A concurrent data structure is a particular way of storing and organizing data for access by multiple computing
threads (or processes) on a computer. A shared mutable state very easily leads to problems when concurrency is involved.
If access to shared mutable objects is not managed properly, applications can quickly become prone to some
hard-to-detect concurrency errors.

Some common concurrent data structures:

1. [Atomic Integer](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html#:~:text=An%20AtomicInteger%20is%20used%20in,deal%20with%20numerically%2Dbased%20classes)
   > The AtomicInteger class protects an underlying int value by providing methods that perform atomic operations on the
   value
   Compare and Swap algorithm used by Atomic Data types for thread-safety
2. Concurrency

### HashMap Vs HashTable Vs ConcurrentHashMap

| **HashMap**      | **HashTable**                                        | **ConcurrentHashMap**                                                                                                                                      |
|------------------|------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Not Thread Safe  | Thread Safe                                          | Thread Safe                                                                                                                                                |                           
| Not synchronized | Synchronized                                         | Synchronized                                                                                                                                               |
| NA               | Every read/write operation needs to acquire the lock | There is no locking at the object level<br/>It never locks the whole Map, instead, it divides the map into segments and locking is done on these segments. |

### String Vs String Builder Vs String Buffer

| String -Immutable text or when modifications are rare.                                                                                                   | StringBuilder - Mutable strings in single-threaded contexts for better performance.                                                                                | StringBuffer - When you need a mutable string and require thread safety.                                             |
|----------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| **Immutability** :String objects are immutable, meaning once created, their values cannot be changed. Any modification results in a new String object.   | **Mutability**: StringBuilder is mutable, allowing modifications without creating new objects.                                                                     | **Mutability**: Like StringBuilder, StringBuffer is mutable and allows for modifications.                            |                           
| **Performance**: Due to immutability, frequent modifications (like concatenations) can lead to performance issues, as new objects are created each time. | **Performance**: More efficient than String for concatenations and modifications, especially in loops or when dealing with large amounts of data.                  | **Performance**: Generally slower than StringBuilder because it is synchronized for thread safety                    |
| Not Thread Safe                                                                                                                                          | **Thread Safety**: Not synchronized, meaning it's not thread-safe. Use it in single-threaded environments or when you don't need to worry about concurrent access. | **Thread Safety**: Synchronized, making it thread-safe. This means it's safe to use in a multi-threaded environment. |
| **Usage**: Best for situations where you have fixed strings or are performing a limited number of modifications.                                         | **Usage**: Ideal for scenarios where you need to build or modify strings dynamically in a single thread.                                                           | **Usage**: Best when you need to modify strings in a multi-threaded context                                          |

### LinkedQueue Vs ConcurrentLinkedQueue

| **LinkedQueue** - When you need a simple, non-thread-safe queue implementation                                                                                                     | **ConcurrentLinkedQueue**- When you require a thread-safe, high-performance queue suitable for concurrent applications.                                                                   |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Implementation**: Generally refers to a standard linked list-based implementation of a queue (not part of the Java standard library, but a common concept).                      | **Implementation**: Part of the Java Collections Framework, specifically in the java.util.concurrent package.                                                                             |                           
| **Thread Safety**: Not thread-safe. If multiple threads access a LinkedQueue concurrently without external synchronization, it can lead to inconsistent states or data corruption. | **Thread Safety**: Designed for concurrent access. It uses lock-free algorithms to ensure thread safety, allowing multiple threads to perform operations simultaneously without blocking. |
| **Performance**: Typically performs well for single-threaded applications where you need a simple queue structure with FIFO (First-In-First-Out) behavior.                         | **Performance**: Generally provides better performance in multi-threaded environments compared to other synchronized queues due to its non-blocking nature.                               |                                                                                                                                                                                   |                                                                                                                                                                                               |
| **Usage**: Suitable for applications where concurrency is not a concern.                                                                                                           | **Usage**: Ideal for high-concurrency applications where multiple threads need to enqueue and dequeue items safely.                                                                       |

### DeadLock

To prevent or resolve deadlocks, consider the following strategies:

1. **Avoid Nested Locks**—Minimize the use of nested locks. If possible, avoid acquiring multiple locks at the same
   time.
2. **Lock Ordering**—Always acquire locks in a consistent order. If every thread acquires locks in the same order,
   deadlocks can be avoided.

```java
class Resource {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                // critical section
            }
        }
    }

    public void method2() {
        synchronized (lock1) {
            synchronized (lock2) {
                // critical section
            }
        }
    }
}
```

In this example, both methods acquire lock1 before lock2, thus preventing deadlocks.

3. **Use Timeout**—When attempting to acquire a lock, use a timeout mechanism. If a thread cannot acquire the lock
   within a specified time, it should back off and try again later.

```java
if(lock.tryLock(timeout, TimeUnit.SECONDS)){
        try{
        // critical section
        }finally{
        lock.

unlock();
    }
            }
```

4. **Use Higher-Level Concurrency Utilities**-Utilize higher-level constructs from the java.util.concurrent package,
   such as ReentrantLock, Semaphore, or CountDownLatch, which provide more control over locking mechanisms.
5. **Deadlock Detection**-Implement a mechanism to detect deadlocks. This can involve periodically checking the state of
   the threads and resources to identify potential deadlocks.

```java
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector {
    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void checkForDeadlock() {
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null) {
            for (long threadId : deadlockedThreads) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
                System.out.println("Deadlock detected: " + threadInfo.getThreadName());
            }
        }
    }
}
```

6. **Reduce Lock Scope**-Limit the scope of locks. Lock only the necessary part of the code, which can reduce the
   chances of deadlocks.
7. **Thread Dump Analysis**-If a deadlock occurs, analyze thread dumps to understand the state of each thread and
   identify which resources are held and which are being waited on. This can help in diagnosing and fixing the deadlock.