package Hackerrank.DataStructures.Trees;

import java.io.*;
import java.util.*;

public class ArrayAndSimpleQueries {
    static class Treap{
        int val;
        int priority;
        int subTreeSize;
        Treap[] child;
        Treap(int val){
            this.val = val;
            this.subTreeSize = 1;
            this.priority = (int) (Math.random()*1000000000);
            child = new Treap[2];
        }
        static int size(Treap treap){
            if(treap==null) return 0;
            else return treap.subTreeSize;
        }
        static void recalc(Treap treap){
            if(treap==null)
                return;
            treap.subTreeSize = 1;
            for(Treap tr: treap.child)
                if(tr!=null)
                    treap.subTreeSize += tr.subTreeSize;
        }
        static Treap merge(Treap l, Treap r){
            if(l==null) return r;
            if(r==null) return l;
            if(l.priority<r.priority){
                l.child[1] = merge(l.child[1], r);
                recalc(l);
                return l;
            }
            else{
                r.child[0] = merge(l, r.child[0]);
                recalc(r);
                return r;
            }
        }
        static Treap[] split(Treap treap, int key){
            if(treap==null)
                return new Treap[]{null, null};
            if(size(treap.child[0])>=key){
                Treap[] splitted_treaps = split(treap.child[0], key);
                treap.child[0] = splitted_treaps[1];
                recalc(treap);
                return new Treap[]{splitted_treaps[0], treap};
            }
            else{
                key = key-size(treap.child[0])-1;
                Treap[] splitted_treaps = split(treap.child[1], key);
                treap.child[1] = splitted_treaps[0];
                recalc(treap);
                return new Treap[]{treap, splitted_treaps[1]};
            }
        }
        static void inorder(Treap root){
            if(root==null)
                return;
            inorder(root.child[0]);
            System.out.print(root.val+" ");
            inorder(root.child[1]);
        }
        static int min(Treap root){
            if(root==null)
                return 0;

            while(root.child[0]!=null){
                root = root.child[0];
            }
            return root.val;
        }
        static int max(Treap root){
            if(root==null)
                return 0;
            while(root.child[1]!=null){
                root = root.child[1];
            }
            return root.val;
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        Treap root = null;
        for(int i=0;i<n;i++){
            Treap treap = new Treap(arr[i]);
            root = Treap.merge(root, treap);
        }

        for(int i=0;i<m;i++){
            int type = sc.nextInt();
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;

            if(type==1){
                Treap[] split_right = Treap.split(root, r+1);
                Treap[] split_left = Treap.split(split_right[0], l);
                Treap mergedLeft = Treap.merge(split_left[1], split_left[0]);
                root = Treap.merge(mergedLeft, split_right[1]);
            }
            else{
                Treap[] split_right = Treap.split(root, r+1);
                Treap[] split_left = Treap.split(split_right[0], l);
                Treap mergedRight = Treap.merge(split_left[0], split_right[1]);
                root = Treap.merge(mergedRight, split_left[1]);
            }
        }

        System.out.println(Math.abs(Treap.min(root) - Treap.max(root)));
        Treap.inorder(root);
        System.out.println();

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
