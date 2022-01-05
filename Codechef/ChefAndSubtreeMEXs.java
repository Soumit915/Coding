package Codechef;

import java.io.*;
import java.util.*;

public class ChefAndSubtreeMEXs {
    static class Node
    {
        int id;
        Node parent;
        long height;
        long nodecount;
        long mexv;
        ArrayList<Node> adjacentnode = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.height = 0;
            this.nodecount = 1;
            this.parent = null;
            this.mexv = 0;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        Tree(int n)
        {
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
                nodelist.add(new Node(i));
        }
        public void addEdge(int p, int c)
        {
            Node parent = nodelist.get(p);
            Node child = nodelist.get(c);

            parent.adjacentnode.add(child);
            child.parent = parent;
        }
        public void dfs()
        {
            Node source = nodelist.get(0);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adjacentnode.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentnode.get(ptr);
                    if(next.adjacentnode.size()==0)
                    {
                        next.nodecount = 1;
                        next.height = 1;
                        next.mexv = 1;
                    }
                    else
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
                    for(Node nd: cur.adjacentnode)
                    {
                        cur.height = Math.max(cur.height, nd.height);
                    }
                    cur.height += 1;

                    for(Node nd: cur.adjacentnode)
                    {
                        cur.nodecount += nd.nodecount;
                    }

                    long mv = -1;
                    for(Node nd: cur.adjacentnode)
                    {
                        mv = Math.max(mv, nd.mexv);
                    }

                    cur.mexv = mv + cur.nodecount;
                }
            }
        }
       /* public long sum()
        {
            long sum = 0;
            Node ptr = nodelist.get(0);
            while(true)
            {
                sum += ptr.nodecount;
                if(ptr.adjacentnode.size()==0)
                    return sum;

                Node next = null;
                long maxH = -1;
                long mxc = -1;
                for(Node nd : ptr.adjacentnode)
                {
                    if(nd.height == maxH)
                    {
                        if(nd.nodecount >= mxc)
                        {
                            next = nd;
                            maxH = nd.height;
                            mxc = nd.nodecount;
                        }
                    }
                    else if(nd.height > maxH)
                    {
                        next = nd;
                        maxH = nd.height;
                        mxc = nd.nodecount;
                    }
                }

                ptr = next;
            }
        }*/
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n-1);
            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++)
            {
                tr.addEdge(arr[i]-1, i+1);
            }

            tr.dfs();
            sb.append(tr.nodelist.get(0).mexv).append("\n");
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
