package FacebookHackercup.QualificationRound_2021;

import java.io.*;
import java.util.*;

public class A2 {
    static class Node{
        int id;
        boolean isVisited;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
        }
        public String toString(){
            return this.id+"";
        }
    }
    static class Graph{
        ArrayList<Node> nodelist;
        int[][] dist = new int[26][26];
        Graph(){
            int n = 26;
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
        }
        public void countDists(){
            for(int i=0;i<26;i++){
                for(int j=0;j<26;j++){
                    dist[i][j] = -1;
                    this.nodelist.get(j).isVisited = false;
                }

                dist[i][i] = 0;
                bfs(this.nodelist.get(i));
            }
        }
        public void bfs(Node source){
            Queue<Node> q = new LinkedList<>();
            q.add(source);
            source.isVisited = true;

            while (!q.isEmpty()){
                Node cur = q.remove();

                for(Node next: cur.adlist){
                    if(!next.isVisited){
                        this.dist[source.id][next.id] = this.dist[source.id][cur.id]+1;
                        next.isVisited = true;
                        q.add(next);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        Soumit output = new Soumit("Input.txt");
        output.streamOutput("Output.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++) {
            sb.append("Case #").append(testi).append(": ");

            String s = sc.next();
            int l = s.length();
            int[] hash = new int[26];
            for(int i=0;i<l;i++){
                hash[s.charAt(i)-65]++;
            }

            Graph gr = new Graph();
            int edges = sc.nextInt();
            int[][] admat = new int[26][26];
            for(int i=0;i<26;i++){
                Arrays.fill(admat[i], INF);
                admat[i][i] = 0;
            }

            for(int i=0;i<edges;i++){
                String edge = sc.next();
                int u = edge.charAt(0)-65;
                int v = edge.charAt(1)-65;
                admat[u][v] = 1;
                gr.addEdge(u, v);
            }

            gr.countDists();

            for(int i=0;i<26;i++){
                System.out.println(Arrays.toString(gr.dist[i]));
            }

            int[][] check = floydWarshall(admat);
            for(int i=0;i<26;i++){
                for(int j=0;j<26;j++){
                    if(check[i][j]!=gr.dist[i][j]){
                        if(check[i][j]!=INF || gr.dist[i][j]!=-1){
                            System.out.println("Wrong answer");
                            System.out.println(s+" "+edges+" "+i+" "+j);
                            System.out.println(check[i][j]+" "+gr.dist[i][j]);
                            System.exit(0);
                        }
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for(int i=0;i<26;i++){
                int sum = 0;
                for(int j=0;j<26;j++){
                    if(hash[j]>0 && gr.dist[j][i]==-1){
                        sum = Integer.MAX_VALUE;
                        break;
                    }
                    sum += gr.dist[j][i]*hash[j];
                }
                min = Math.min(min, sum);
            }

            if(min==Integer.MAX_VALUE)
                sb.append("-1\n");
            else sb.append(min).append("\n");
        }
        output.println(sb.toString());

        sc.close();
        output.close();
    }

    final static int INF = 99999, V = 26;

    static int[][] floydWarshall(int[][] graph)
    {
        int[][] dist = new int[V][V];
        int i, j, k;

        /* Initialize the solution matrix
           same as input graph matrix.
           Or we can say the initial values
           of shortest distances
           are based on shortest paths
           considering no intermediate
           vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration,
               we have shortest
               distances between all pairs
               of vertices such that
               the shortest distances consider
               only the vertices in
               set {0, 1, 2, .. k-1} as
               intermediate vertices.
          ----> After the end of an iteration,
                vertex no. k is added
                to the set of intermediate
                vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        //printSolution(dist);
        return dist;
    }

    static void printSolution(int[][] dist)
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
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
            byte[] buf = new byte[3000064]; // line length
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

        public void sort(int[] arr) {
            ArrayList<Integer> arlist = new ArrayList<>();
            for (int i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
        }

        public void sort(long[] arr) {
            ArrayList<Long> arlist = new ArrayList<>();
            for (long i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
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
