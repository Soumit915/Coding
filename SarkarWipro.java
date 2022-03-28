import java.io.*;
import java.util.*;

public class SarkarWipro {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if((s.charAt(i) - '0')%2==1){
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb);
    }
}
