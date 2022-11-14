package Codeforces.Round811Div3;

import java.util.*;
import java.io.*;

public class D {

    static class Node{
        int id;
        String s;

        Node(int id, String s){
            this.id = id;
            this.s = s;
        }
    }

    static class Range implements Comparable<Range>{
        int sid, start, end, len;

        Range(int sid, int start, int end){
            this.sid = sid;
            this.start = start;
            this.end = end;
            this.len = end - start + 1;
        }

        public int compareTo(Range range){
            return Integer.compare(this.end-this.start + 1, range.end-range.start + 1) * -1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            String t = sc.next();
            int tl = t.length();

            int n = sc.nextInt();
            Node[] s = new Node[n];
            for(int i=0;i<n;i++){
                s[i] = new Node(i, sc.next());
            }

            Arrays.sort(s, (a, b) -> Integer.signum(b.s.length() - a.s.length()));

            List<Range> ranges = new ArrayList<>();

            HashMap<Integer, Range> hash = new HashMap<>();
            for(int i=0;i<n;i++){
                String si = s[i].s;
                int lsi = si.length();

                for(int j=0;j<=tl - lsi;j++){
                    if(hash.containsKey(j))
                        continue;

                    String tstr = t.substring(j, j + lsi);

                    if(tstr.equals(si)){
                        Range range = new Range(s[i].id, j, j+lsi-1);
                        hash.put(j, range);
                    }
                }
            }

            if(hash.get(0) == null){
                sb.append("-1\n");
                continue;
            }

            int ptr = 0;
            boolean flag = true;
            Range best = hash.get(0);
            while(ptr < tl){
                Range range = hash.getOrDefault(ptr, best);

                if(range.end < ptr){
                    flag = false;
                    break;
                }

                if(range.end >= best.end){
                    ranges.add(range);
                    best = range;
                }
                else{
                    ranges.add(best);
                }

                Range next = best;
                for(int i=ptr + 1;i<=best.end;i++){
                    if(hash.getOrDefault(i, next).end > next.end){
                        next = hash.get(i);
                    }
                }

                ptr = best.end + 1;
                best = next;
            }

            if(!flag){
                sb.append("-1\n");
                continue;
            }

            sb.append(ranges.size()).append("\n");
            for(Range range: ranges){
                sb.append(range.sid+1).append(" ").append(range.start+1).append("\n");
            }
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
