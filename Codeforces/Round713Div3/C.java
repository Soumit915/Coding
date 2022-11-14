package Codeforces.Round713Div3;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            char[] s = sc.next().toCharArray();
            int n = s.length;
            if(a+b!=n){
                sb.append("-1\n");
                continue;
            }

            if(n==1){
                if(s[0]=='?'){
                    if(a==1){
                        s[0] = '0';
                    }
                    else{
                        s[0] = '1';
                    }
                }
                else{
                    if(s[0]=='0'){
                        if(a!=1) {
                            sb.append("-1\n");
                            continue;
                        }
                    }
                    else {
                        if(b!=1){
                            sb.append("-1\n");
                            continue;
                        }
                    }
                }
                sb.append(s[0]).append("\n");
                continue;
            }

            boolean flag = true;
            for(int i=0;i<Math.ceil(n/2.0);i++){
                if(i==n-i-1){
                    if(s[i]!='?'){
                        if(s[i]=='1')
                            b-=1;
                        else a-=1;
                    }
                    continue;
                }

                if(s[i]!='?' || s[n-i-1]!='?'){
                    if(s[i]!='?' && s[n-i-1]!='?'){
                        if(s[i]!=s[n-i-1]){
                            flag = false;
                            break;
                        }
                        else{
                            if(s[i]=='1')
                                b-=2;
                            else a-=2;
                        }
                    }
                    else{
                        if(s[i]=='?'){
                            s[i] = s[n-i-1];
                        }
                        else{
                            s[n-i-1] = s[i];
                        }
                        if(s[i]=='1')
                            b-=2;
                        else a-=2;
                    }
                }
            }

            if(!flag || a<0 || b<0){
                sb.append("-1\n");
                continue;
            }

            //System.out.println(a+" "+b+" "+Arrays.toString(s));
            for(int i=0;i<Math.ceil(n/2.0);i++){
                if (i==n-i-1 && s[i]=='?'){
                    if(a==1){
                        s[i] = '0';
                        a--;
                    }
                    else{
                        s[i] = '1';
                        b--;
                    }
                }

                if(s[i]=='?' && s[n-i-1]=='?'){
                    if(a>=2){
                        s[i] = '0';
                        s[n-i-1] = '0';
                        a-=2;
                    }
                    else if(b>=2){
                        //System.out.println(i);
                        s[i] = '1';
                        s[n-i-1] = '1';
                        b-=2;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            if(!flag || a!=0 || b!=0){
                sb.append("-1\n");
                //System.out.println(flag+" "+a+" "+b);
            }
            else{
                for (char c : s) {
                    sb.append(c);
                }
                sb.append("\n");
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
