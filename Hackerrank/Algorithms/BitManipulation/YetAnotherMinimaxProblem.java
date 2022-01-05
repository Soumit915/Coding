package Hackerrank.Algorithms.BitManipulation;

import java.io.*;
import java.util.*;

public class YetAnotherMinimaxProblem {
    static class Node
    {
        int val;
        Node c0;
        Node c1;
        boolean isLeaf;
        Node()
        {
            this.val = 0;
            this.c0 = null;
            this.c1 = null;
            this.isLeaf = false;
        }
    }
    static class Tree
    {
        Node root;
        Tree()
        {
            this.root = new Node();
        }
        public void add(int val, String s)
        {
            int n = s.length();
            Node ptr = root;
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='0')
                {
                    if(ptr.c0==null)
                        ptr.c0 = new Node();
                    ptr = ptr.c0;
                }
                else
                {
                    if(ptr.c1==null)
                        ptr.c1 = new Node();
                    ptr = ptr.c1;
                }
            }

            ptr.isLeaf = true;
            ptr.val = val;
        }
        public void dfs()
        {
            dfs(this.root);
        }
        public void dfs(Node root)
        {
            if(root==null)
                return;
            if(root.isLeaf)
            {
                System.out.println("Test: "+root.val);
                return;
            }

            dfs(root.c0);
            dfs(root.c1);
        }
        public int get(int val, String s)
        {
            int n = s.length();

            Node ptr = root.c0;
            for(int i=1;i<n;i++)
            {
                if(s.charAt(i)=='0')
                {
                    if(ptr.c0==null)
                    {
                        ptr = ptr.c1;
                    }
                    else
                        ptr = ptr.c0;
                }
                else
                {
                    if(ptr.c1==null)
                        ptr = ptr.c0;
                    else ptr = ptr.c1;
                }
            }

            return val ^ ptr.val;
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int max = -1;
        for(int i=0;i<n;i++)
        {
            max = Math.max(max, arr[i]);
        }

        if(max == 0)
        {
            System.out.println(0);
            System.exit(0);
        }

        String s = Integer.toBinaryString(max);
        int mbs = s.length();

        ArrayList<Integer> msb0 = new ArrayList<>();
        ArrayList<Integer> msb1 = new ArrayList<>();

        int k = mbs;
        int msb_set = 0;
        while (msb0.size()==0)
        {
            k--;
            msb_set |= (1<< k);
            msb1 = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                if(arr[i]<msb_set)
                    msb0.add(arr[i]);
                else msb1.add(arr[i]);
            }
        }

        int min = Integer.MAX_VALUE;
        Tree tr = new Tree();
        for (Integer integer : msb0) {
            String bin = Integer.toBinaryString(integer);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < mbs - bin.length(); j++) {
                sb.append('0');
            }
            for(int j = mbs-k-1;j < bin.length(); j++)
            {
                sb.append(bin.charAt(j));
            }
            tr.add(integer, sb.toString());
        }

        for (Integer integer : msb1) {
            String bin = Integer.toBinaryString(integer);
            StringBuilder sb = new StringBuilder();
            for(int j = mbs-k-1;j < bin.length();j++)
            {
                sb.append(bin.charAt(j));
            }
            min = Math.min(tr.get(integer, sb.toString()), min);
        }

        System.out.println(min);

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
