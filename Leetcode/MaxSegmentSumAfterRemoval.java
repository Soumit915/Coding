package Leetcode;

import java.util.*;
import java.io.*;

public class MaxSegmentSumAfterRemoval {

    static int getNextPowerOf2(int n){
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);
        n |= (n >> 26);

        return n + 1;
    }

    static class Node{
        long max;
        int first_zero, last_zero;
    }

    static void build(Node[] segTree, int si, int[] arr, int l, int r){
        if(l == r){
            Node node = new Node();
            node.max = arr[l];
            node.first_zero = -1;
            node.last_zero = -1;

            segTree[si] = node;
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);

        Node node = new Node();
        Node left = segTree[2*si + 1];
        Node right = segTree[2*si + 2];

        long val = left.max + right.max;
        node.first_zero = -1;
        node.last_zero = -1;
        node.max = val;

        segTree[si] = node;
    }

    static void update(Node[] segTree, int si, int index, long[] sum, int l, int r){
        if(l == r){
            Node node = new Node();
            node.first_zero = l;
            node.last_zero = l;
            node.max = 0;

            segTree[si] = node;
            return ;
        }

        int mid = (l + r) / 2;

        if(index <= mid) {
            update(segTree, 2 * si + 1, index, sum, l, mid);

            Node left = segTree[2*si + 1];
            Node right = segTree[2*si + 2];
            Node node = new Node();

            node.first_zero = left.first_zero;
            node.last_zero = right.last_zero;

            if(node.last_zero == -1)
                node.last_zero = left.last_zero;
            if(node.first_zero == -1)
                node.first_zero = right.first_zero;

            node.max = Math.max(left.max, right.max);

            long mid_val;
            if(left.last_zero != -1 && right.first_zero != -1){
                mid_val = sum[right.first_zero - 1] - sum[left.last_zero];
            }
            else if(left.last_zero != -1){
                mid_val = sum[r] - sum[left.last_zero];
            }
            else{
                mid_val = sum[right.first_zero - 1];
                if(l > 0)
                    mid_val -= sum[l-1];
            }

            node.max = Math.max(node.max , mid_val);

            segTree[si] = node;
        }
        else {
            update(segTree, 2 * si + 2, index, sum, mid + 1, r);

            Node left = segTree[2*si + 1];
            Node right = segTree[2*si + 2];
            Node node = new Node();

            node.first_zero = left.first_zero;
            node.last_zero = right.last_zero;

            if(node.last_zero == -1)
                node.last_zero = left.last_zero;
            if(node.first_zero == -1)
                node.first_zero = right.first_zero;

            node.max = Math.max(left.max, right.max);

            long mid_val;
            if(left.last_zero != -1 && right.first_zero != -1){
                mid_val = sum[right.first_zero - 1] - sum[left.last_zero];
            }
            else if(left.last_zero != -1){
                mid_val = sum[r] - sum[left.last_zero];
            }
            else{
                mid_val = sum[right.first_zero - 1];
                if(l > 0)
                    mid_val -= sum[l-1];
            }

            node.max = Math.max(node.max , mid_val);

            segTree[si] = node;
        }

    }

    public static long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;

        int sn = 2*getNextPowerOf2(n) - 1;
        Node[] segTree = new Node[sn];
        build(segTree, 0, nums, 0, n-1);

        long[] sum = new long[n];
        sum[0] = nums[0];
        for(int i=1;i<n;i++)
            sum[i] = sum[i-1] + nums[i];

        long[] ans = new long[n];
        for(int i=0;i<removeQueries.length;i++){
            update(segTree, 0, removeQueries[i], sum, 0, n-1);
            ans[i] = segTree[0].max;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] nums = {244,19,445,671,801,103,291,335,781,33,51,789,746,510,38,7,529,905};
        int[] removeQueries = {4,8,11,12,1,5,0,9,6,17};

        System.out.println(Arrays.toString(maximumSegmentSum(nums, removeQueries)));

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
