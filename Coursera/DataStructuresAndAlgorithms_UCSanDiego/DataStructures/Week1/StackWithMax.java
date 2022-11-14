package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week1;

import java.util.Scanner;
import java.util.Stack;

public class StackWithMax {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int q = sc.nextInt();

        Stack<Integer> stk = new Stack<>();
        Stack<Integer> garstk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while(q-->0)
        {
            String operation = sc.next();
            if(operation.equals("push"))
            {
                int val = sc.nextInt();
                if(stk.isEmpty())
                {
                    stk.push(val);
                    garstk.push(0);
                }
                else if(stk.peek()>val)
                {
                    int c = garstk.pop();
                    c++;
                    garstk.push(c);
                }
                else {
                    stk.push(val);
                    garstk.push(0);
                }
            }
            else if(operation.equals("pop"))
            {
                if(stk.isEmpty())
                    continue;
                if(garstk.peek()==0)
                {
                    garstk.pop();
                    stk.pop();
                }
                else
                {
                    int c = garstk.pop();
                    c--;
                    garstk.push(c);
                }
            }
            else
            {
                sb.append(stk.peek()).append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }

}
