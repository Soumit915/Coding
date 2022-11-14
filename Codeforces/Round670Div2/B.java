package Codeforces.Round670Div2;

import java.io.*;

public class B {
    public static long getSum(int[] hash, int len)
    {
        int i=6000;
        long ans = 1;
        while (len>=0)
        {
            if(hash[i]>=len)
            {
                for(int ind=1;ind<=len;ind++)
                {
                    ans = ans*(i-3000);
                }
                break;
            }
            else
            {
                for(int ind=1;ind<=hash[i];ind++)
                {
                    ans = ans*(i-3000);
                    len--;
                }
            }
            i--;
        }

        return ans;
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc= new Reader();
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int minus=0;
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                if(arr[i]<0)
                    minus++;
            }

            int[] hash = new int[6001];
            for(int i=0;i<n;i++)
            {
                hash[arr[i]+3000]++;
            }

            long max = getSum(hash, 5);

            if(minus>=2)
            {
                int len = 2;
                int i=0;
                long ans = 1;
                while (len>=0)
                {
                    if(hash[i]>=len)
                    {
                        for(int ind=1;ind<=len;ind++)
                        {
                            ans = ans*(i-3000);
                        }
                        break;
                    }
                    else
                    {
                        for(int ind=1;ind<=hash[i];ind++)
                        {
                            ans = ans*(i-3000);
                            len--;
                        }
                    }
                    i++;
                }

                ans = ans * getSum(hash, 3);
                max = Math.max(ans, max);
            }

            if(minus>=4)
            {
                int len = 4;
                int i=0;
                long ans = 1;
                while (len>=0)
                {
                    if(hash[i]>=len)
                    {
                        for(int ind=1;ind<=len;ind++)
                        {
                            ans = ans*(i-3000);
                        }
                        break;
                    }
                    else
                    {
                        for(int ind=1;ind<=hash[i];ind++)
                        {
                            ans = ans*(i-3000);
                            len--;
                        }
                    }
                    i++;
                }
                ans = ans * getSum(hash, 1);
                max = Math.max(ans, max);
            }

            System.out.println(max);
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
