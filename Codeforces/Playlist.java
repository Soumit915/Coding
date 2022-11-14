package Codeforces;

import java.util.*;
import java.io.*;

public class Playlist {

    static class Node{
        int id;
        Node prev;
        Node next;

        Node(int id){
            this.id = id;
        }
    }

    static int gcd(int a, int b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int[] a = sc.nextIntArray(n);

            if(n == 1){
                if(a[0] == 1)
                    sb.append("1 1\n");
                else sb.append("0\n");
                continue;
            }

            Node[] nodes = new Node[n];
            for(int i=0;i<n;i++){
                nodes[i] = new Node(i);
                if(i > 0){
                    nodes[i].prev = nodes[i-1];
                    nodes[i-1].next = nodes[i];
                }
            }

            nodes[0].prev = nodes[n-1];
            nodes[n-1].next = nodes[0];

            Queue<Node> q = new LinkedList<>();
            int[] inQ = new int[n];
            int last = -1;
            for(int i=0;i<n-1;i++){
                if(last == i)
                    continue;
                if(gcd(a[i], a[i+1]) == 1){
                    q.add(nodes[i+1]);
                    inQ[i+1] = 1;
                    last = i+1;
                }
            }

            if(last != n-1){
                if(gcd(a[n-1], a[0]) == 1){
                    q.add(nodes[0]);
                    inQ[0] = 1;
                }
            }

            List<Integer> list = new ArrayList<>();
            Set<Node> set = new HashSet<>();
            while(!q.isEmpty()){
                Node node = q.remove();
                inQ[node.id]--;

                if(set.contains(node) || inQ[node.id]>0){
                    continue;
                }

                Node prev = node.prev;
                Node next = node.next;

                if(gcd(a[node.id], a[prev.id]) != 1){
                    continue;
                }

                list.add(node.id);
                set.add(node);

                prev.next = next;
                next.prev = prev;

                if(gcd(a[prev.id], a[next.id]) == 1){
                    q.add(next);
                    inQ[next.id]++;
                }
            }

            sb.append(list.size()).append(" ");
            for(int i: list){
                sb.append(i+1).append(" ");
            }
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
