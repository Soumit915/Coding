package Codechef;

import java.io.*;
import java.util.*;

public class MissingAPoint {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int m = 4*n-1;

            HashMap<Integer, Integer> xhash = new HashMap<>();
            HashMap<Integer, Integer> yhash = new HashMap<>();
            for(int i=0;i<m;i++)
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if(xhash.containsKey(x))
                {
                    xhash.put(x, xhash.get(x)+1);
                }
                else
                {
                    xhash.put(x, 1);
                }

                if(yhash.containsKey(y))
                {
                    yhash.put(y, yhash.get(y)+1);
                }
                else
                {
                    yhash.put(y, 1);
                }
            }

            Set<Integer> xset = xhash.keySet();
            Set<Integer> yset = yhash.keySet();
            int xans = 0;
            int yans = 0;
            for(int i: xset)
            {
                if(xhash.get(i)%2==1) {
                    xans = i;
                    break;
                }
            }
            for(int i: yset)
            {
                if(yhash.get(i)%2==1)
                {
                    yans = i;
                    break;
                }
            }

            System.out.println(xans+" "+yans);
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

        public void close() throws IOException
        {
            din.close();
        }
    }
}
