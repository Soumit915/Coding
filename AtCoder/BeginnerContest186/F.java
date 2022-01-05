package AtCoder.BeginnerContest186;

import java.io.*;
import java.util.*;

public class F {
    static class Obstacle
    {
        int x;
        int y;
        Obstacle(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    static class xSorter implements Comparator<Obstacle>
    {
        public int compare(Obstacle o1, Obstacle o2)
        {
            return Integer.compare(o1.x, o2.x);
        }
    }
    static class ySorter implements Comparator<Obstacle>
    {
        public int compare(Obstacle o1, Obstacle o2)
        {
            return Integer.compare(o1.y, o2.y);
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int no = sc.nextInt();

        HashMap<Integer, ArrayList<Obstacle>> x_biased = new HashMap<>();
        HashMap<Integer, ArrayList<Obstacle>> y_biased = new HashMap<>();
        for(int i=0;i<no;i++)
        {
            Obstacle newobstacle = new Obstacle(sc.nextInt(), sc.nextInt());

            ArrayList<Obstacle> temp = x_biased.getOrDefault(newobstacle.x, new ArrayList<>());
            temp.add(newobstacle);
            x_biased.put(newobstacle.x, temp);

            temp = y_biased.getOrDefault(newobstacle.y, new ArrayList<>());
            temp.add(newobstacle);
            y_biased.put(newobstacle.y, temp);
        }

        for(int i : x_biased.keySet())
            x_biased.get(i).sort(new xSorter());
        for(int i : y_biased.keySet())
            y_biased.get(i).sort(new ySorter());

        ArrayList<Obstacle> arrlist_x0 = x_biased.getOrDefault(0, new ArrayList<>());
        long min_x0 = m;
        if(arrlist_x0.size()!=0)
            min_x0 = arrlist_x0.get(0).y;

        ArrayList<Obstacle> arrayList_y0 = y_biased.getOrDefault(0, new ArrayList<>());
        long min_y0 = n;
        if(arrayList_y0.size()!=0)
            min_y0 = arrayList_y0.get(0).x;

        long count = 0;
        if(min_x0<min_y0)
        {
            for(long i=min_x0+1;i<=min_y0;i++)
            {
                ArrayList<Obstacle> arrlist_y = y_biased.getOrDefault((int) i, new ArrayList<>());
                long min_y = n;
                if(arrlist_y.size()!=0)
                    min_y = arrlist_y.get(0).x;
                count += min_y;
            }
        }
        else if(min_x0>min_y0)
        {
            for(long i=min_y0+1;i<=min_x0;i++)
            {
                ArrayList<Obstacle> arrlist_x = x_biased.getOrDefault((int) i, new ArrayList<>());
                long min_x  = n;
                if(arrlist_x.size()!=0)
                    min_x = arrlist_x.get(0).y;
                count += min_x;
            }
        }

        count += (min_x0 * min_y0) - 2;
        System.out.println(count);

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
