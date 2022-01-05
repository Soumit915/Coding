package Codeforces.Round710Div3;

import java.io.*;
import java.util.*;

public class E {
    static class Set
    {
        int id;
        Set parent;
        Set(int id)
        {
            this.id = id;
            this.parent = this;
        }
        public void union(Set b)
        {
            Set pa = this.get();
            Set pb = b.get();

            int ca = pa.id;
            int cb = pb.id;
            if(ca < cb)
            {
                pb.parent = pa.get();
            }
            else
            {
                pa.parent = pb.get();
            }
        }
        public Set get()
        {
            if(this.parent == this)
                return this;
            this.parent = this.parent.get();
            return this.parent;
        }
    }
    static int getNextEmptyPosition(int[] hash, int pointer){
        for(int i=pointer+1;i<hash.length;i++){
            if(hash[i]==0)
                return i;
        }
        return -1;
    }
    static int[] getMinimalArray(int[] arr){
        int n = arr.length;
        int[] hash = new int[n+10];
        int[] presentedVisited = new int[n+10];
        for(int i=0;i<n;i++){
            hash[arr[i]] = 1;
            presentedVisited[arr[i]] = 1;
        }

        int[] minimal = new int[n];
        int pointer = 0;
        for(int i=0;i<n;i++){
            if(presentedVisited[arr[i]]!=1){
                pointer = getNextEmptyPosition(hash, pointer);
                minimal[i] = pointer;
                //System.out.println(i+" "+pointer+" "+Arrays.toString(hash));
                hash[pointer] = 1;
            }
            else{
                presentedVisited[arr[i]] = 0;
                minimal[i] = arr[i];
            }
        }

        return minimal;
    }
    static int[] getMaximalArray(int[] arr){
        int n = arr.length;
        int[] hash = new int[n+10];
        int[] presentedVisited = new int[n+10];

        Set[] sets = new Set[n+1];
        for(int i=0;i<sets.length;i++){
            sets[i] = new Set(i);
        }

        for(int i=0;i<n;i++){
            hash[arr[i]] = 1;
            presentedVisited[arr[i]] = 1;
            sets[arr[i]].union(sets[arr[i]-1]);
        }

        int[] maximal = new int[n];
        for(int i=0;i<n;i++){
            if(presentedVisited[arr[i]]!=1){
                Set cur = sets[arr[i]];
                Set next = cur.get();
                maximal[i] = next.id;
                //System.out.println(i+" "+cur.id+" "+next.id);
                next.union(sets[next.id-1]);
            }
            else{
                presentedVisited[arr[i]] = 0;
                maximal[i] = arr[i];
            }
        }

        return maximal;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            int[] minimal = getMinimalArray(arr);
            int[] maximal = getMaximalArray(arr);

            for(int i: minimal)
                sb.append(i).append(" ");
            sb.append("\n");

            for(int i: maximal)
                sb.append(i).append(" ");
            sb.append("\n");
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
