package Codeforces.ITMO_PilotCourse.DisjointSetUnion.Step1;

import java.io.*;
import java.util.*;

public class B_DSU2 {
    static class Set
    {
        int count;
        int min;
        int max;
        Set parent;
        Set(int id)
        {
            this.count = 1;
            this.min = id;
            this.max = id;
            this.parent = this;
        }
        public void union(Set b)
        {
            Set pa = this.get();
            Set pb = b.get();
            if(pa == pb)
                return;

            if(pa.count > pb.count)
            {
                pb.parent = pa;
                pa.count += pb.count;
                pa.max = Math.max(pa.max, pb.max);
                pa.min = Math.min(pa.min, pb.min);
            }
            else
            {
                pa.parent = pb;
                pb.count += pa.count;
                pb.max = Math.max(pa.max, pb.max);
                pb.min = Math.min(pa.min, pb.min);
            }
        }
        public Set get()
        {
            if(this.parent==this)
                return this;
            this.parent = this.parent.get();
            return this.parent;
        }
    }

    public static void main(String[] args) throws IOException {
        double start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        double processing = 0.0;
        Set[] set = new Set[n];
        for(int i=0;i<n;i++)
            set[i] = new Set(i+1);

        StringBuilder sb = new StringBuilder();
        sc.next();
        while (m-->0)
        {
            String line = sc.readLine();
            int linelength = line.length();
            if(linelength==0)
                continue;
            String type;
            int ns=0, i=0;
            while(i<linelength && line.charAt(i)!=' ')
                i++;
            type = line.substring(ns, i);
            ns = ++i;

            if(type.equals("union"))
            {
                while(i<linelength && line.charAt(i)!=' ')
                    i++;
                int a = Integer.parseInt(line.substring(ns, i))-1;
                ns = ++i;
                String bs = line.substring(ns);
                bs = bs.trim();
                int b = Integer.parseInt(bs)-1;
                set[a].union(set[b]);
            }
            else
            {
                String as = line.substring(ns);
                as = as.trim();
                int a = Integer.parseInt(as)-1;
                Set s = set[a].get();
                sb.append(s.min).append(" ").append(s.max).append(" ").append(s.count).append("\n");
            }
        }
        double end1 = System.currentTimeMillis();
        System.out.println(sb);

        double end = System.currentTimeMillis();
        System.out.println((end1-start)/1000);
        System.out.println((end-start)/1000);
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
