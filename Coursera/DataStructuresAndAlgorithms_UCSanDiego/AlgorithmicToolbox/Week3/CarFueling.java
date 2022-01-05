package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week3;

import java.io.*;
import java.util.*;

public class CarFueling {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int d = sc.nextInt();
        int m = sc.nextInt();

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int count = 0;
        int prev = 0;
        for(int i=0;i<n;i++)
        {
            if(arr[i]==prev+m)
            {
                count++;
                prev = arr[i];
            }
            else if(arr[i]>prev+m)
            {
                if((i==0 && arr[i]>m) || (arr[i]-arr[i-1]>m))
                {
                    count = -1;
                    break;
                }
                else
                {
                    count++;
                    prev = arr[i-1];
                }
            }
        }

        if(count!=-1)
        {
            if(d-arr[n-1]>m)
            {
                count = -1;
            }
            else if(d>prev+m)
            {
                count++;
            }
        }

        System.out.println(count);
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
