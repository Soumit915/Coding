package TestingCode;

import java.io.*;
import java.util.*;

public class OutputChecker {

    static boolean isValid(int[] arr, int x){
        int n = arr.length;
        int[] hash = new int[n+1];
        for (int j : arr) {
            if(j%x > n)
                return false;
            hash[j % x]++;
        }

        for(int i=1;i<=n;i++){
            if(hash[i] == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        FileReader fr1 = new FileReader("Output1.txt");
        BufferedReader br1 = new BufferedReader(fr1);

        FileReader fr2 = new FileReader("Output2.txt");
        BufferedReader br2 = new BufferedReader(fr2);

        String a1;
        int line = 0;
        //Soumit sc = new Soumit("Input.txt");
        //sc.nextInt();
        while((a1 = br1.readLine()) != null)
        {
            //int n = sc.nextInt();
            /*int[][] input = new int[n][3];
            for(int i=0;i<n;i++){
                input[i][0] = sc.nextInt();
                input[i][1] = sc.nextInt();
                input[i][2] = sc.nextInt();
            }*/

            a1 = a1.trim();
            String a2 = br2.readLine();
            if(a2==null && !a1.equals("")){
                System.out.print(a1);
                System.out.println("Line limit exceeded in test-output");
                System.exit(0);
            }
            else if(a2==null && a1.equals("")){
                break;
            }

            a2 = a2.trim();

            if(!a1.equals(a2)){
                /*if(a1.startsWith("YES")){
                    int val = Integer.parseInt(a1.substring(4));
                    if(isValid(v, val)){
                        line++;
                        continue;
                    }
                }*/
                System.out.println("Wrong Answer at line: "+line);
                /*System.out.println(n);
                for(int[] i: input)
                    System.out.println(Arrays.toString(i));*/
                System.out.println(a1);
                System.out.println(a2);

                //System.out.println(n+" "+Arrays.toString(v));
                System.exit(0);
            }
            line++;
        }

        String a2 = br2.readLine();
        if(a2==null || a2.trim().equals("")) {
            System.out.println("Correct");
        }
        else{
            System.out.println("Line limit exceeded in main line");
        }

        br1.close();
        fr1.close();

        br2.close();
        fr2.close();
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
