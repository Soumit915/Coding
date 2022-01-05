import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class LCAQuery_SparseTable {
    static class Node {
        int id;
        int level;
        ArrayList<Node> adjacentlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
        public int compareTo(Node n)
        {
            return Integer.compare(this.level, n.level);
        }
        public Node min(Node a)
        {
            if(this.compareTo(a)<0)
            {
                return this;
            }
            else
            {
                return a;
            }
        }
        public Node max(Node a)
        {
            if(this.compareTo(a)>0)
            {
                return this;
            }
            else
            {
                return a;
            }
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        ArrayList<Node> eulerWalk;
        Tree(int n)
        {
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v)
        {
            Node nu = this.nodelist.get(u);
            Node nv = this.nodelist.get(v);

            nu.adjacentlist.add(nv);
            nv.adjacentlist.add(nu);
        }
        public void eulerWalk(Node source)
        {
            eulerWalk = new ArrayList<>();
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            source.level = 0;

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                eulerWalk.add(cur);

                if(ptr<cur.adjacentlist.size()-1)
                {
                    ptr++;
                    Node parent = null;
                    if(!stk.isEmpty())
                        parent = stk.peek();
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentlist.get(ptr);
                    if(next == parent)
                        continue;

                    next.level = cur.level+1;
                    stk.push(next);
                    ptrstk.push(-1);
                }
            }
        }
    }
    public static int log(int n)
    {
        if(n==0)
            return n;
        return (int) (Math.log(n)/Math.log(2));
    }
    public static void built(Node[][] sparseTables, ArrayList<Node> eulerWalk)
    {
        int n = eulerWalk.size();
        int cols = sparseTables[0].length;
        for(int i=0;i<n;i++)
        {
            sparseTables[i][0] = eulerWalk.get(i);
        }

        for(int i=1;i<cols;i++)
        {
            for(int j=0;j<n;j++)
            {
                int val = (int) Math.pow(2, i-1);
                if(j+val<n)
                {
                    sparseTables[j][i] = sparseTables[j][i-1].min(sparseTables[j+val][i-1]);
                }
            }
        }
    }
    public static Node query(Node[][] sparseTable, int l, int r)
    {
        int diff = log(r-l);
        Node min = sparseTable[l][diff];

        int left = (r-l+1) - (int) Math.pow(2, diff);
        min = min.min(sparseTable[l+left][diff]);

        return min;
    }
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int n = sc.nextInt();
        Tree tr = new Tree(n);
        for(int i=0;i<n-1;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            tr.addEdge(u, v);
        }

        tr.eulerWalk(tr.nodelist.get(0));
        ArrayList<Node> eulerWalk = tr.eulerWalk;

        int cols = (int) (Math.ceil(Math.log(eulerWalk.size())/Math.log(2)));
        Node[][] sparseTables = new Node[eulerWalk.size()][cols];
        for(int i=0;i<eulerWalk.size();i++)
        {
            for(int j=0;j<cols;j++)
            {
                sparseTables[i][j] = new Node(0);
                sparseTables[i][j].level = Integer.MAX_VALUE;
            }
        }
        built(sparseTables, eulerWalk);

        int[] fi = new int[n];
        for(int i=eulerWalk.size()-1;i>=0;i--)
        {
            fi[eulerWalk.get(i).id] = i;
        }

        StringBuilder sb = new StringBuilder();
        int q = sc.nextInt();
        while (q-->0)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            if(fi[v]<fi[u])
            {
                int t = u;
                u = v;
                v = t;
            }
            sb.append(query(sparseTables, fi[u], fi[v]).id+1).append("\n");
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
