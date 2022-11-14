package Codechef;

import java.util.*;

public class ChefGotRecipes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        HashMap<Character, Integer> hash = new HashMap<>();
        hash.put('a',0);
        hash.put('e',1);
        hash.put('i',2);
        hash.put('o',3);
        hash.put('u',4);

        while (t-->0)
        {
            int n = sc.nextInt();
            ArrayList<Byte> bitvalue = new ArrayList<>();

            for(int i=0;i<n;i++)
            {
                String s = sc.next();
                BitSet bit = new BitSet(5);
                for(int j=0;j<s.length();j++)
                {
                    bit.set(hash.get(s.charAt(j)));
                }

                bitvalue.add(bit.toByteArray()[0]);
            }

            HashSet<Byte> set = new HashSet<>(bitvalue);

            long count=0;
            long c,no;
            for(byte i=1;i<31;i++)
            {
                c=0;no=0;
                if(!set.contains(i))
                    continue;
                for(int j=0;j<n;j++)
                {
                    if(i<bitvalue.get(j) && (i|bitvalue.get(j))==31)
                        c++;
                    else if(i==bitvalue.get(j))
                        no++;
                }
                count += (no*c);
            }

            c=0;
            for(int i=0;i<n;i++)
            {
                if(bitvalue.get(i)==31)
                {
                    c++;
                }
            }
            count += ((c*(c-1))/2);

            System.out.println(count);
        }
    }
}
