package Codeforces;

import java.util.*;
import java.io.*;

public class TwoFairs {

    static class Dsu{
        int id;
        int count;
        Dsu parent;

        boolean hasA, hasB;

        Dsu(int id){
            this.id = id;
            this.count = 1;
            this.parent = this;
            this.hasA = false;
            this.hasB = false;
        }

        public Dsu getParent(){
            if(this.parent == this)
                return this;

            this.parent = this.parent.getParent();
            return this.parent;
        }

        public void merge(Dsu b){
            Dsu pa = this.getParent();
            Dsu pb = b.getParent();

            if(pa.count < pb.count){
                pa.parent = pb;
                pb.count += pa.count;
            }
            else{
                pb.parent = pa;
                pa.count += pb.count;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (tc-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            Dsu[] set = new Dsu[n];
            for(int i=0;i<n;i++)
                set[i] = new Dsu(i);

            List<List<Integer>> a_edges = new ArrayList<>();
            List<List<Integer>> b_edges = new ArrayList<>();

            for(int i=0;i<m;i++){
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;

                if(u == a || v == a){

                    if(u==b || v==b)
                        continue;

                    List<Integer> alist = new ArrayList<>();
                    alist.add(u);
                    alist.add(v);

                    a_edges.add(alist);
                }
                else if(u == b || v == b){
                    List<Integer> blist = new ArrayList<>();
                    blist.add(u);
                    blist.add(v);

                    b_edges.add(blist);
                }
                else{
                    Dsu set_u = set[u];
                    Dsu set_v = set[v];

                    if(set_u.getParent() == set_v.getParent())
                        continue;

                    set_u.merge(set_v);
                }
            }

            for(List<Integer> a_list : a_edges){
                Dsu component;
                if(a_list.get(0) != a){
                    component = set[a_list.get(0)].getParent();
                }
                else{
                    component = set[a_list.get(1)].getParent();
                }
                component.hasA = true;
            }

            for(List<Integer> b_list : b_edges){
                Dsu component;
                if(b_list.get(0) != b){
                    component = set[b_list.get(0)].getParent();
                }
                else{
                    component = set[b_list.get(1)].getParent();
                }
                component.hasB = true;
            }

            HashSet<Dsu> parents = new HashSet<>();
            for(Dsu dsu: set)
                parents.add(dsu.getParent());

            List<Long> a_component = new ArrayList<>();
            List<Long> b_component = new ArrayList<>();

            for(Dsu parent: parents){
                if(parent.hasA && !parent.hasB){
                    a_component.add((long) parent.count);
                }

                if(parent.hasB && !parent.hasA){
                    b_component.add((long) parent.count);
                }
            }

            long b_sum = 0;
            for(long i: b_component){
                b_sum += i;
            }

            long a_sum = 0;
            for(long i: a_component){
                a_sum += i;
            }

            long ans = a_sum * b_sum;

            sb.append(ans).append("\n");
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
