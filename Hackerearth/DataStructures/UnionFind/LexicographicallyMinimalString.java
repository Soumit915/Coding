package Hackerearth.DataStructures.UnionFind;

import java.io.*;

class LexicographicallyMinimalString {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        String a = sc.readLine();
        String b = sc.readLine();
        String c = sc.readLine();

        String ans = Solution(a,c,b);
        System.out.println(ans);
    }
    static int parent(int[] nodes, int a)
    {
        while(true)
        {
            if(nodes[a] == a)
                return a;
            a = nodes[a];
        }
    }
    static String Solution(String A, String C, String B){

        int al = A.length();
        int cl = C.length();

        int[] nodes = new int[26];
        for(int i=0;i<26;i++)
        {
            nodes[i] = i;
        }
        for(int i=0;i<al;i++)
        {
            char cha = A.charAt(i);
            char chb = B.charAt(i);

            cha -= 97;
            chb -= 97;

            int pa = parent(nodes, cha);
            int pb = parent(nodes, chb);

            if(pa<pb)
            {
                nodes[pb] = pa;
                nodes[chb] = pa;
            }
            else if(pa>pb)
            {
                nodes[pa] = pb;
                nodes[cha] = pb;
            }
        }

        StringBuilder outS = new StringBuilder();
        for(int i=0;i<cl;i++)
        {
            char chc = C.charAt(i);

            int pc = parent(nodes, chc-97);

            outS.append((char) (pc+97));
        }

        return outS.toString();

    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }
}