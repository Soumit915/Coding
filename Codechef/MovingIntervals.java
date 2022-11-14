package Codechef;

import java.io.*;
import java.util.*;

/*
1
15 4 1
4 8
7 10
11 12
12 13
*/

public class MovingIntervals {
    static class Interval{
        int id;
        int start;
        int end;
        Interval(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
    static class StartSorter implements Comparator<Interval>{
        public int compare(Interval i1, Interval i2){
            return Integer.compare(i1.start, i2.start);
        }
    }
    static class EndSorter implements Comparator<Interval>{
        public int compare(Interval i1, Interval i2){
            return Integer.compare(i1.end, i2.end);
        }
    }
    static String case0(Interval[] intervals){
        PriorityQueue<Interval> start = new PriorityQueue<>(new StartSorter());
        PriorityQueue<Interval> end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            start.add(interval);
            end.add(interval);
        }

        int count = 0;
        while (!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();
            if(si.start<=ei.end){
                count++;
                start.remove();
            }
            else{
                count--;
                end.remove();
            }

            if(count>1)
                return "Bad";
        }
        return "Good";
    }
    static String spcase(int c, Interval[] intervals){
        PriorityQueue<Interval> start = new PriorityQueue<>(new StartSorter());
        PriorityQueue<Interval> end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            start.add(interval);
            end.add(interval);
        }

        int count = 0;
        Interval inter1=null, inter2=null;
        while(!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();
            if(si.start<=ei.end){
                count++;
                Interval inter = start.remove();
                
                if(count==1){
                    inter1 = inter;
                }
                else {
                    inter2 = inter;
                    break;
                }
            }
            else{
                count--;
                end.remove();
                inter1 = null;
            }
        }

        start = new PriorityQueue<>(new StartSorter());
        end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            if(interval.id == inter1.id)
                continue;
            start.add(interval);
            end.add(interval);
        }

        count = 0;
        int max = start.peek().start-1;
        while(!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();

            if(si.start<=ei.end){
                count++;
                start.remove();
            }
            else{
                count--;
                Interval inter = end.remove();

                if(count==0){
                    max = Math.max(start.peek().start - inter.end - 1, max);
                }
            }
        }

        while(!end.isEmpty()){
            count--;
            Interval inter = end.remove();

            if(count==0){
                max = Math.max(c - inter.end, max);
            }
        }

        if(max>=inter1.end - inter1.start +1)
            return "Good";

        start = new PriorityQueue<>(new StartSorter());
        end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            if(interval.id == inter2.id)
                continue;
            start.add(interval);
            end.add(interval);
        }

        count = 0;
        max = start.peek().start-1;
        while(!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();

            if(si.start<=ei.end){
                count++;
                start.remove();
            }
            else{
                count--;
                Interval inter = end.remove();

                if(count==0){
                    max = Math.max(start.peek().start - inter.end - 1, max);
                }
            }
        }

        while(!end.isEmpty()){
            count--;
            Interval inter = end.remove();

            if(count==0){
                max = Math.max(c - inter.end, max);
            }
        }

        if(max>=inter2.end - inter2.start +1)
            return "Good";
        else return "Bad";
    }
    static String case1(int c, Interval[] intervals){
        PriorityQueue<Interval> start = new PriorityQueue<>(new StartSorter());
        PriorityQueue<Interval> end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            start.add(interval);
            end.add(interval);
        }

        int count = 0;
        boolean flag = true;
        Interval inter1=null, inter2=null;
        Interval mainInterval=null;
        while(!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();

            if(si.start<=ei.end){
                count++;
                Interval inter = start.remove();

                if(count>2)
                    return "Bad";
                else if(count==2){
                    if(flag){
                        inter2 = inter;
                        flag = false;
                    }
                    else{
                        if(mainInterval==null){
                            inter2 = inter;
                            mainInterval = inter1;
                        }
                        else{
                            if(mainInterval!=inter1){
                                return "Bad";
                            }
                            inter2 = inter;
                        }
                    }
                }
                else{
                    inter1 = inter;
                }
            }
            else{
                count--;
                Interval inter = end.remove();

                if(count==0){
                    inter1 = null;
                }
                else if(count==1){
                    if(inter1==inter){
                        inter1 = inter2;
                    }
                    inter2 = null;
                }
            }
        }

        System.out.println(flag+" "+mainInterval.id);

        if(flag)
            return "Good";
        
        if(mainInterval==null)
            return spcase(c, intervals);

        start = new PriorityQueue<>(new StartSorter());
        end = new PriorityQueue<>(new EndSorter());
        for(Interval interval: intervals) {
            start.add(interval);
            end.add(interval);
        }

        count = 0;
        int max = start.peek().start-1;
        while(!start.isEmpty()){
            Interval si = start.peek();
            Interval ei = end.peek();

            if(si.start<=ei.end){
                count++;
                start.remove();
            }
            else{
                count--;
                Interval inter = end.remove();

                if(count==0){
                    max = Math.max(start.peek().start - inter.end - 1, max);
                }
            }
        }

        while(!end.isEmpty()){
            count--;
            Interval inter = end.remove();

            if(count==0){
                max = Math.max(c - inter.end, max);
            }
        }

        if(max>=mainInterval.end - mainInterval.start +1)
            return "Good";
        else return "Bad";
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int c = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            Interval[] intervals = new Interval[n];
            for(int i=0;i<n;i++)
                intervals[i] = new Interval(i, sc.nextInt(), sc.nextInt());

            if(k==0)
                sb.append(case0(intervals));
            else sb.append(case1(c, intervals));
            sb.append("\n");
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
