package Codeforces.Round760Div3;

import java.io.*;
import java.util.*;

public class G {
    static class Dsu{
        int id;
        int ll;
        int ul;
        int acount;
        Dsu parent;
        Dsu(int id, int ll, int ul, int acount){
            this.id = id;
            this.ll = ll;
            this.ul = ul;
            this.acount = acount;
            this.parent = this;
        }
        public Dsu getParent(){
            if(this.parent == this)
                return this;
            this.parent = this.parent.getParent();
            return this.parent;
        }
        public void union(Dsu b){
            Dsu a = this;

            Dsu ap = a.getParent();
            Dsu bp = b.getParent();

            ap.ul = bp.ul;
            ap.acount += bp.acount;

            bp.parent = ap;
        }
    }

    static class DiffPair implements Comparable<DiffPair>{
        long diff;
        int ind;
        DiffPair(long diff, int ind){
            this.diff = diff;
            this.ind = ind;
        }
        public int compareTo(DiffPair dp){
            return Long.compare(this.diff, dp.diff);
        }
    }

    static long getSum(ArrayList<Long> list, int ll, int ul){
        if(ul<0)
            return 0;
        long sum = list.get(ul);
        if(ll==0)
            return sum;
        else return sum - list.get(ll - 1);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        long[] a = sc.nextLongArray(n);
        long[] b = sc.nextLongArray(m);

        sc.sort(a);
        sc.sort(b);

        ArrayList<Long> list = new ArrayList<>();
        ArrayList<Boolean> inA = new ArrayList<>();
        int ai = n-1, bi = m-1;
        while(ai>=0 && bi>=0){
            if(a[ai]>b[bi]){
                list.add(a[ai]);
                inA.add(true);
                ai--;
            }
            else{
                list.add(b[bi]);
                inA.add(false);
                bi--;
            }
        }
        while(ai>=0){
            list.add(a[ai]);
            inA.add(true);
            ai--;
        }
        while(bi>=0){
            list.add(b[bi]);
            inA.add(false);
            bi--;
        }

        ArrayList<Long> prefSum = new ArrayList<>();
        prefSum.add(list.get(0));
        for(int i=1;i<list.size();i++){
            prefSum.add(prefSum.get(i-1) + list.get(i));
        }

        DiffPair[] diffPairs = new DiffPair[n+m-1];
        for(int i=0;i<diffPairs.length;i++){
            diffPairs[i] = new DiffPair(list.get(i) - list.get(i+1), i+1);
        }
        Arrays.sort(diffPairs);

        Dsu[] dsu = new Dsu[n+m];
        for(int i=0;i<list.size();i++){
            dsu[i] = new Dsu(i, i, i, inA.get(i)?1:0);
        }

        TreeMap<Long, Long> sumMap = new TreeMap<>();
        long totsum = 0;
        for(long l: a)
            totsum += l;
        sumMap.put(0L, totsum);

        for(int i=0;i<n+m-1;i++){
            DiffPair dp = diffPairs[i];

            Dsu prev = dsu[dp.ind-1].getParent();
            Dsu cur = dsu[dp.ind].getParent();

            totsum -= getSum(prefSum, prev.ll, prev.ll+prev.acount-1)
                    + getSum(prefSum, cur.ll, cur.ll+cur.acount-1);

            prev.union(cur);
            totsum += getSum(prefSum, prev.ll, prev.ll+prev.acount-1);

            sumMap.put(dp.diff, totsum);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            long qi = sc.nextLong();

            long ans = sumMap.get(sumMap.floorKey(qi));
            sb.append(ans).append("\n");
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
