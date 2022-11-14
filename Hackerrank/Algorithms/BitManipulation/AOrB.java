package Hackerrank.Algorithms.BitManipulation;

import java.io.*;
import java.util.*;

public class AOrB
{
    static HashMap<String, Character> hash;
    public static String getBits(char ch)
    {
        switch(ch)
        {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
        }
        return "";
    }
    public static String getBin(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            sb.append(getBits(s.charAt(i)));
        }
        return sb.toString();
    }
    public static void setHexaChar()
    {
        hash = new HashMap<>();
        hash.put("0000", '0');
        hash.put("0001", '1');
        hash.put("0010", '2');
        hash.put("0011", '3');
        hash.put("0100", '4');
        hash.put("0101", '5');
        hash.put("0110", '6');
        hash.put("0111", '7');
        hash.put("1000", '8');
        hash.put("1001", '9');
        hash.put("1010", 'A');
        hash.put("1011", 'B');
        hash.put("1100", 'C');
        hash.put("1101", 'D');
        hash.put("1110", 'E');
        hash.put("1111", 'F');
    }
    public static char getHexaChar(String s)
    {
        return hash.get(s);
    }
    public static String getHexa(char[] a)
    {
        StringBuilder sb = new StringBuilder();
        int n = a.length;
        for(int i=0;i<n;i+=4)
        {

            String s = String.valueOf(a[i]) +
                    a[i + 1] +
                    a[i + 2] +
                    a[i + 3];
            char ch = getHexaChar(s);
            if(ch=='0' && sb.toString().length()==0)
                continue;
            sb.append(ch);
        }

        return sb.toString();
    }
    public static String pad(String a, int l)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<l-a.length();i++)
        {
            sb.append("0");
        }
        sb.append(a);

        return sb.toString();
    }
    public static void main(String[] args) throws IOException
    {
        Soumit sc = new Soumit();

        int q = sc.nextInt();
        setHexaChar();

        StringBuilder sb = new StringBuilder();
        while(q-->0)
        {
            int k = sc.nextInt();

            String a = sc.next();
            String b = sc.next();
            String c = sc.next();

            int maxlength = Math.max(a.length(), Math.max(b.length(), c.length()));

            a = pad(a, maxlength);
            b = pad(b, maxlength);
            c = pad(c, maxlength);

            a = getBin(a);
            b = getBin(b);
            c = getBin(c);

            char[] ac = a.toCharArray();
            char[] bc = b.toCharArray();
            char[] cc = c.toCharArray();

            boolean flag = true;
            int n = ac.length;
            for(int i=0;i<n;i++)
            {
                char c1 = ac[i];
                char c2 = bc[i];
                char c3 = cc[i];

                if(c3=='1')
                {
                    if(c1=='0' && c2=='0')
                    {
                        if(k>0)
                        {
                            bc[i] = '1';
                            k--;
                        }
                        else
                        {
                            flag = false;
                            break;
                        }
                    }
                }
                else
                {
                    if(c1=='1' || c2=='1')
                    {
                        if(k>0)
                        {
                            if(c1=='1' && c2=='1')
                            {
                                if(k>1)
                                {
                                    ac[i] = '0';
                                    bc[i] = '0';
                                    k-=2;
                                }
                                else
                                {
                                    flag = false;
                                    break;
                                }
                            }
                            else if(c1=='0' && c2=='1')
                            {
                                bc[i] = '0';
                                k--;
                            }
                            else
                            {
                                ac[i] = '0';
                                k--;
                            }
                        }
                        else
                        {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if(!flag)
            {
                sb.append(-1).append("\n");
                continue;
            }

            for(int i=0;i<n && k>0;i++)
            {
                char c1 = ac[i];
                char c2 = bc[i];
                char c3 = cc[i];

                if(c3=='1')
                {
                    if(c1=='1' && c2=='1')
                    {
                        ac[i] = '0';
                        k--;
                    }
                    else if(c1=='1' && c2=='0')
                    {
                        if(k>1)
                        {
                            ac[i] = '0';
                            bc[i] = '1';
                            k-=2;
                        }
                    }
                }
            }

            a = getHexa(ac);
            if(a.length()==0)
                a = "0";
            b = getHexa(bc);
            if(b.length()==0)
                b = "0";

            sb.append(a).append("\n");
            sb.append(b).append("\n");
        }

        System.out.println(sb);
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
            byte[] buf = new byte[100064]; // line length
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
