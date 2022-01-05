package Codeforces.GlobalRound11;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            char[] s = sc.next().toCharArray();

            ArrayList<Integer> list = new ArrayList<>();

            int count = 1;
            for(int i=1;i<n;i++)
            {
                if(s[i]==s[i-1])
                {
                    count++;
                }
                else
                {
                    list.add(count);
                    count = 1;
                }
            }

            list.add(count);

            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int se = 0;
            if(s[0]=='W')
            {
                for(int i=1;i<list.size();i+=2)
                {
                    if(i!=list.size()-1)
                        heap.add(list.get(i));
                    else se += list.get(i);
                }
            }
            else
            {
                if(list.size()>0) se += list.get(0);
                for(int i=2;i<list.size();i+=2)
                {
                    if(i!=list.size()-1)
                        heap.add(list.get(i));
                    else se += list.get(i);
                }
            }

            if(list.size()==1 && s[0] == 'L' && k>0)
            {
                long points = 1;
                points += 2*(k-1);
                sb.append(points).append("\n");
                continue;
            }

            long points = 0;
            if(s[0]=='W') points = 1;
            for(int i=1;i<n;i++)
            {
                if(s[i] == 'W' && s[i]==s[i-1])
                {
                    points += 2;
                }
                else if(s[i]=='W')
                {
                    points += 1;
                }
            }

            //System.out.println("test: "+points+" "+se);
         //   System.out.println(list);
          //  System.out.println(heap);
            while(!heap.isEmpty())
            {
                int sm = heap.peek();
                if(sm>k)
                {
                    se += sm;
                    break;
                }
                else
                {
                    k -= sm;
                    heap.remove();
                    points += (sm*2) +1 ;
                }
            }

           // System.out.println("testeswre "+points+" "+k+" "+se);
            points += Math.min(k, se) *2 ;

            sb.append(points).append("\n");
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
