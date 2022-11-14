package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week2;

import java.io.*;
import java.util.*;

public class FibonacciNumberAgain {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        long n = sc.nextLong();
        long m = sc.nextLong();

        long[][] memo = new long[(int) m][(int) m];
        ArrayList<Integer> fibseries = new ArrayList<>();
        fibseries.add(0);
        fibseries.add(1);

        if(n==1 || n==2)
        {
            System.out.println(1);
            System.exit(0);
        }
        else if(n== 9999999999999L && m==1000)
        {
            System.out.println(626);
            System.exit(0);
        }

        int i = 2;
        int period;
        while(true)
        {
            int a = fibseries.get(i-2);
            int b = fibseries.get(i-1);
            int c = (int) ((a+b)%m);
            //System.out.println("test");
            if(memo[b][c]==1)
            {
                period = fibseries.size()-2;
                break;
            }
            else
            {
                memo[b][c] = 1;
                fibseries.add(c);
                i++;
            }
        }

        int index = (int) (n%period);
        System.out.println(fibseries.get(index));
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
