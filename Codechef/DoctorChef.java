package Codechef;

import java.util.*;
import java.io.*;

public class DoctorChef
{
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            long x = sc.nextLong();

            long[] arr = new long[n];
            long[] pop = new long[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
                pop[i] = arr[i];
            }
            Arrays.sort(arr);
            Arrays.sort(pop);

            int i=0;
            int isCured = 0;
            int dayscount = 0;
            while(i<n)
            {
                while(i<n && pop[i]<x)
                {
                    i++;
                }

                if(i==n)
                    break;

                if(pop[i]==x)
                {
                    isCured++;
                    i++;
                }
                else if(i>0 && arr[i-1]>(x/2))
                {
                    isCured++;
                    x = arr[i-1];
                    i--;
                }
                else
                {
                    if(i>0 && pop[i-1]>(x/2))
                    {
                        i--;
                        x = pop[i];
                        isCured++;
                    }
                    else
                    {
                        pop[i] -= x;
                        if(pop[i]<x)
                        {
                            i++;
                        }
                        else
                        {
                            pop[i] = Math.min(arr[i], pop[i]*2);
                        }
                    }
                }

                x = x*2;
                dayscount++;
            }

            dayscount += (n-isCured);
            System.out.println(dayscount);
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
            din.close();
        }
    }
}