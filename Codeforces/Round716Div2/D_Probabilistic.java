package Codeforces.Round716Div2;

import java.io.*;
import java.util.*;

public class D_Probabilistic {
    static int lowerBound(ArrayList<Integer> arr, int ll, int ul, int val) {
        int mid = (ll+ul)/2;
        if(arr.get(mid)==val)
        {
            if(mid!=ul && arr.get(mid+1)==val)
                return lowerBound(arr, mid+1, ul, val);
            else return mid;
        }
        else if(arr.get(mid)<val)
        {
            if(mid!=ul && arr.get(mid+1)<=val)
                return lowerBound(arr, mid+1, ul, val);
            else return mid;
        }
        else return lowerBound(arr, ll, mid-1, val);
    }
    static int upperBound(ArrayList<Integer> arr, int ll, int ul, int val) {
        int mid = (ll+ul)/2;
        if(arr.get(mid)==val)
        {
            if(mid!=ll && arr.get(mid-1)==val)
                return upperBound(arr, ll, mid-1, val);
            else return mid;
        }
        else if(arr.get(mid)>val)
        {
            if(mid!=ll && arr.get(mid-1)>=val)
                return upperBound(arr, ll, mid-1, val);
            else return mid;
        }
        else return upperBound(arr, mid+1, ul, val);
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        //Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        ArrayList<ArrayList<Integer>> hashlist = new ArrayList<>();
        for(int i=0;i<=n;i++){
            hashlist.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            hashlist.get(arr[i]).add(i);
        }

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while(q-->0){
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;

            int range = r-l+1;
            int max = -1;
            for(int i=0;i<20;i++){
                int v = random.nextInt(r-l+1)+l;
                ArrayList<Integer> list = hashlist.get(arr[v]);
                int rind = lowerBound(list, 0, list.size()-1, r);
                int lind = upperBound(list, 0, list.size()-1, l);

                int count = rind-lind+1;
                max = Math.max(count, max);
            }

            int restfreq = range-max;
            if(restfreq<max){
                sb.append(max-restfreq).append("\n");
            }
            else{
                sb.append("1\n");
            }
        }

        System.out.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

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
