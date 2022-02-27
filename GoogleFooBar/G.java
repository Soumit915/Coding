package GoogleFooBar;

import java.io.*;
import java.util.*;

public class G {
    static boolean isOk(int a, int b){
        if((a + b)%2==1)
            return true;

        if(a==b)
            return false;

        if(a%2==0 && b%2==0)
            return isOk(a/2, b/2);

        if(a<b){
            return isOk(a*2, b - a);
        }
        else{
            return isOk(a-b, b*2);
        }
    }

    static boolean getMatching(boolean[][] bpGraph, int u,
                               boolean[] seen, int[] matchR)
    {
        for (int v = 0; v < bpGraph.length; v++)
        {
            if (bpGraph[u][v] && !seen[v])
            {
                seen[v] = true;

                if (matchR[v] < 0 || getMatching(bpGraph, matchR[v],
                        seen, matchR))
                {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    static int maxBiPartiteMatching(boolean[][] admat)
    {
        int n = admat.length;

        int[] games = new int[n];
        for(int i = 0; i < n; ++i)
            games[i] = -1;

        int game_matches = 0;
        for (int u = 0; u < n; u++)
        {
            boolean[] isVisited =new boolean[n] ;
            for(int i = 0; i < n; ++i)
                isVisited[i] = false;

            if (getMatching(admat, u, isVisited, games))
                game_matches++;
        }
        return game_matches;
    }

    public static int solution(int[] banana_list){
        int n = banana_list.length;
        boolean[][] admat = new boolean[n][n];

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                admat[i][j] = isOk(banana_list[i], banana_list[j]);
                admat[j][i] = admat[i][j];
            }
        }

        int max = maxBiPartiteMatching(admat);
        return n - max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        System.out.println(solution(arr));

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
