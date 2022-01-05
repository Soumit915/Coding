import java.io.*;
import java.util.*;

public class Epam2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        StringBuilder s = new StringBuilder();
        Stack<Integer> opsstk = new Stack<>();
        Stack<String> constk = new Stack<>();
        for(int i=0;i<n;i++){
            int type = sc.nextInt();
            if(type==1){
                String t = sc.next();
                s.append(t);

                opsstk.push(1);
                constk.push(t);
            }
            else if(type==2){
                int p = sc.nextInt();

                opsstk.push(2);
                constk.push(s.substring(s.length()-p));

                s = new StringBuilder(s.substring(0, s.length() - p));
            }
            else if(type==3){
                int p = sc.nextInt();
                System.out.println(s.charAt(p-1));
            }
            else{
                int lastops = opsstk.pop();
                String laststr = constk.pop();

                if(lastops==1){
                    s = new StringBuilder(s.substring(0, s.length()-laststr.length()));
                }
                else{
                    s.append(laststr);
                }
            }
        }

        sc.close();
    }
}
