package GoogleKickStart.RoundF_2022;

import java.util.*;
import java.io.*;

public class C3 {

    static class Vegetable{
        long qi, li, vi;
        int id;

        Vegetable(long qi, long li, long vi){
            this.qi = qi;
            this.li = li;
            this.vi = vi;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=tc;testi++){
            sb.append("Case #").append(testi).append(": ");

            long d = sc.nextLong();
            int n = sc.nextInt();
            long x = sc.nextLong();

            Vegetable[] vegetables = new Vegetable[n];
            for(int i=0;i<n;i++){
                vegetables[i] = new Vegetable(sc.nextLong(), sc.nextLong(), sc.nextLong());
            }

            Arrays.sort(vegetables, (v1, v2) -> {
                if(v1.vi == v2.vi){
                    return Long.signum(v2.li - v1.li);
                }
                else{
                    return Long.signum(v2.vi - v1.vi);
                }
            });

            for(int i=0;i<n;i++){
                vegetables[i].id = i;
            }

            PriorityQueue<Vegetable> heap = new PriorityQueue<>((v1, v2) -> Integer.signum(v1.id - v2.id));
            TreeSet<Long> ss_tree = new TreeSet<>();

            HashMap<Long, List<Vegetable>> hash = new HashMap<>();
            for(int i=0;i<n;i++){
                long shouldstart = d - vegetables[i].li;
                if(shouldstart <= 0)
                    continue;

                List<Vegetable> list = hash.getOrDefault(shouldstart, new ArrayList<>());
                list.add(vegetables[i]);
                hash.put(shouldstart, list);
                ss_tree.add(shouldstart);
            }

            long ans = 0;
            long ptr = d;
            while(ptr > 0 && !ss_tree.isEmpty()){
                long cur = ss_tree.pollLast();
                if(cur > ptr)
                    continue;

                List<Vegetable> vege_list = hash.getOrDefault(cur, new ArrayList<>());
                heap.addAll(vege_list);

                long left = x;
                while(left > 0 && heap.size() > 0){
                    Vegetable vegetable = heap.remove();

                    if(vegetable.qi < left){
                        ans += vegetable.qi * vegetable.vi;
                        left -= vegetable.qi;
                    }
                    else{

                        long willEmptyCount = (vegetable.qi - left) / x;
                        long max = Math.max(ss_tree.isEmpty()?-1:ss_tree.last()+1, Math.max(1, cur - willEmptyCount));

                        long gone = (cur - max) * x + left;

                        vegetable.qi -= gone;
                        ans += gone * vegetable.vi;

                        ss_tree.add(max - 1);

                        if(vegetable.qi > 0)
                            heap.add(vegetable);
                        ptr = max - 1;

                        break;

                    }
                }
            }

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
