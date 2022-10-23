package TestingCode;

import java.io.*;
import java.util.*;

public class CreateInput {
    static class Set
    {
        int id;
        int count;
        Set parent;
        Set(int id)
        {
            this.id = id;
            this.count = 1;
            this.parent = null;
        }
        public void union(Set repb)
        {
            if(this.count >= repb.count)
            {
                repb.parent = this;
                this.count = this.count+repb.count;
            }
            else
            {
                this.parent = repb;
                repb.count = this.count+repb.count;
            }
        }
        public Set compress()
        {
            if(this.parent!=null)
            {
                this.parent = this.parent.find();
            }
            return this;
        }
        public void findUnion(Set repb)
        {
            Set k,k1;
            if(this.parent == null)
                k = this;
            else
                k = this.parent;
            if(repb.parent == null)
                k1 = repb;
            else
                k1 = repb.parent;

            k.union(k1);
        }
        public Set find()
        {
            if(this.parent == null)
            {
                return this;
            }
            this.compress();
            return this.parent;
        }
    }
    static int[] edge;
    static class Node
    {
        int id;
        long val;
        Node parent;
        ArrayList<Node> adjacentnode = new ArrayList<>();
        ArrayList<Long> pathvals = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.val = 0;
            this.parent = null;
        }
    }
    static class Tree {
        ArrayList<Node> nodelist;

        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
            }
            edge = new int[n];
        }

        public void addEdge(int xi, int yi) {
            Node nu = nodelist.get(xi);
            Node nv = nodelist.get(yi);

            nu.adjacentnode.add(nv);
            nv.adjacentnode.add(nu);
        }

        public void setParent() {
            Node source = nodelist.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);

            while (!stk.isEmpty()) {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                if (ptr < cur.adjacentnode.size() - 1) {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentnode.get(ptr);

                    if (cur.parent == next) {
                        continue;
                    }

                    next.parent = cur;
                    stk.push(next);
                    ptrstk.push(-1);
                }
            }
        }
    }

    static class Range{
        int l, r;
        Range(int l, int r){
            this.l = l;
            this.r = r;
        }
    }

    static Range getLRRange(int n){
        int l = (int) (Math.random() * n);
        int r = (int) (Math.random() * n + 1);
        r--;

        if(l > r)
            return new Range(r, l);
        else return new Range(l, r);
    }

    static class Triplets implements Comparable<Triplets>{
        int ti, x, y;

        Triplets(int ti, int x, int y){
            this.ti = ti;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Triplets triplets){
            return Integer.compare(this.ti, triplets.ti);
        }
    }

    static int[] getPermutation(int n){
        int[] a = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            a[i] = (int) (Math.random() * n + 1);

            while(set.contains(a[i])){
                a[i] = (int) (Math.random() * n + 1);
            }

            set.add(a[i]);
        }

        return a;
    }

    public static void main(String[] args) throws IOException
    {
        Soumit sc = new Soumit();
        sc.streamOutput("Input.txt");

        int t = 1;

        //sc.println(t+"");
        while(t-->0){

            int n = (int) (Math.random() * 100000);
            //sc.println(n+" ");

            for(int i=0;i<100000;i++){
                int v = (int) (Math.random() * 315 + 2);
                v = v * v;

                sc.print(v+",");
            }
            sc.println();
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

        public void println()
        {
            pw.println();
        }

        public void println(String a) {
            pw.println(a);
        }

        public void print(String a) {
            pw.print(a);
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