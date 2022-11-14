package Codechef;

import java.io.*;
import java.util.*;

public class ChefLikesGoodSubsequences {
    static class Query implements Comparable<Query>
    {
        int id;
        int index;
        int val;
        Query(int id, int index, int val)
        {
            this.id = id;
            this.index = index;
            this.val = val;
        }
        public int compareTo(Query q)
        {
            return Integer.compare(this.index, q.index);
        }
    }
    public static void main(String[] args) throws IOException {
        //Soumit sc = new Soumit("Input.txt");
        //sc.streamOutput("Output1.txt");

        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-->0)
        {
            int n = sc.nextInt();
            int q = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            int[] dp = new int[n];
            dp[0] = 1;
            for(int i=1;i<n;i++)
            {
                if(arr[i]==arr[i-1])
                {
                    dp[i] = dp[i-1];
                }
                else
                {
                    dp[i] = dp[i-1]+1;
                }
            }

            Query[] queries = new Query[q];
            for(int query = 0;query<q;query++)
            {
                queries[query] = new Query(query, sc.nextInt()-1, sc.nextInt());
            }

            int[] ans = new int[q];
            int rel = 0;
            for(Query query: queries)
            {
                int id = query.id;
                int ind = query.index;
                int val = query.val;

                if(arr[ind]==val)
                {
                    rel += 0;
                }
                else {

                    if(ind!=n-1 && arr[ind] == arr[ind+1])
                    {
                        if(ind!=0 && arr[ind]==arr[ind-1])
                        {
                            rel += 2;
                        }
                        else
                        {
                            rel += 1;
                        }
                    }
                    else
                    {
                        if(ind!=0 && arr[ind]==arr[ind-1])
                        {
                            rel += 1;
                        }
                    }

                    if(ind!=n-1 && val == arr[ind+1])
                    {
                        if(ind!=0 && val==arr[ind-1])
                        {
                            rel -= 2;
                        }
                        else {
                            rel -= 1;
                        }
                    }
                    else {
                        if(ind!=0 && val==arr[ind-1])
                        {
                            rel -= 1;
                        }
                    }
                }

                arr[ind] = val;
                ans[id] = rel;
            }

            for(int i=0;i<q;i++)
            {
                ans[i] += dp[n-1];
            }

            for(int i: ans)
                sb.append(i).append("\n");
        }

        System.out.println(sb.toString());

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
            byte[] buf = new byte[100064]; // line length
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
