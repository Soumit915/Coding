import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Juspay_SecondMinimumTime {
    static class Sorter implements Comparator<Node>{
        public int compare(Node n1, Node n2){
            return Integer.compare(n1.id, n2.id);
        }
    }
    static class Node{
        int id;
        boolean isVisited1;
        int dist1;
        Node prev;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited1 = false;
            this.dist1 = -1;
            this.prev = null;
        }
    }
    static class Graph{
        ArrayList<Node> nodelist;
        Graph(int n){
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public void bfs(){
            Node source = nodelist.get(0);
            Queue<Node> q = new LinkedList<>();

            q.add(source);
            source.isVisited1 = true;
            source.dist1 = 0;

            while(!q.isEmpty()){
                Node cur = q.remove();
                if(cur.id == nodelist.size()-1)
                    continue;

                for(Node node: cur.adlist){
                    if(!node.isVisited1){
                        node.isVisited1 = true;
                        node.dist1 = cur.dist1+1;
                        node.prev = cur;
                        q.add(node);
                    }
                }
            }
        }
        public void bfs(Node start, Node end){

            for(Node node: nodelist){
                node.prev = null;
                node.dist1 = 0;
                node.isVisited1 = false;
            }

            Node source = nodelist.get(0);
            Queue<Node> q = new LinkedList<>();

            q.add(source);
            source.isVisited1 = true;
            source.dist1 = 0;

            while(!q.isEmpty()){
                Node cur = q.remove();
                if(cur.id == nodelist.size()-1)
                    continue;

                for(Node node: cur.adlist){
                    if((node==start && cur==end) || (node==end && cur==start))
                        continue;

                    if(!node.isVisited1){
                        node.isVisited1 = true;
                        node.dist1 = cur.dist1+1;
                        node.prev = cur;
                        q.add(node);
                    }
                }
            }
        }
    }

    static long countTime(long edgecount, long t, long c){
        long ans = 0;
        for(int i=1;i<=edgecount;i++){
            ans = (ans + c);
            long k = ans / t;
            if(k%2!=0 && i!=edgecount){
                ans = (k+1)*t;
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        long t = sc.nextLong();
        long c = sc.nextLong();

        Graph gr = new Graph(n);
        for(int i=0;i<m;i++){
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            gr.addEdge(u, v);
        }

        for(Node node: gr.nodelist){
            node.adlist.sort(new Sorter());
        }

        gr.bfs();

        int edgecount = gr.nodelist.get(n-1).dist1;
        if (edgecount != -1) {
            Node ptr = gr.nodelist.get(n - 1);
            Stack<Node> stk = new Stack<>();
            while (ptr != null) {
                stk.push(ptr);
                ptr = ptr.prev;
            }

            ArrayList<Node> path = new ArrayList<>();
            while (!stk.isEmpty()) {
                path.add(stk.pop());
            }

            ArrayList<Integer> arlist = new ArrayList<>();
            for (int i = 1; i < path.size(); i++) {
                Node start = path.get(i - 1);
                Node end = path.get(i);
                gr.bfs(start, end);
                arlist.add(gr.nodelist.get(n - 1).dist1);
            }

            Collections.sort(arlist);

            for (Integer integer : arlist) {
                if (integer != -1) {
                    System.out.println(countTime(integer, t, c));
                    System.exit(0);
                }
            }

        }

        System.out.println(-1);

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
