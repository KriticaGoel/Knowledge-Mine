package designPattern.structural.decorator;

public class IcecreamDecorator {

    static void main(String[] args) {

        IceCream ic = new ButterScotchScoop (new OrangeCone( new ChocolateSyrup( new OrangeCone())));
        System.out.println(ic.getCost());
        System.out.println(ic.getDescription());
    }
}

interface IceCream {
    Double getCost();
    String getDescription();
}


class OrangeCone implements IceCream {

    private IceCream icecream;
    public OrangeCone() {}
    public OrangeCone(IceCream icecream) {
        this.icecream = icecream;
    }
    @Override
    public Double getCost() {
        if (icecream!= null && icecream.getCost() != null) {
            return icecream.getCost()+10.00;
        }
        return 10.00;
    }

    @Override
    public String getDescription() {
        if (icecream!= null && icecream.getDescription() != null) {
            return icecream.getDescription()+ " Orange Cone";
        }
        return "Orange Cone";
    }
}

class ChocolateSyrup implements IceCream {

    private final IceCream icecream;

    public ChocolateSyrup(IceCream icecream) {
        this.icecream = icecream;
    }
    @Override
    public Double getCost() {
        return icecream.getCost() +20.00;
    }

    @Override
    public String getDescription() {
        return  icecream.getDescription() +" Chocolate Syrup";
    }
}

class ButterScotchScoop implements IceCream {
    private final IceCream icecream;
    public ButterScotchScoop(IceCream icecream) {
        this.icecream = icecream;
    }
    @Override
    public Double getCost() {
        return icecream.getCost() + 30.00;
    }
    @Override
    public String getDescription() {
        return icecream.getDescription() + " Butter Scotch Scoop";
    }
}