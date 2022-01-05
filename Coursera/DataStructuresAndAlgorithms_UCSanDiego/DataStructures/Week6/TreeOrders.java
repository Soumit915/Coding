package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week6;

import java.io.*;
import java.util.*;

public class TreeOrders {
    static class Node
    {
        int id;
        int key;
        Node left;
        Node right;
        Node(int id)
        {
            this.id = id;
            this.left = null;
            this.right = null;
        }
    }

    static class Tree
    {
        ArrayList<Node> nodelist;
        Node root;
        ArrayList<Integer> inorder;
        ArrayList<Integer> preorder;
        ArrayList<Integer> postorder;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            this.inorder = new ArrayList<>(n);
            this.preorder = new ArrayList<>(n);
            this.postorder = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }

            root = nodelist.get(0);
        }
        public void addEdge(int id, int node, int left, int right)
        {
            Node nd = this.nodelist.get(id);
            nd.key = node;
            if(left<0)
                nd.left = null;
            else nd.left = this.nodelist.get(left);

            if(right<0)
                nd.right = null;
            else nd.right = this.nodelist.get(right);
        }
        public void inorder()
        {
            if(root==null)
                return;
            inorder(root);
        }
        public void inorder(Node root)
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
                    inorder.add(cur.key);

                    stk.push(cur);
                    stkptr.push(1);

                    if(cur.right!=null)
                    {
                        stk.push(cur.right);
                        stkptr.push(-1);
                    }
                }
            }
        }
        public void preorder()
        {
            if(root==null)
                return;
            preorder(root);
        }
        public void preorder(Node root)
        {
            Stack<Node> stk = new Stack<>();
            Stack<Integer> stkptr = new Stack<>();
            stk.push(root);
            stkptr.push(-1);
            preorder.add(root.key);

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
                        preorder.add(cur.left.key);
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
                        preorder.add(cur.right.key);
                        stk.push(cur.right);
                        stkptr.push(-1);
                    }
                }
            }
        }
        public void postorder()
        {
            if(root==null)
                return;
            postorder(root);
        }
        public void postorder(Node root)
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
                    postorder.add(cur.key);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=0;i<n;i++)
        {
            int node = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            tr.addEdge(i, node, left, right);
        }

        tr.inorder();
        tr.preorder();
        tr.postorder();

        StringBuilder sb = new StringBuilder();
        for(int i: tr.inorder)
            sb.append(i).append(" ");
        sb.append("\n");
        for(int i: tr.preorder)
            sb.append(i).append(" ");
        sb.append("\n");
        for(int i: tr.postorder)
            sb.append(i).append(" ");
        sb.append("\n");

        System.out.println(sb.toString());

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
