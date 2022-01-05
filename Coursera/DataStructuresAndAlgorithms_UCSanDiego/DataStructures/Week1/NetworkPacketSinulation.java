package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week1;

import java.io.*;
import java.util.*;

public class NetworkPacketSinulation {
    static class Packet
    {
        int id;
        long arrival;
        long processing;
        Packet(int id, long arrival, long processing)
        {
            this.id = id;
            this.arrival = arrival;
            this.processing = processing;
        }
    }
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int s = sc.nextInt();
        int n = sc.nextInt();

        Queue<Packet> buffer = new LinkedList<>();
        Packet[] packets = new Packet[n];
        for(int i=0;i<n;i++)
        {
            Packet p = new Packet(i, sc.nextLong(), sc.nextLong());
            packets[i] = p;
        }

        if(n<1)
            System.exit(0);

        long[] start = new long[n];
        for(int i=0;i<n;i++)
            start[i] = -1;
        long t = packets[0].arrival;
        int i = 0;
        start[0] = t;
        while(i<n && packets[i].arrival==t)
        {
            if(buffer.size()<s)
            {
                buffer.add(packets[i]);
            }

            if(buffer.peek().processing==0)
            {
                Packet pckt = buffer.remove();
                t = Math.max(t, pckt.arrival);
                start[pckt.id] = t;
            }
            i++;
        }

        //System.out.println(buffer);

        if(!buffer.isEmpty())
        {
            Packet pckt1 = buffer.peek();
            t = Math.max(t, pckt1.arrival);
            start[pckt1.id] = t;
            t += pckt1.processing;
        }

        //System.out.println(buffer);

        Packet pckt;
        while(i<n)
        {
            while(i<n && packets[i].arrival<t)
            {
                if(buffer.size()<s)
                {
                    buffer.add(packets[i]);
                }
                i++;
            }

            buffer.remove();

            if(i<n && buffer.isEmpty())
                t = Math.max(t, packets[i].arrival);

            while(i<n && packets[i].arrival==t)
            {
                if(buffer.size()<s)
                {
                    buffer.add(packets[i]);
                }
                i++;
            }

            if(!buffer.isEmpty())
            {
                pckt = buffer.peek();
                t = Math.max(t, pckt.arrival);
                start[pckt.id] = t;
                t += pckt.processing;
            }

            //System.out.println(buffer+" "+t);
        }

        if(!buffer.isEmpty())
            buffer.remove();

        while(!buffer.isEmpty())
        {
            pckt = buffer.remove();
            t = Math.max(t, pckt.arrival);
            start[pckt.id] = t;
            t += pckt.processing;
        }

        StringBuilder sb = new StringBuilder();
        for(long l: start)
            sb.append(l).append("\n");
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
