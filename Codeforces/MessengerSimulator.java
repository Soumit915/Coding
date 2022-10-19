package Codeforces;

import java.util.*;
import java.io.*;

public class MessengerSimulator {

    static class Node {
        List<Integer> arlist;

        Node(){
            this.arlist = new ArrayList<>();
        }
    }

    static int getNextPowerOf2(int n){
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);
        n |= (n >> 25);

        return n + 1;
    }

    static List<Integer> getSortedSet(List<Integer> li1, List<Integer> li2){
        List<Integer> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();

        int n = li1.size();
        int m = li2.size();

        int p = 0, q = 0;

        while(p<n && q<m){
            if(li1.get(p) < li2.get(q)){
                if(!set.contains(li1.get(p))) {
                    list.add(li1.get(p));
                    set.add(li1.get(p));
                }
                p++;
            }
            else{
                if(!set.contains(li2.get(q))) {
                    list.add(li2.get(q));
                    set.add(li2.get(q));
                }
                q++;
            }
        }

        while(p<n){
            if(!set.contains(li1.get(p))) {
                list.add(li1.get(p));
                set.add(li1.get(p));
            }
            p++;
        }

        while(q<m){
            if(!set.contains(li2.get(q))) {
                list.add(li2.get(q));
                set.add(li2.get(q));
            }
            q++;
        }

        return list;
    }

    static void build(Node[] segTree, int si, int[] message, int l, int r){
        if(l == r){
            segTree[si] = new Node();
            segTree[si].arlist.add(message[l]);
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, message, l, mid);
        build(segTree, 2*si + 2, message, mid + 1, r);

        segTree[si] = new Node();
        segTree[si].arlist = getSortedSet(segTree[2*si+1].arlist, segTree[2*si+2].arlist);
    }

    static int binarySearch(List<Integer> list, int k){
        int l = 0, r = list.size() - 1;

        while(l < r){
            int mid = (l + r) / 2;

            if(list.get(mid) >= k){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }

        if(list.get(l) < k)
            l++;

        return list.size() - l;
    }

    static int query(Node[] segTree, int si, int k, int start, int end, int l, int r){
        if(l > r)
            return 0;

        //no overlap
        if(r < start || l > end){
            return 0;
        }

        //total overlap
        if(l >= start && r <= end){
            return binarySearch(segTree[si].arlist, k);
        }

        //partial overlap
        int mid = (l + r) / 2;
        return query(segTree, 2*si + 1, k, start, end, l, mid) + query(segTree, 2*si + 2, k, start, end, mid + 1, r);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] message = sc.nextIntArray(m);
        for(int i=0;i<m;i++){
            message[i]--;
        }

        int sn = 2 * getNextPowerOf2(m) - 1;
        Node[] segTree = new Node[sn];

        build(segTree, 0,  message, 0, m-1);

        HashMap<Integer, List<Integer>> hash = new HashMap<>();
        for(int i=0;i<m;i++){
            List<Integer> list = hash.getOrDefault(message[i], new ArrayList<>());
            list.add(i);

            hash.put(message[i], list);
        }

        int[] min = new int[n];
        int[] max = new int[n];
        for(int i=0;i<n;i++){
            List<Integer> list = hash.getOrDefault(i, new ArrayList<>());

            int cur_max = i;
            int cur_pos = i;

            int last = -1;
            for(int p: list){

                if(last == -1){
                    int c = query(segTree, 0, (i + 1),0, p - 1, 0, m-1);
                    cur_pos = cur_pos + c;
                }
                else{
                    cur_pos = query(segTree, 0, 0, last + 1, p - 1, 0, m-1);
                }

                cur_max = Math.max(cur_max, cur_pos);
                last = p;
                cur_pos = 0;

            }

            if(last == -1){
                int c = query(segTree, 0, (i + 1),0, m - 1, 0, m-1);
                cur_pos = cur_pos + c;
            }
            else{
                cur_pos = query(segTree, 0, 0, last + 1, m - 1, 0, m-1);
            }

            cur_max = Math.max(cur_max, cur_pos);

            int cur_min;

            if(list.size() != 0)
                cur_min = 0;
            else cur_min = i;

            min[i] = cur_min;
            max[i] = cur_max;

        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(min[i]+1).append(" ").append(max[i]+1).append("\n");
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
