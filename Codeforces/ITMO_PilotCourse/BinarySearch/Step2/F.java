package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.io.*;
import java.util.*;

public class F {
    public static boolean isValid(String t, String p, int[] arr, int ind)
    {
        int n = t.length();
        boolean[] hash = new boolean[n];
        for(int i=0;i<=ind;i++)
        {
            hash[arr[i]] = true;
        }

        int j=0;
        int count = 0;
        for(int i=0;i<n;i++)
        {
            if(!hash[i] && (p.charAt(j)==t.charAt(i))) {
                count++;
                j++;
            }

            if(j==p.length())
                return true;
        }

        return count==p.length();
    }
    public static void main(String[] args) throws IOException
    {
        Soumit sc = new Soumit();
        String t = sc.next();
        String p = sc.next();

        int n = t.length();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt()-1;
        }

        int l=0;
        if(!isValid(t, p, arr, 0)) {
            System.out.println(0);
            System.exit(0);
        }
        else if(isValid(t, p, arr, n-1))
        {
            System.out.println(n);
            System.exit(0);
        }
        int r=n-1;
        while(r-l>1)
        {
            int mid = (l+r)/2;
            if(isValid(t, p, arr, mid))
            {
                l = mid;
            }
            else
            {
                r = mid;
            }
        }

        System.out.println(l+1);
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
            byte[] buf = new byte[1000064]; // line length
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
