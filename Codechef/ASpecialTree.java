package Codechef;

import java.io.*;
import java.util.*;

public class ASpecialTree {
    static class Node{
        int id;
        boolean isVisited;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
        }
    }
    static class Tree{
        ArrayList<Node> nodelist;
        Node[][] ancestors;
        int[] level;
        int log;
        Tree(int n){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
            log = (int) Math.ceil(Math.log(n)/Math.log(2));
            ancestors = new Node[n][log+1];
            level = new int[n];
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public void setAncestors(Node source, Node parent)
        {
            ancestors[source.id][0] = parent;
            for(int i=1;i<=log;i++)
                if(ancestors[source.id][i-1] == null)
                    ancestors[source.id][i] = null;
                else
                    ancestors[source.id][i] = ancestors[ancestors[source.id][i-1].id][i-1];

            for(Node node: source.adlist)
                if(node!=parent)
                {
                    level[node.id] = level[source.id]+1;
                    setAncestors(node, source);
                }
        }
        public Node lca(Node u, Node v)
        {
            if(level[u.id]<level[v.id])
            {
                Node node = u;
                u = v;
                v = node;
            }

            for(int i=log;i>=0;i--)
                if(level[u.id]-(1 << i)>=level[v.id])
                    u = ancestors[u.id][i];

            if(u==v)
                return u;

            for(int i=log;i>=0;i--)
            {
                if(ancestors[u.id][i]!=null && ancestors[u.id][i]!=ancestors[v.id][i])
                {
                    u = ancestors[u.id][i];
                    v = ancestors[v.id][i];
                }
            }

            return ancestors[u.id][0];
        }
        public boolean isSpecial(){
            for(Node node: this.nodelist){
                if(node.adlist.size()>2)
                    return false;
                for(Node adnode: node.adlist){
                    if(Math.abs(adnode.id-node.id)!=1)
                        return false;
                }
            }
            return true;
        }
    }
    static void doSpecial(int n, int k, int a, int[] fi, StringBuilder sb){
        ArrayList<Integer> anslist = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(i<=a){
                int da = Math.abs((fi[0]-1)-a);
                int db = Math.abs((fi[0]-1)-i);
                sb.append(da-db).append(" ");
                anslist.add(fi[0]);
            }
            else{
                int da = Math.abs((fi[fi.length-1]-1)-a);
                int db = Math.abs((fi[fi.length-1]-1)-i);
                sb.append(da-db).append(" ");
                anslist.add(fi[fi.length-1]);
            }
        }
        sb.append("\n");
        for(int i: anslist)
            sb.append(i).append(" ");
        sb.append("\n");
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int a = sc.nextInt()-1;

            int[] fi = sc.nextIntArray(k);
            sc.sort(fi);

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++){
                tr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }
            if(tr.isSpecial()){
                doSpecial(n, k, a, fi, sb);
                continue;
            }

            tr.setAncestors(tr.nodelist.get(0), null);

            int[][] dists = new int[n][n];
            for(int i=0;i<n;i++){
                dists[i][i] = 0;
            }
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    Node lcaij = tr.lca(tr.nodelist.get(i), tr.nodelist.get(j));
                    int ldi = tr.level[i]-tr.level[lcaij.id];
                    int ldj = tr.level[j]-tr.level[lcaij.id];
                    dists[i][j] = ldi+ldj;
                    dists[j][i] = ldi+ldj;
                }
            }

            ArrayList<Integer> anslist = new ArrayList<>();
            ArrayList<Integer> speciallist = new ArrayList<>();
            for(int i=0;i<n;i++){
                int max = Integer.MIN_VALUE;
                int sp = -1;
                for (int value : fi) {
                    int spnode = value - 1;
                    int d = dists[a][spnode] - dists[i][spnode];
                    max = Math.max(max, d);
                    if(max==d){
                        sp = value;
                    }
                }
                anslist.add(max);
                speciallist.add(sp);
            }

            for(int i:anslist)
                sb.append(i).append(" ");
            sb.append("\n");
            for(int i:speciallist)
                sb.append(i).append(" ");
            sb.append("\n");
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
