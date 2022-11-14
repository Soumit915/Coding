
//https://stackoverflow.com/questions/68551474/mex-from-root-node-to-every-other-node-in-a-tree

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class PathMex {
    static class Node{
        int nodeno;
        int value;
        ArrayList<Node> adjacencylist = new ArrayList<>();
        Node(int nodeno, int value){
            this.nodeno = nodeno;
            this.value = value;
        }
    }

    static class AugTree{
        ArrayList<Node> nodelist;
        AugTree(int n, int[] val){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, val[i]));
            }
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);
            nv.adjacencylist.add(nu);
        }
        public void dfs(Node source, int curmex, int[] result, HashMap<Integer, Integer> map){
            if (!map.containsKey(source.value)) {
                map.put(source.value, 1);
            }
            else {
                map.put(source.value, map.get(source.value) + 1);
            }

            while(map.containsKey(curmex))
                curmex++;
            result[source.nodeno] = curmex;

            for (Node child : source.adjacencylist) {
                this.dfs(child, curmex, result, map);
            }

            if (map.containsKey(source.value)) {
                if (map.get(source.value) == 1) {
                    map.remove(source.value);
                }
                else {
                    map.put(source.value, map.get(source.value) - 1);
                }
            }
        }
    }

    static int[] solve(int n, int[] values, int[] parent){
        int[] result = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();

        AugTree augTree = new AugTree(n, values);
        for(int i=1;i<n;i++){
            augTree.addEdge(i, parent[i-1]-1);
        }
        augTree.dfs(augTree.nodelist.get(0), 1, result, map);

        return result;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            int n = sc.nextInt();
            int[] val = new int[n];
            for(int i=0;i<n;i++){
                val[i] = sc.nextInt();
            }

            int[] parent = new int[n-1];
            for(int i=0;i<n-1;i++){
                parent[i] = sc.nextInt();
            }

            int[] ans = solve(n, val, parent);
            for(int i: ans)
                sb.append(i).append(" ");
            sb.append("\n");
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
