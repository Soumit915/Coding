package Codeforces.Round700Div2;

import java.io.*;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int[] next = new int[n];
        int[] hash = new int[n+10];
        Arrays.fill(hash, 1000000);
        for(int i=n-1;i>=0;i--){
            next[i] = hash[arr[i]];
            hash[arr[i]] = i;
        }

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> indexlist1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> indexlist2 = new ArrayList<>();
        list1.add(arr[0]);
        indexlist1.add(0);
        for(int i=1;i<n;i++){
            int x = list1.get(list1.size()-1);
            int y = 0;
            if(!list2.isEmpty())
                y = list2.get(list2.size()-1);

            int nextx = next[indexlist1.get(list1.size()-1)];
            int nexty = 1000000;
            if(!list2.isEmpty())
                nexty = next[indexlist2.get(list2.size()-1)];

            if(arr[i]==x){
                list2.add(arr[i]);
                indexlist2.add(i);
            }
            else if(arr[i]==y){
                list1.add(arr[i]);
                indexlist1.add(i);
            }
            else if(arr[i]==x && arr[i]==y){
                list2.add(arr[i]);
                indexlist2.add(i);
            }
            else{
                if(nextx<nexty){
                    list1.add(arr[i]);
                    indexlist1.add(i);
                }
                else if(nextx>nexty){
                    list2.add(arr[i]);
                    indexlist2.add(i);
                }
                else{
                    list1.add(arr[i]);
                    indexlist1.add(i);
                }
            }

            if(hash[i]!=1000000)
                hash[i] = next[hash[i]];
        }

        int count1 = 0, count2 = 0;
        for(int j=0;j<list1.size()-1;j++){
            if(!list1.get(j).equals(list1.get(j + 1))){
                count1++;
            }
        }
        if(list1.size()!=0)
            count1++;
        for(int j=0;j<list2.size()-1;j++){
            if(!list2.get(j).equals(list2.get(j + 1))){
                count1++;
            }
        }
        if(list2.size()!=0)
            count2++;

        System.out.println(count1+count2);

        sc.close();
    }

    static class Soumit {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
        private PrintWriter pw;
        private int bufferPointer, bytesRead;

        public Soumit() {
            din = new DataInputStream(System.in);
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
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
