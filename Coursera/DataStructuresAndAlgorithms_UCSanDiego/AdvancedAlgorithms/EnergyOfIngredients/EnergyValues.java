package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AdvancedAlgorithms.EnergyOfIngredients;

import java.io.*;

class GausianElimination
{
    double[] answer;
    GausianElimination(int n)
    {
        answer = new double[n];
    }
    public void solveEquations(double[][] ingred, int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=i;j<n;j++)
            {
                if(ingred[j][i]!=0)
                {
                    //swap the values of j and i
                    double[] t = ingred[i];
                    ingred[i] = ingred[j];
                    ingred[j] = t;
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                double pivot = ingred[j][i]/ingred[i][i];
                for(int k=i;k<=n;k++)
                {
                    ingred[j][k] -= ingred[i][k]*pivot;
                }
            }
        }

        for(int i=n-1;i>=0;i--)
        {
            for(int j=i+1;j<n;j++)
            {
                ingred[i][n] -= ingred[i][j]*answer[j];
                ingred[i][j] = 0;
            }
            ingred[i][n] /= ingred[i][i];
            ingred[i][i] /= ingred[i][i];
            answer[i] = ingred[i][n];
        }
    }
}

public class EnergyValues {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();

        double[][] ingred = new double[n][n+1];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<=n;j++)
            {
                ingred[i][j] = sc.nextDouble();
            }
        }

        GausianElimination ge = new GausianElimination(n);
        ge.solveEquations(ingred, n);

        for(int i=0;i<n;i++)
        {
            System.out.print(ge.answer[i]+" ");
        }
        System.out.println();
    }
    static class Reader
    {
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
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

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
