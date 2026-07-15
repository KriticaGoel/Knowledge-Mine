## Agenda

1. Print 1 to N
2. Adder


1. Print 1 to N

```
Write code to achieve the following
A class Client with a main method.
Create a class ArrayCreator which takes as input a number (n)
ArrayCreator should create an ArrayList which should contain numbers from 1 to n
ArrayCreator should implement appropriate Callable interface and return the arraylist discussed above to calling thread
Client class should invoke ArrayCreator over a new thread and get the arraylist from ArrayCreator class and print it.
```

```java
 public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newCachedThreadPool();
        Future<ArrayList<Integer>> result =es.submit(new ArrayCreator(5));
        System.out.println(result.get());
        es.shutdown();

    }
```

```java
public class ArrayCreator implements Callable<ArrayList<Integer>> {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int n;
    ArrayCreator(int n){
        this.n=n;
    }

    @Override
    public ArrayList<Integer> call() throws Exception {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=1; i<=n;i++){
            arr.add(i);
        }
        return arr;

    }
}
```

2. Adder

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

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> result = es.submit(new Adder(10, 20));//Non-Blocking
        System.out.println(result.get());//Blocking, wait till you don't get an output
        es.shutdown();
    }
```