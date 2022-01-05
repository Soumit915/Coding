package Codechef;

import java.io.*;

public class ChefinaAndSwap {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            long n = sc.nextLong();

            if(n%4==1 || n%4==2)
                sb.append(0).append("\n");
            else if(n==3 || n==4)
                sb.append(2).append("\n");
            else
            {
                double totsum = (double) (n * (n+1))/2;
                long k = (long) totsum / 2;
                double disc = 1 + (8*k);
                double D = Math.sqrt(disc);
                long sols = (long) Math.ceil((D-1)/2);

                long checksol =  (sols * (sols+1))/2;
                long ans;
                if(checksol == k)
                {
                    ans = n - sols;
                    long before = sols;
                    long after = n - sols;
                    before = (before*(before-1))/2;
                    after = (after*(after-1))/2;
                    ans = (ans+before+after);
                }
                else
                {
                    ans = n - sols+1;
                }

                sb.append(ans).append("\n");
            }
        }

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
