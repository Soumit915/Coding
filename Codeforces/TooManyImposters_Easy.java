package Codeforces;

import java.util.*;
import java.io.*;

public class TooManyImposters_Easy {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        while (tc-->0){
            int n = sc.nextInt();

            List<Integer> list = new ArrayList<>();
            int imposter_group = -1, crewmate_group = -1;
            for(int i=1;i<=n;i+=3){
                int a = i, b = i + 1, c = i + 2;
                System.out.println("? "+a+" "+b+" "+c);
                System.out.flush();

                int v = sc.nextInt();

                if(v == 0){
                    imposter_group = i;
                }
                else{
                    crewmate_group = i;
                }
            }

            int imposter = -1, crewmate = -1;
            for(int i=0;i<3;i++){
                int im1 = imposter_group, im2 = imposter_group + 1;
                int cw1 = crewmate_group + i;

                System.out.println("? "+im1+" "+im2+" "+cw1);
                System.out.flush();

                int v = sc.nextInt();

                if(v == 1){
                    imposter = imposter_group + 2;
                    crewmate = cw1;
                    break;
                }
            }

            if(imposter == -1 || crewmate == -1){
                imposter = imposter_group;

                for(int i=0;i<3;i++){
                    int cw1 = crewmate_group, cw2 = crewmate_group + 1;
                    int im1 = imposter_group + i;

                    System.out.println("? "+cw1+" "+cw2+" "+im1);
                    System.out.flush();

                    int v = sc.nextInt();

                    if(v == 0){
                        crewmate = crewmate_group + 2;
                        break;
                    }
                }

                if(imposter == -1 || crewmate == -1){
                    crewmate = crewmate_group;
                }
            }

            list.add(imposter);
            for(int i=1;i<=n;i++){
                if(i != imposter && i!=crewmate){
                    System.out.println("? "+i+" "+crewmate+" "+imposter);
                    System.out.flush();

                    int v = sc.nextInt();

                    if(v == 0){
                        list.add(i);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(list.size()).append(" ");
            for(int i: list){
                sb.append(i).append(" ");
            }
            sb.append("\n");

            System.out.println("! "+sb);
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
