package designPattern.creational.factory.FileParser;

public class AbstractFactory {

    static void main(String[] args) {

        UiFactory android = new AndroidFactory();
        android.createButtonFactory().createButton();
        android.createCheckboxFactory().createCheckbox();

    }
}

interface Button {
    void createButton();
}

interface Checkbox{
    void createCheckbox();
}


// Android Implementation
class AndroidButton implements Button {
    @Override
    public void createButton() {
        System.out.println("Creating Android Button...");
    }
}
class AndroidCheckbox implements Checkbox {
    @Override
    public void createCheckbox() {
        System.out.println("Creating Addroid Checkbox...");
    }
}

class IosButton implements Button {
    @Override
    public void createButton() {
        System.out.println("Creating IOS Button...");
    }
}

class IosCheckbox implements Checkbox {
    @Override
    public void createCheckbox() {
        System.out.println("Creating IOS Checkbox...");
    }
}

//Abstract Factory

interface UiFactory{
    Button createButtonFactory();
    Checkbox createCheckboxFactory();

}
class AndroidFactory implements UiFactory {
    @Override
    public Button createButtonFactory() {
        return new AndroidButton();
    }

    @Override
    public Checkbox createCheckboxFactory() {
        return new AndroidCheckbox();
    }

}

class IOSFactory implements UiFactory {
    @Override
    public Button createButtonFactory() {
        return new IosButton();
    }
    @Override
    public Checkbox createCheckboxFactory() {
        return new IosCheckbox();
    }
}



