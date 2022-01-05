package Hackerearth.DataStructures.Array1D;

import java.io.DataInputStream;
import java.io.IOException;

public class MarkTheAnswer {
    public static void main(String[] args) throws IOException
    {

        Reader sc = new Reader();
        int n = sc.nextInt();
        int max = sc.nextInt();

        int val;
        int count=0;
        int omit=0;
        for(int i=0;i<n;i++)
        {
            val = sc.nextInt();
            if(/*omit<2 && */val>max)
                omit++;
            else
                count++;

            if(omit==2)
                break;
        }

        System.out.println(count);
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
