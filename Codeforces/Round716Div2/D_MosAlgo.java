package Codeforces.Round716Div2;

import java.io.*;
import java.util.*;

public class D_MosAlgo {
    static class Query implements Comparable<Query>{
        int id;
        int start;
        int end;
        int blockno;
        Query(int id, int start, int end, int blockno){
            this.id = id;
            this.start = start;
            this.end = end;
            this.blockno = blockno;
        }
        public int compareTo(Query q){
            int c = Integer.compare(this.blockno, q.blockno);
            if(c==0){
                c = Integer.compare(this.end, q.end);
                if(this.blockno%2==0){
                    return c;
                }
                else{
                    return c*-1;
                }
            }
            else return c;
        }
    }
    public static void main(String[] args) throws IOException {
        long ss = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int blocksize = (int) Math.sqrt(n);

        Query[] queries = new Query[q];
        for(int i=0;i<q;i++){
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;
            int blockno = l/blocksize;
            queries[i] = new Query(i, l, r, blockno);
        }
        Arrays.sort(queries);

        int ll=0, ul=0;
        int[] hash = new int[n+10];
        int[] counter = new int[n+10];
        hash[arr[0]] = 1;
        counter[1] = 1;
        int max = 1;
        int[] output = new int[q];
        for(Query query: queries){
            while(ul<query.end){
                ul++;
                counter[hash[arr[ul]]]--;
                hash[arr[ul]]++;
                counter[hash[arr[ul]]]++;
                max = Math.max(max, hash[arr[ul]]);
            }
            while(ll>query.start){
                ll--;
                counter[hash[arr[ll]]]--;
                hash[arr[ll]]++;
                counter[hash[arr[ll]]]++;
                max = Math.max(max, hash[arr[ll]]);
            }
            while(ul>query.end){
                counter[hash[arr[ul]]]--;
                hash[arr[ul]]--;
                counter[hash[arr[ul]]]++;
                while(counter[max]==0)
                    max--;
                ul--;
            }
            while(ll<query.start){
                counter[hash[arr[ll]]]--;
                hash[arr[ll]]--;
                counter[hash[arr[ll]]]++;
                while(counter[max]==0)
                    max--;
                ll++;
            }

            int rangelength = (query.end-query.start+1);
            int restfreq = rangelength-max;
            if(restfreq<max){
                output[query.id] = max-restfreq;
            }
            else{
                output[query.id] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i: output){
            sb.append(i).append("\n");
        }
        sc.println(sb.toString());

        long es = System.currentTimeMillis();
        System.out.println((es-ss)/1000.0);
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
