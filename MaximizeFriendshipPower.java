import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class MaximizeFriendshipPower {
    static long mod = 998244353;
    static class Node{
        int id;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
        }
    }
    static class Graph{
        ArrayList<Node> nodelist;
        Graph(int n){
            this.nodelist = new ArrayList<>();
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
        public ArrayList<Integer> countKingdoms(){
            ArrayList<Integer> componentSizes = new ArrayList<>();
            boolean[] isVisited = new boolean[nodelist.size()];
            for(Node node: nodelist){
                if(!isVisited[node.id]){
                    componentSizes.add(dfs(node, isVisited));
                }
            }
            return componentSizes;
        }
        public int dfs(Node source, boolean[] isVisited){
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.add(source);
            ptrstk.add(-1);
            isVisited[source.id] = true;

            int c = 1;
            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(!isVisited[next.id]){
                        stk.push(next);
                        ptrstk.push(-1);
                        isVisited[next.id] = true;
                        c++;
                    }
                }
            }
            return c;
        }
    }
    static long x, y;
    static void gcdExtended(long a, long b){
        if(a%b==0){
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static long modInverse2(){
        gcdExtended(2, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    static long nC2(long n){
        long nn_1 = (n*(n-1))%mod;
        long by2 = modInverse2();
        return (nn_1*by2)%mod;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            Graph gr = new Graph(n);
            for(int i=0;i<m;i++){
                gr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }

            ArrayList<Long> arr = new ArrayList<>();
            for(int i=0;i<n;i++){
                arr.add(sc.nextLong());
            }
            arr.sort(Collections.reverseOrder());

            ArrayList<Integer> componentSizes = gr.countKingdoms();
            componentSizes.sort(Collections.reverseOrder());

            int ind = 0;
            long ans = 0;
            for(int size: componentSizes){
                long sum = 0;
                for(int i=ind;i<ind+size;i++){
                    sum = (sum + arr.get(i))%mod;
                }
                ind += size;
                ans = (ans + (nC2(sum)))%mod;
            }

            sb.append(ans).append("\n");
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
