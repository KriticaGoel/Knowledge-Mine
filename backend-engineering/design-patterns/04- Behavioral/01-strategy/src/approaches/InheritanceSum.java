package designPattern.behaviour.strategy.approaches;

public class InheritanceSum {

    static void main(String[] args) {
        BinarySum cal = new IntegerSums();
        System.out.println(cal.sum(1,2));

        BinarySum cal2 = new DoubleSums();
        System.out.println(cal2.sum(1.5,2.5));
    }
}

 class BinarySum{

    Object sum(Object a, Object b){
        return a;
    }

}

class IntegerSums extends BinarySum{
    @Override
    Object sum(Object a, Object b){
        if(a instanceof Integer && b instanceof Integer){
            return (Integer)a + (Integer)b;
        }
        throw new IllegalArgumentException("Invalid input type");
    }
}

class DoubleSums extends BinarySum{
    @Override
    Object sum(Object a, Object b){
        if(a instanceof Double && b instanceof Double){
            return (Double)a + (Double)b;
        }
        throw new IllegalArgumentException("Invalid input type");
    }
}