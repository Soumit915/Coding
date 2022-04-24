package CUrBrain_Questions;

import java.io.*;
import java.util.*;

public class AND {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t--> 0){
            long a = sc.nextLong();
            long b = sc.nextLong();

            sb.append(a&b).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
