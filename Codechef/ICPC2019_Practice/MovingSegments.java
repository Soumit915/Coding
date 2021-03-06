package Codechef.ICPC2019_Practice;

import java.io.*;
import java.util.*;

public class MovingSegments {
    static class Segments
    {
        long start;
        long end;
        long velocity;
        Segments(long start, long end, long velocity)
        {
            this.start = start;
            this.end = end;
            this.velocity = velocity;
        }
    }
    static class Start implements Comparable<Start>
    {
        int id;
        long start;
        Start(int id, long start)
        {
            this.id = id;
            this.start = start;
        }
        public int compareTo(Start s)
        {
            return Long.compare(this.start, s.start);
        }
    }
    static class End implements Comparable<End>
    {
        int id;
        long end;
        End(int id, long end)
        {
            this.id = id;
            this.end = end;
        }
        public int compareTo(End e)
        {
            return Long.compare(this.end, e.end);
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();

            HashMap<Long, ArrayList<Segments>> hash = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                Segments segments = new Segments(sc.nextLong(), sc.nextLong(), sc.nextLong());

                ArrayList<Segments> arlist = hash.getOrDefault(segments.velocity, new ArrayList<>());
                arlist.add(segments);
                hash.put(segments.velocity, arlist);
            }

            boolean flag = true;
            for(Long l: hash.keySet())
            {
                ArrayList<Segments> arlist = hash.get(l);
                n = arlist.size();

                PriorityQueue<Start> starts = new PriorityQueue<>();
                PriorityQueue<End> ends = new PriorityQueue<>();

                for(int i=0;i<n;i++)
                {
                    starts.add(new Start(i, arlist.get(i).start));
                    ends.add(new End(i, arlist.get(i).end));
                }

                int current = 0;
                while(!starts.isEmpty())
                {
                    Start s = starts.peek();
                    End e = ends.peek();

                    if(s.start<=e.end)
                    {
                        current++;
                        starts.remove();
                    }
                    else {
                        current--;
                        ends.remove();
                    }

                    if(current>2)
                    {
                        flag = false;
                    }

                    if(!flag)
                        break;
                }

                if(!flag)
                    break;
            }

            sb.append(flag?"YES":"NO").append("\n");
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
