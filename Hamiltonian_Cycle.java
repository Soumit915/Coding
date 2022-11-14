import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Hamiltonian_Cycle {

    static class Vertex
    {
        int id;
        boolean isVisited;
        ArrayList<Vertex> adjacentlist;
        Vertex(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.adjacentlist = new ArrayList<>();
        }
    }

    static class Graph
    {
        ArrayList<Vertex> vertexlist;
        Graph(int n)
        {
            this.vertexlist = new ArrayList<Vertex>(n);
            for(int i=0;i<n;i++)
            {
                this.vertexlist.add(new Vertex(i));
            }
        }
        public void addEdge(int u, int v)
        {
            Vertex start = vertexlist.get(u);
            Vertex end = vertexlist.get(v);

            start.adjacentlist.add(end);
            end.adjacentlist.add(start);
        }
    }

    static class HamiltonianCycle
    {
        int n;
        Vertex startingVertex;
        ArrayList<Vertex> cycle = new ArrayList<>();
        HamiltonianCycle(int n, Vertex start)
        {
            this.n = n;
            this.startingVertex = start;
        }
        public boolean isHamiltonianCycle()
        {
            startingVertex.isVisited = true;
            return dfs(this.startingVertex,1);
        }
        public boolean dfs(Vertex source, int nov)
        {
            for(Vertex adnode: source.adjacentlist)
            {
                if(adnode == startingVertex && nov == n)
                {
                    cycle.add(adnode);
                    cycle.add(source);
                    return true;
                }

                if(!adnode.isVisited)
                {
                    adnode.isVisited = true;
                    if(dfs(adnode,nov+1))
                    {
                        cycle.add(source);
                        return true;
                    }
                    adnode.isVisited = false;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        Graph gr = new Graph(n);

        int m = sc.nextInt();

        for(int i=0;i<m;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            gr.addEdge(u, v);
        }

        /*gr.addEdge(0,1);
        gr.addEdge(0,2);
        gr.addEdge(0,3);
        gr.addEdge(2,1);
        gr.addEdge(4,1);
        gr.addEdge(5,1);
        gr.addEdge(2,3);
        gr.addEdge(2,5);
        gr.addEdge(3,5);
        gr.addEdge(4,5);*/

        HamiltonianCycle hc = new HamiltonianCycle(n,gr.vertexlist.get(0));
        boolean status = hc.isHamiltonianCycle();
        ArrayList<Vertex> cyclelist = hc.cycle;
        Collections.reverse(cyclelist);

        if(status)
        {
            System.out.println("Hamiltonian Cycle is present.");
            for(Vertex v: cyclelist)
                System.out.print(v.id+ " ");
            System.out.println();
        }
        else
        {
            System.out.println("Hamiltonian Cycle is not present.");
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
