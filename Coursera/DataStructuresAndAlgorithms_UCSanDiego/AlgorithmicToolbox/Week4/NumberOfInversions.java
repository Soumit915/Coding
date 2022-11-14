package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week4;

import java.io.*;

public class NumberOfInversions {
    public static int merge(int[] arr, int ll, int mid, int ul)
    {
        int al = mid-ll+1;
        int bl = ul-mid;
        int[] A = new int[al];
        int[] B = new int[bl];

        if (al >= 0) System.arraycopy(arr, ll, A, 0, al);
        for(int i=0;i<bl;i++)
        {
            B[i] = arr[mid+i+1];
        }

        int count=0;
        int p = 0, q = 0, k = ll;
        while(p<al && q<bl)
        {
            if(A[p] <= B[q])
            {
                arr[k] = A[p];
                p++;
            }
            else
            {
                count += (al-p);
                arr[k] = B[q];
                q++;
            }
            k++;
        }

        while(p<al)
        {
            arr[k] = A[p];
            p++;k++;
        }

        while(q<bl)
        {
            arr[k] = B[q];
            q++;k++;
        }

        return count;
    }
    public static int countInversions(int[] arr, int ll, int ul)
    {
        if(ll<ul)
        {
            int mid = (ll+ul)/2;
            int countl = countInversions(arr, ll, mid);
            int countr = countInversions(arr, mid+1, ul);
            return countl + countr + merge(arr, ll, mid, ul);
        }
        return 0;
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int count = countInversions(arr, 0, n-1);
        System.out.println(count);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100064]; // line length
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

        public long nextLong() throws IOException
        {
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

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
