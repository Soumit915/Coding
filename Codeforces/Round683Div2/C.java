package Codeforces.Round683Div2;

import java.io.*;
import java.util.*;

public class C {
    static class Item implements Comparable<Item>
    {
        int id;
        long weight;
        Item(int id, long weight)
        {
            this.id = id;
            this.weight = weight;
        }
        public int compareTo(Item i)
        {
            return Long.compare(this.weight, i.weight);
        }
    }
    public static int search(long[] sum, long atleast, long atmost, int ll, int ul)
    {
        //System.out.println("limits; "+ll+" "+ul);
        if(ll==ul) {
           // System.out.println(sum[ll]+" "+atleast+" "+atmost);
            if (atleast <= sum[ll] && sum[ll] <= atmost)
                return ll;
        }
        else if(ll<ul)
        {
            int mid = (ll+ul)/2;
           // System.out.println("Test: "+mid);
            if(atleast<=sum[mid] && sum[mid]<=atmost)
                return mid;
            else if(sum[mid]<atleast)
                return search(sum ,atleast, atmost, mid+1, ul);
            else return search(sum, atleast, atmost, ll, mid-1);
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        /*Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");*/

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            int n = sc.nextInt();
            long w = sc.nextLong();

            long c = (long) Math.ceil(w/2.0);
            long[] weights = sc.nextLongArray(n);

            Item[] items = new Item[n];
            for(int i=0;i<n;i++)
                items[i] = new Item(i, weights[i]);

            Arrays.sort(items);

            long[] sum = new long[n];
            sum[0] = items[0].weight;
            for(int i=1;i<n;i++)
            {
                sum[i] = items[i].weight + sum[i-1];
            }

            int start = -1;
            int end = -1;
            for(int i=0;i<n;i++)
            {
                //System.out.println(sum[i]);
                if(sum[i]<c)
                    continue;
                else if(sum[i]>=c && sum[i]<=w)
                {
                    start = 0;
                    end= i;
                    break;
                }

                int k = search(sum, sum[i]-w, sum[i]-c, 0, i-1);
               // System.out.println(sum[i]+" "+k);
                if(k!=-1)
                {
                    start = k+1;
                    end = i;
                    break;
                }
            }

            if(start==-1)
                sb.append("-1\n");
            else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(items[i].id);

                Collections.sort(list);
                sb.append(list.size()).append("\n");
                for(int i: list)
                    sb.append(i+1).append(" ");
                sb.append("\n");
            }
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
