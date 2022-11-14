package Codeforces.CM_1;

import java.io.*;
import java.util.*;

public class A {

    static class Target{
        int x, y;
        Target(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q23 = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(arr[i] == 1){
                q1.add(i);
            }
            else if(arr[i] == 2){
                q23.add(i);
            }
            else if(arr[i] == 3){
                q23.add(i);
            }
        }

        boolean[] isVisited = new boolean[n];
        List<Target> list = new ArrayList<>();
        boolean flag = true;
        for(int i=0;i<n;i++){
            if(arr[i] == 0 || isVisited[i]){
                isVisited[i] = true;
                continue;
            }

            if(arr[i] == 1){
                list.add(new Target(i, i));
                isVisited[i] = true;

                while(!q1.isEmpty() && q1.peek() <= i)
                    q1.remove();
            }
            else if(arr[i] == 2){
                if(q1.isEmpty()){
                    flag = false;
                    break;
                }

                int nextOne = q1.remove();

                list.add(new Target(i, i));
                list.add(new Target(i, nextOne));
                isVisited[i] = true;
                isVisited[nextOne] = true;
            }
            else{
                while(!q23.isEmpty() && q23.peek() <= i)
                    q23.remove();

                if((!q1.isEmpty() && !q23.isEmpty() && q23.peek() > q1.peek())){
                    int nextOne = q1.remove();

                    list.add(new Target(i, i));
                    list.add(new Target(i, nextOne));
                    list.add(new Target(nextOne, nextOne));
                    isVisited[i] = true;
                    isVisited[nextOne] = true;
                }
                else if(!q23.isEmpty()){
                    int next23 = q23.remove();

                    if(arr[next23] == 2){

                        if(q1.isEmpty()){
                            flag = false;
                            break;
                        }

                        int nextOne = q1.remove();

                        list.add(new Target(i, i));
                        list.add(new Target(i, next23));
                        list.add(new Target(next23, next23));
                        list.add(new Target(next23, nextOne));

                        isVisited[i] = true;
                        isVisited[next23] = true;
                        isVisited[nextOne] = true;
                    }
                    else{
                        list.add(new Target(i, i));
                        list.add(new Target(i, next23));

                        isVisited[i] = true;
                    }
                }
                else if(!q1.isEmpty()){
                    int nextOne = q1.remove();

                    list.add(new Target(i, i));
                    list.add(new Target(i, nextOne));
                    list.add(new Target(nextOne, nextOne));
                    isVisited[i] = true;
                    isVisited[nextOne] = true;
                }
                else{
                    flag = false;
                    break;
                }
            }
        }

        if(flag){
            sb.append(list.size()).append("\n");
            for(Target t: list){
                sb.append(t.x + 1).append(" ").append(t.y + 1).append("\n");
            }
        }
        else{
            sb.append("-1\n");
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
