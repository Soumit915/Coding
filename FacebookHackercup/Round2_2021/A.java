package FacebookHackercup.Round2_2021;

import java.io.*;
import java.util.*;

public class A {
    static class Group implements Comparable<Group>{
        int weight;
        int val;
        Group(int weight, int val){
            this.weight = weight;
            this.val = val;
        }
        public int compareTo(Group gr){
            int c = Integer.compare(this.weight, gr.weight);
            if(c==0)
                return Integer.compare(gr.val, this.val);
            else return c;
        }
        public String toString(){
            return this.weight+" "+this.val;
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++) {
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] p = new int[n+1][m];
            for(int i=0;i<n+1;i++){
                p[i] = sc.nextIntArray(m);
            }

            int[][] dp = new int[n+1][m];
            Arrays.fill(dp[0], -1);
            for(int i=1;i<=n;i++){
                Arrays.fill(dp[i], -2);
            }

            PriorityQueue<Group> heap = new PriorityQueue<>();
            for(int i=0;i<m;i++){
                heap.add(new Group(p[0][i], dp[0][i]));
            }

            for(int i=1;i<=n;i++){
                //System.out.println(i+" : ");

                HashMap<Integer, Stack<Integer>> map = new HashMap<>();
                for(int j=0;j<m;j++){
                    Stack<Integer> stk = map.getOrDefault(p[i][j], new Stack<>());
                    stk.push(j);
                    map.put(p[i][j], stk);
                }

                //System.out.println(map);

                ArrayList<Group> groups = new ArrayList<>();
                while(!heap.isEmpty()){
                    Group group = heap.remove();
                    Stack<Integer> stk = map.getOrDefault(group.weight, new Stack<>());
                    //System.out.println("Check : "+group);
                    //System.out.println(map);

                    if(stk.isEmpty()){
                        groups.add(group);
                    }
                    else{
                        int index = stk.pop();
                        dp[i][index] = group.val;
                        //System.out.println("Test: "+index);
                    }
                }

                //System.out.println(groups);
                //System.out.println(Arrays.toString(dp[i]));

                int index = 0;
                for(int j=0;j<m;j++){
                    if(dp[i][j]==-2){
                        //System.out.println(index);
                        dp[i][j] = groups.get(index).val + 1;
                        index++;
                    }
                }

                heap = new PriorityQueue<>();
                for(int j=0;j<m;j++){
                    heap.add(new Group(p[i][j], dp[i][j]));
                }
            }

            /*for(int i=1;i<=n;i++){
                System.out.println(Arrays.toString(dp[i]));
            }*/

            int tot = 0;
            for(int i=0;i<m;i++){
                tot += Math.max(0, dp[n][i]);
            }

            sb.append(tot).append("\n");
        }

        sc.println(sb.toString());

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
