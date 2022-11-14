package Codeforces;

import java.util.*;
import java.io.*;

public class ValuablePaper {

    static class Node{
        int id;
        int type;

        List<Road> roads = new ArrayList<>();

        Node(int id, int type){
            this.id = id;
            this.type = type;
        }
    }

    static class Road{
        Node airport, factory;
        int days;

        Road(Node airport, Node factory, int days){
            this.airport = airport;
            this.factory = factory;
            this.days = days;
        }
    }

    static class Graph{
        List<Node> airports, factories;

        Graph(int n){
            this.airports = new ArrayList<>();
            for(int i=0;i<n;i++){
                airports.add(new Node(i, 0));
            }

            this.factories = new ArrayList<>();
            for(int i=0;i<n;i++){
                factories.add(new Node(i, 1));
            }
        }

        public void addRoad(int a, int f, int days){
            Node airport = airports.get(a);
            Node factory = factories.get(f);

            Road road = new Road(airport, factory, days);

            airport.roads.add(road);
            factory.roads.add(road);
        }

        public boolean isValid(int threshold){

            int n = airports.size();
            Node[] airport_factory = new Node[n];
            Node[] factory_airport = new Node[n];

            for(Node node: airports){
                for(Road road: node.roads){
                    if(factory_airport[road.factory.id]==null && road.days <= threshold){
                        airport_factory[node.id] = road.factory;
                        factory_airport[road.factory.id] = node;
                        break;
                    }
                }
            }

            for(Node node: airports){
                if(airport_factory[node.id] != null)
                    continue;

                Queue<Node> q = new LinkedList<>();
                q.add(node);

                HashMap<Node, Node> parent = new HashMap<>();

                boolean flag = false;
                while(!q.isEmpty()){
                    Node cur = q.remove();

                    if(cur.type == 0){
                        for(Road road: cur.roads){
                            if(road.days > threshold)
                                continue;

                            if(factory_airport[road.factory.id]==null){
                                parent.put(road.factory, cur);

                                Node ptr = road.factory;
                                while(ptr != null){
                                    Node factory = ptr;
                                    Node airport = parent.get(ptr);

                                    airport_factory[airport.id] = factory;
                                    factory_airport[factory.id] = airport;

                                    ptr = parent.get(airport);
                                }

                                flag = true;
                                break;
                            }
                            else{
                                Node next = road.factory;
                                if(!parent.containsKey(road.factory)){
                                    q.add(next);
                                    parent.put(next, cur);
                                }
                            }
                        }

                        if (flag)
                            break;
                    }
                    else{
                        Node next = factory_airport[cur.id];
                        if(!parent.containsKey(next)){
                            q.add(next);
                            parent.put(next, cur);
                        }
                    }
                }

                if(!flag)
                    return false;
            }

            return true;
        }
    }

    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int d = sc.nextInt();

            gr.addRoad(u, v, d);
        }

        int l = 1, r = 1000000100;
        while (l < r){
            int mid = (l + r) / 2;

            if(gr.isValid(mid)){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }

        if(l > 1000000000)
            System.out.println(-1);
        else System.out.println(l);

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
