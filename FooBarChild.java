
interface FooInterface{
    default public void defaultMethod(){
        System.out.println("Foo");
    }
}

interface BarInterface{
    default public void defaultMethod(){
        System.out.println("Bar");
    }
}

public class FooBarChild implements FooInterface, BarInterface {
    public static void main(String[] args) {

    }

    public void defaultMethod(){
        System.out.println("kaka");
    }
}