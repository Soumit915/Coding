package Codechef.InternshipContest;

import java.io.*;
import java.util.*;

public class E {
    static class Node
    {
        int id;
        boolean isVisited;
        ArrayList<Node> adjacentList = new ArrayList<>();
        ArrayList<Integer> dist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adjacentList.add(nv);
            nv.adjacentList.add(nu);
        }
        public void dfs()
        {
            Node source = nodelist.get(0);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adjacentList.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentList.get(ptr);
                    if(next.isVisited)
                        continue;

                    next.isVisited = true;
                    if(next.adjacentList.size()==1)
                    {
                        next.dist.add(0);
                    }
                    else
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
                    for(Node nd: cur.adjacentList)
                    {
                        if(!stk.isEmpty() && nd==stk.peek())
                            continue;

                        int max = 0;
                        for(int i: nd.dist)
                            max = Math.max(max, i);
                        cur.dist.add(max+1);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                tr.addEdge(u, v);
            }

            tr.dfs();

            /*for(Node nd: tr.nodelist)
            {
                System.out.print(nd.id+" ");
                for(int i: nd.dist)
                    System.out.print(i+" ");
                System.out.println();
            }*/

            int max = 0;
            for(Node nd: tr.nodelist)
            {
                nd.dist.sort(Collections.reverseOrder());
                if(nd.dist.size()==1)
                    max = Math.max(max, nd.dist.get(0));
                else max = Math.max(max, nd.dist.get(0)+nd.dist.get(1));
            }

            int ans = (int) Math.ceil(((double)max)/2);
            sb.append(ans).append("\n");
        }
        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/1000);
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