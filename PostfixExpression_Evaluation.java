import java.io.*;
import java.util.*;

public class PostfixExpression_Evaluation {
    public static ArrayList<String> getInput(String s)
    {
        s += " ";
        int n = s.length();
        int last = 0;
        ArrayList<String> arlist = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            char ch = s.charAt(i);
            if(ch==' ')
            {
                String str = s.substring(last, i);
                arlist.add(str);
                last = i+1;
            }
        }

        return arlist;
    }
    public static boolean isSymbol(String ch)
    {
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        ArrayList<String> exp = getInput(input);

        Stack<Integer> stk = new Stack<>();

        for (String s : exp) {
            if (isSymbol(s)) {
                int a = stk.pop();
                if (stk.isEmpty()) {
                    stk.push(a * -1);
                    continue;
                }
                int b = stk.pop();

                switch (s) {
                    case "+":
                        stk.push(b + a);
                        break;
                    case "-":
                        stk.push(b - a);
                        break;
                    case "*":
                        stk.push(b * a);
                        break;
                    case "/":
                        stk.push(b / a);
                        break;
                }
            } else {
                int v = Integer.parseInt(s);
                stk.push(v);
            }
        }

        System.out.println(stk.pop());

        sc.close();
    }

}
