package Codechef;

import java.io.*;
import java.util.*;

public class MedianRearangement {

    static class Group implements Comparable<Group>{
        long val;
        int index;
        Group(long val, int index){
            this.val = val;
            this.index = index;
        }
        public int compareTo(Group gr){
            return Long.compare(this.val, gr.val);
        }
        public String toString(){
            return this.val+", "+this.index;
        }
    }

    static int getMedianIndex(int n){
        return (int) Math.ceil((n+1)/2.0);
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long k = sc.nextLong();
            long[] arr = sc.nextLongArray(n*n);

            if(n==1){
                if(arr[0]<=k){
                    sb.append(arr[0]).append("\n");
                }
                else{
                    sb.append(-1).append("\n");
                }
                continue;
            }

            sc.sort(arr);

            int medind = getMedianIndex(n);
            PriorityQueue<Group> medianHeap = new PriorityQueue<>();
            PriorityQueue<Group> smallHaap = new PriorityQueue<>(Collections.reverseOrder());
            HashSet<Integer> set = new HashSet<>();

            long[][] mat = new long[n][n];
            int arrind = 0;
            long curk = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<medind;j++){
                    mat[i][j] = arr[arrind];
                    if(j==medind-1){
                        medianHeap.add(new Group(mat[i][j], arrind));
                        set.add(arrind);
                        curk += mat[i][j];
                    }
                    else{
                        smallHaap.add(new Group(mat[i][j], arrind));
                    }
                    arrind++;
                }
            }
            for(int i=0;i<n;i++){
                for(int j=medind;j<n;j++){
                    mat[i][j] = arr[arrind];
                    arrind++;
                }
            }

            if(curk>k){
                sb.append("-1\n");
                continue;
            }

            arrind = 0;
            while(curk<=k){
                Group good = medianHeap.peek();
                while(set.contains(smallHaap.peek().index)){
                    smallHaap.remove();
                }
                if(smallHaap.peek().val<=good.val){
                    break;
                }

                if(arrind>=arr.length)
                    break;

                Group next = null;
                while(arrind<arr.length){
                    if(set.contains(arrind)) {
                        arrind++;
                        continue;
                    }
                    if(arrind>good.index && arr[arrind]>=good.val){
                        next = new Group(arr[arrind], arrind);
                        arrind++;
                        break;
                    }
                    arrind++;
                }

                if(next==null)
                    break;

                if(curk+(next.val-good.val)>k)
                    break;

                medianHeap.remove();
                smallHaap.add(good);
                set.remove(good.index);

                medianHeap.add(next);
                set.add(next.index);

                curk += (next.val - good.val);
            }

            sb.append(medianHeap.peek().val).append("\n");
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
