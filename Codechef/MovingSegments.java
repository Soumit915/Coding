package Codechef;

import java.io.*;
import java.util.*;

public class MovingSegments {
    static class Line implements Comparable<Line>
    {
        int l;
        int r;
        Line(int l, int r)
        {
            this.l = l;
            this.r = r;
        }
        public boolean doesOverlap(Line line)
        {
            return this.r >= line.l;
        }
        public int compareTo(Line p)
        {
            return Integer.compare(this.l, p.l);
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();

            HashMap<Integer, ArrayList<Line>> hash = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                int l = sc.nextInt();
                int r = sc.nextInt();
                int v = sc.nextInt();
                ArrayList<Line> arr;
                if(hash.containsKey(v))
                {
                    arr = hash.get(v);
                }
                else
                {
                    arr = new ArrayList<>();
                }
                arr.add(new Line(l,r));
                hash.put(v,arr);
            }

            Set<Integer> keyset = hash.keySet();
            boolean flag = true;
            for(int v: keyset)
            {
                ArrayList<Line> arr = hash.get(v);
                Collections.sort(arr);
                HashSet<Line> set1 = new HashSet<>();
                HashSet<Line> set2 = new HashSet<>();

                for(int i=0;i<arr.size();i++)
                {
                    Line line1 = arr.get(i);
                    HashSet<Line> setp = null;
                    if(set1.contains(line1))
                        setp = set1;
                    else if(set2.contains(line1))
                        setp = set2;
                    for(int j=i+1;j<arr.size();j++)
                    {
                        Line line2 = arr.get(j);
                        if(line1.doesOverlap(line2))
                        {
                            if(setp == null)
                            {
                                setp = set1;
                                set1.add(line1);
                                set2.add(line2);
                            }
                            else if(setp == set1)
                            {
                                if(set1.contains(line2))
                                {
                                    flag = false;
                                    break;
                                }
                                else
                                {
                                    set2.add(line2);
                                }
                            }
                            else
                            {
                                if(set2.contains(line2))
                                {
                                    flag = false;
                                    break;
                                }
                                else
                                {
                                    set1.add(line2);
                                }
                            }
                        }
                        else
                        {
                            set1.add(line1);
                        }
                    }
                    if(!flag)
                        break;
                }
                if(!flag)
                    break;
            }
            if(flag)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
