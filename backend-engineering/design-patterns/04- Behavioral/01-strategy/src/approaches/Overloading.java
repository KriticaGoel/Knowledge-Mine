package designPattern.behaviour.strategy.approaches;

import java.math.BigDecimal;

public class Overloading {
    static void main(String[] args) {
        SumUtils sumUtils = new SumUtils();
        System.out.println(sumUtils.sum(10, 20));
        System.out.println(sumUtils.sum(10.5, 20.5));
        System.out.println(sumUtils.sum(10, 20.5));
        System.out.println(sumUtils.sum(new BigDecimal("10.5"), new BigDecimal("20.5")));

    }
}

//Overloading → Compile time → Static binding
//Overriding → Runtime → Dynamic binding

class SumUtils{

    int sum(int a, int b){
        return a+b;
    }

    double sum(double a, double b){
        return a+b;
    }

    double sum(int a, double b){
        return a+b;
    }

    BigDecimal sum(BigDecimal a, BigDecimal b){
        return a.add(b);
    }
}
