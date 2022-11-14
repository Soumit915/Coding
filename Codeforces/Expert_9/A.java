package Codeforces.Expert_9;

import java.util.*;
import java.io.*;

public class A {

    static int getMax(int[] a){
        int max = a[0];
        for(int i: a)
            max = Math.max(max, i);

        return max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();

        System.out.println("? 1");
        System.out.flush();
        int[] d1 = sc.nextIntArray(n);

        int max = getMax(d1);

        List<List<Integer>> node_dist = new ArrayList<>();
        for(int i=0;i<=max;i++){
            node_dist.add(new ArrayList<>());
        }

        int odd = 0, even = 0;

        for(int i=0;i<n;i++){
            int v = d1[i];
            List<Integer> cur_nodelist = node_dist.get(v);

            cur_nodelist.add(i+1);

            if(i==0)
                continue;

            if(d1[i] % 2 == 0){
                even++;
            }
            else{
                odd++;
            }
        }

        int[][] edges = new int[n-1][2];
        int edge_ptr = 0;

        List<Integer> d2 = node_dist.get(1);
        for(int i: d2){
            edges[edge_ptr][0] = 1;
            edges[edge_ptr][1] = i;
            edge_ptr++;
        }

        int start = 2 - (even>odd?1:0);
        for(int i=start;i<=max;i+=2){
            List<Integer> cur_list = node_dist.get(i);

            if(i%2 == 0){

                for(int v : cur_list){
                    System.out.println("? "+v);
                    int[] d = sc.nextIntArray(n);

                    for(int j=1;j<n;j++){
                        if(d[j] != 1)
                            continue;

                        edges[edge_ptr][0] = v;
                        edges[edge_ptr][1] = j + 1;
                        edge_ptr++;
                    }
                }
            }
            else{
                for(int v : cur_list){
                    System.out.println("? "+v);
                    int[] d = sc.nextIntArray(n);

                    for(int j=1;j<n;j++){
                        if(d[j] != 1)
                            continue;

                        edges[edge_ptr][0] = v;
                        edges[edge_ptr][1] = j + 1;
                        edge_ptr++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        System.out.println("!");
        for(int i=0;i<n-1;i++){
            sb.append(edges[i][0]).append(" ").append(edges[i][1]).append("\n");
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
