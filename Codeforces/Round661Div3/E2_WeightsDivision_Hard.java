package Codeforces.Round661Div3;

import java.io.*;
import java.util.*;

public class E2_WeightsDivision_Hard {
    static class Node{
        int id;
        boolean isVisited;
        ArrayList<Edge> edgelist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
        }
    }
    static class Edge implements Comparable<Edge>{
        Node u;
        Node v;
        long weight;
        long weightBy2;
        int cost;
        long leafs_factor;
        Edge(Node u, Node v, long weight, int cost){
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.weightBy2 = this.weight/2;
            this.cost = cost;
            this.leafs_factor = 0;
        }
        public int compareTo(Edge e){
            return Long.compare(this.weight-this.weightBy2, e.weight-e.weightBy2);
        }
        public String toString(){
            return this.u.id+" "+this.v.id+" "+this.weight+" "+this.weightBy2+" "+this.cost+" "+this.leafs_factor;
        }
    }
    static class Tree{
        ArrayList<Node> nodelist;
        ArrayList<Edge> edgelist;
        Tree(int n){
            nodelist = new ArrayList<>(n);
            edgelist = new ArrayList<>(n-1);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v, long weight, int cost){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);
            Edge e = new Edge(nu, nv, weight, cost);

            nu.edgelist.add(e);
            nv.edgelist.add(e);
            edgelist.add(e);
        }
        public int dfs(Node source){
            source.isVisited = true;
            if(source.edgelist.size()==1 && source.id!=0)
                return 1;

            for(Edge e: source.edgelist){
                Node child;
                if(e.u==source){
                    child = e.v;
                }
                else child = e.u;

                if(!child.isVisited) {
                    e.leafs_factor = dfs(child);
                }
            }

            int sum = 0;
            for(Edge e: source.edgelist){
                sum += e.leafs_factor;
            }
            return sum;
        }
    }
    static int binarySearch(ArrayList<Long> list, long key, int ll, int ul){
        if(ll<=ul){
            int mid = (ll+ul)/2;
            if(list.get(mid)==key){
                return mid;
            }
            else if(list.get(mid)<key){
                if(mid==0){
                    return mid;
                }
                else if(list.get(mid-1)>key){
                    return mid;
                }
                else return binarySearch(list, key, ll, mid-1);
            }
            else{
                if(mid==list.size()-1){
                    return -1;
                }
                else return binarySearch(list, key, mid+1, ul);
            }
        }
        else return -1;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long k = sc.nextLong();

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++){
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                long weight = sc.nextLong();
                int cost = sc.nextInt();
                tr.addEdge(u, v, weight, cost);
            }

            tr.dfs(tr.nodelist.get(0));
            /*for(Edge e: tr.edgelist){
                System.out.println(e);
            }*/

            long totsum1 = 0;
            long totsum2 = 0;
            PriorityQueue<Edge> heap1 = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Edge> heap2 = new PriorityQueue<>(Collections.reverseOrder());
            for(Edge e: tr.edgelist){
                e.weight *= e.leafs_factor;
                e.weightBy2 *= e.leafs_factor;
                if(e.cost==1){
                    totsum1 += e.weight;
                    heap1.add(e);
                }
                else{
                    totsum2 += e.weight;
                    heap2.add(e);
                }
            }

            ArrayList<Long> list1 = new ArrayList<>();
            list1.add(totsum1);
            ArrayList<Long> list2 = new ArrayList<>();
            list2.add(totsum2);
            while(!heap1.isEmpty()){
                Edge e = heap1.remove();
                totsum1 -= (e.weight-e.weightBy2);
                e.weight = e.weightBy2;
                e.weightBy2 /= e.leafs_factor;
                e.weightBy2 /= 2;
                e.weightBy2 *= e.leafs_factor;

                if(e.weight!=0){
                    heap1.add(e);
                }
                list1.add(totsum1);
            }

            while(!heap2.isEmpty()){
                Edge e = heap2.remove();
                totsum2 -= (e.weight-e.weightBy2);
                e.weight = e.weightBy2;
                e.weightBy2 /= e.leafs_factor;
                e.weightBy2 /= 2;
                e.weightBy2 *= e.leafs_factor;

                if(e.weight!=0){
                    heap2.add(e);
                }
                list2.add(totsum2);
            }

            /*System.out.println(list1);
            System.out.println(list2);*/

            int cost = Integer.MAX_VALUE;
            for(int i=0;i<list1.size();i++){
                long lval1 = list1.get(i);
                if(lval1>k){
                    continue;
                }

                int li2 = binarySearch(list2, k-lval1, 0, list2.size()-1);
                //System.out.println(i+" "+li2);
                if(li2!=-1)
                    cost = Math.min(cost, i+li2*2);
            }

            sb.append(cost).append("\n");
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
