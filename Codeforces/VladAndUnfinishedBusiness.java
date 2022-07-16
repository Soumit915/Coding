package Codeforces;

import java.io.*;
import java.util.*;

public class VladAndUnfinishedBusiness {

    static class Node{
        int id;
        Node parent;
        List<Node> adlist = new ArrayList<>();

        int return_cost;
        int single_cost;

        boolean isDest;
        boolean hasWork;

        Node(int id){
            this.id = id;
            this.parent = null;
            this.isDest = false;
            this.hasWork = false;
            this.return_cost = 0;
            this.single_cost = 0;
        }
    }

    static class Tree{

        List<Node> nodelist;
        Tree(int n){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }

        public void setParent(int x){
            Node source = nodelist.get(x);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);

            while(!stk.isEmpty()) {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr < cur.adlist.size() - 1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(stk.isEmpty() || cur.parent!=next){
                        stk.push(next);
                        ptrstk.push(-1);
                        next.parent = cur;
                    }
                }
                else {
                    for(Node node: cur.adlist){
                        if(node == cur.parent)
                            continue;
                        if(node.hasWork || node.return_cost > 0){
                            cur.return_cost += node.return_cost + 2;
                        }

                        cur.isDest |= node.isDest;
                    }
                }
            }
        }

        public int dfs(int x){
            Node source = nodelist.get(x);

            Stack<Node> stk = new Stack<>();
            stk.push(source);

            int cost = 0;

            while(!stk.isEmpty()){
                source = stk.pop();

                for(Node node: source.adlist){
                    if(node == source.parent)
                        continue;

                    if((node.hasWork || node.return_cost>0) && !node.isDest){
                        cost += (node.return_cost + 2);
                    }
                    else if(node.isDest){
                        cost++;
                        stk.push(node);
                    }
                }
            }

            return cost;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;

            Tree tr = new Tree(n);

            int[] a = new int[k];
            for(int i=0;i<k;i++){
                a[i] = sc.nextInt() - 1;
                tr.nodelist.get(a[i]).hasWork = true;
            }

            for(int i=0;i<n-1;i++){
                tr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }

            tr.nodelist.get(y).isDest = true;

            tr.setParent(x);

            int ans = tr.dfs(x);

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
