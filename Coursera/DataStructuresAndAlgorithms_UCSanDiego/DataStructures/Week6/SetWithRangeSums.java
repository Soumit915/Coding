package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week6;

import java.io.*;
import java.util.*;

public class SetWithRangeSums {
    static long mod = (long) 1e9+1;
    static class Node
    {
        long val;
        long sum;
        Node left;
        Node right;
        long min;
        long max;
        int height;
        Node(long val)
        {
            this.val = val;
            this.sum = 0;
            this.left = null;
            this.right = null;
            this.min = this.val;
            this.max = this.val;
            this.height = 0;
        }
    }
    static class Tree
    {
        Node root;
        Tree()
        {
            root = null;
        }
        public int height(Node root)
        {
            if(root==null)
                return 0;
            return root.height;
        }
        public int bf(Node root)
        {
            if(root==null)
                return 0;
            return height(root.left)-height(root.right);
        }
        public long sum(Node nd)
        {
            if(nd==null)
                return 0;
            return nd.sum;
        }
        public Node ll_rotation(Node root)
        {
            Node b = root.left;
            root.left = b.right;
            b.right = root;

            if(b.left!=null) b.min = b.left.min;
            else b.min = b.val;
            if(b.right!=null) b.max = b.right.max;
            else b.max = b.val;
            if(root.left!=null) root.min = root.left.min;
            else root.min = root.val;
            if(root.right!=null) root.max = root.right.max;
            else root.max = root.val;

            root.sum = sum(root.left)+sum(root.right)+ root.val;
            b.sum = sum(b.left)+sum(b.right)+b.val;

            root.height = 1+Math.max(height(root.left), height(root.right));
            b.height = 1+Math.max(height(b.left), height(b.right));
            return b;
        }
        public Node rr_rotation(Node root)
        {
            Node b = root.right;
            root.right = b.left;
            b.left = root;

            if(b.left!=null) b.min = b.left.min;
            else b.min = b.val;
            if(b.right!=null) b.max = b.right.max;
            else b.max = b.val;
            if(root.left!=null) root.min = root.left.min;
            else root.min = root.val;
            if(root.right!=null) root.max = root.right.max;
            else root.max = root.val;

            root.sum = sum(root.left)+sum(root.right)+ root.val;
            b.sum = sum(b.left)+sum(b.right)+b.val;

            root.height = 1+Math.max(height(root.left), height(root.right));
            b.height = 1+Math.max(height(b.left), height(b.right));
            return b;
        }
        public void add(long val)
        {
            if(root==null) {
                root = new Node(val);
                root.sum = val;
                return;
            }
            root = add(root, val);
        }
        public Node add(Node root, long val)
        {
            if(root==null)
            {
                Node node = new Node(val);
                node.height = 1;
                node.sum = node.val;
                return node;
            }
            else if(root.val==val)
                return root;
            else if(root.val<val) {
                root.right = add(root.right, val);
                if(root.right!=null) root.max = root.right.max;
                else root.max = root.val;
            }
            else {
                root.left = add(root.left, val);
                if(root.left!=null) root.min = root.left.min;
                else root.min = root.val;
            }

            root.height = 1+Math.max(height(root.left), height(root.right));
            root.sum = sum(root.left)+sum(root.right)+root.val;
            int bf = bf(root);
            if(bf>1)
            {
                if(val>root.left.val)
                {
                    root.left = rr_rotation(root.left);
                }
                root = ll_rotation(root);
            }
            else if(bf<-1)
            {
                if(val<root.right.val)
                {
                    root.right = ll_rotation(root.right);
                }
                root = rr_rotation(root);
            }
            return root;
        }
        public Node inorderSuccessor(Node root)
        {
            while(root.left!=null)
                root = root.left;
            return root;
        }
        public void delete(long val)
        {
            if(root==null)
                return;
            root = delete(root, val);
        }
        public Node delete(Node root, long val)
        {
            if(root==null)
                return null;
            else if(val<root.val)
            {
                root.left = delete(root.left, val);
                root.sum = sum(root.left)+sum(root.right)+root.val;
                if(root.left!=null) root.min = root.left.min;
                else root.min = root.val;
            }
            else if(val>root.val)
            {
                root.right = delete(root.right, val);
                root.sum = sum(root.left)+sum(root.right)+root.val;
                if(root.right!=null) root.max = root.right.max;
                else root.max = root.val;
            }
            else
            {
                if(root.left==null)
                {
                    return root.right;
                }
                else if(root.right==null)
                {
                    return root.left;
                }
                else
                {
                    Node inorderSuc = inorderSuccessor(root.right);
                    root.val = inorderSuc.val;
                    root.right = delete(root.right, root.val);
                    root.sum = sum(root.left)+sum(root.right)+root.val;
                    root.height = 1+Math.max(height(root.left), height(root.right));
                    return root;
                }
            }

            root.height = 1+Math.max(height(root.left), height(root.right));
            int bf = height(root.left)-height(root.right);
            if(bf > 1 && bf(root.left) == 0)
            {
                root = this.ll_rotation(root);
            }
            else if(bf > 1 && bf(root.left) == 1)
            {
                root = this.ll_rotation(root);
            }
            else if(bf > 1 && bf(root.left) == -1)
            {
                root.left = this.rr_rotation(root.left);
                root = this.ll_rotation(root);
            }
            else if(bf < -1 && bf(root.right) == 0)
            {
                root = this.rr_rotation(root);
            }
            else if(bf < -1 && bf(root.right) == 1)
            {
                root.right = this.ll_rotation(root.right);
                root = this.rr_rotation(root);
            }
            else if(bf < -1 && bf(root.right) == -1)
            {
                root = this.rr_rotation(root);
            }
            return root;
        }
        public boolean find(long val)
        {
            if(root==null)
                return false;
            return find(root, val);
        }
        public boolean find(Node root, long val)
        {
            if(root==null)
                return false;
            else if(val<root.val)
                return find(root.left, val);
            else if(val>root.val)
                return find(root.right, val);
            else return true;
        }
        public long sumRange(long l, long r)
        {
            if(root==null)
                return 0;
            return sumRange(root, l, r);
        }
        public long sumRange(Node root, long l, long r)
        {
            if(root==null)
                return 0;

            //total overlap
            if(root.min>=l && root.max<=r)
                return root.sum;

            //no overlap
            if(root.max<l || root.min>r)
                return 0;

            //partial overlap
            if(root.val>r)
                return sumRange(root.left, l, r);
            else if(root.val<l)
                return sumRange(root.right, l, r);
            else
            {
                return sumRange(root.left, l, r) + root.val + sumRange(root.right, l, r);
            }
        }
        public void inorder()
        {
            if(root==null)
                return;
            inorder(root);
        }
        public void inorder(Node root)
        {
            if(root==null)
                return;
            inorder(root.left);
            System.out.println(root.val+" "+root.sum+" "+root.min+" "+root.max);
            inorder(root.right);
        }
        ArrayList<Long> vals;
        public void in(Node root)
        {
            if(root==null)
                return;
            in(root.left);
            vals.add(root.val);
            in(root.right);
        }
        public long bruteForce(long l, long r)
        {
            vals = new ArrayList<>();
            in(root);
            if(vals == null)
                return 0;
            long sum = 0;
            for(long lv: vals)
                if(lv>=l && lv<=r)
                    sum += lv;
            return sum;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        /*long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");*/

        int n = sc.nextInt();

        Tree tr = new Tree();
        long sumV = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++)
        {
            char ch = sc.next().charAt(0);
            /*char ch = (char) sc.read();
            ch = (char) sc.read();*/
            if(ch=='+')
            {
                long val = sc.nextLong();
                val += sumV;
                val = val%mod;

                if(tr.find(val))
                    continue;
                tr.add(val);
            }
            else if(ch=='-')
            {
                long val = sc.nextLong();
                val += sumV;
                val = val%mod;
                if(!tr.find(val))
                    continue;
                tr.delete(val);
            }
            else if(ch=='?')
            {
                long val = sc.nextLong();
                val += sumV;
                val = val%mod;
                if(tr.find(val))
                    sb.append("Found\n");
                else sb.append("Not found\n");
            }
            else
            {
                long l = sc.nextLong();
                l += sumV;
                l = l%mod;
                long r = sc.nextLong();
                r += sumV;
                r = r%mod;
                sumV = tr.sumRange(l, r);
                /*long st = tr.bruteForce(l, r);
                if(st!=sumV)
                {
                    System.out.println("tc: "+l+" "+r);
                    System.out.println("Wrong Answer "+i);
                    System.out.println(sumV+" "+st);
                    System.exit(0);
                }*/
                sb.append(sumV).append("\n");
                sumV %= mod;
            }
        }

        System.out.println(sb.toString());

        /*long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/1000);*/
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
