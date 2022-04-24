import java.util.*;
import java.io.*;
import java.text.*;

public class Test{
    static FastReader in;
    static PrintWriter out;

    static class ATM{
        long[] store;
        long[] deno;

        public ATM() {
            store = new long[5];
            deno = new long[5];
            deno[0] = 20;
            deno[1] = 50;
            deno[2] = 100;
            deno[3] = 200;
            deno[4] = 500;
        }

        public void deposit(int[] banknotesCount) {

            for(int i=0;i<banknotesCount.length;i++){
                store[i] += banknotesCount[i];
            }
        }

        public int[] withdraw(int amount) {

            int[] ans = new int[5];
            for(int i=store.length-1;i>=0;i--){
                int needed = (int) Math.min(store[i], amount / deno[i]);

                ans[i] = needed;
                amount -= (deno[i] * ((long) needed));
            }

            if(amount == 0){
                for(int i=0;i<5;i++)
                    store[i] -= ans[i];
                return ans;
            }

            return new int[]{-1};
        }
    }



    public static void main(String[] args)throws IOException{
        in = new FastReader();
        out = new PrintWriter(System.out);
        solve();
        out.flush();
        out.close();
    }

    static void solve()throws IOException{
        for(int tc = 1, tt = ni(); tc<=tt; tc++){
            long a = nl(), b = nl(), n = nl(), mid = 0;
            boolean found = false;
            while(!found){
                mid = (a+b)>>1;
                pni(mid);
                String s = n();
                if(s.charAt(4) == 'B')b = mid-1;
                else if(s.charAt(4) == 'S')a = mid+1;
                else break;
            }
        }
    }

    static void p(Object o){
        out.print(o);
    }

    static void pn(Object o){
        out.println(o);
    }

    static void pni(Object o){
        out.println(o);
        out.flush();
    }

    static int ni()throws IOException{
        return in.nextInt();
    }

    static long nl()throws IOException{
        return in.nextLong();
    }

    static double nd()throws IOException{
        return in.nextDouble();
    }

    static String nln()throws IOException{
        return in.readLine();
    }

    static String n() throws IOException{
        return in.readString();
    }

    static class FastReader{
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer, buf;
        private int bufferPointer, bytesRead;
        public FastReader(){
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            buf = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException{
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readString() throws IOException{
            int cnt = 0, c;
            while((c = read()) != -1){
                if(c<=32)break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public String readLine() throws IOException{
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while((c = read()) != -1){
                if(c == '\n')break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException{
            int ret = 0;
            byte c = read();
            while(c <= ' ')c = read();
            boolean neg = (c == '-');
            if(neg)c = read();
            do{
                ret = ret * 10 + c - '0';
            }while ((c = read()) >= '0' && c <= '9');

            if(neg)return -ret;
            return ret;
        }

        public long nextLong() throws IOException{
            long ret = 0;
            byte c = read();
            while(c <= ' ')c = read();
            boolean neg = (c == '-');
            if(neg)c = read();
            do{
                ret = ret * 10 + c - '0';
            }while((c = read()) >= '0' && c <= '9');
            if (neg)return -ret;
            return ret;
        }

        public double nextDouble() throws IOException{
            double ret = 0, div = 1;
            byte c = read();
            while(c <= ' ')c = read();
            boolean neg = (c == '-');
            if(neg)c = read();
            do{
                ret = ret*10+c-'0';
            }while((c=read()) >= '0' && c<= '9');
            if(c == '.')while((c = read()) >= '0' && c <= '9')ret += (c - '0') / (div *= 10);
            if (neg)return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException{
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)buffer[0] = -1;
        }

        private byte read() throws IOException{
            if(bufferPointer == bytesRead)fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException{
            if (din == null)return;
            din.close();
        }
    }
}