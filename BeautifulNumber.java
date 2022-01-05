import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class BeautifulNumber {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int[][][] combs = getCombs();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            System.out.println(t);

            long n = sc.nextLong();

            int sn = getNumberLen(n);
            long ans1 = getMaxValue(sn+1);

            long ans = Long.MAX_VALUE;
            for(int[] arr: combs[sn-1]){
                long curans = getIdealVal(arr, n+1, sn);
                if(curans>n){
                    ans = Math.min(curans, ans);
                }
            }

            long i=n+1;
            while(!isBeautiful(i)){
                i++;
            }

            if(Math.min(ans, ans1)!=i){
                System.out.println("Wrong answer");
                System.exit(0);
            }

            sb.append(Math.min(ans, ans1)).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }

    static int getNumberLen(long n){
        int c = 0;
        while(n>0){
            c++;
            n /= 10;
        }
        return c;
    }

    static long getMaxValue(int n){
        switch(n){
            case 1: return 1;
            case 2: return 22;
            case 3: return 122;
            case 4: return 1333;
            case 5: return 14444;
            case 6: return 122333;
            case 7: return 1224444;
            case 8: return 12255555;
            case 9: return 122666666;
            case 10: return 1223334444;
            case 11: return 12233355555L;
            case 12: return 122333666666L;
            case 13: return 1223337777777L;
            case 14: return 12233388888888L;
        }
        return -1;
    }

    static int[][][] getCombs(){

        return new int[][][]{
                {
                    {1}}, {{2}
                },
                {
                    {1, 2}, {3}
                },
                {
                    {1, 3}, {4}
                },
                {
                    {1, 4}, {3, 2}, {5}
                },
                {
                    {2, 4}, {3, 2, 1}, {1, 5}, {6}
                },
                {
                    {1, 2, 4}, {3, 4}, {2, 5}, {1, 6}, {7}
                },
                {
                    {1, 3, 4}, {1, 2, 5}, {5, 2}, {6, 2}, {7, 1}, {8}
                },
                {
                    {2, 3, 4}, {1, 3, 5}, {5, 4}, {1, 2, 6}, {3, 6}, {2, 7}, {1, 8}, {9}
                },
                {
                    {1, 2, 3, 4}, {2, 3, 5}, {5, 4, 1}, {6, 3, 1}, {6, 4},
                        {7, 2, 1}, {7, 3}, {8, 2}, {9, 1}
                },
                {
                    {5, 3, 2, 1}, {5, 4, 2}, {6, 3, 2}, {6, 4, 1}, {6, 5}, {7, 3, 1}, {7, 4},
                        {8, 2, 1}, {8, 3}, {9, 2}
                },
                {
                    {5, 4, 2, 1}, {5, 4, 3}, {6, 3, 2, 1}, {6, 4, 2}, {6, 5, 1}, {7, 3, 2}, {7, 4, 1},
                        {8, 3, 1}, {8, 4}, {9, 2, 1}, {7, 5}, {9, 3}
                },
                {
                    {5, 4, 3, 1}, {6, 4, 2, 1}, {6, 4, 3}, {6, 5, 2}, {7, 3, 2, 1}, {7, 4, 2},
                        {8, 3, 2}, {8, 5}, {7, 5, 1}, {7, 6}, {8, 4, 1}, {9, 3, 1}, {9, 4}
                }
        };
    }

    static long getIdealVal(int[] arr, long n, int sn){
        int[] hash = new int[10];
        for(int a: arr){
            hash[a] = a;
        }

        String s = Long.toString(n);
        int[] ans = new int[sn];
        for(int i=0;i<sn;i++){
            int d = s.charAt(i)-'0';

            if(hash[d]>0){
                hash[d]--;
                ans[i] = d;
                continue;
            }

            for(int j=d+1;j<10;j++){
                if(hash[j]>0){
                    hash[j]--;
                    ans[i] = j;
                    i++;
                    for(int i1=0;i1<10;i1++){
                        for(int k=1;k<=hash[i1];k++){
                            ans[i] = i1;
                            i++;
                        }
                    }
                    return getNumber(ans);
                }
            }

            for(int rev=i-1;rev>=0;rev--){
                hash[ans[rev]]++;
                for(int j=ans[rev]+1;j<10;j++){
                    if(hash[j]>0){
                        hash[j]--;
                        ans[rev] = j;
                        rev++;
                        for(int i1=0;i1<10;i1++){
                            for(int k=1;k<=hash[i1];k++){
                                ans[rev] = i1;
                                rev++;
                            }
                        }
                        return getNumber(ans);
                    }
                }
            }

            return Long.MAX_VALUE;
        }
        return getNumber(ans);
    }

    static long getNumber(int[] arr){
        long ans = 0;
        for (int j : arr) {
            ans = (ans * 10) + j;
        }
        return ans;
    }

    static boolean isBeautiful(long n){
        int[] hash = new int[10];
        while(n>0){
            int j = (int) n%10;
            hash[j]++;
            n/=10;
        }

        for(int i=0;i<hash.length;i++){
            if(hash[i]!=0){
                if(hash[i]!=i)
                    return false;
            }
        }
        return true;
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
