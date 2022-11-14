package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week2;

import java.io.*;
import java.util.*;

public class LastDigitOfFibonacciSum {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        long n = sc.nextLong();

        long[][] memo = new long[10][10];
        int m = 10;
        ArrayList<Integer> fibseries = new ArrayList<>();
        fibseries.add(0);
        fibseries.add(1);

        int i = 2;
        int period;
        while(true)
        {
            int a = fibseries.get(i-2);
            int b = fibseries.get(i-1);
            int c = (a+b)%m;
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

        int[] sum = new int[period];
        sum[0] = fibseries.get(0);
        for(int j=1;j<period;j++)
        {
            sum[j]  = (sum[j-1] + fibseries.get(j))%10;
        }

        int index = (int) (n%period);
        long ans = (n/period) * (sum[sum.length-1]);
        ans = ans%10;
        ans += (sum[index]);
        ans %= 10;
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
