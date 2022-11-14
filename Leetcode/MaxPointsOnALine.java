package Leetcode;

import java.io.*;
import java.util.*;

public class MaxPointsOnALine {

    static int gcd(int a, int b){
        if(a%b == 0)
            return b;
        else return gcd(b, a%b);
    }

    static class Number{
        int num;
        int deno;

        Number(int num, int deno){
            int gcd = gcd(num, deno);

            num /= gcd;
            deno /= gcd;

            this.num = num;
            this.deno = deno;
        }
    }

    static Number getSlope(int x1, int y1, int x2, int y2){
        return new Number(y2 - y1 , x2 - x1);
    }

    static Number getIntercept(int x1, int y1, int x2, int y2){
        return new Number(y1*x2 - y2*x1 , x2 - x1);
    }

    public int maxPoints(int[][] points) {
        int n = points.length;

        Map<Integer, Map<Integer, Number>> slope_map = new HashMap<>();
        Map<Integer, Map<Integer, Number>> intercept_map = new HashMap<>();

        Map<Number, Map<Number, Integer>> lines = new HashMap<>();

        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                Number slope = getSlope(points[i][0], points[i][1], points[j][0], points[j][1]);
                Number intercept = getIntercept(points[i][0], points[i][1], points[j][0], points[j][1]);


                if(slope_map.containsKey(slope.num) &&
                        slope_map.get(slope.num).containsKey(slope.deno)){
                    slope = slope_map.get(slope.num).get(slope.deno);
                }
                else{
                    Map<Integer, Number> slope_deno_map = slope_map.getOrDefault(slope.num, new HashMap<>());
                    slope_deno_map.put(slope.deno , slope);

                    slope_map.put(slope.num, slope_deno_map);
                }

                if(intercept_map.containsKey(intercept.num) &&
                        intercept_map.get(intercept.num).containsKey(intercept.deno)){
                    intercept = intercept_map.get(intercept.num).get(intercept.deno);
                }
                else{
                    Map<Integer, Number> intercept_deno_map = intercept_map.getOrDefault(intercept.num, new HashMap<>());
                    intercept_deno_map.put(intercept.deno , intercept);

                    intercept_map.put(intercept.num, intercept_deno_map);
                }

                Map<Number, Integer> lines_count = lines.getOrDefault(slope, new HashMap<>());
                lines_count.put(intercept, lines_count.getOrDefault(intercept, 0) + 1);
                lines.put(slope, lines_count);
            }

            for(Number slope : lines.keySet()){
                Map<Number, Integer> lines_count = lines.get(slope);
                for(Number intercept : lines_count.keySet()){
                    max = Math.max(lines_count.get(intercept), max);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();


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
