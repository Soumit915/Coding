package Hackerrank.Algorithms.Searching;

import java.util.*;

public class GridLandMetro {
    static class Track implements Comparable<Track>
    {
        int start;
        int end;
        Track(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Track t)
        {
            if(this.start == t.start)
                return Integer.compare(this.end, t.end);
            else
                return Integer.compare(this.start, t.start);
        }
    }
    static long gridlandMetro(long n, long m, int k, int[][] track) {

        Map<Integer, ArrayList<Track>> hash = new HashMap<>();
        for(int i=0;i<k;i++)
        {
            ArrayList<Track> tlist;
            if(hash.containsKey(track[i][0]))
            {
                tlist = hash.get(track[i][0]);
            }
            else
            {
                tlist = new ArrayList<>();
            }
            tlist.add(new Track(track[i][1], track[i][2]));
            hash.put(track[i][0], tlist);
        }

        Set<Integer> keyset = hash.keySet();
        long totaltrackcells = 0;
        for(int key: keyset)
        {
            ArrayList<Track> tracks = hash.get(key);
            Collections.sort(tracks);
            int start = tracks.get(0).start;
            int end = tracks.get(0).end;
            int sum = 0;
            for(int i=1;i<tracks.size();i++)
            {
                Track t = tracks.get(i);
                if(t.start>end)
                {
                    sum += end-start+1;
                    start = t.start;
                }
                end = t.end;
            }
            sum += end-start+1;
            totaltrackcells += sum;
        }

        long area = n;
        area = area*m;
        return area-totaltrackcells;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] tracks = new int[k][3];
        for(int i=0;i<k;i++)
        {
            tracks[i][0] = sc.nextInt();
            tracks[i][1] = sc.nextInt();
            tracks[i][2] = sc.nextInt();
        }

        System.out.println(gridlandMetro(n, m, k, tracks));
    }
}
