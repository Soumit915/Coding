package Thread;

import java.text.DecimalFormat;

public class Hacking_ThreadingBulkCalc {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        long start = System.currentTimeMillis();

        for(int i=1;i<=30000000;i++){
            DecimalFormat formatter = new DecimalFormat("#0.0000");
            //System.out.println("[C"+param+"] Number: "+i+" Square Root: "
            //+formatter.format(Math.sqrt(i)));
            //sb.append("Number: "+i+" Square Root: "
                    //+formatter.format(Math.sqrt(i))+"\n");
        }
        long end = System.currentTimeMillis();

        System.out.println(sb);
        System.out.println("Time: "+(end-start));
    }
}
