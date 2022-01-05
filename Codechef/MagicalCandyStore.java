package Codechef;

import java.io.*;
import java.util.*;

public class MagicalCandyStore {
    static long mod = (long) 1e9+7;
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-->0)
        {
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);

            int index1 = -1;
            for(int i=0;i<n;i++)
            {
                if(arr[i]==1) {
                    index1 = i;
                    break;
                }
            }

            if(n==1)
            {
                long sum = (arr[0]%2==0?arr[0]-1: arr[0]);
                int q = sc.nextInt();
                while (q-->0)
                {
                    long r = sc.nextLong();
                    long ans;

                    ans = (sum * (r-1+mod)%mod)%mod;
                    ans = (ans + arr[0])%mod;

                    sb.append(ans).append("\n");
                }
            }
            else if(index1==-1 || index1==n-1)
            {
                long[] sum = new long[n];
                sum[0] = arr[0]%2==0?arr[0]: arr[0]-1;
                for(int i=1;i<n-1;i++)
                {
                    sum[i] = ((arr[i]%2==0?arr[i]: arr[i]-1)+sum[i-1])%mod;
                }

                if(n!=1) sum[n-1] = ((arr[n-1]%2==1?arr[n-1]: arr[n-1]-1) + sum[n-2])%mod;

                int q = sc.nextInt();
                while(q-->0)
                {
                    long r = sc.nextLong();
                    long ans;

                    long loops = (r/n);
                    long left = (r%n);

                    ans = (loops * sum[n-1])%mod;

                    if(left>1) ans = (ans + (sum[(int) left-2]))%mod;

                    if(left==0) ans = (ans - (arr[n-1]%2==1?arr[n-1]: arr[n-1]-1) + mod)%mod;

                    ans = (ans + arr[(int) (n-1+left)%n])%mod;

                    sb.append(ans).append("\n");
                }
            }
            else if(index1==0)
            {
                int q = sc.nextInt();
                while (q-->0)
                {
                    long r = sc.nextLong();
                    long ans;

                    long loops = (r/n);
                    long left = (r%n);

                    ans = loops;
                    if(left==1) {
                        if (loops == 0)
                            ans = 1;
                    }
                    else if(left!=0)
                        ans++;

                    sb.append(ans).append("\n");
                }
            }
            else
            {
                long[] sum = new long[n];
                sum[0] = arr[0]%2==0?arr[0]: arr[0]-1;
                for(int i=1;i<n-1;i++)
                {
                    sum[i] = ((arr[i]%2==0?arr[i]: arr[i]-1)+sum[i-1])%mod;
                }

                sum[n-1] = ((arr[n-1]%2==1?arr[n-1]: arr[n-1]-1) + sum[n-2])%mod;

                long loopsum = (sum[n-1]+(arr[index1-1]%2==0?-1: 1)+mod)%mod;

                int q = sc.nextInt();
                while(q-->0)
                {
                    long r = sc.nextLong();
                    long ans;

                    long loops = (r/n);
                    long left = (r%n);

                    ans = (loops * loopsum)%mod;

                    if(left-1==index1)
                    {
                        ans = (ans + sum[index1] + 1)%mod;
                    }
                    else if(left-1<index1)
                    {
                        if(left>1) ans = (ans + (sum[(int) left-2]))%mod;

                        if(left==0) ans = (ans - (arr[n-1]%2==1?arr[n-1]: arr[n-1]-1) + mod)%mod;

                        ans = (ans + arr[(int) (n-1+left)%n])%mod;
                    }
                    else
                    {
                        if(index1>1) ans = (ans + sum[index1-2])%mod;
                        ans = (ans + (arr[index1-1]%2==1?arr[index1-1]:arr[index1-1]-1))%mod;

                        ans = (ans + sum[(int) left-2] - sum[index1] +mod)%mod;
                        ans = (ans + arr[(int) (n-1+left)%n])%mod;
                    }

                    sb.append(ans).append("\n");
                }
            }

        }

        System.out.println(sb.toString());

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
