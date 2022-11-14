package GoogleCodeJam.Year_2021.QualificationRound;

import java.io.*;
import java.util.*;

public class MedianSort {
    static ArrayList<Integer> insert(ArrayList<Integer> arlist, int pos, int val){
        ArrayList<Integer> newlist = new ArrayList<>();
        for(int i=0;i<pos;i++){
            newlist.add(arlist.get(i));
        }
        newlist.add(val);
        for(int i=pos;i<arlist.size();i++){
            newlist.add(arlist.get(i));
        }
        return newlist;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        int n = sc.nextInt();
        sc.nextInt();

        for(int testi = 1;testi<=t;testi++){

            ArrayList<Integer> arlist = new ArrayList<>();
            arlist.add(1);
            arlist.add(2);
            System.out.println("1 3 2");
            int l3 = sc.nextInt();
            if(l3==1){
                arlist = insert(arlist, 0, 3);
            }
            else if(l3==2){
                arlist.add(3);
            }
            else if(l3==3){
                arlist = insert(arlist, 1, 3);
            }
            for(int i=4;i<=n;i++){
                int ll = 1;
                int ul = arlist.size()-1;

                while(ll<=ul){
                    if(ll==ul){
                        if(ll==1 || ul==arlist.size()-1){
                            System.out.println(arlist.get(ll-1)+" "+i+" "+arlist.get(ll));
                            int l = sc.nextInt();
                            if(l==arlist.get(ll-1)){
                                ll = 0;
                            }
                            else if(l==arlist.get(ll)){
                                ll = ll+1;
                                ul = ll;
                            }
                        }
                        break;
                    }
                    else if(ll+1==ul){
                        if(ll==1){
                            System.out.println(arlist.get(ll-1)+" "+i+" "+arlist.get(ll));
                            int l = sc.nextInt();
                            if(arlist.get(ll-1)==l){
                                ll = 0;
                                break;
                            }
                            else if(arlist.get(ll)==l){
                                ll = ll+1;
                            }
                            else{
                                break;
                            }
                        }
                        else if(ul==arlist.size()-1){
                            System.out.println(arlist.get(ul-1)+" "+i+" "+arlist.get(ul));
                            int l = sc.nextInt();
                            if(arlist.get(ul-1)==l){
                                ul = ll;
                                break;
                            }
                            else if(arlist.get(ul)==l){
                                ll = ul+1;
                                ul = ll;
                                break;
                            }
                            else{
                                ll = ul;
                                break;
                            }
                        }
                        else{
                            System.out.println(arlist.get(ll-1)+" "+i+" "+arlist.get(ll));
                            int l = sc.nextInt();
                            if(arlist.get(ll-1)==l){
                                ll = 0;
                            }
                            else if(arlist.get(ll)==l){
                                ll = ll+1;
                            }
                            break;
                        }
                    }
                    else{
                        int firstmid = (ll-1)+(ul-ll+1)/3;
                        int secondmid = (firstmid)+(ul-firstmid)/2;
                        System.out.println(arlist.get(firstmid)+" "+i+" "+arlist.get(secondmid));
                        int l = sc.nextInt();
                        if(l==arlist.get(firstmid)){
                            ul = firstmid;
                        }
                        else if(l==arlist.get(secondmid)){
                            ll = secondmid+1;
                        }
                        else {
                            ll = firstmid+1;
                            ul = secondmid;
                        }
                    }
                }

                if(ul<ll && ul!=-1){
                    arlist = insert(arlist, ul, i);
                }
                else arlist = insert(arlist, ll, i);
            }

            for(int i: arlist)
                System.out.print(i+" ");
            System.out.println();
            int flag = sc.nextInt();

            if(flag==-1){
                System.exit(0);
            }
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
