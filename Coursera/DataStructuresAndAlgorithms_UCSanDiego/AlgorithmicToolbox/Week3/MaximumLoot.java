package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week3;

import java.io.*;
import java.util.*;

public class MaximumLoot {
    static class Item implements Comparable<Item>
    {
        double value;
        double weight;
        double ratio;
        Item(double value, double weight)
        {
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }
        public int compareTo(Item i)
        {
            return Double.compare(this.ratio, i.ratio);
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int C = sc.nextInt();

        Item[] items = new Item[n];
        for(int i=0;i<n;i++)
        {
            items[i] = new Item(sc.nextDouble(), sc.nextDouble());
        }
        Arrays.sort(items, Collections.reverseOrder());

        double ans = 0.0;
        for(int i=0;i<n && C>0;i++)
        {
            if(items[i].weight > C)
            {
                ans += (C * items[i].ratio);
                break;
            }

            ans += items[i].value;
            C -= items[i].weight;
        }

        System.out.println(ans);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
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

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

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
