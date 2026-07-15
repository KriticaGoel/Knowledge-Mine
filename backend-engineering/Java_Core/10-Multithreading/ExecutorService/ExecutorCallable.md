## Agenda

- [Executor](#executor)
- [ExecutorService](#executorService)
- [Callable and Future](#callable-and-future)

### Executor

> The Executor interface is used to execute tasks. It is a generic interface that can be used to execute any kind of
> task.

1. Manage the threads and make the thread reusable.
2. Decide how to run multithreading/task efficiently.
3. To create or manage thread is reusable.

### ExecutorService:

```java
public class NumberPrint extends Thread {
    private int number;

    public NumberPrint(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println(number);
    }
}
```

**newFixedThreadPool** -Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded
queue. At any point, at most nThreads threads will be active processing tasks. If additional tasks are submitted when
all threads are active, they will wait in the queue until a thread is available. If any thread terminates due to a
failure during execution prior to shutdown, a new one will take its place if needed to execute subsequent tasks. The
threads in the pool will exist until it is explicitly shutdown.

```java
public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    for (int i = 1; i < 100; i++) {
        es.submit(new NumberPrint(i));
    }
    es.shutdown();
}
```

**newCachedThreadPool** - Creates a thread pool that creates new threads as needed, but will reuse previously
constructed threads when they are available. These pools will typically improve the performance of programs that execute
many short-lived asynchronous tasks. If no existing thread is available, a new thread will be created and added to the
pool. Threads that have not been used for sixty seconds are terminated and removed from the cache.

```java
 public static void main(String[] args) {
    ExecutorService es = Executors.newCachedThreadPool();
    for (int i = 1; i < 100; i++) {
        es.submit(new NumberPrint(i));
    }
    es.shutdown();
}
```

### Callable and Future

> Runnable do not return a result. If we want to execute a task that returns a result, we can use the Callable
> interface. The Callable interface is a functional interface that has only one method:

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

```java
 public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(10);
    Future<Integer> result = es.submit(new Adder(10, 20));//Non-Blocking
    System.out.println(result.get());//Blocking, wait till you don't get an output
    es.shutdown();
}
```

```java
public class Adder implements Callable<Integer> {
    int i;
    int j;

    public Adder(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Integer call() throws Exception {
        return i + j;
    }
}
```



