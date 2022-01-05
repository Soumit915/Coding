package Codeforces.EducationalRound110;

import java.io.*;
import java.util.*;

public class D {
    static void update_dec(int[] count_tree, int cur){
        count_tree[cur] = 0;
        if(cur==0)
            return;
        update_dec(count_tree, (cur-1)/2);
    }
    static void update_inc(int[] count_tree, int cur, char[] now){
        do{
            int left = 2*cur+1;
            int right = 2*cur+2;
            if(now[cur]=='?'){
                count_tree[cur] = count_tree[left]+count_tree[right];
            }
            else if(now[cur]=='0'){
                count_tree[cur] = count_tree[left];
            }
            else{
                count_tree[cur] = count_tree[right];
            }

            if(cur==0)
                break;

            cur = (cur-1) / 2;
        }while(cur>=0);
    }
    static void build(int[] count_tree, int cur, char now){

        if(now=='?'){
            int left = 2*cur+1;
            int right = 2*cur+2;

            count_tree[cur] += count_tree[left];
            count_tree[cur] += count_tree[right];
        }
        else if(now=='0'){
            int left = 2*cur+1;
            count_tree[cur] += count_tree[left];
        }
        else{
            int right = 2*cur+2;
            count_tree[cur] += count_tree[right];
        }
    }
    static int[] hash;
    static void get(char[] s, String input, int games, int k){

        int d = 1;
        int index = 0;
        while(d<=k){
            int nodes = (int) Math.pow(2, d-1);
            for(int i=games-nodes;i<games;i++){
                s[index] = input.charAt(i);
                hash[i] = index;
                index++;
            }

            games -= nodes;
            d++;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();

        int games = (int) Math.pow(2, k)-1;

        int[] count_tree = new int[2*games+1];
        int n = count_tree.length;
        for(int i=games;i<n;i++){
            count_tree[i] = 1;
        }

        String input = sc.next();
        char[] s = new char[input.length()];
        hash = new int[input.length()];
        get(s, input, games, k);

        for(int i=games-1;i>=0;i--){
            build(count_tree, i, s[i]);
        }

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (q-->0){
            int index = sc.nextInt()-1;
            char ch = sc.next().charAt(0);

            update_dec(count_tree, hash[index]);

            s[hash[index]] = ch;
            update_inc(count_tree, hash[index], s);

            sb.append(count_tree[0]).append("\n");
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
