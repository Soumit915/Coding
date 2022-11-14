import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Test1 {

    public static int sum(int a[],int k)
    {
        int sum=0;
        while(k>=1)
        {
            sum+=a[k];
            k-=k&-k;
        }
        return sum;
    }
    public static void update(int a[],int d,int k,int n)
    {
        while(k<=n)
        {
            a[k]+=d;
            k+=k&-k;
        }
    }
    public static long power(long a,long b,long m)
    {
        long res=1;
        while(b>0)
        {
            if(b%2!=0)
            {
                res=(res%m*a%m)%m;
            }
            b=b/2;
            a=(a%m*a%m)%m;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int t = sc.nextInt();
        while (t-->0) {

            int n=sc.nextInt();
            int m=sc.nextInt();
            int[] a =new int[n];
            int[] b =new int[m];
            for(int i=0;i<n;i++)
            {
                a[i]=sc.nextInt();
            }
            for(int i=0;i<m;i++)
            {
                b[i]=sc.nextInt();
            }
            long mod=998244353;
            long[] fac =new long[n+1];
            fac[0]=1;
            for(int i=1;i<=n;i++)
            {
                fac[i]=(fac[i-1]*i)%mod;
            }
            int n1=200000;
            int[] freq =new int[n1+1];
            for(int i=0;i<n;i++)
            {
                freq[a[i]]++;
            }
            int[] arr =new int[n1+1];
            for(int i=1;i<freq.length;i++)
            {
                int d=freq[i];
                update(arr,d,i,n1);
            }
            long v=fac[n];
            long p=1;
            for(int i=1;i<freq.length;i++)
            {
                int fr=freq[i];
                p=(p*fac[fr])%mod;
            }
            v=(v*power(p,mod-2,mod))%mod;
            long x=n;
            long sum=0;
            int f=0;
            for(int i=0;i<Math.min(n,m);i++)
            {
                int val=b[i];
                long s=sum(arr,val-1);
                long v1=(v*s)%mod;
                long num = v1;
                long deno = power(x,mod-2,mod);

                v1=(v1*power(x,mod-2,mod))%mod;
                sum=(sum+v1)%mod;

                if(freq[val]==0)
                {
                    f=1;
                    break;
                }
                update(arr,-1,val,n1);
                long fr=freq[val];
                v=(v*fr)%mod;
                v=(v*power(x,mod-2,mod))%mod;
                freq[val]--;
                x--;
            }

            if(f==0)
            {
                if(n<m)
                {
                    sum=(sum+1)%mod;
                }
            }
            sc.println(sum+"");
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
