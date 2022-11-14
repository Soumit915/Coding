package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week6;

import java.io.*;
import java.util.*;

public class IsThisBST {
    static class Node
    {
        int id;
        int key;
        Node left;
        Node right;

        int max;
        int min;
        Node(int id)
        {
            this.id = id;
            this.left = null;
            this.right = null;
        }
    }

    static class Tree {
        ArrayList<Node> nodelist;
        Node root;
        ArrayList<Integer> inorder;

        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            this.inorder = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
            }

            root = nodelist.get(0);
        }

        public void addEdge(int id, int node, int left, int right) {
            Node nd = this.nodelist.get(id);
            nd.key = node;
            nd.max = node;
            nd.min = node;
            if (left < 0)
                nd.left = null;
            else nd.left = this.nodelist.get(left);

            if (right < 0)
                nd.right = null;
            else nd.right = this.nodelist.get(right);
        }

        public boolean isBst()
        {
            Stack<Node> stk = new Stack<>();
            Stack<Integer> stkptr = new Stack<>();
            stk.push(root);
            stkptr.push(-1);

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = stkptr.pop();

                if(ptr==-1)
                {
                    stk.push(cur);
                    stkptr.push(0);

                    if(cur.left!=null)
                    {
                        stk.push(cur.left);
                        stkptr.push(-1);
                    }
                }
                else if(ptr==0)
                {
                    stk.push(cur);
                    stkptr.push(1);

                    if(cur.right!=null)
                    {
                        stk.push(cur.right);
                        stkptr.push(-1);
                    }
                }
                else
                {
                    if(cur.left!=null)
                    {
                        if(cur.left.max>=cur.key)
                            return false;
                        cur.min = cur.left.min;
                    }

                    if(cur.right!=null)
                    {
                        if(cur.right.min<cur.key)
                            return false;
                        cur.max = cur.right.max;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();

        if(n==0)
        {
            System.out.println("CORRECT");
            System.exit(0);
        }

        Tree tr = new Tree(n);
        for(int i=0;i<n;i++)
        {
            int node = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            tr.addEdge(i, node, left, right);
        }

        if(tr.isBst())
            System.out.println("CORRECT");
        else System.out.println("INCORRECT");

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
