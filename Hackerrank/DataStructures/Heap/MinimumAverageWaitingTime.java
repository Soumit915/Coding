package Hackerrank.DataStructures.Heap;

import java.util.*;

public class MinimumAverageWaitingTime
{
    static class Jobs implements Comparable<Jobs>
    {
        long arrival;
        long burst;

        Jobs(long arrival, long burst)
        {
            this.arrival = arrival;
            this.burst = burst;
        }
        public int compareTo(Jobs j)
        {
            return Long.compare(this.burst, j.burst);
        }
    }
    static class Sort implements Comparator<Jobs>
    {
        public int compare(Jobs j1, Jobs j2)
        {
            return Long.compare(j1.arrival, j2.arrival);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Jobs[] jobs = new Jobs[n];
        for(int i=0;i<n;i++)
        {
            jobs[i] = new Jobs(sc.nextLong(), sc.nextLong());
        }

        Arrays.sort(jobs, new Sort());

        PriorityQueue<Jobs> minheap = new PriorityQueue<>();

        long time = jobs[0].arrival;
        int i=0;
        long wtime = 0;
        while(i<n)
        {
            while(i<n && jobs[i].arrival<=time)
            {
                minheap.add(jobs[i]);
                i++;
            }

            if(minheap.isEmpty())
            {
                time = jobs[i].arrival;
                continue;
            }

            Jobs j = minheap.remove();
            time += j.burst;
            wtime += (time-j.arrival);
        }

        while(!minheap.isEmpty())
        {
            Jobs j = minheap.remove();
            time += j.burst;
            wtime += (time-j.arrival);
        }

        long awt = wtime / n;
        System.out.println(awt);
    }
}