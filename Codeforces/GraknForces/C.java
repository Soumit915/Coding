package Codeforces.GraknForces;

import java.io.*;
import java.util.*;

public class C {
    public static boolean isValid(long[] arr, long l, double t)
    {
        int n = arr.length;
        double sspeed = 1;
        double last = 0;
        double totstart = 0;
        double s_distcovered = 0;
        for (long value : arr) {
            double dist = ((double) value - last) / sspeed;
            if (totstart + dist > t) {
                double left = t - totstart;
                double went = sspeed * left;
                totstart = t;
                s_distcovered += went;
                break;
            }
            totstart += dist;
            last = value;
            sspeed++;
            s_distcovered = value;
        }

        if(totstart<t)
        {
            double left = t-totstart;
            double went = sspeed*left;
            s_distcovered += went;
        }

        double espeed = 1;
        last = l;
        double totend = 0;
        double e_distcovered = 0;
        for(int i=n-1;i>=0;i--)
        {
            double dist = (last -arr[i])/espeed;
            if(totend+dist>t)
            {
                double left = t-totend;
                double went = espeed*left;
                totend = t;
                e_distcovered += went;
                break;
            }
            totend+=dist;
            last = arr[i];
            espeed++;
            e_distcovered = l-arr[i];
        }

        if(totend<t)
        {
            double left = t-totend;
            double went = espeed*left;
            e_distcovered += went;
        }

        return s_distcovered >= l - e_distcovered;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();

        while (t-->0)
        {
            long n = sc.nextLong();
            long l = sc.nextLong();

            long[] arr = sc.nextLongArray((int) n);

            double ll = 0;
            double ul = 1e17;
            double factor = 0.0000001;
            while(ul-ll>factor)
            {
                double mid = ll+(ul-ll)/2;
                if(isValid(arr, l, mid))
                {
                    ul = mid;
                }
                else
                {
                    ll = mid;
                }
            }

            System.out.println(ll);
        }

        sc.close();
    }

    static class Soumit {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
        private PrintWriter pw;
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

        public void streamOutput(String file) throws IOException {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        }

        public void println(String a) {
            pw.println(a);
        }

        public void print(String a) {
            pw.print(a);
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

        public int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }

            return arr;
        }

        public long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }

            return arr;
        }

        public double[] nextDoubleArray(int n) throws IOException {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextDouble();
            }

            return arr;
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
            /*if (din == null)
                return;*/
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
