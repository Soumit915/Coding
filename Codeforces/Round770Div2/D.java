package Codeforces.Round770Div2;
import java.io.*;
import java.util.*;

public class D {

    static class Pair{
        int one, two;
        int non_zero1, non_zero2;
        Pair(int one, int two, int non_zero1, int non_zero2){
            this.one = one;
            this.two = two;

            this.non_zero1 = non_zero1;
            this.non_zero2 = non_zero2;
        }
    }

    static Pair getMaxMinIndex(Soumit sc, int a, int b, int c, int d) throws IOException {
        System.out.println("? "+b+" "+c+" "+d);
        System.out.flush();
        int va = sc.nextInt();

        System.out.println("? "+a+" "+c+" "+d);
        System.out.flush();
        int vb = sc.nextInt();

        System.out.println("? "+b+" "+a+" "+d);
        System.out.flush();
        int vc = sc.nextInt();

        System.out.println("? "+b+" "+c+" "+a);
        System.out.flush();
        int vd = sc.nextInt();

        if(va==vb && vb==vc && vc==vd){
            return new Pair(a, b, c, d);
        }

        if(va==vb && vb==vc){
            return new Pair(d, a, b, c);
        }
        else if(va==vc && vc==vd){
            return new Pair(b, a, c, d);
        }
        else if(va==vb && vb==vd){
            return new Pair(c, a, b, d);
        }
        else if(vb==vc && vc==vd){
            return new Pair(a, b, c, d);
        }

        if(va==vb && ((vc!=vd) || (vc==vd && va>vc))) {
            return new Pair(c, d, a, b);
        }
        else if(va==vc && ((vb!=vd) || (vb==vd && va>vb))) {
            return new Pair(b, d, a, c);
        }
        else if(va==vd && ((vc!=vb) || (vc==vb && va>vc))) {
            return new Pair(b, c, a, d);
        }
        else if(vb==vc && ((va!=vd) || (va==vd && vb>vd))) {
            return new Pair(a, d, b, c);
        }
        else if(vb==vd && ((va!=vc) || (va==vc && vb>vc))) {
            return new Pair(a, c, b, d);
        }
        else {
            return new Pair(a, b, c, d);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();

            List<Integer> nonZeroIndexList = new ArrayList<>();

            int a1 = 1, a2 = 2;
            for(int i=3;i<=n;i+=2){
                if(i+1>n)
                    break;
                Pair p = getMaxMinIndex(sc, a1, a2, i, i+1);
                a1 = p.one;
                a2 = p.two;

                nonZeroIndexList.add(p.non_zero1);
                nonZeroIndexList.add(p.non_zero2);
            }

            if(n%2==1){
                Pair p = getMaxMinIndex(sc, a1, a2, n, nonZeroIndexList.get(0));
                a1 = p.one;
                a2 = p.two;
            }

            System.out.println("! "+a1+" "+a2);
            System.out.flush();
        }
        
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
