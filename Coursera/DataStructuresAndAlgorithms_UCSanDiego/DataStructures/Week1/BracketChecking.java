package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week1;

import java.util.*;

public class BracketChecking {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        Stack<Character> stk = new Stack<>();
        Stack<Integer> stkind = new Stack<>();
        boolean flag = true;
        int ind = -1;
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='[')
            {
                stk.push(ch);
                stkind.push(i);
            }
            else if(ch==')' || ch=='}' || ch==']')
            {
                if(stk.isEmpty())
                {
                    ind = i+1;
                    flag= false;
                    break;
                }
                char top = stk.peek();

                if(ch==')')
                {
                    if(top!='(')
                    {
                        ind = i+1;
                        flag = false;
                        break;
                    }
                }
                else if(ch=='}')
                {
                    if(top!='{')
                    {
                        ind = i+1;
                        flag = false;
                        break;
                    }
                }
                else if(ch==']')
                {
                    if(top!='[')
                    {
                        ind = i+1;
                        flag = false;
                        break;
                    }
                }

                stk.pop();
                stkind.pop();
            }
        }

        if(stk.size()!=0 && flag)
            System.out.println(stkind.pop()+1);
        else if(flag)
            System.out.println("Success");
        else System.out.println(ind);
    }
}
