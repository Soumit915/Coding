package Hackerrank.Hackfest2020;

import java.io.*;
import java.util.*;

public class RBG_Queries {
    static class Color implements Comparable<Color>
    {
        int id;
        int red;
        int blue;
        int green;
        Color(int id, int red, int blue, int green)
        {
            this.id = id;
            this.red = red;
            this.blue = blue;
            this.green = green;
        }
        public int compareTo(Color c)
        {
            int r = Integer.compare(this.red, c.red);
            if(r==0)
            {
                r = Integer.compare(this.blue, c.blue);
                if(r==0)
                    return Integer.compare(this.green, c.green);
                else return r;
            }
            return r;
        }
    }
    public static int getnextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);

        return n+1;
    }
    public static void built(int[] segTree, int sn, int[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);
        segTree[sn] = Math.min(segTree[2*sn+1], segTree[2*sn+2]);
    }
    public static void update(int[] segTree, int sn, int val, int ind, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(ind<=mid)
        {
            update(segTree, 2*sn+1, val, ind, ll, mid);
        }
        else
        {
            update(segTree, 2*sn+2, val, ind, mid+1, ul);
        }

        segTree[sn] = Math.min(segTree[2*sn+1], segTree[2*sn+2]);
    }
    public static long query(int[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll)
        {
            return Integer.MAX_VALUE;
        }

        //for total overlap
        if(start<=ll && end>=ul)
        {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        return Math.min(query(segTree, 2*sn+1, start, end, ll, mid)
                , query(segTree, 2*sn+2, start, end, mid+1, ul));
    }
    public static void main(String[] args) throws IOException {
        //Soumit sc = new Soumit("Input.txt");
        //sc.streamOutput("Output1.txt");

        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        Color[] colors = new Color[n];
        Color[] queries = new Color[q];
        for(int i=0;i<n;i++)
        {
            colors[i] = new Color(i, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        for(int i=0;i<q;i++)
        {
            queries[i] = new Color(i, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(colors);
        Arrays.sort(queries);

        String[] ans = new String[q];
        for(int i=0;i<q;i++)
        {
            ans[i] = "NO";
        }

        HashMap<Integer, TreeSet<Integer>> blue = new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> green = new HashMap<>();
        int i=0, j=0;
        while(i<n && j<q)
        {
            int r = colors[i].red;

            int sn = 2*getnextPowerOf2(100001)-1;
            int[] segTree = new int[sn];

            int[] bluearr = new int[100001];
            for(int i1=0;i1<100001;i1++)
            {
                bluearr[i1] = Integer.MAX_VALUE;
            }
            built(segTree, 0, bluearr, 0, 100000);

            while(i<n && colors[i].red==r)
            {
                int b = colors[i].blue;
                int g = colors[i].green;

                bluearr[b] = Math.min(bluearr[b], g);
                update(segTree, 0, bluearr[b], b, 0, 100000);

                TreeSet<Integer> blue_tree = blue.getOrDefault(b, new TreeSet<>());
                blue_tree.add(g);
                blue.put(b, blue_tree);

                TreeSet<Integer> green_tree = green.getOrDefault(g, new TreeSet<>());
                green_tree.add(b);
                green.put(g, green_tree);
                i++;
            }

            while(j<q && queries[j].red<r)
            {
                j++;
            }

            while(j<q && queries[j].red==r)
            {
                int b = queries[j].blue;
                int g = queries[j].green;

                TreeSet<Integer> blue_tree = blue.getOrDefault(b, null);
                TreeSet<Integer> green_tree = green.getOrDefault(g, null);

                //System.out.println(blue);
                //System.out.println(green);
                if(query(segTree, 0, 0, b, 0, 100000) > g)
                {
                    j++;
                    continue;
                }

                if(blue_tree==null || green_tree==null)
                {
                    //System.out.println("tester");
                    j++;
                    continue;
                }

                if(blue_tree.floor(g)==null || green_tree.floor(b)==null)
                {
                    //System.out.println("test");
                    j++;
                    continue;
                }

                /*System.out.println(query(segTree, 0, 0, b, 0, 100000));
                System.out.println(b);*/
                ans[queries[j].id] = "YES";
                j++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String s: ans)
            sb.append(s).append("\n");

        System.out.print(sb.toString());

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
