package FacebookHackercup.Round1_2021;

import java.io.*;
import java.util.*;

public class A3 {
    static long mod = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        Soumit output = new Soumit("Input.txt");
        output.streamOutput("Output.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++) {
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            String s = sc.next();

            long[] dp = new long[n];
            long last = -1, dplast = 0;
            char chlast = '\u0000';
            int x = -1, o = -1;

            for(int i=n-1;i>=0;i--){
                if(s.charAt(i)=='X')
                    x = i;
                else if(s.charAt(i)=='O')
                    o = i;
            }

            char chfirst = '\u0000';
            if(x!=-1 && o!=-1){
                if(Math.min(x, o)==x){
                    chfirst = 'X';
                }
                else{
                    chfirst = 'O';
                }
            }

            long sumtillnow = 0;
            long size = 0;
            long cu = 0, count = 0;
            for(int i=0;i<n;i++){
                if(last==-1){
                    if(s.charAt(i)=='F') {
                        dp[i] = 0;
                        size = (size+1)%mod;
                    }
                    else if(s.charAt(i)=='.'){
                        size = (size*2)%mod;
                    }
                    else{
                        dp[i] = 0;
                        last = i;
                        chlast = s.charAt(i);
                        dplast = 0;
                        size = (size+1)%mod;
                        //cu = (cu+1)%mod;
                    }
                }
                else{
                    if(s.charAt(i)=='F'){
                        dp[i] = dplast;
                        sumtillnow = (dp[i] + sumtillnow)%mod;
                        size = (size+1)%mod;
                        count = (count + cu)%mod;
                    }
                    else if(s.charAt(i)!='.'){
                        System.out.println(i+": "+dplast+" "+chlast+" "+last);
                        if(s.charAt(i)==chlast){
                            dp[i] = dplast;
                        }
                        else{
                            dp[i] = (dplast + last + 1)%mod;
                            cu = (cu+1)%mod;
                        }
                        count = (count + cu)%mod;
                        System.out.println(i+":- "+count+" "+cu);
                        last = size;
                        chlast = s.charAt(i);
                        dplast = dp[i];
                        sumtillnow = (dp[i] + sumtillnow)%mod;
                        size = (size+1)%mod;
                    }
                    else{
                        long countextra = 0;
                        if(chlast!=chfirst){
                            countextra = ((last+1)*size)%mod;
                        }
                        System.out.println(dplast+" "+
                                size+" "+count+" "+countextra+" "+sumtillnow);
                        long val = ((dplast*size)%mod + (size*count)%mod
                                + sumtillnow + countextra)%mod;
                        dp[i] = val;
                        dplast = ((dplast*2)%mod + (chlast!=chfirst?(last+1):0)
                                + (cu*size)%mod)%mod;
                        last = (last + size)%mod;
                        count = (count + (cu * size)%mod)%mod;
                        cu = (cu*2)%mod;
                        sumtillnow = (dp[i] + sumtillnow)%mod;
                        size = (size * 2)%mod;

                        System.out.println("Test: "+i+" "+dplast+" "+last+" "+count+" "+cu+" "+size);
                    }
                }
            }

            System.out.println(Arrays.toString(dp));

            sb.append(sumtillnow).append("\n");
        }

        output.println(sb.toString());

        sc.close();
        output.close();
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
