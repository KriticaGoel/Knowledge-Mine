package designPattern.behaviour.strategy.approaches;

import java.math.BigDecimal;

@FunctionalInterface
interface SumFunction<T,U,R>{
    R apply(T t, U u);
}

//class to implement interface  - OVER ENGINEERING
class IntegerSum implements SumFunction<Integer,Integer,Integer>{

    @Override
    public Integer apply(Integer a, Integer b){
        return a+b;
    }
}

//Implement using Lambda
public class SimpleLambda {
    static void main(String[] args) {

        SumFunction<Number,Number, Double> sumNumber =(a,b) ->a.doubleValue() + b.doubleValue();

        System.out.println(sumNumber.apply(3,4));
        System.out.println(sumNumber.apply(3.5,4.5));
        //System.out.println(sumNumber.apply("Hello","World"); // not work since string is not a number class
        System.out.println(sumNumber.apply(new BigDecimal("3.5"), new BigDecimal("4.5")));


        // Generic SumOperation with different types (String concatenation)
        SumFunction<String,String,String> sumString1= (a,b) -> a.concat(b);
        System.out.printf("Sum of string is %s \n",sumString1.apply("Hello ", "World"));



        // Generic SumOperation with Integer
        SumFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.printf("Sum of integer %s and %s is %s \n ",1,2,sum.apply(1,2));

        // Generic SumOperation with Double
        SumFunction<Double, Double, Double> sumDouble = (a, b) -> a+ b;
        System.out.printf("Sum of double %s and %s is %s \n ",1.5,2.5,sumDouble.apply(1.5, 2.5));

        // Generic SumOperation with BigDecimal
        SumFunction<BigDecimal, BigDecimal, BigDecimal> sumBigDecimal = (a, b) -> a.add(b);
        System.out.println("Sum of BigDecimal is " + sumBigDecimal.apply(new BigDecimal("10.5"), new BigDecimal("20.3")));

        // Generic SumOperation with different types (String concatenation)
        SumFunction<String,String,String> sumString= (a,b) -> a.concat(b);
        System.out.printf("Sum of string is %s \n",sumString.apply("Hello ", "World"));

    }
}






