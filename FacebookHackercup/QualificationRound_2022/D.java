package FacebookHackercup.QualificationRound_2022;

import java.util.*;
import java.io.*;

public class D {

    static class Node{
        int id;
        int q_count;

        HashMap<Integer, Long> admap = new HashMap<>();
        HashSet<Query> query_set = new HashSet<>();

        Node(int id){
            this.id = id;
        }
    }

    static class Graph{
        List<Node> nodelist;

        Graph(int n){
            nodelist = new ArrayList<>();

            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v, long w){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.admap.put(nv.id, w);
            nv.admap.put(nu.id, w);
        }
    }

    static class Query {
        int id;
        Node x, y;

        Query(int id, Node x, Node y){
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int ti=1;ti<=tc;ti++){

            sb.append("Case #").append(ti).append(": ");

            int n = sc.nextInt();
            int m = sc.nextInt();
            int q = sc.nextInt();

            Graph gr = new Graph(n);

            for(int i=0;i<m;i++){
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                long w = sc.nextLong();

                gr.addEdge(u, v, w);
            }

            List<Query> querylist = new ArrayList<>();
            for(int i=0;i<q;i++){
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;

                Node nx = gr.nodelist.get(x);
                Node ny = gr.nodelist.get(y);

                nx.q_count++;
                ny.q_count++;

                Query query = new Query(i, nx, ny);

                querylist.add(query);

                nx.query_set.add(query);
                ny.query_set.add(query);
            }

            HashMap<Integer, HashMap<Integer, Long>> carry_map = new HashMap<>();

            List<Node> nodelist = new ArrayList<>(gr.nodelist);
            nodelist.sort((n1, n2) -> {
                int c = Integer.signum(n1.admap.size() - n2.admap.size());
                if(c==0)
                    return Integer.signum(n1.q_count - n2.q_count);
                else return c;
            });

            long[] ans = new long[q];
            for(Node node : nodelist){
                for(Query query : node.query_set){
                    int x_id = query.x.id;
                    int y_id = query.y.id;

                    if(carry_map.getOrDefault(x_id, new HashMap<>()).containsKey(y_id)){
                        ans[query.id] = carry_map.get(x_id).get(y_id);

                        if(node.id == x_id){
                            query.y.query_set.remove(query);
                        }
                        else{
                            query.x.query_set.remove(query);
                        }

                        continue;
                    }

                    Node dest_node;
                    if(x_id == node.id)
                        dest_node = query.y;
                    else dest_node = query.x;

                    long tot = node.admap.getOrDefault(dest_node.id, 0L) * 2L;
                    for(int adnode_id : node.admap.keySet()){
                        if(adnode_id == y_id)
                            continue;

                        if(dest_node.admap.containsKey(adnode_id)){
                            tot += Math.min(node.admap.get(adnode_id) , dest_node.admap.getOrDefault(adnode_id, 0L));
                        }
                    }

                    HashMap<Integer, Long> dest_map = carry_map.getOrDefault(x_id, new HashMap<>());
                    dest_map.put(y_id, tot);
                    carry_map.put(x_id, dest_map);

                    dest_map = carry_map.getOrDefault(y_id, new HashMap<>());
                    dest_map.put(x_id, tot);
                    carry_map.put(y_id, dest_map);

                    ans[query.id] = tot;
                }
            }

            for(long l : ans){
                sb.append(l).append(" ");
            }
            sb.append("\n");
        }

        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

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
