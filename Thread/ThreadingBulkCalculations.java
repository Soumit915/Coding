package Thread;

import java.text.DecimalFormat;

public class ThreadingBulkCalculations {
    static StringBuilder sb = new StringBuilder();
    static class LocalThread extends Thread{
        int counter;    //Initialising the thread process with a global member
        LocalThread(String name){
            super(name);
            this.counter = 1;
        }
        //Thread function to be executed
        public void run(){
            int param = this.counter;
            this.counter++;
            //System.out.println("Starting C"+param);
            //sb.append("Starting C"+param+"\n");

            for(int i=param;i<=30000000;i+=3){
                DecimalFormat formatter = new DecimalFormat("#0.0000");
                //System.out.println("[C"+param+"] Number: "+i+" Square Root: "
                        //+formatter.format(Math.sqrt(i)));
                //sb.append("[C"+param+"] Number: "+i+" Square Root: "
                       // +formatter.format(Math.sqrt(i))+"\n");
            }
            //sb.append("Closing C"+param+"\n");
            //System.out.println("Closing C"+param);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("Starting main thread");

        long start = System.currentTimeMillis();

        //sb.append("Starting main thread\n");
        LocalThread[] threads = new LocalThread[3];
        for(int i=1;i<=3;i++) {
            LocalThread lt = new LocalThread("C"+i);
            lt.start(); //Starting the thread
            threads[i-1] = lt;
        }

        for (LocalThread thread : threads) thread.join();

        //System.out.println("Closing main thread");
        //sb.append("Closing main thread\n");

        long end = System.currentTimeMillis();

        System.out.println(sb);
        System.out.println("Time: "+(end-start));
    }
}
