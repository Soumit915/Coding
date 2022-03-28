package Leetcode;
import java.io.*;
import java.util.*;

public class CountGoodTripletsInArray {

    static int[] hash;

    static class Val{
        int ind;
        int val;
        long ci2;
        long ci3;
        Val(int ind, int val){
            this.ind = ind;
            this.val = val;
            this.ci2 = 0;
            this.ci3 = 0;
        }
        Val(int ind, int val, long ci2){
            this.ind = ind;
            this.val = val;
            this.ci2 = ci2;
            this.ci3 = 0;
        }
    }

    static boolean isOK(int a, int b){
        return (hash[a] < hash[b]);
    }

    public static long merge(Val[] arr, int ll, int mid, int ul)
    {
        int al = mid-ll+1;
        int bl = ul-mid;
        Val[] A = new Val[al];
        Val[] B = new Val[bl];

        if (al >= 0) System.arraycopy(arr, ll, A, 0, al);
        for(int i=0;i<bl;i++)
        {
            B[i] = arr[mid+i+1];
        }

        long count=0;
        int p = 0, q = 0, k = ll;
        while(p<al && q<bl)
        {
            if(isOK(A[p].val, B[q].val))
            {
                count += (bl - q);
                A[p].ci2 += (bl - q);
                arr[k] = A[p];
                p++;
            }
            else
            {
                arr[k] = B[q];
                q++;
            }
            k++;
        }

        while(p<al)
        {
            arr[k] = A[p];
            p++;k++;
        }

        while(q<bl)
        {
            arr[k] = B[q];
            q++;k++;
        }

        return count;
    }
    public static long countInversions(Val[] arr, int ll, int ul)
    {
        if(ll<ul)
        {
            int mid = (ll+ul)/2;
            long countl = countInversions(arr, ll, mid);
            long countr = countInversions(arr, mid+1, ul);
            return countl + countr + merge(arr, ll, mid, ul);
        }
        return 0;
    }

    public static long merge3(Val[] arr, int ll, int mid, int ul)
    {
        int al = mid-ll+1;
        int bl = ul-mid;
        Val[] A = new Val[al];
        Val[] B = new Val[bl];

        if (al >= 0) System.arraycopy(arr, ll, A, 0, al);
        long totsum = 0;
        for(int i=0;i<bl;i++)
        {
            B[i] = arr[mid+i+1];
            totsum += B[i].ci2;
        }

        long count=0;
        int p = 0, q = 0, k = ll;
        while(p<al && q<bl)
        {
            if(isOK(A[p].val, B[q].val))
            {
                count += (bl - q);
                A[p].ci3 += totsum;
                arr[k] = A[p];
                p++;
            }
            else
            {
                totsum -= B[q].ci2;
                arr[k] = B[q];
                q++;
            }
            k++;
        }

        while(p<al)
        {
            arr[k] = A[p];
            p++;k++;
        }

        while(q<bl)
        {
            arr[k] = B[q];
            q++;k++;
        }

        return count;
    }
    public static long countInversions3(Val[] arr, int ll, int ul)
    {
        if(ll<ul)
        {
            int mid = (ll+ul)/2;
            long countl = countInversions3(arr, ll, mid);
            long countr = countInversions3(arr, mid+1, ul);
            return countl + countr + merge3(arr, ll, mid, ul);
        }
        return 0;
    }

    static long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        hash = new int[n];

        for(int i=0;i<n;i++){
            hash[nums2[i]] = i;
        }

        Val[] vals = new Val[n];
        for(int i=0;i<n;i++){
            vals[i] = new Val(i, nums1[i]);
        }

        countInversions(vals, 0, n-1);

        Val[] vals1 = new Val[n];
        for(int i=0;i<n;i++){
            vals1[i] = new Val(i, nums1[i], vals[hash[nums1[i]]].ci2);
        }

        countInversions3(vals1, 0, n-1);

        long ans = 0;
        for(int i=0;i<n;i++){
            ans += vals1[i].ci3;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        
        int n = sc.nextInt();
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        for(int i=0;i<n;i++){
            nums1[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            nums2[i] = sc.nextInt();
        }

        System.out.println(goodTriplets(nums1, nums2));
        
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
