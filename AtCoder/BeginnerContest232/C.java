package AtCoder.BeginnerContest232;

import java.io.*;
import java.util.*;

public class C {
    static class Node{
        int id;
        int pid;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.pid = id;
        }
    }

    static class Graph{
        Node[] nodes;
        Graph(int n){
            nodes = new Node[n];
            for(int i=0;i<n;i++){
                nodes[i] = new Node(i);
            }
        }
        public void addEdge(int u, int v){
            Node nu = nodes[u];
            Node nv = nodes[v];

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
    }

    static boolean getNextPermutation(Node[] a){
        int n = a.length;
        for(int i=n-1;i>0;i--){
            int l = -1;
            if(a[i].id>a[i-1].id){
                for(int j=i;j<n;j++){
                    if(a[j].id>a[i-1].id)
                        l = j;
                }

                Node t = a[l];
                a[l] = a[i-1];
                a[i-1] = t;

                for(int j=i;j<n-j+i-1;j++){
                    t = a[j];
                    a[j] = a[n-j+i-1];
                    a[n-j+i-1] = t;
                }
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr1 = new Graph(n);
        Graph gr2 = new Graph(n);

        for(int i=0;i<m;i++){
            gr1.addEdge(sc.nextInt()-1, sc.nextInt()-1);
        }
        for(int i=0;i<m;i++){
            gr2.addEdge(sc.nextInt()-1, sc.nextInt()-1);
        }

        do{
            for(int i=0;i<n;i++){
                gr2.nodes[i].pid = i;
            }

            boolean flag = true;
            for(int i=0;i<n && flag;i++){
                Node node_gr1 = gr1.nodes[i];
                Node node_gr2 = gr2.nodes[i];

                if(node_gr1.adlist.size() != node_gr2.adlist.size()){
                    flag = false;
                    break;
                }

                Set<Integer> set = new HashSet<>();
                for(Node neighbours: node_gr1.adlist){
                    set.add(neighbours.id);
                }

                for(Node neighbours: node_gr2.adlist){
                    if(!set.contains(neighbours.pid)){
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                System.out.println("Yes");
                System.exit(0);
            }
        }while(getNextPermutation(gr2.nodes));

        System.out.println("No");

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
