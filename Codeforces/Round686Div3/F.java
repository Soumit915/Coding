package Codeforces.Round686Div3;

import java.io.*;
import java.util.*;

public class F {
    public static int nextPowerof2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        return (n+1);
    }

    public static void maxbuilt(long[] seg_Tree, long[] arr, int sn, int ll, int ul)
    {
        if(ll==ul)
        {
            seg_Tree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        maxbuilt(seg_Tree, arr, 2*sn+1, ll, mid);
        maxbuilt(seg_Tree, arr, 2*sn+2, mid+1, ul);
        seg_Tree[sn] = Math.max(seg_Tree[2*sn+1], seg_Tree[2*sn+2]);
    }
    public static long maxquery(long[] seg_Tree, int ind, int start, int end, int l, int r)
    {
        //Check for total overlap
        if(start >= l && end <= r)
        {
            return seg_Tree[ind];
        }
        //Check for no overlap
        if(start>r || end<l)
        {
            return Long.MIN_VALUE;
        }
        //Check for partial overlap
        int mid = (start+end)/2;
        return Math.max(maxquery(seg_Tree, ind*2+1, start, mid, l, r),
                maxquery(seg_Tree, ind*2+2, mid+1, end, l, r));
    }
    public static void maxupdate(long[] seg_Tree, int ind, int start, int end, int ni, long val)
    {
        if(start == end)
        {
            seg_Tree[ind] = val;
            return;
        }

        int mid = (start+end)/2;
        if(ni<=mid)
        {
            maxupdate(seg_Tree, 2*ind+1, start, mid, ni, val);
        }
        else
        {
            maxupdate(seg_Tree, 2*ind+2, mid+1, end, ni, val);
        }

        seg_Tree[ind] = Math.max(seg_Tree[2*ind+1], seg_Tree[2*ind+2]);
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-->0)
        {
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);

            int sn = 2*nextPowerof2(n)-1;
            if((n&(n-1))==0)
                sn = 2*n-1;
            long[] maxseg_tree = new long[sn];
            for(int i=0;i<sn;i++)
                maxseg_tree[i] = Long.MIN_VALUE;
            maxbuilt(maxseg_tree,arr,0,0,n-1);

            boolean flag = false;
            for(int i=1;i<n;i++)
            {
                if(maxquery(maxseg_tree, 0, 0, n-1, 0, i-1)==arr[i] &&
                        arr[i]==maxquery(maxseg_tree, 0, 0, n-1, i+1, n-1))
                {
                    if(i+1+1<n)
                    {
                        sb.append("YES").append("\n");
                        sb.append(i).append(" 1 ").append(n-i-1).append("\n");
                        flag = true;
                        break;
                    }

                }
            }

            if(!flag)
                sb.append("NO\n");
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
