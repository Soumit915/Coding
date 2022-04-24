package Codeforces.Round780Div3;

import java.io.*;
import java.util.*;

public class D {

    static class Parts{
        int start;
        int end;
        boolean isPositive;
        int two;
        Parts(int start, int end, int two, boolean isPositive){
            this.start = start;
            this.end = end;
            this.two = two;
            this.isPositive = isPositive;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            List<Parts> partsList = new ArrayList<>();
            int last = -1;
            int count = 0;
            int negatives = 0;
            for(int i=0;i<n;i++){
                if(arr[i] == 0){
                    if(last < i - 1){
                        partsList.add(new Parts(last + 1, i - 1, count, negatives%2==0));
                    }

                    last = i;
                    count = 0;
                    negatives = 0;
                }
                else{
                    if(arr[i] < 0){
                        negatives++;
                    }

                    if(Math.abs(arr[i]) == 2){
                        count++;
                    }
                }
            }

            if(last < n - 1) {
                partsList.add(new Parts(last + 1, n - 1, count, negatives % 2 == 0));
            }

            int max = 0;
            int start = -1, end = -1;
            for(Parts part: partsList){
                if(part.isPositive){
                    if(max < part.two){
                        max = part.two;
                        start = part.start;
                        end = part.end;
                        continue;
                    }
                    continue;
                }

                int beginInd = -1;
                int begin_2count = 0;
                for(int i=part.start;i<=part.end;i++){
                    if(Math.abs(arr[i]) == 2){
                        begin_2count++;
                    }

                    if(arr[i] < 0){
                        beginInd = i;
                        break;
                    }
                }

                int endInd = -1;
                int end_2count = 0;
                for(int i=part.end;i>=part.start;i--){
                    if(Math.abs(arr[i]) == 2){
                        end_2count++;
                    }

                    if(arr[i] < 0){
                        endInd = i;
                        break;
                    }
                }

                if(beginInd == -1){
                    continue;
                }

                if(beginInd == endInd){
                    if(begin_2count > end_2count){
                        if(max < begin_2count){
                            if(arr[beginInd] == -2)
                                max = begin_2count - 1;
                            else max = begin_2count;
                            start = part.start;
                            end = beginInd - 1;
                            continue;
                        }
                    }
                    else{
                        if(max < end_2count){
                            if(arr[beginInd] == -2)
                                max = end_2count - 1;
                            else max = end_2count;
                            start = endInd + 1;
                            end = part.end;
                            continue;
                        }
                    }
                }

                int left_2count;
                if(begin_2count < end_2count){
                    left_2count = part.two - begin_2count;
                    if(max < left_2count){
                        max = left_2count;
                        start = beginInd + 1;
                        end = part.end;
                    }
                }
                else{
                    left_2count = part.two - end_2count;
                    if(max < left_2count){
                        max = left_2count;
                        start = part.start;
                        end = endInd - 1;
                    }
                }
            }

            if(start < 0) {
                sb.append("0 ").append(n - end - 1).append("\n");
            }
            else {
                sb.append(start).append(" ").append(n - end - 1).append("\n");
            }
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
