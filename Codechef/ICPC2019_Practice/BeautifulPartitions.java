package Codechef.ICPC2019_Practice;

import java.io.*;
import java.util.*;

public class BeautifulPartitions {
    static int lis(ArrayList<Long> list, long val, int ll, int ul){
        if(ll<=ul){
            int mid = (ll+ul)/2;
            long mid_pair = list.get(mid);

            if(mid_pair==val){
                return mid;
            }
            else if(mid_pair<val){
                if(mid==list.size()-1){
                    list.add(val);
                    return mid+1;
                }
                else{
                    return lis(list, val, mid+1, ul);
                }
            }
            else{
                if(mid==0){
                    list.set(mid, val);
                    return mid;
                }
                else if(list.get(mid-1)<val){
                    list.set(mid, val);
                    return mid;
                }
                else{
                    return lis(list, val, ll, mid-1);
                }
            }
        }
        else return -1;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            long[] arr = sc.nextLongArray(n);

            ArrayList<Long> arlist = new ArrayList<>();
            long[] prefix = new long[n];
            prefix[0] = arr[0];
            for(int i=1;i<n;i++){
                prefix[i] = prefix[i-1] + arr[i];
            }

            int start = n;
            for(int i=0;i<n;i++){
                if(prefix[i]>0){
                    start = i;
                    break;
                }
            }

            if(start>=n || prefix[n-1]<=0){
                sb.append("NO\n");
                continue;
            }

            int[] dp = new int[n];
            dp[start] = 1;
            arlist.add(prefix[start]);
            for(int i=start+1;i<n;i++){
                if(prefix[i]<=0)
                    continue;
                int ind = lis(arlist, prefix[i], 0, arlist.size()-1);
                dp[i] = ind+1;
            }

            if(dp[n-1]<k){
                sb.append("NO\n");
            }
            else{
                sb.append("YES\n");
                int count = dp[n-1];
                ArrayList<Integer> ans = new ArrayList<>();
                for(int i=n-1;i>=0;i--){
                    if(dp[i]==count){
                        count--;
                        ans.add(i);
                    }

                    if(count==dp[n-1]-k)
                        break;
                }

                int st = -1;
                for(int i=ans.size()-1;i>=0;i--){
                    sb.append(ans.get(i)-st).append(" ");
                    st = ans.get(i);
                }
                sb.append("\n");
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
