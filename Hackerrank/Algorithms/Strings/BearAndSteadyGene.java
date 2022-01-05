package Hackerrank.Algorithms.Strings;

import java.util.*;

public class BearAndSteadyGene
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        HashMap<Character, Integer> hash = new HashMap<>();
        hash.put('A', 0);
        hash.put('C', 0);
        hash.put('G', 0);
        hash.put('T', 0);
        int max = n/4;
        int start=-1, end=-1;
        for(int i=0;i<n;i++)
        {
            if(hash.get(s.charAt(i))==max)
            {
                start = i;
                break;
            }
            hash.put(s.charAt(i), hash.get(s.charAt(i))+1);
        }

        if(start==-1)
        {
            System.out.println(0);
            System.exit(0);
        }

        for(int i=n-1;i>=0;i--)
        {
            if(hash.get(s.charAt(i))==max)
            {
                end = i;
                break;
            }
            hash.put(s.charAt(i), hash.get(s.charAt(i))+1);
        }

        int min = end-start+1;
        while(start>0)
        {
            if(hash.get(s.charAt(end))<max)
            {
                hash.put(s.charAt(end), hash.get(s.charAt(end))+1);
                end--;
            }
            else if(start>0 && hash.get(s.charAt(start-1)) == max && s.charAt(start-1)==s.charAt(end))
            {
                start--;
                end--;
            }
            else
            {
                while(start>0 && s.charAt(start-1)!=s.charAt(end))
                {
                    start--;
                    hash.put(s.charAt(start), hash.get(s.charAt(start))-1);
                }
            }
            min = Math.min(min, end-start+1);
        }

        System.out.println(min);
    }
}