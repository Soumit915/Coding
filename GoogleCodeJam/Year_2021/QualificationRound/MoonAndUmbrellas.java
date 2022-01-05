package GoogleCodeJam.Year_2021.QualificationRound;

import java.io.*;
import java.util.*;

public class MoonAndUmbrellas {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int a = sc.nextInt();
            int b = sc.nextInt();
            String s = sc.next();
            s = s.toLowerCase();

            int n = s.length();
            int[] dpc = new int[n];
            int[] dpj = new int[n];
            dpc[0] = 0;
            dpj[0] = 0;
            for(int i=1;i<n;i++){
                if(s.charAt(i-1)=='c' && s.charAt(i)=='c'){
                    dpc[i] = dpc[i-1];
                }
                else if(s.charAt(i-1)=='c' && s.charAt(i)=='j'){
                    dpj[i] = dpc[i-1]+a;
                }
                else if(s.charAt(i-1)=='c' && s.charAt(i)=='?'){
                    dpc[i] = dpc[i-1];
                    dpj[i] = dpc[i-1]+a;
                }
                else if(s.charAt(i-1)=='j' && s.charAt(i)=='c'){
                    dpc[i] = dpj[i-1]+b;
                }
                else if(s.charAt(i-1)=='j' && s.charAt(i)=='j'){
                    dpj[i] = dpj[i-1];
                }
                else if(s.charAt(i-1)=='j' && s.charAt(i)=='?'){
                    dpc[i] = dpj[i-1]+b;
                    dpj[i] = dpj[i-1];
                }
                else {
                    int min = Math.min(dpc[i - 1], dpj[i - 1] + b);
                    if(s.charAt(i-1)=='?' && s.charAt(i)=='c'){
                        dpc[i] = min;
                    }
                    else {
                        if (s.charAt(i - 1) != '?' || s.charAt(i) != 'j') {
                            dpc[i] = min;
                        }
                        dpj[i] = Math.min(dpc[i - 1] + a, dpj[i - 1]);
                    }
                }
            }

            if(s.charAt(n-1)=='c'){
                sb.append(dpc[n-1]).append("\n");
            }
            else if(s.charAt(n-1)=='j'){
                sb.append(dpj[n-1]).append("\n");
            }
            else{
                sb.append(Math.min(dpc[n-1], dpj[n-1])).append("\n");
            }
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
            byte[] buf = new byte[3000064]; // line length
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

        public void sort(int[] arr) {
            ArrayList<Integer> arlist = new ArrayList<>();
            for (int i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
        }

        public void sort(long[] arr) {
            ArrayList<Long> arlist = new ArrayList<>();
            for (long i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
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
