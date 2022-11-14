package Hackerrank.Algorithms.BitManipulation;

import java.io.*;
import java.util.*;

public class WhatsNext {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            int n = sc.nextInt();

            long[] arr = new long[n];
            for(int i=0;i<n;i++)
                arr[i] = sc.nextLong();
            if(n%2==0)
            {
                if(n==2)
                {
                    if(arr[0]==1)
                    {
                        sb.append(2).append("\n");
                        sb.append("1 ").append(arr[1]+1).append(" ");
                    }
                    else
                    {
                        sb.append(3).append("\n");
                        sb.append("1 ").append(arr[1]+1).append(" ");
                        sb.append(arr[0]-1);
                    }
                }
                else
                {
                    if(arr[n-3]==1)
                    {
                        if(arr[n-2]==1)
                        {
                            sb.append(n-2).append("\n");
                            for(int i=0;i<n-4;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-4]+1).append(" ");
                            sb.append(arr[n-3]+arr[n-1]).append(" ");
                        }
                        else
                        {
                            sb.append(n-1).append("\n");
                            for(int i=0;i<n-4;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-4]+1).append(" ");
                            sb.append(arr[n-3]+arr[n-1]).append(" ");
                            sb.append(arr[n-2]-1).append(" ");
                        }
                    }
                    else
                    {
                        if(arr[n-2]==1)
                        {
                            sb.append(n).append("\n");
                            for(int i=0;i<n-4;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-4]).append(" ");
                            sb.append(arr[n-3]-1).append(" ");
                            sb.append(1).append(" ");
                            sb.append(arr[n-1]+1).append(" ");
                        }
                        else
                        {
                            sb.append(n+1).append("\n");
                            for(int i=0;i<n-4;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-4]).append(" ");
                            sb.append(arr[n-3]-1).append(" ");
                            sb.append(1).append(" ");
                            sb.append(arr[n-1]+1).append(" ");
                            sb.append(arr[n-2]-1).append(" ");
                        }
                    }
                }
            }
            else
            {
                if(n==1)
                {
                    if(arr[0]==1)
                    {
                        sb.append(2).append("\n");
                        sb.append("1 1");
                    }
                    else
                    {
                        sb.append(3).append("\n");
                        sb.append(1).append(" ");
                        sb.append(1).append(" ");
                        sb.append(arr[0]-1).append(" ");
                    }
                }
                else
                {
                    if(arr[n-2]==1)
                    {
                        if(arr[n-1]==1)
                        {
                            sb.append(n-1).append("\n");
                            for(int i=0;i<n-3;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-3]+1).append(" ");
                            sb.append(arr[n-2]).append(" ");
                        }
                        else {
                            sb.append(n).append("\n");
                            for(int i=0;i<n-3;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-3]+1).append(" ");
                            sb.append(arr[n-2]).append(" ");
                            sb.append(arr[n-1]-1).append(" ");
                        }
                    }
                    else
                    {
                        if(arr[n-1]==1)
                        {
                            sb.append(n+1).append("\n");
                            for(int i=0;i<n-3;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-3]).append(" ");
                            sb.append(arr[n-2]-1).append(" ");
                            sb.append(1).append(" ");
                            sb.append(1).append(" ");
                        }
                        else
                        {
                            sb.append(n+2).append("\n");
                            for(int i=0;i<n-3;i++)
                            {
                                sb.append(arr[i]).append(" ");
                            }
                            sb.append(arr[n-3]).append(" ");
                            sb.append(arr[n-2]-1).append(" ");
                            sb.append(1).append(" ");
                            sb.append(1).append(" ");
                            sb.append(arr[n-1]-1).append(" ");
                        }
                    }
                }
            }
            sb.append("\n");
        }

        sc.println(sb.toString());

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
