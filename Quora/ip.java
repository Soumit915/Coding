package Quora;

import java.util.*;

public class ip {

    static String getBitTillClassC(String s){

        int n = 0;
        List<String> list = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                list.add(s.substring(n, i));
                n = i + 1;
            }
        }

        return list.get(0)+"."+list.get(1)+"."+list.get(2)+".";
    }

    static String getClassD(String s){
        Scanner sc = new Scanner(s);
        sc.useDelimiter("/");

        String ipv4 = sc.next();

        int n = 0;
        List<String> list = new ArrayList<>();
        for(int i=0;i<ipv4.length();i++){
            if(ipv4.charAt(i)=='.'){
                list.add(ipv4.substring(n, i));
                n = i + 1;
            }
        }
        list.add(ipv4.substring(n));

        int classD = Integer.parseInt(list.get(3));

        if(classD > 127)
            return "128";
        else return "0";
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Map<String, HashSet<String>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            String s = sc.next() + sc.nextLine();
            String bin = getBitTillClassC(s);
            String bitD = getClassD(s);

            HashSet<String> set = map.getOrDefault(bin, new HashSet<>());
            set.add(bitD);
            map.put(bin, set);
        }

        int[] arr = new int[n];

        StringBuilder sb = new StringBuilder();
        for(String s: map.keySet()){
            HashSet<String> set = map.get(s);
            for(String classD: set){
                String min = s+""+classD+"/25";
                sb.append(min).append("\n");
            }
        }

        System.out.println(sb);
    }
}
