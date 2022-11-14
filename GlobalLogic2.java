import java.io.*;
import java.util.*;

public class GlobalLogic2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--)
            sb.append(s.charAt(i));

        System.out.println(sb);
    }
}
