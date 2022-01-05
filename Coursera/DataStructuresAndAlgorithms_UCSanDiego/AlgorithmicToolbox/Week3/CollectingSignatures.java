package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week3;

import java.util.*;

public class CollectingSignatures {
    static class StartPoint implements Comparable<StartPoint>
    {
        int id;
        int start;
        StartPoint(int id, int start)
        {
            this.id = id;
            this.start = start;
        }
        public int compareTo(StartPoint s)
        {
            return Integer.compare(this.start, s.start);
        }
    }
    static class EndPoint implements Comparable<EndPoint>
    {
        int id;
        int end;
        EndPoint(int id, int end)
        {
            this.id = id;
            this.end = end;
        }
        public int compareTo(EndPoint e)
        {
            return Integer.compare(this.end, e.end);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<StartPoint> start = new PriorityQueue<>();
        PriorityQueue<EndPoint> end = new PriorityQueue<>();
        for(int i=0;i<n;i++)
        {
            start.add(new StartPoint(i, sc.nextInt()));
            end.add(new EndPoint(i, sc.nextInt()));
        }

        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] isPresent = new boolean[n];
        boolean[] arr = new boolean[n];
        while(!start.isEmpty())
        {
            StartPoint sp = start.peek();
            EndPoint ep = end.peek();

            if(sp.start<=ep.end)
            {
                isPresent[sp.id] = true;
                start.remove();
            }
            else
            {
                //System.out.println("test");
                if(!arr[ep.id])
                {
                    for(int j=0;j<n;j++)
                    {
                        if(isPresent[j])
                            arr[j] = true;
                    }
                    ans.add(ep.end);
                }
                isPresent[ep.id] = false;
                end.remove();
            }
        }

        while(!end.isEmpty())
        {
            EndPoint ep = end.remove();
            if(!arr[ep.id])
            {
                for(int j=0;j<n;j++)
                {
                    if(isPresent[j])
                        arr[j] = true;
                }
                ans.add(ep.end);
            }
            isPresent[ep.id] = false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for(int i: ans)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}
