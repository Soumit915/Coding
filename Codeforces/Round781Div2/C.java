package Codeforces.Round781Div2;

import java.io.*;
import java.util.*;

public class C {

    static class Node{
        int id;
        List<Node> child = new ArrayList<>();
        Node parent;

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

        public void addEdge(int parent, int child){
            Node pnode = nodelist.get(parent);
            Node cnode = nodelist.get(child);

            cnode.parent = pnode;
            pnode.child.add(cnode);
        }

        public int countNonLeafNodes(){
            int count = 0;
            for(Node node: nodelist){
                if(node.child.size() != 0){
                    count++;
                }
            }

            return count;
        }

        public List<Integer> childCount(){
            List<Integer> arlist = new ArrayList<>();
            for(Node node: nodelist){
                if(node.child.size() != 0)
                    arlist.add(node.child.size());
            }

            arlist.sort(Collections.reverseOrder());

            return arlist;
        }
    }

    static boolean isPossible(List<Integer> list, int mid){
        int sum = 0;
        int canbe = mid;
        for (Integer integer : list) {
            sum += integer;
            canbe += Math.min(mid, integer);
        }

        return sum <= canbe;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int  t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();

            Graph gr = new Graph(n);

            int[] parents = sc.nextIntArray(n-1);
            for(int i=0;i<n-1;i++){
                gr.addEdge(parents[i]-1, i+1);
            }

            int nonLeafCount = gr.countNonLeafNodes();
            List<Integer> list = gr.childCount();

            int time = nonLeafCount + 1;

            for(int i=0;i<list.size();i++){
                list.set(i, list.get(i) - time + i);
            }

            int ll = 0, ul = 1000000;
            while(ll < ul){
                int mid = (ll + ul) / 2;

                if (isPossible(list, mid)) {
                    ul = mid;
                }
                else{
                    ll = mid + 1;
                }
            }

            sb.append(time + ll).append("\n");
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
