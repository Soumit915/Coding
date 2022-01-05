import java.io.*;
import java.util.*;

public class Virtusa {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] arr = new String[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.next();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for(int i=1;i<n;i++){
            sb.append(arr[i].charAt(2));
        }

        System.out.println(sb);
    }
}
