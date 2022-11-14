package Hackerearth.DataStructures.Heaps_PriorityQueues;

import java.io.*;
import java.util.*;

public class GuptasProblem {
    static class Pump implements Comparable<Pump>
    {
        int index;
        int dist;
        int amountPetrol;
        long residuePetrol;
        Pump(int dist, int amountPetrol)
        {
            this.index = -1;
            this.dist = dist;
            this.amountPetrol = amountPetrol;
        }
        public int compareTo(Pump p)
        {
            return Integer.compare(this.dist, p.dist)*(-1);
        }
    }
    static class Priority_amount implements Comparator<Pump>
    {
        public int compare(Pump p1, Pump p2)
        {
            return Integer.compare(p1.amountPetrol, p2.amountPetrol);
        }
    }
    public static long min(Pump[] pumps, int ind)
    {
        long min = Integer.MAX_VALUE;
        for(int i=ind;i<pumps.length;i++)
        {
            min = Math.min(min, pumps[i].residuePetrol);
        }

        return min;
    }
    public static void decrement(Pump[] pumps, int ind, long val)
    {
        for(int i=ind;i<pumps.length;i++)
        {
            pumps[i].residuePetrol -= val;
        }
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int p = sc.nextInt();

            Pump[] pumps = new Pump[n];
            for(int i=0;i<n;i++)
            {
                pumps[i] = new Pump(sc.nextInt(), sc.nextInt());
            }
            Arrays.sort(pumps);

            for(int i=0;i<n;i++)
                pumps[i].index = i;

            PriorityQueue<Pump> minheap = new PriorityQueue<>(new Priority_amount());
            long tankquantity = p;
            boolean flag = true;
            for(int i=0;i<n;i++)
            {
                //System.out.println(i+" "+pumps[i].dist+" "+tankquantity);
                if(tankquantity<(k-pumps[i].dist))
                {
                    flag = false;
                    break;
                }
                tankquantity += pumps[i].amountPetrol;
                pumps[i].residuePetrol = tankquantity;
            }

            if(tankquantity<k)
            {
                flag = false;
            }
            else
            {
                tankquantity -= k;
            }

            if(!flag)
            {
                sb.append("-1\n");
                continue;
            }

            int count = n;
            minheap.addAll(Arrays.asList(pumps));
            while(!minheap.isEmpty())
            {
                Pump minpump = minheap.remove();
                if(minpump.amountPetrol>tankquantity)
                    break;
                long min = min(pumps, minpump.index);
                if(min>=minpump.amountPetrol)
                {
                    decrement(pumps, minpump.index, minpump.amountPetrol);
                    tankquantity -= minpump.amountPetrol;
                    count--;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/1000);

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
