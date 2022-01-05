package Codeforces.Round662Div2;

import java.io.*;

public class C {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[] arr = new int[n];
            int[] hash = new int[n+5];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                hash[arr[i]]++;
            }

            int max = Integer.MIN_VALUE;
            for(int i=0;i<=n+2;i++)
            {
                if(hash[i]>max)
                {
                    max = hash[i];
                }
            }

            int count = 0;
            for(int i=0;i<=n+2;i++)
            {
                if(hash[i]==max)
                    count++;
            }

            int ans;
            if(count==1)
            {
                ans = n- max;
                max--;
                ans = ans/max;
            }
            else
            {
                ans = n-(max*count);
                ans = (ans/(max-1));
                ans = ans + count-1;
            }

            System.out.println(ans);
        }
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
