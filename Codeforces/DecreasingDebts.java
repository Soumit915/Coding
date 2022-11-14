package Codeforces;

import java.util.*;
import java.io.*;

public class DecreasingDebts {

    static class People{
        int id;

        HashSet<Debt> taken = new HashSet<>();
        HashSet<Debt> given = new HashSet<>();

        long net_debt;

        People(int id){
            this.id = id;
            this.net_debt = 0;
        }
    }

    static class Debt {
        People u, v;
        long val;

        Debt(People u, People v, long val){
            this.u = u;
            this.v = v;
            if(u==v)
                val = 0;
            this.val = val;
        }
    }

    static class Graph{
        List<People> peoplelist;
        List<Debt> anslist;

        Graph(int n){
            this.peoplelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                peoplelist.add(new People(i));
            }

            anslist = new ArrayList<>();
        }

        public void addEdge(int u, int v, long w){
            People pu = peoplelist.get(u);
            People pv = peoplelist.get(v);

            Debt debt = new Debt(pu, pv, w);

            pu.given.add(debt);
            pv.taken.add(debt);
        }

        public void dfs(){
            for(People people: peoplelist){
                normaliseDebts(people);
            }

            join_edges();
        }

        public void normaliseDebts(People source){

            for(Debt debt : source.given)
                source.net_debt += debt.val;

            for(Debt debt: source.taken)
                source.net_debt -= debt.val;

        }

        public void join_edges(){
            ArrayList<People> pos = new ArrayList<>();
            ArrayList<People> neg = new ArrayList<>();

            peoplelist.forEach((people) -> {
                if(people.net_debt > 0)
                    pos.add(people);
                else if(people.net_debt < 0) neg.add(people);

                people.net_debt = Math.abs(people.net_debt);
            });

            int pptr = 0, nptr = 0;

            while(pptr < pos.size() && nptr < neg.size()){
                People pos_people = pos.get(pptr);
                People neg_people = neg.get(nptr);

                if(pos_people.net_debt == neg_people.net_debt) {
                    Debt debt = new Debt(pos_people, neg_people, pos_people.net_debt);

                    anslist.add(debt);

                    pos_people.net_debt = 0;
                    neg_people.net_debt = 0;

                    pptr++; nptr++;
                }
                else if(pos_people.net_debt < neg_people.net_debt){
                    Debt debt = new Debt(pos_people, neg_people, pos_people.net_debt);

                    anslist.add(debt);

                    neg_people.net_debt -= pos_people.net_debt;
                    pos_people.net_debt = 0;

                    pptr++;
                }
                else{
                    Debt debt = new Debt(pos_people, neg_people, neg_people.net_debt);

                    anslist.add(debt);

                    pos_people.net_debt -= neg_people.net_debt;
                    neg_people.net_debt = 0;

                    nptr++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
        }

        gr.dfs();

        int count = 0;
        StringBuilder sb = new StringBuilder();

        for(Debt debt : gr.anslist){
            count++;
            sb.append(debt.u.id + 1).append(" ").append(debt.v.id+1).append(" ").append(debt.val).append("\n");
        }

        System.out.println(count);
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
