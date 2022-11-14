package FacebookHackercup.Round1_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class A_Perimetric1 {
    static class Room implements Comparable<Room>
    {
        int id;
        long l;
        long h;
        Room(int id, long l, long h)
        {
            this.id = id;
            this.l = l;
            this.h = h;
        }
        public int compareTo(Room r)
        {
            return Long.compare(this.h, r.h);
        }
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                int k = sc.nextInt();
                long w = sc.nextLong();

                long[] l = new long[n];
                Room[] room = new Room[n];
                for(int i=0;i<k;i++)
                {
                    l[i] = sc.nextLong();
                }
                long Al = sc.nextLong();
                long Bl = sc.nextLong();
                long Cl = sc.nextLong();
                long Dl = sc.nextLong();
                for(int i=k;i<n;i++)
                {
                    l[i] = ((Al*l[i-2]+Bl*l[i-1]+Cl)%Dl)+1;
                }

                long[] h = new long[n];
                for(int i=0;i<k;i++)
                {
                    h[i] = sc.nextLong();
                    room[i] = new Room(i, l[i], h[i]);
                }
                long Ah = sc.nextLong();
                long Bh = sc.nextLong();
                long Ch = sc.nextLong();
                long Dh = sc.nextLong();
                for(int i=k;i<n;i++)
                {
                    h[i] = ((Ah*h[i-2]+Bh*h[i-1]+Ch)%Dh)+1;
                    room[i] = new Room(i, l[i], h[i]);
                }

                long[] p = new long[n];
                PriorityQueue<Room> maxheap = new PriorityQueue<>(Collections.reverseOrder());
                long mod = (long) 1e9+7;
                maxheap.add(room[0]);
                p[0] = (2*w+2*h[0])%mod;
                for(int i=1;i<n;i++)
                {
                    if(l[i-1]+w<l[i])
                    {
                        p[i] = (p[i-1]+(2*w+2*h[i])%mod)%mod;
                    }
                    else
                    {
                        Room gh = maxheap.peek();
                        while (gh.l+w<l[i])
                        {
                            maxheap.remove();
                            gh = maxheap.peek();
                        }
                        p[i] = (p[i-1]+(2*(l[i]-l[i-1])))%mod;
                        if(gh.h<=h[i])
                        {
                            p[i] = (p[i]+2*(h[i]-gh.h))%mod;
                        }
                    }
                    maxheap.add(room[i]);
                }

                long product = 1;
                for(int i=0;i<n;i++)
                {
                    product = (product*p[i])%mod;
                }

                pw.println("Case #"+testi+": "+product);
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}