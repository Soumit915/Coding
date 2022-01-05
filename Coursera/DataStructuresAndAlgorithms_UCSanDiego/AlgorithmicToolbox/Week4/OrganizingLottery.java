package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week4;

import java.util.*;
import java.io.*;

public class OrganizingLottery {
    static class Start implements Comparable<Start>
    {
        int id;
        int point;
        Start(int id, int point)
        {
            this.id = id;
            this.point = point;
        }
        public int compareTo(Start s)
        {
            return Integer.compare(this.point, s.point);
        }
    }
    static class End implements Comparable<End>
    {
        int id;
        int point;
        End(int id, int point)
        {
            this.id = id;
            this.point = point;
        }
        public int compareTo(End e)
        {
            return Integer.compare(this.point, e.point);
        }
    }
    static class Point implements Comparable<Point>
    {
        int id;
        int point;
        Point(int id, int point)
        {
            this.id = id;
            this.point = point;
        }
        public int compareTo(Point p)
        {
            return Integer.compare(this.point, p.point);
        }
    }
    public static void main(String[] args) throws  IOException
    {
        Reader sc = new Reader();
        int s = sc.nextInt();
        int p = sc.nextInt();

        PriorityQueue<Start> start = new PriorityQueue<>();
        PriorityQueue<End> end = new PriorityQueue<>();
        for(int i=0;i<s;i++)
        {
            start.add(new Start(i, sc.nextInt()));
            end.add(new End(i, sc.nextInt()));
        }

        PriorityQueue<Point> point = new PriorityQueue<>();
        for(int i=0;i<p;i++)
        {
            point.add(new Point(i, sc.nextInt()));
        }

        int[] ans = new int[p];
        BitSet bs = new BitSet();
        while(!start.isEmpty() && !point.isEmpty())
        {
            Start starti = start.peek();
            End endi = end.peek();
            Point pointi = point.peek();

            if(Math.min(Math.min(starti.point, endi.point), pointi.point) == starti.point)
            {
                bs.set(starti.id);
                start.remove();
            }
            else if(Math.min(Math.min(starti.point, endi.point), pointi.point) == pointi.point)
            {
                ans[pointi.id] = bs.cardinality();
                point.remove();
            }
            else if(Math.min(Math.min(starti.point, endi.point), pointi.point) == endi.point)
            {
                bs.clear(endi.id);
                end.remove();
            }
        }

        while(!end.isEmpty() && !point.isEmpty())
        {
            End endi = end.peek();
            Point pointi = point.peek();

            if(Math.min(endi.point, pointi.point) == pointi.point)
            {
                ans[pointi.id] = bs.cardinality();
                point.remove();
            }
            else if(Math.min(endi.point, pointi.point) == endi.point)
            {
                bs.clear(endi.id);
                end.remove();
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i: ans)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
