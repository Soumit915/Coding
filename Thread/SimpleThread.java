package Thread;

public class SimpleThread extends Thread{

    SimpleThread(){
        System.out.println("running");
    }
    public static void main(String[] args) {
        SimpleThread st = new SimpleThread();
    }
}
