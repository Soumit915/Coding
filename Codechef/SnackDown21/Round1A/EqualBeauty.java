package Codechef.SnackDown21.Round1A;

import java.io.*;
import java.util.*;

public class EqualBeauty {
    static long getGoodVal(long[] arr, int ll, int ul){
        int n = ul - ll + 1;
        if(n%2==1){
            int med = n / 2 + ll;
            long cost = 0;
            for(int i=ll;i<=ul;i++){
                cost += Math.abs(arr[i] - arr[med]);
            }
            return cost;
        }
        else{
            int med1 = ll + n/2 - 1;
            int med2 = ll + n/2;
            long median = (arr[med1] + arr[med2])/2;
            long cost = 0;
            for(int i=ll;i<=ul;i++){
                cost += Math.abs(arr[i] - median);
            }
            return cost;
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);
            sc.sort(arr);

            if(n==2){
                sb.append(0).append("\n");
                continue;
            }
            else if(n==3){
                long ans = Math.min(Math.abs(arr[2]-arr[1]), Math.abs(arr[1]-arr[0]));
                sb.append(ans).append("\n");
                continue;
            }

            long amax = arr[n-1];
            long bmin = arr[0];

            TreeMap<Long, Integer> tree = new TreeMap<>();
            for(int i=1;i<n-1;i++){
                tree.put(arr[i], tree.getOrDefault(arr[i], 0)+1);
            }

            long min = Long.MAX_VALUE;
            for(int i=1;i<n-1;i++){
                tree.put(arr[i], tree.get(arr[i])-1);
                if(tree.get(arr[i])==0){
                    tree.remove(arr[i]);
                }

                //if arr[i] is amin
                long bmax = amax-arr[i]+bmin;
                if(tree.containsKey(bmax)){
                    min = 0;
                    break;
                }
                else{
                    if(tree.floorKey(bmax)!=null){
                        min = Math.min(min, Math.abs(bmax-tree.floorKey(bmax)));
                    }
                    if(tree.ceilingKey(bmax)!=null){
                        min = Math.min(min, Math.abs(bmax-tree.ceilingKey(bmax)));
                    }
                }
                //------------------------------------------

                //if arr[i] is bmax
                long amin = amax - (arr[i]-bmin);
                if(tree.containsKey(amin)){
                    min = 0;
                    break;
                }
                else{
                    if(tree.floorKey(amin)!=null){
                        min = Math.min(min, Math.abs(amin-tree.floorKey(amin)));
                    }
                    if(tree.ceilingKey(amin)!=null){
                        min = Math.min(min, Math.abs(amin-tree.ceilingKey(amin)));
                    }
                }
                //------------------------------------------
                tree.put(arr[i], tree.getOrDefault(arr[i], 0)+1);
            }

            min = Math.min(min,
                    Math.min(getGoodVal(arr, 0, n-2), getGoodVal(arr, 1, n-1)));

            sb.append(min).append("\n");
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
