package Codeforces.Round678Div2;

import java.io.*;
import java.util.*;

public class D {
    static class Node
    {
        int id;
        long val;
        Node parent;
        ArrayList<Node> adjacentnode = new ArrayList<>();

        long max;
        long sum;
        long leaves;
        Node(int id)
        {
            this.id = id;
            this.val = 0;
            this.parent = null;
            this.max = -1;
            this.sum = 0;
            this.leaves = 0;
        }
    }
    static class Tree {
        ArrayList<Node> nodelist;

        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int xi, int yi) {
            Node nu = nodelist.get(xi);
            Node nv = nodelist.get(yi);

            nu.adjacentnode.add(nv);
            nv.parent = nu;
        }

        public void setParent()
        {
            Node source = nodelist.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                if(ptr<cur.adjacentnode.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentnode.get(ptr);

                    if(cur.parent==next)
                    {
                        continue;
                    }

                    if(next.adjacentnode.size()==0)
                    {
                        next.leaves = 1;
                        next.max = next.val;
                        next.sum = 0;
                    }
                    else
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
                    for(Node nd: cur.adjacentnode) {
                        cur.max = Math.max(cur.max, nd.max);
                    }

                    for(Node nd: cur.adjacentnode)
                    {
                        if(nd.max!=cur.max)
                        {
                            cur.sum += (cur.max-nd.max)*nd.leaves;
                        }
                        cur.sum += nd.sum;
                        cur.leaves += nd.leaves;
                    }

                    if(cur.val>cur.sum)
                    {
                        cur.val -= cur.sum;
                        cur.max += Math.ceil(((double)cur.val)/cur.leaves);
                        if(cur.val%cur.leaves!=0)
                            cur.sum = cur.leaves - (cur.val)%cur.leaves;
                        else cur.sum = 0;
                    }
                    else
                    {
                        cur.sum -= cur.val;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int n = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=1;i<n;i++)
        {
            tr.addEdge(sc.nextInt()-1, i);
        }

        for(int i=0;i<n;i++)
        {
            tr.nodelist.get(i).val = sc.nextLong();
        }

        tr.setParent();

        long l = tr.nodelist.get(0).max;
        System.out.println(l+"");

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


