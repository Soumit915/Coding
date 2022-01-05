package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.util.*;

public class D {
    static class Worker
    {
        int id;
        int ti; //time taken for each balloons
        int zi; //total number of balloons each turn
        int yi; //time taken to rest after each turn
        Worker(int id, int ti, int zi, int yi)
        {
            this.id = id;
            this.ti = ti;
            this.zi = zi;
            this.yi = yi;
        }
    }
    public static boolean isValid(Worker[] workers, int time, int m)
    {
        for (Worker worker : workers) {
            int pt = worker.ti * worker.zi + worker.yi;
            int totprocesscount = time / pt;
            int rem = time % pt;
            int extra = Math.min(rem / worker.ti, worker.zi);

            int totalballoons = totprocesscount * worker.zi + extra;
            if (totalballoons >= m)
                return true;
            else m -= totalballoons;
        }

        return false;
    }
    public static int[] getDist(Worker[] workers, int time, int m)
    {
        int n = workers.length;
        int[] dist = new int[n];
        for (Worker worker : workers) {
            int pt = worker.ti * worker.zi + worker.yi;
            int totprocesscount = time / pt;
            int rem = time % pt;
            int extra = Math.min(rem / worker.ti, worker.zi);

            int totalballoons = totprocesscount * worker.zi + extra;
            if (totalballoons >= m) {
                dist[worker.id] = m;
                return dist;
            }
            else{
                dist[worker.id] = totalballoons;
                m -= totalballoons;
            }
        }

        return dist;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        Worker[] workers = new Worker[n];
        for(int i=0;i<n;i++)
        {
            workers[i] = new Worker(i, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        int ll = 0;
        int ul = (int) 1e9;
        while(ll<ul)
        {
            int mid = ll + (ul-ll)/2;
            if(isValid(workers, mid, m))
            {
                ul = mid;
            }
            else
            {
                ll = mid+1;
            }
        }

        int optimal = ll;
        int[] dist = getDist(workers, optimal, m);
        StringBuilder sb = new StringBuilder();
        sb.append(optimal).append("\n");
        for(int i: dist)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}
