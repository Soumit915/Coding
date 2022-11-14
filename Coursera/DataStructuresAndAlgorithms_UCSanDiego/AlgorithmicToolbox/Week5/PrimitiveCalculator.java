package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week5;

import java.io.*;
import java.util.*;

public class PrimitiveCalculator {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] dp = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            dp[i] = 10000000;
        }
        dp[1] = 0;
        for(int i=1;i<=n;i++)
        {
            if(i+1<=n)
                dp[i+1] = Math.min(1+dp[i], dp[i+1]);
            if(2*i<=n)
                dp[2*i] = Math.min(1+dp[i], dp[2*i]);
            if(3*i<=n)
                dp[3*i] = Math.min(1+dp[i], dp[3*i]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append("\n");
        Stack<Integer> stk = new Stack<>();
        stk.push(n);
        int ptr = n;
        while(ptr>1)
        {
            int min = dp[ptr-1];
            if(ptr%3==0 && ptr/3>=1) {
                min = Math.min(min, dp[ptr/3]);
            }
            if(ptr%2==0 && ptr/2>=1){
                min = Math.min(min, dp[ptr/2]);
            }

            if(min == dp[ptr/3]){
                stk.push(ptr/3);
                ptr = ptr/3;
            }
            else if(min == dp[ptr/2]){
                stk.push(ptr/2);
                ptr = ptr/2;
            }
            else{
                stk.push(ptr-1);
                ptr--;
            }
        }

        while(!stk.isEmpty())
        {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb);
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
