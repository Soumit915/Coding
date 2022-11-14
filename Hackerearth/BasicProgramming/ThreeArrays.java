package Hackerearth.BasicProgramming;

import java.io.*;

public class ThreeArrays {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        long xl = sc.nextLong();
        long xr = sc.nextLong();
        long yl = sc.nextLong();
        long yr = sc.nextLong();
        long zl = sc.nextLong();
        long zr = sc.nextLong();

        long xptr = xl, yptr, zptr;
        do {
            yptr = yl;
            do {
                zptr = zl;
                do {
                    int count = 0;
                    for(int i=0;i<n;i++)
                    {
                        long exp = xptr*a[i] + yptr*b[i] - zptr*c[i];
                        if(exp%m == 0)
                            count++;
                    }

                    if(count == k)
                    {
                        System.out.println(xptr+" "+yptr+" "+zptr);
                        System.exit(0);
                    }

                    zptr++;
                }while (((zptr%m)!=(zl%m)) && (zptr<=zr));
                yptr++;
            }while ((yptr%m)!=(yl%m) && (yptr<=yr));
            xptr++;
        }while((xptr%m)!=(xl%m) && (xptr<=xr));

        System.out.println(-1);
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
