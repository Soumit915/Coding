package Hackerrank.Hackfest2020;

import java.io.*;
import java.util.*;

public class RBG_Queries_BruteForce {
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
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

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

            boolean flag = false;
            for(int i1=0;i1<n;i1++)
            {
                for(int i2=0;i2<n;i2++)
                {
                    for(int i3=0;i3<n;i3++)
                    {
                        Color c1 = colors[i1];
                        Color c2 = colors[i2];
                        Color c3 = colors[i3];

                        int r = Math.max(c1.red, Math.max(c2.red, c3.red));
                        int b = Math.max(c1.blue, Math.max(c2.blue, c3.blue));
                        int g = Math.max(c1.green, Math.max(c2.green, c3.green));

                        if(r==queries[i].red && b==queries[i].blue && g==queries[i].green)
                        {
                            flag = true;
                            break;
                        }
                    }

                    if(flag)
                        break;
                }

                if(flag)
                    break;
            }

            if(flag)
                sc.println("YES");
            else sc.println("NO");
        }

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
