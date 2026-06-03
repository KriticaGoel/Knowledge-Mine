package designPattern.behaviour.strategy.approaches;

public class Strategy {

    static void main(String[] args) {
        Context context = new Context(new SumOperation());
        System.out.println(context.apply(5, 10)); // Output: 15

        context = new Context(new SubstractionOperation());
        System.out.println(context.apply(5, 10)); // Output: -5

        context = new Context(new ProductOperation());
        System.out.println(context.apply(5, 10)); // Output: 50
    }
}

//1. Interface (Strategy)

//2. Implementation class ( define the behaviour)

//3. Context ( Calling Strategy at run time)

interface Operation<T,U,R>{
    R perform(T t, U u);
}

class SumOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a + b;
    }
}

class SumDoubleOperation implements Operation<Double, Double, Double> {
    @Override
    public Double perform(Double a, Double b) {
        return a + b;
    }
}

class SubstractionOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a-b;
    }
}

class ProductOperation implements Operation<Integer, Integer, Integer> {
    @Override
    public Integer perform(Integer a, Integer b) {
        return a*b;
    }
}

class Context{

    private Operation <Integer, Integer, Integer> operation;
    public Context(Operation<Integer, Integer, Integer> operation){
        this.operation = operation;
    }

    public Integer apply(Integer a, Integer b){
        return operation.perform(a,b);
    }

}
