package Codeforces;

import java.util.*;
import java.io.*;

public class Garlands {

    static class AskQuery{
        int id;
        int x1, y1, x2, y2;

        AskQuery(int id, int x1, int y1, int x2, int y2){
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static class Garland{
        int id;
        int[][] coordinates;

        List<AskQuery> queries = new ArrayList<>();

        Garland(int id, int len){
            this.id = id;
            coordinates = new int[len][3];
        }
    }

    static void update(long[][] bit, int i, int j, long v){
        int n = bit.length;
        int m = bit[0].length;

        while(i <= n){
            int j1 = j;

            while(j1 <= m){
                bit[i-1][j1-1] += v;
                j1 += (j1 & (-j1));
            }

            i += (i & (-i));
        }
    }

    static long query(long[][] bit, int x, int y){
        long sum = 0;

        while(x > 0){
            int y1 = y;

            while(y1 > 0){
                sum += bit[x - 1][y1-1];
                y1 -= (y1 & (-y1));
            }

            x -= (x & (-x));
        }

        return sum;
    }

    static long query(long[][] bit, int x1, int y1, int x2, int y2){
        return query(bit, x2, y2) - query(bit, x2, y1-1) - query(bit, x1-1, y2) + query(bit, x1-1, y1-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstline = br.readLine();
        String[] firstline_arr = firstline.split(" ");
        int n = Integer.parseInt(firstline_arr[0]);
        int m = Integer.parseInt(firstline_arr[1]);
        int k = Integer.parseInt(firstline_arr[2]);

        List<Garland> garlands = new ArrayList<>();

        for(int i=0;i<k;i++){
            int len = Integer.parseInt(br.readLine());
            Garland garland = new Garland(i, len);

            for(int j=0;j<len;j++){
                String bead = br.readLine();
                String[] bead_arr = bead.split(" ");
                garland.coordinates[j][0] = Integer.parseInt(bead_arr[0]);
                garland.coordinates[j][1] = Integer.parseInt(bead_arr[1]);
                garland.coordinates[j][2] = Integer.parseInt(bead_arr[2]);
            }

            garlands.add(garland);
        }

        int askquery_count = 0;

        int q = Integer.parseInt(br.readLine());
        boolean[] isLightedGarland = new boolean[k];
        Arrays.fill(isLightedGarland, true);

        for(int i=0;i<q;i++){
            String[] query = (br.readLine()).split(" ");

            if(query[0].equals("SWITCH")){
                int index = Integer.parseInt(query[1]) - 1;
                isLightedGarland[index] = !isLightedGarland[index];
            }
            else{
                askquery_count++;
                int x1 = Integer.parseInt(query[1]);
                int y1 = Integer.parseInt(query[2]);
                int x2 = Integer.parseInt(query[3]);
                int y2 = Integer.parseInt(query[4]);

                AskQuery askQuery = new AskQuery(askquery_count - 1, x1, y1, x2, y2);
                for(int j=0;j<k;j++){
                    if(isLightedGarland[j]){
                        garlands.get(j).queries.add(askQuery);
                    }
                }
            }
        }

        long[] ans = new long[askquery_count];

        long[][] bit = new long[n][m];
        for(int i=0;i<k;i++){

            Garland garland = garlands.get(i);

            for(int j=0;j<garland.coordinates.length;j++){
                int x = garland.coordinates[j][0];
                int y = garland.coordinates[j][1];
                long w = garland.coordinates[j][2];

                update(bit, x, y, w);
            }

            for(AskQuery askQuery : garland.queries){
                long sum = query(bit, askQuery.x1, askQuery.y1, askQuery.x2, askQuery.y2);
                ans[askQuery.id] += sum;
            }

            for(int j=0;j<garland.coordinates.length;j++){
                int x = garland.coordinates[j][0];
                int y = garland.coordinates[j][1];
                long w = garland.coordinates[j][2];

                update(bit, x, y, -w);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(long i : ans){
            sb.append(i).append("\n");
        }

        System.out.println(sb);

        br.close();
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
