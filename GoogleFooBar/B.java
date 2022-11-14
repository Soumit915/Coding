package GoogleFooBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class B {
    static String pad(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()+1;i<=n;i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
    static String getSorted(String s, int order){
        ArrayList<Character> list = new ArrayList<>();
        for(int i=0;i<s.length();i++)
            list.add(s.charAt(i));

        if(order==1)
            Collections.sort(list);
        else list.sort(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(char ch: list)
            sb.append(ch);
        return sb.toString();
    }
    static String getNext(String s, int b){
        String sx = getSorted(s, -1);
        String sy = getSorted(s, 1);
        int x = Integer.parseInt(sx, b);
        int y = Integer.parseInt(sy, b);
        int z = x - y;
        String sz = Integer.toString(z, b);
        return pad(sz, s.length());
    }
    public static int solution(String n, int b) {
        //Your code here
        HashMap<String, Integer> indexmap = new HashMap<>();
        indexmap.put(n, 1);

        int index = 1;

        while(true){
            n = getNext(n, b);
            if(n.equals("0")){
                return 1;
            }

            if(indexmap.containsKey(n)){
                int start = indexmap.get(n);
                return index - start + 1;
            }
            else{
                index++;
                indexmap.put(n, index);
            }
        }
    }
    public static void main(String[] args){
        String n = "1211";
        int b = 10;
        System.out.println(solution(n, b));
    }
}