package Codechef;

import java.io.*;
import java.util.*;

public class Path_etic_Sum {
    static class Node
    {
        int id;
        int val;
        ArrayList<Node> adjacentList = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }
    static class Tree {
        ArrayList<Node> nodelist;

        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                this.nodelist.add(new Node(i));
            }
        }

        public void add(int u, int v) {
            Node nu = this.nodelist.get(u);
            Node nv = this.nodelist.get(v);

            nu.adjacentList.add(nv);
            nv.adjacentList.add(nu);
        }

        public void dfs() {
            Node source = nodelist.get(0);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            boolean[] isVisited = new boolean[nodelist.size()];
            source.val = 1;
            isVisited[source.id] = true;

            while (!stk.isEmpty()) {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if (ptr < cur.adjacentList.size() - 1) {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);
                    Node nxt = cur.adjacentList.get(ptr);
                    if (!isVisited[nxt.id]) {
                        stk.push(nxt);
                        ptrstk.push(-1);
                        isVisited[nxt.id] = true;
                        if(cur.val==1)
                            nxt.val = 2;
                        else nxt.val = 1;
                    }
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            StringBuilder sb = new StringBuilder();

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                tr.add(u, v);
            }

            tr.dfs();

            for(Node nd: tr.nodelist) {
                sb.append(nd.val).append(" ");
            }
            sb.append("\n");

            System.out.print(sb);
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

        public void println(String a) {
            pw.println(a);
        }

        public void print(String a) {
            pw.print(a);
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
