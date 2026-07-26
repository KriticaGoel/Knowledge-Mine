## Agenda

* [Producer-consumer problem](#Producer-consumer-problem)
* [Semaphores](#Parallel-solution-Semaphore)

## Producer-consumer problem

The Producer-Consumer problem is a classic synchronization problem in operating systems.

The problem is defined as follows: there is a fixed-size buffer and a Producer process, and a Consumer process.

The Producer process creates an item and adds it to the shared buffer. The Consumer process takes items out of the
shared buffer and “consumes” them.

![Producer vs consumer](http://pages.cs.wisc.edu/~bart/537/lecturenotes/figures/s6-prodcons.jpg)

Certain conditions must be met by the Producer and the Consumer processes to have consistent data synchronisation:

* The Producer process must not produce an item if the shared buffer is full.

* The Consumer process must not consume an item if the shared buffer is empty.

### Producer

The task of the producer is to create a unit of work and add it to the store. The consumer will pick that up when it is
available. The producer cannot add exceed the number of max units of the store.

```java
    public void run() {
        while (true) {
            if (store.size() < maxSizeOfStore) {
                store.add(new UnitOfWork());
            }
        }
    }
```

### Consumer

The role of the consumer is to pick up unit of works from the queue or the store once they have been added by the
consumer. The consumer can only pick up units if there are any available.

```java
    public void run() {
        while (true) {
            if (store.size() > 0) {
                store.remove();
            }
        }
    }
```

The above code will lead to concurrency issues since multiple thread can access the store at one time.
What happens if there is only one unit present, but two consumers try to acquire it at the same time.
Since the size of the store will be 1, both of them will be allowed to execute, but one will error out.

### Base Solution - Mutex

In order to solver the concurrency issue, we can use mutexes or locks.
In Java this can be achieved by simply wrapping the critical section in a synchronised block.

```java
    public void run() {
        while (true) {
            synchronized (store) {
                if (store.size() < maxSizeOfStore) {
                    store.add(new Shirt());
                }
            }
        }
    }
```

Now only one thread will be able to access the store at one time. This will solve the concurrency issue, but our
execution does not happen in parallel now. Due to the mutex, only one thread can access the store at a time.

### Parallel solution-Semaphore

> Semaphore is a variable or abstract data type used to control access to a common resource by multiple threads and
> avoid critical section problems in a concurrent system such as a multitasking operating system. The main attribute of a
> semaphore is how many thread does it control. If a semaphore handles just one thread, it is effectively similar to a
> mutex.

1. It Can be used to restrict the number of users to a particular resource or group of Theory.resources.
2. Unlike the lock that allows **one number of users** to a resource. Semaphore can restrict **any number of users** to
   a resource.

#### Difference between Semaphore and Lock

1. Semaphore does not know the owner of thread
    1. Many threads can acquire a permit
    2. The Same thread can acquire the semaphore multiple time
2. Semaphore can be released by any thread.
    1. even thread didnt acquire it.
    2. Two threads can enter a critical section that introduces BUG in a system

Semaphore can't eliminate Thread locking. It can be used for a producer and consumer problem

**Solution** :
To solve our producer and consumer problem, we can use two semaphores:

1. **For Producer** - This semaphore will control the maximum number of producers and is initialised with the max stor
   size.
2. **For Consumer** - This semaphore controls the maximum number of consumers. This starts with 0 active threads.

```java
Semaphore forProducer = new Semaphore(maxSize);
Semaphore forConsumer = new Semaphore(0);
```

The ideal situation for us is that:

* There are parallel threads that are able to produce until all the store is filled.
* There are parallel threads that are able to consume until all the store is empty.

Thus, to achieve this we use each producer thread to signal to the consumer that it has added a new unit(using consumer
released in producer thread).
Similarly, once the consumer has reduced the unit of works, it signals it to the producer to start making more(using
producer released inside consumer thread).

1. acquire will check there is demand to run the thread.
2. release will ask OS to notify the consumer thread that producer work is finished now they can run.

![producerConsumer.PNG](..%2F..%2F..%2Fresources%2FproducerConsumer.PNG)
```java

// Producer

while (true) {
    forProducer.acquire();
    store.add(new UnitOfWork());
    forConsumer.release();
}
```

```java

// Consumer

while (true) {
    forConsumer.acquire();
    store.add(new UnitOfWork());
    forProducer.release();
}
```

![multithreadProducerConsumer.PNG](..%2F..%2F..%2Fresources%2FmultithreadProducerConsumer.PNG)
### CODE

> 1. Producer and consumer code

```java
public class Main {

    public static void main(String[] args) {
        //Shared Object
        Queue<Object> queue = new ConcurrentLinkedQueue<>();

        //Create two Semaphore
        Semaphore producer = new Semaphore(2);// give access to producer first with capacity 5
        Semaphore consumer = new Semaphore(0); // Block consumer to execute unless producer notifies

        for (int i = 0; i < 5; i++) {
            Producer p = new Producer(queue, producer, consumer);
            Consumer c = new Consumer(queue, producer, consumer);
            Thread t1 = new Thread(p);
            t1.start();
            System.out.println(t1.getName() + " t1 started");
            Thread t2 = new Thread(c);
            t2.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Final result " + queue.size());


    }
}
```

```java
public class Producer implements Runnable {
    private Queue<Object> sharedDrive;
    private Semaphore producer;
    private Semaphore consumer;

    Producer(Queue<Object> sharedDrive, Semaphore producer, Semaphore consumer) {
        this.sharedDrive = sharedDrive;
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Producer going to start " + producer.availablePermits());
            producer.acquire();
            System.out.println(Thread.currentThread().getName() + ": Producer started " + producer.availablePermits());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedDrive.add(new Object());
        //notify consumer to continue the task
        System.out.println(Thread.currentThread().getName() + ": Producer going to released consumer " + consumer.availablePermits());
        consumer.release();
        System.out.println(Thread.currentThread().getName() + ": Consumer count " + consumer.availablePermits());
    }
}
```

```java
public class Consumer implements Runnable {

    private Queue<Object> sharedDrive;
    private Semaphore producer;
    private Semaphore consumer;

    Consumer(Queue<Object> sharedDrive, Semaphore producer, Semaphore consumer) {
        this.sharedDrive = sharedDrive;
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Consumer going to start " + consumer.availablePermits());
            consumer.acquire();
            System.out.println(Thread.currentThread().getName() + ": Consumer started " + consumer.availablePermits());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedDrive.remove();
        //notify producer to continue the task
        System.out.println(Thread.currentThread().getName() + ": Consumer going to released producer " + producer.availablePermits());
        producer.release();
        System.out.println(Thread.currentThread().getName() + ": producer count " + producer.availablePermits());
    }
}
```

```
OUTPUT
Thread-0 t1 started
Thread-2 t1 started
Thread-4 t1 started
Thread-6 t1 started
Thread-8 t1 started
Thread-5: Consumer going to start 0
Thread-8: Producer going to start 2
Thread-3: Consumer going to start 0
Thread-0: Producer going to start 2
Thread-4: Producer going to start 2
Thread-2: Producer going to start 2
Thread-9: Consumer going to start 0
Thread-7: Consumer going to start 0
Thread-1: Consumer going to start 0
Thread-8: Producer started 1
Thread-0: Producer started 0
Thread-6: Producer going to start 2
Thread-8: Producer going to released consumer 0
Thread-0: Producer going to released consumer 0
Thread-8: Consumer count 1
Thread-3: Consumer started 0
Thread-0: Consumer count 1
Thread-5: Consumer started 0
Thread-3: Consumer going to released producer 0
Thread-5: Consumer going to released producer 0
Thread-4: Producer started 0
Thread-4: Producer going to released consumer 0
Thread-2: Producer started 0
Thread-3: producer count 1
Thread-4: Consumer count 1
Thread-5: producer count 1
Thread-9: Consumer started 0
Thread-2: Producer going to released consumer 0
Thread-9: Consumer going to released producer 0
Thread-2: Consumer count 1
Thread-7: Consumer started 0
Thread-7: Consumer going to released producer 0
Thread-9: producer count 1
Thread-6: Producer started 0
Thread-6: Producer going to released consumer 0
Thread-7: producer count 1
Thread-6: Consumer count 1
Thread-1: Consumer started 0
Thread-1: Consumer going to released producer 1
Thread-1: producer count 2
Final result 0
```

> 2. Print in order

Suppose we have a class:

public class Foo {
public void first() { print("first"); }
public void second() { print("second"); }
public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call
second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed
after first(), and third() is executed after second().

```java
class Foo {

    Semaphore afterFirst;
    Semaphore afterSecond;

    public Foo() {
        afterFirst = new Semaphore(0);
        afterSecond = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        afterFirst.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        afterFirst.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        afterSecond.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        afterSecond.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

    }
}
```

```
Output:
FirstSecondThird
FirstSecondThird
FirstSecondThird
FirstSecondThird
FirstSecondThird
FirstSecondThird
```

> 3. H20

There are two kinds of threads: oxygen and hydrogen. Your goal is to group these threads to form water molecules.

There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads
will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These
threads should pass the barrier in groups of three, and they must immediately bond with each other to form a water
molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule
do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it must wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it must wait for an oxygen thread and
another hydrogen thread.
We do not have to worry about matching the threads up explicitly; the threads do not necessarily know which other
threads they are paired up with. The key is that threads pass the barriers in complete sets; thus, if we examine the
sequence of threads that bind and divide them into groups of three, each group should contain one oxygen and two
hydrogen threads.

```java
class H2O {
    Semaphore h;
    Semaphore o;

    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }
}

```

```
Output:
HHO
HHO
HHO
HHO
HHO
```

> 4. Print FooBar Alternately

Suppose you are given the following code:

```java
class FooBar {
    public void foo() {
        for (int i = 0; i < n; i++) {
            print("foo");
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            print("bar");
        }
    }
}
```

The same instance of FooBar will be passed to two different threads:

thread A will call foo(), while

thread B will call bar().

Modify the given program to output "foobar" n times.

```java
private static class FooBar {
    private int n;
    Semaphore foo;
    Semaphore bar;

    public FooBar(int n) {
        this.n = n;
        foo = new Semaphore(1);
        bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
```

```
Output:
foobar
```

> 5.Print Zero Even Odd


https://leetcode.com/problemset/concurrency/

