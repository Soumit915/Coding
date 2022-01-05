package Codechef;

import java.util.*;

public class Password {
    static boolean hasCapital(String s){
        int n = s.length();
        for(int i=1;i<n-1;i++){
            if(Character.isUpperCase(s.charAt(i)))
                return true;
        }
        return false;
    }
    static boolean hasLowerCase(String s){
        int n = s.length();
        for(int i=0;i<n;i++){
            if(Character.isLowerCase(s.charAt(i)))
                return true;
        }
        return false;
    }
    static boolean hasDigit(String s){
        int n = s.length();
        for(int i=1;i<n-1;i++){
            if(Character.isDigit(s.charAt(i)))
                return true;
        }
        return false;
    }
    static boolean hasSpecialCharacter(String s){
        int n = s.length();
        char[] arr = { '@', '#', '%', '&', '?' };
        Set<Character> set = new HashSet<>();
        for(char ch: arr)
            set.add(ch);
        for(int i=1;i<n-1;i++){
            if(set.contains(s.charAt(i)))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0) {
            String s = sc.next();

            boolean flag = true;

            int n = s.length();
            if(n>=10 && hasCapital(s) && hasDigit(s) && hasSpecialCharacter(s) && hasLowerCase(s))
                sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
