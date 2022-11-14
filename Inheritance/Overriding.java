package Inheritance;

import java.io.IOException;

class Overriding {
    static int var = 20;
    public void display()
    {
        System.out.println("This is superclass");
    }
}
class Subclass extends Overriding{
    static int var = 30;
    public void display()
    {
        System.out.println("This is subclass");
        super.display();
        System.out.println("The value of var is superclass is : "+super.var);
    }
    public static void main(String args[]) throws IOException
    {
        Subclass sbob = new Subclass();
        sbob.display();
        //super.display();
        System.out.println("The value of var is : "+var);
    }
}
