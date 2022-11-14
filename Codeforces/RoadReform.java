package Codeforces;

import java.io.*;
import java.util.*;

public class RoadReform {

    static class Edge implements Comparable<Edge>{
        int u, v;
        long si;

        Edge(int u, int v, long si){
            this.u = u;
            this.v = v;
            this.si = si;
        }

        public int compareTo(Edge edge){
            return Long.compare(this.si, edge.si);
        }
    }

    static class Set{
        int id;
        int c;
        Set parent;

        Set(int id){
            this.id = id;
            this.c = 0;
            this.parent = this;
        }

        public void union(Set u, Set v){
            Set pu = u.getParent();
            Set pv = v.getParent();

            if(pu.c > pv.c){
                pv.parent = pu;
                pu.c += pv.c;
            }
            else{
                pu.parent = pv;
                pv.c += pu.c;
            }
        }

        public Set getParent(){
            if(this.parent == this)
                return this;

            this.parent = this.parent.getParent();
            return this.parent;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){

            int n = sc.nextInt();
            int m = sc.nextInt();
            long k = sc.nextLong();

            Edge[] edge = new Edge[m];
            for(int i=0;i<m;i++){
                edge[i] = new Edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
            }

            Arrays.sort(edge);

            Set[] nodes = new Set[n];
            for(int i=0;i<n;i++){
                nodes[i] = new Set(i);
            }

            long max = Integer.MIN_VALUE;
            boolean flag = false;
            List<Long> list = new ArrayList<>();
            for(int i=0;i<m;i++){
                int u = edge[i].u;
                int v = edge[i].v;
                long s = edge[i].si;

                if(nodes[u].getParent() == nodes[v].getParent()){
                    if(list.size() > 0)
                        continue;

                    if(Math.abs(max - k) > Math.abs(s - k)){
                        if(s > k) {
                            list.add(s);
                            flag = true;
                        }
                        else max = s;
                    }
                }
                else{
                    if(list.size() > 0){
                        list.add(s);
                    }
                    else if(s > k){
                        list.add(s);
                    }
                    else if(Math.abs(max - k) > Math.abs(s - k)){
                        max = s;
                    }
                    nodes[u].union(nodes[u], nodes[v]);
                }
            }

            if(list.size() == 1){
                max = Math.abs(k - list.get(0));
            }
            else if(list.size() > 0){
                max = 0;
                for(int i=flag?1:0;i<list.size();i++){
                    max += Math.abs(k - list.get(i));
                }
            }
            else{
                max = Math.abs(max - k);
            }

            sb.append(max).append("\n");
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
