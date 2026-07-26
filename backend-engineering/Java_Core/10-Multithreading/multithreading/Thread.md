# Threads and Multithreading

- [Threads and Multithreading](#threads-and-multithreading)
    - [Threads](#threads)
        - [Thread vs Process](#thread-vs-process)
        - [Concurrency vs Parallelism](#concurrency-vs-parallelism)
        - [Context Switching](#context-switching)
        - [Implementing Runnable Interface](#1-implementing-runnable-interface)
        - [Extending Thread Class](#2-extending-thread-class)
        - [Thread Interruption](#3-thread-interruption)
        - [Daemon Threads](#4-daemon-threads)
        - [Join Thread / Thread Coordination](#5join-thread--thread-coordination)

## Threads

> A thread is a lightweight process. It is a unit of execution within a process. A process can have multiple threads.
> Each thread has its own program counter, stack, and registers. Threads share the same address space. This means that all
> threads in a process can access the same memory. This is different from processes where each process has its own address
> space.

Often, a process needs to perform multiple tasks at the same time. For example, a web browser needs to download a file
and display a web page at the same time. Creating a new process for each task is expensive. This is because creating a
new process requires a lot of Theory.resources.

Threads are used to solve this problem. Threads are used to perform multiple tasks within a process. This is done by
sharing the same address space. This means that all threads in a process can access the same memory. This is different
from processes where each process has its own address space.

Thread is a sequential flow of tasks within a process. Threads in OS can be of the same or different types. Threads are
used to increase the performance of the applications.
Each thread has its own program counter, stack, and set of registers. But the threads of a single process might share
the same code and data/file. Threads are also termed as lightweight processes as they share common Theory.resources.

![Thread.PNG](..%2F..%2F..%2Fresources%2FThread.PNG)
### Thread vs Process

| Process                                                                                 | Thread                                                                                                     |
|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| Processes use more Theory.resources and hence they are termed as heavyweight processes. | Threads share Theory.resources and hence they are termed as lightweight processes.                         |
| Creation and termination times of processes are slower.                                 | Creation and termination times of threads are faster compared to processes.                                |
| Processes have their own code and data/file.                                            | Threads share code and data/file within a process.                                                         |
| Communication between processes is slower.                                              | Communication between threads is faster.                                                                   |
| Context Switching in processes is slower.                                               | Context switching in threads is faster.                                                                    |
| Processes are independent of each other.                                                | Threads, on the other hand, are interdependent. (i.e they can read, write or change another thread’s data) |
| Eg: Opening two different browsers.                                                     | Eg: Opening two tabs in the same browser.                                                                  

![threadProcess.PNG](..%2F..%2F..%2Fresources%2FthreadProcess.PNG)
### Concurrency vs Parallelism

* Concurrent - At the same time, **but not necessarily at the same instant**. It is possible for multiple threads to be
  at different stages of execution at the same time **but not being processed together**. A single core CPU can only
  execute one thread at a time. But it can switch between threads very quickly. This is called context switching. This
  is how concurrency is achieved. A single core CPU can have concurrency but not parallelism.
* Parallel - At the same time and **at the same instant**. It is possible for multiple threads to be at different stages
  of execution at the same time and **being processed together**. A single core CPU cannot achieve parallelism. It can
  only achieve concurrency. A multi-core CPU can achieve both concurrency and parallelism.

###### [Home](#threads-and-multithreading)

### Context Switching

**Definition**: Context switching refers to the process of storing the state of a currently running thread or process,
so that it can be resumed later, and restoring the state of the next thread or process to run.

**Cost Implications**: It is generally more expensive to switch between threads of different processes (inter-process
switching) than between threads of the same process (intra-process switching). This is because switching between
different processes requires additional overhead, such as switching memory contexts.

## Multithreading Implementation

### 1. Implementing Runnable Interface

```java
public class MyRunnable implements Runnable {
 public void run() {
     // Code to be executed by this thread
 }
}
```

```java
Thread thread2 = new Thread(new Runnable() {
    public void run() {
        System.out.println("Third thread");
    }
});
thread2.start();
```

### 2. Extending Thread Class

```java
public class MyThread extends Thread {
    public void run() {
        // Code to be executed by this thread
    }
}
```

**Case Study**

You can create a simple simulation of a system with a username and password, where a hacker thread attempts to break it,
and simultaneously, a police thread tries to arrive within 10 seconds.

```java
public class SecuritySystem {
    private static String correctUsername = "admin";
    private static String correctPassword = "password";
    private volatile boolean hacked = false;

    public static void main(String[] args) {
        SecuritySystem system = new SecuritySystem();
        Thread hacker = new Thread(system.new Hacker());
        Thread police = new Thread(system.new Police());

        hacker.start();
        police.start();
    }

    class Hacker implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 100 && !hacked; i++) {
                    // Simulate guessing username/password
                    Thread.sleep(100); // Simulating time taken to guess
                    if (guessCredentials()) {
                        hacked = true;
                        System.out.println("Hacker broke the system!");
                    }
                }
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }

        private boolean guessCredentials() {
            // Simulation logic to guess credentials; returns true if successful
            return Math.random() < 0.01; // 1% chance of success
        }
    }

    class Police implements Runnable {
        public void run() {
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("Police arriving in " + i + " seconds...");
                    Thread.sleep(1000); // Sleep for 1 second
                }
                if (!hacked) {
                    System.out.println("Police arrived! System protected successfully.");
                }
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
    }
}
```

### 3. Thread Interruption

> To interrupt a thread (e.g., to stop a long-running task), you can use the
> interrupt() method. The interrupted thread can check its interrupted status using

To check whether anyone called thread to interrupt return boolean

```java
Thread.currentThread().

isInterrupted()
```

### 4. Daemon Threads

> Daemon threads are background threads that don't prevent the JVM from exiting when the main thread terminates. This
> makes them ideal for tasks that should run in the background without blocking the application from closing.

```java
Thread daemonThread = new Thread(new MyRunnable());
daemonThread.

setDaemon(true);
daemonThread.

start();
```

#### When to use Damon Thread

1. **Background Tasks**: Such as file-saving operations in a text editor, where you want to save user data without
   interrupting the user experience.
2. **Third-party Libraries**: When using external libraries that spawn threads, you may want these to run in the
   background without blocking your application.

```java
class BackgroundTask extends Thread {
    public void run() {
        // Background operation
        while (true) {
            System.out.println("Running in background...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

public class DaemonExample {
    public static void main(String[] args) {
        BackgroundTask task = new BackgroundTask();
        task.setDaemon(true); // Set as daemon thread
        task.start();

        // Main thread logic
        System.out.println("Main thread is finishing.");
    }
}
```

**Problems** :
This approach can introduce a race condition since the start method begins the thread while the finished method may try
to print results prematurely.

**Solution** :
Using join(): By calling thread.join(), you ensure the main thread waits for the worker thread to finish. However, it
can lead to potential issues if the worker takes too long.

### 5.Join Thread / Thread Coordination

> Using join() allows you to wait for a thread to complete its execution before proceeding. This is essential for
> managing dependencies between threads.

**Implementation Steps**

1. Print Results in a Worker Thread: A thread can perform a task, like calculating a factorial.
2. Access Thread Variables: You can retrieve results from a thread instance by storing references
3. Race Conditions: If the main thread tries to access results before the worker thread finishes, it may lead to
   inconsistencies.
4. Implement an isFinished method to check and print values once finished.

**Problem with join**
If one thread takes longer than expected, the main thread will wait indefinitely.

**Solution**
Timeout with join(): You can specify a timeout to avoid indefinite waiting.

```java
class FactorialThread extends Thread {
    private int number;
    private long result;

    public FactorialThread(int number) {
        this.number = number;
    }

    public void run() {
        result = factorial(number);
    }

    public long getResult() {
        return result;
    }

    public boolean isFinished() {
        return this.getState() == State.TERMINATED;
    }

    private long factorial(int n) {
        if (n == 0) return 1;
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}

public class ThreadCoordinationExample {
    public static void main(String[] args) {
        FactorialThread factorialThread = new FactorialThread(5);
        factorialThread.start();

        try {
            factorialThread.join(2000); // Wait up to 2000 ms for the thread to finish
            if (factorialThread.isFinished()) {
                System.out.println("Factorial: " + factorialThread.getResult());
            } else {
                System.out.println("Thread took too long to finish.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

###### [Home](#threads-and-multithreading)
