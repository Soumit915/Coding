package Codeforces.ICPC4;

import java.util.*;
import java.io.*;

public class A {

    static class Blockade{
        int c1 ,c2;

        Blockade(int c1, int c2){
            this.c1 = c1;
            this.c2 = c2;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        boolean[][] grid = new boolean[2][n];

        HashMap<Integer, HashSet<Blockade>> hash1 = new HashMap<>();
        HashMap<Integer, HashSet<Blockade>> hash2 = new HashMap<>();

        int tot = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;

            grid[r][c] = !grid[r][c];

            if(grid[r][c]){
                if(r == 0){

                    if(c - 1 >=0 && grid[1][c-1]){
                        Blockade blockade = new Blockade(c, c-1);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c-1, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c-1, set2);

                        tot++;
                    }

                    if(c + 1 < n && grid[1][c+1]){
                        Blockade blockade = new Blockade(c, c+1);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c+1, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c+1, set2);

                        tot++;
                    }

                    if(grid[1][c]){
                        Blockade blockade = new Blockade(c, c);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c, set2);

                        tot++;
                    }

                }
                else{

                    if(c - 1 >=0 && grid[0][c-1]){
                        Blockade blockade = new Blockade(c-1, c);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c-1, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c-1, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c, set2);

                        tot++;
                    }

                    if(c + 1 < n && grid[0][c+1]){
                        Blockade blockade = new Blockade(c+1, c);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c+1, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c+1, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c, set2);

                        tot++;
                    }

                    if(grid[0][c]){
                        Blockade blockade = new Blockade(c, c);

                        HashSet<Blockade> set1 = hash1.getOrDefault(c, new HashSet<>());
                        set1.add(blockade);
                        hash1.put(c, set1);

                        HashSet<Blockade> set2 = hash2.getOrDefault(c, new HashSet<>());
                        set2.add(blockade);
                        hash2.put(c, set2);

                        tot++;
                    }
                }
            }
            else{
                if(r == 0){
                    HashSet<Blockade> set = hash1.getOrDefault(c, new HashSet<>());
                    tot -= set.size();

                    for(Blockade blockade: set){
                        int c2 = blockade.c2;
                        HashSet<Blockade> set2 = hash2.get(c2);

                        set2.remove(blockade);
                    }

                    hash1.remove(c);
                }
                else{
                    HashSet<Blockade> set = hash2.getOrDefault(c, new HashSet<>());
                    tot -= set.size();

                    for(Blockade blockade: set){
                        int c1 = blockade.c1;
                        HashSet<Blockade> set1 = hash1.get(c1);

                        set1.remove(blockade);
                    }

                    hash2.remove(c);
                }
            }

            if(tot == 0){
                sb.append("Yes\n");
            }
            else{
                sb.append("No\n");
            }
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
