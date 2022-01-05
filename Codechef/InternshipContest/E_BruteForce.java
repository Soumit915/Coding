package Codechef.InternshipContest;

import java.io.*;
import java.util.*;

public class E_BruteForce {
    static class Node {
        int id;
        boolean isVisited;
        int distance;
        boolean leaves;
        ArrayList<Node> edgelist;
        Node(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.distance = -1;
            this.edgelist = new ArrayList<>();
        }
    }

    static class Graph
    {
        ArrayList<Node> nodelist;
        Graph(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
                nodelist.add(new Node(i));
        }
        public void addEdge(int u, int v)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.edgelist.add(nv);
            nv.edgelist.add(nu);
        }
        public void bfs(Node n1)
        {
            Queue<Node> q = new LinkedList<>();
            q.add(n1);
            n1.isVisited = true;
            n1.distance = 0;

            while (!q.isEmpty())
            {
                Node nd = q.remove();

                for(Node neighbour: nd.edgelist)
                {
                    if(neighbour.isVisited)
                        continue;

                    q.add(neighbour);
                    neighbour.distance = nd.distance+1;
                    neighbour.isVisited = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();

            Graph gr = new Graph(n);
            for(int i=0;i<n-1;i++)
            {
                gr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }

            ArrayList<Node> leaves = new ArrayList<>();
            for(Node nd: gr.nodelist)
            {
                if(nd.edgelist.size() == 1) {
                    leaves.add(nd);
                    nd.leaves = true;
                }
            }

            int max = -1;
            for(int i=0;i<leaves.size();i++)
            {
                gr.bfs(leaves.get(i));
                for(Node nd: leaves)
                {
                    if(nd == leaves.get(i))
                        continue;

                    max = Math.max(max, nd.distance);
                }

                for(Node nd: gr.nodelist)
                {
                    nd.distance = 0;
                    nd.isVisited = false;
                }
            }

            int ans = (int) Math.ceil(((double)max)/2);
            sc.println(ans+"");
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
