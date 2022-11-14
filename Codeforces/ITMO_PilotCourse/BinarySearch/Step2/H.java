package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.io.*;
import java.util.*;

public class H {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        String s = sc.next();
        int n = s.length();

        long b=0, sa=0, c=0;
        for(int i=0;i<n;i++)
        {
            if(s.charAt(i)=='B')
                b++;
            else if(s.charAt(i)=='S')
                sa++;
            else c++;
        }

        long nbreads = sc.nextLong();
        long nsausages = sc.nextLong();
        long ncheese = sc.nextLong();

        long pbreads = sc.nextLong();
        long psausages = sc.nextLong();
        long pcheese = sc.nextLong();

        long r = sc.nextLong();

        long ll = 0;
        long ul = (long) 1e15;
        while(ul-ll>1)
        {
            long mid = (ll+ul)/2;
            if(isValid(b, sa, c, nbreads, nsausages, ncheese, pbreads, psausages, pcheese, r, mid))
            {
                ll = mid;
            }
            else
            {
                ul = mid;
            }
        }

        System.out.println(ll);
    }
    public static boolean isValid(long b, long s, long c, long nb, long ns, long nc,
                                  long pb, long ps, long pc, long r, long tobemade)
    {
        b *= tobemade;
        s *= tobemade;
        c *= tobemade;

        b = Math.max(0, b-nb);
        s = Math.max(0, s-ns);
        c = Math.max(0, c-nc);

        long costneeded = b*pb + s*ps + c*pc;
        return costneeded<=r;
    }
    static class Soumit {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
        private int bufferPointer, bytesRead;
        StringTokenizer st;

        public Soumit() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Soumit(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
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

        public double nextDouble() throws IOException {
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

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
