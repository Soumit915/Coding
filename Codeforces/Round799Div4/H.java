package Codeforces.Round799Div4;

import java.io.*;
import java.util.*;

public class H {

    static class Segment implements Comparable<Segment>{
        int a, l, r;
        int len;

        Segment(int a, int l, int r, int count){
            this.a = a;
            this.l = l;
            this.r = r;
            this.len = count;
        }

        public int compareTo(Segment s){
            return Integer.compare(this.len, s.len);
        }
    }

    static class Node{
        int start, end;
        int val;

        Node(int start, int end, int val){
            this.start = start;
            this.end = end;
            this.val = val;
        }

        Node(int end, int val){
            this.start = end - Math.abs(val) + 1;
            this.end = end;
            this.val = val;
        }
    }

    static List<Node> getAdjacentList(List<Integer> arlist){
        List<Node> adjacent = new ArrayList<>();

        int count = 1;
        for(int i=1;i<arlist.size();i++){
            if(arlist.get(i) == arlist.get(i-1) + 1)
                count++;
            else {
                adjacent.add(new Node(arlist.get(i-1), count));
                adjacent.add(new Node(arlist.get(i) - 1,
                        (arlist.get(i) - arlist.get(i-1) - 1) * -1));
                count = 1;
            }
        }
        adjacent.add(new Node(arlist.get(arlist.size() - 1), count));

        return adjacent;
    }

    static Segment getBestSegment(List<Node> list, int val){
        List<Node> adMaxList = new ArrayList<>();
        adMaxList.add(list.get(0));

        for(int i=1;i<list.size();i++){
            adMaxList.add(new Node(list.get(i).start, list.get(i).end,
                    Math.max(0, adMaxList.get(i-1).val + list.get(i).val)));
        }

        int max = 0;
        int maxIndex = -1;
        for(int i=0;i<list.size();i++){
            max = Math.max(max, adMaxList.get(i).val);
            if(max == adMaxList.get(i).val){
                maxIndex = i;
            }
        }

        int start = 0;
        int end = -1;
        int cost = 0;

        for(int i=maxIndex;i>=0;i--){
            if(cost + list.get(i).val == max){
                start = list.get(i).start;
                end = list.get(maxIndex).end;
                break;
            }
            else{
                cost += list.get(i).val;
            }
        }

        return new Segment(val, start, end, max);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            Map<Integer, List<Integer>> hash = new HashMap<>();
            for(int i=0;i<n;i++){
                List<Integer> list = hash.getOrDefault(arr[i], new ArrayList<>());
                list.add(i);

                hash.put(arr[i], list);
            }

            Map<Integer, List<Node>> adjacentHash = new HashMap<>();
            for(int i: hash.keySet()){
                List<Integer> list = hash.get(i);
                adjacentHash.put(i, getAdjacentList(list));
            }

            List<Segment> gamblingList = new ArrayList<>();
            for(int i: adjacentHash.keySet()){
                List<Node> list = adjacentHash.get(i);
                Segment segment = getBestSegment(list, i);
                gamblingList.add(segment);
            }

            Collections.sort(gamblingList);

            Segment best = gamblingList.get(gamblingList.size() - 1);

            sb.append(best.a).append(" ").append(best.l+1).append(" ").append(best.r+1).append("\n");
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
