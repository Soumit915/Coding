package Codeforces.RateUp_Contest_4;

import java.io.*;
import java.util.*;

public class A {

    static class Node{
        long count;
        long val;

        Node(long val, long count){
            this.val = val;
            this.count = count;
        }
    }

    static boolean isEqual(List<Node> a, List<Node> b){
        for(int i=0;i<a.size();i++){
            if(a.get(i).val != b.get(i).val || a.get(i).count != b.get(i).count)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            long[] a = sc.nextLongArray(n);

            int k = sc.nextInt();
            long[] b = sc.nextLongArray(k);

            Node[] a_dis = new Node[n];
            for(int i=0;i<n;i++){
                long v = a[i];
                while(v%m == 0){
                    v /= m;
                }
                a_dis[i] = new Node(v, a[i] / v);
            }

            Node[] b_dis = new Node[k];
            for(int i=0;i<k;i++){
                long v = b[i];
                while(v%m == 0){
                    v /= m;
                }
                b_dis[i] = new Node(v, b[i] / v);
            }

            List<Node> a_list = new ArrayList<>();
            a_list.add(a_dis[0]);
            for(int i=1;i<n;i++){
                if(a_dis[i].val == a_dis[i-1].val){
                    Node last = a_list.get(a_list.size() - 1);
                    last.count += a_dis[i].count;
                }
                else{
                    a_list.add(a_dis[i]);
                }
            }

            List<Node> b_list = new ArrayList<>();
            b_list.add(b_dis[0]);
            for(int i=1;i<k;i++){
                if(b_dis[i].val == b_dis[i-1].val){
                    Node last = b_list.get(b_list.size() - 1);
                    last.count += b_dis[i].count;
                }
                else{
                    b_list.add(b_dis[i]);
                }
            }

            if(a_list.size() == b_list.size() && isEqual(a_list, b_list)){
                sb.append("Yes\n");
            }
            else {
                sb.append("No\n");
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
