package CUrBrain_Questions;

import java.io.*;
import java.util.*;

public class ExamCheating {
    static int[] a,b;
    static ArrayList<Integer> L= new ArrayList<>();
    static ArrayList<Integer> R= new ArrayList<>();
    static Map<Integer,Integer> map=new HashMap<>(); //indexes of each studen -> teacher
    static Map<Integer,Integer> count= new HashMap<>();//position of max. element within a range -> its count over the range represented by that index
    static Map<Integer,Integer> ctr= new HashMap<>();//calculate net ocrrances of max.

    public static void main(String []args) throws IOException
    {
        long start = System.currentTimeMillis();

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int i,j;

            a=new int[n];
            b=new int[n];
            L = new ArrayList<>();
            R = new ArrayList<>();
            map=new HashMap<>();
            count= new HashMap<>();
            ctr= new HashMap<>();

            for(i=0;i<n;i++) {
                b[i]=sc.nextInt();
                //System.out.println(i+" "+n+" "+b[i]);
                count.put(i+a.length,1);
            }

            build();
            for(i=0;i<m;i++)
            {
                int l=sc.nextInt(),r=sc.nextInt();
                L.add(l-1);R.add(r-1);
                for(j=l-1;j<r;j++)
                    map.put(j,i);
            }

            int d=sc.nextInt();
            //System.out.println(d);
            for(i=0;i<n;i++)
            {
                int x=map.get(i);
                int r=n-1,l=0,y=0,z=0,l1=0,r1=0;
                if(i+d<n){y=map.get(i+d);r=i+d;}
                if(i-d>=0){z=map.get(i-d);l=i-d;}
                int k=b[i];
                if(R.get(x)<r)l1=R.get(x)+1;
                else l1=r;
                //System.out.println(L);
                if(L.get(x)>l) {
                    //System.out.println("test: "+L.get(x)+" "+x);
                    r1 = L.get(x);
                }
                else r1=l;

                int max=0;
                if(map.get(l)!=x) {
                    /*System.out.println(l+" "+r1+" "+n+" "+m);
                    System.out.println(Arrays.toString(b));*/
                    max = Math.max(max, query(l, r1));
                }

                if(map.get(r)!=x)
                    max=Math.max(max,query(l1,r+1));

                if(R.get(x)>=r&&L.get(x)<=l)
                    sb.append("-1\n");
                else if(max>k)
                {
                    sb.append(max).append(" ");
                    sb.append(ctr.get(max)).append("\n");
                }
                else sb.append("-1\n");
                ctr.clear();
            }
        }

        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

        sc.close();
    }
    static void build()
    {
        for(int i=a.length-1;i>0;i--)
        {
            int x=0,y=0;
            if(2*i>=a.length)x=b[2*i-a.length];
            else x=a[2*i];
            if(2*i+1>=a.length)y=b[2*i+1-a.length];
            else y=a[2*i+1];
            a[i]=Math.max(x,y);
            if(x>y)count.put(i,count.get(2*i));
            else if(x<y)count.put(i,count.get(2*i+1));
            else count.put(i,count.get(2*i+1)+count.get(2*i));
        }
    }
    static int query(int l,int r)
    {
        int max=0;//l--;
        int g=l,h=r;
        for(l+=a.length,r+=a.length;l<r;l/=2,r/=2)
        {
            int y=0;
            if((l&1)==1)
            {
                if(l>=a.length)y=b[l-a.length];
                else y=a[l];
                if(ctr.get(y)!=null)ctr.put(y,count.get(l)+ctr.get(y));
                else ctr.put(y,count.get(l));
                max=Math.max(max,y);
                l++;
            }
            y=0;
            if((r&1)==1)
            {
                r--;
                if(r>=a.length)
                    y=b[r-a.length];
                else y=a[r];

                if(ctr.get(y)!=null)ctr.put(y,count.get(r)+ctr.get(y));
                else ctr.put(y,count.get(r));
                max=Math.max(max,y);
            }
        }
        return max;
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
