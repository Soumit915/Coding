package Hackerrank.DataStructures.Trees;

import java.io.*;
import java.util.*;

public class MedianUpdates {
    static class Number implements Comparable<Number>
    {
        long val;
        int count;
        Number(long val)
        {
            this.val = val;
            this.count = 0;
        }
        Number(long val, int count)
        {
            this.val = val;
            this.count = count;
        }
        public int compareTo(Number n)
        {
            return Long.compare(this.val, n.val);
        }
    }
    static class Tree
    {
        TreeSet<Number> tree;
        private int tsize;
        Tree()
        {
            tree = new TreeSet<>();
            this.tsize = 0;
        }
        public void add(long val)
        {
            Number num = new Number(val);
            if(this.tree.contains(num))
            {
                tree.ceiling(num).count++;
            }
            else
            {
                tree.add(new Number(num.val, 1));
            }
            this.tsize++;
        }
        public void remove(long val)
        {
            if(tsize==0)
                return;
            Number num = new Number(val);
            if(this.tree.contains(num))
            {
                tree.ceiling(num).count--;
                if(tree.ceiling(num).count==0)
                    tree.remove(num);
                this.tsize--;
            }
        }
        public boolean contains(long val)
        {
            return tree.contains(new Number(val));
        }
        public int size()
        {
            return this.tsize;
        }
        public boolean isEmpty()
        {
            return this.tsize==0;
        }
        public long first()
        {
            return tree.first().val;
        }
        public long last()
        {
            return tree.last().val;
        }
    }
    public static void main(String[] args) throws IOException
    {
        Soumit sc = new Soumit();

        int n = sc.nextInt();

        Tree tree1 = new Tree();
        Tree tree2 = new Tree();

        StringBuilder sb = new StringBuilder();
        while(n-->0)
        {
            //char ch = sc.next().charAt(0);

            byte c = sc.read();
            char ch = (char) c;
            long val = sc.nextLong();
            //System.out.println("test "+val+" "+c);

            if(ch=='a')
            {
                if(tree1.isEmpty())
                {
                    tree1.add(val);
                    sb.append(val);
                }
                else if(tree1.size()==tree2.size())
                {
                    long v2 = tree2.first();

                    if(val>v2)
                    {
                        tree2.remove(v2);
                        tree1.add(v2);
                        tree2.add(val);
                    }
                    else
                    {
                        tree1.add(val);
                    }
                    sb.append(tree1.last());
                }
                else
                {
                    long v1 = tree1.last();

                    if(val<v1)
                    {
                        tree1.remove(v1);
                        tree2.add(v1);
                        tree1.add(val);
                    }
                    else
                    {
                        tree2.add(val);
                    }
                    long d1 = tree1.last();
                    long d2 = tree2.first();
                    if((d1+d2)/2==0 && (d1+d2)<0)
                    {
                        sb.append("-0.5");
                    }
                    else
                    {
                        sb.append((d1+d2)/2);
                        if((d1+d2)%2!=0)
                        {
                            sb.append(".5");
                        }
                    }
                }
            }
            else
            {
                if(tree1.size()==0 && tree2.size()==0)
                {
                    sb.append("Wrong!");
                }
                else
                {
                    if(tree1.contains(val))
                    {
                        tree1.remove(val);
                        if(tree1.size()!=tree2.size())
                        {
                            long v = tree2.first();
                            tree2.remove(v);
                            tree1.add(v);
                            sb.append(tree1.last());
                        }
                        else
                        {
                            if(tree1.size()==0)
                            {
                                sb.append("Wrong!");
                            }
                            else
                            {
                                long d1 = tree1.last();
                                long d2 = tree2.first();
                                if((d1+d2)/2==0 && (d1+d2)<0)
                                {
                                    sb.append("-0.5");
                                }
                                else
                                {
                                    sb.append((d1+d2)/2);
                                    if((d1+d2)%2!=0)
                                    {
                                        sb.append(".5");
                                    }
                                }
                            }
                        }
                    }
                    else if(tree2.contains(val))
                    {
                        tree2.remove(val);
                        if(tree1.size()-1==tree2.size())
                        {
                            sb.append(tree1.last());
                        }
                        else
                        {
                            long v = tree1.last();
                            tree1.remove(v);
                            tree2.add(v);
                            long d1 = tree1.last();
                            long d2 = tree2.first();
                            if((d1+d2)/2==0 && (d1+d2)<0)
                            {
                                sb.append("-0.5");
                            }
                            else
                            {
                                sb.append((d1+d2)/2);
                                if((d1+d2)%2!=0)
                                {
                                    sb.append(".5");
                                }
                            }
                        }
                    }
                    else
                    {
                        sb.append("Wrong!");
                    }
                }
            }
            sb.append("\n");
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
