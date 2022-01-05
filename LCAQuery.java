import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class LCAQuery {
    static class Node
    {
        int id;
        ArrayList<Node> neighbour = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }

    static class Tree
    {
        ArrayList<Node> nodelist;
        Node[][] lcaDP;
        int[] level;
        int logn;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
            logn =  (int) Math.ceil(Math.log(n)/Math.log(2));
            lcaDP = new Node[n][logn+1];
            level = new int[n];
        }
        public void addEdge(int u, int v)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.neighbour.add(nv);
            nv.neighbour.add(nu);
        }
        public void setAncestor(Node source, Node parent)
        {
            lcaDP[source.id][0] = parent;
            for(int i=1;i<=logn;i++)
                if(lcaDP[source.id][i-1] == null)
                    lcaDP[source.id][i] = null;
                else
                    lcaDP[source.id][i] = lcaDP[lcaDP[source.id][i-1].id][i-1];
            for(Node nd: source.neighbour)
                if(nd!=parent)
                {
                    level[nd.id] = level[source.id]+1;
                    setAncestor(nd, source);
                }
        }
        public Node lca(Node u, Node v)
        {
            if(level[u.id]<level[v.id])
            {
                Node nd = u;
                u = v;
                v = nd;
            }

            for(int i=logn;i>=0;i--)
                if(level[u.id]-(int)Math.pow(2, i)>=level[v.id])
                    u = lcaDP[u.id][i];

            if(u==v)
                return u;

            for(int i=logn;i>=0;i--)
            {
                if(lcaDP[u.id][i] !=null && lcaDP[u.id][i] != lcaDP[v.id][i])
                {
                    u = lcaDP[u.id][i];
                    v = lcaDP[v.id][i];
                }
            }

            return lcaDP[u.id][0];
        }
    }

    public static void main(String[] args) throws IOException
    {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");
        int n = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=0;i<n-1;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            tr.addEdge(u, v);
        }

        tr.setAncestor(tr.nodelist.get(0), null);

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(q-->0)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            sb.append(tr.lca(tr.nodelist.get(u), tr.nodelist.get(v)).id+1).append("\n");
        }

        sc.println(sb.toString());

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
