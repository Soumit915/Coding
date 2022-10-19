package Codeforces.Expert_7;

import java.util.*;
import java.io.*;

public class E {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();

        while(tc-->0){
            int n = sc.nextInt();

            int tot_count = (1 << n);

            List<Integer> list = new ArrayList<>();
            for(int i=1;i<=tot_count;i++){
                list.add(i);
            }

            while(list.size() != 1){
                if(list.size() == 2){
                    System.out.println("? "+list.get(0)+" "+list.get(1));
                    System.out.flush();
                    int winner = sc.nextInt();

                    int ans;
                    if(winner == 1)
                        ans = list.get(0);
                    else ans = list.get(1);

                    list = new ArrayList<>();
                    list.add(ans);
                }
                else{
                    List<Integer> local = new ArrayList<>();

                    StringBuilder sb = new StringBuilder();
                    for(int i=0;i<list.size();i+=4){
                        sb.append("? ").append(list.get(i)).append(" ").append(list.get(i + 2)).append("\n");
                    }
                    System.out.println(sb);
                    System.out.flush();

                    int[] winner = sc.nextIntArray(list.size() / 4);

                    sb = new StringBuilder();
                    for(int j=0;j<winner.length;j++){
                        int i = j * 4;

                        if(winner[j] == 0){
                            sb.append("? ").append(list.get(i + 1)).append(" ").append(list.get(i + 3)).append("\n");
                        }
                        else if(winner[j] == 1){
                            sb.append("? ").append(list.get(i)).append(" ").append(list.get(i + 3)).append("\n");
                        }
                        else{
                            sb.append("? ").append(list.get(i + 1)).append(" ").append(list.get(i + 2)).append("\n");
                        }
                    }
                    System.out.println(sb);
                    System.out.flush();

                    int[] winner2 = sc.nextIntArray(winner.length);

                    for(int j=0;j<winner.length;j++){
                        int i = j * 4;

                        if(winner[j] == 0){
                            if(winner2[j] == 1)
                                local.add(list.get(i+1));
                            else local.add(list.get(i+3));
                        }
                        else if(winner[j] == 1){
                            if(winner2[j] == 1)
                                local.add(list.get(i));
                            else local.add(list.get(i+3));
                        }
                        else{
                            if(winner2[j] == 1)
                                local.add(list.get(i+1));
                            else local.add(list.get(i+2));
                        }
                    }

                    list = local;
                }
            }

            System.out.println("! "+list.get(0));
            System.out.flush();
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
