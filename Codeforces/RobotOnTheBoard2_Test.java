package Codeforces;

import java.io.*;
import java.util.*;

public class RobotOnTheBoard2_Test {

    static long start;
    public static void main(String[] args) throws FileNotFoundException {

        start = System.currentTimeMillis();

        InputStream inputStream = new FileInputStream("Input.txt");
        QuickReader in = new QuickReader(inputStream);

        try(PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out)))
        {
            new RobotOnTheBoard2_Test().solve(in, out);
            System.out.println((System.currentTimeMillis()-start)/1000.0);
        }
    }

    int n,m;

    public void solve(QuickReader in, PrintWriter out)
    {
        int T = in.nextInt();
        while(T-->0)
        {
            n = in.nextInt();
            m = in.nextInt();
            char[][] b = new char[n][];
            for(int i=0;i<n;i++)
                b[i] = in.next().toCharArray();
            int last = n*m;
            int[] to = new int[n*m+1];
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                {
                    if(b[i][j] == 'L')
                        to[getId(i,j)] = (j>0)? getId(i,j-1) : last;
                    else if(b[i][j] == 'R')
                        to[getId(i,j)] = (j<m-1)? getId(i,j+1) : last;
                    else if(b[i][j] == 'U')
                        to[getId(i,j)] = (i>0)? getId(i-1,j) : last;
                    else
                        to[getId(i,j)] = (i<n-1)? getId(i+1,j) : last;
                }
            to[last] = last;

            int[] used = new int[n*m+1];
            int[] resLen = new int[n*m+1];
            for(int i=0;i<=n*m;i++)
                resLen[i] = -1;

            for(int i=0;i<=n*m;i++)
                if(used[i] == 0)
                {
                    int cur = i;
                    while(used[cur] == 0)
                    {
                        used[cur] = 1;
                        cur = to[cur];
                    }

                    if( used[cur] == 1 )
                    {
                        int start = cur;
                        int len = 0;
                        do
                        {
                            cur = to[cur];
                            len++;
                        }
                        while(cur != start);

                        do
                        {
                            resLen[cur] = len;
                            cur = to[cur];
                        }
                        while(cur != start);
                    }

                    cur = i;
                    while(used[cur] == 1)
                    {
                        used[cur] = 2;
                        cur = to[cur];
                    }
                }

            resLen[last] = 0;

            int[] seq = new int[n*m+1];
            for(int i=0;i<n*m;i++)
            {
                if(resLen[i] == -1)
                {
                    int cur = i;
                    int pos = 0;
                    while(resLen[cur] == -1)
                    {
                        seq[pos++] = cur;
                        cur = to[cur];
                    }
                    for(int j=pos-1;j>=0;j--)
                        resLen[seq[j]] = resLen[to[seq[j]]]+1;
                }
            }

            int res = 1;
            int resI = 1;
            int resJ = 1;
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    if(res < resLen[getId(i,j)])
                    {
                        res = resLen[getId(i, j)];
                        resI = i+1;
                        resJ = j+1;
                    }

            out.println(resI + " " + resJ + " " + res);
        }
    }

    public int getId(int r, int c)
    {
        return r*m + c;
    }

    static class QuickReader
    {
        BufferedReader in;
        StringTokenizer token;

        public QuickReader(InputStream ins)
        {
            in=new BufferedReader(new InputStreamReader(ins));
            token=new StringTokenizer("");
        }

        public boolean hasNext()
        {
            while (!token.hasMoreTokens())
            {
                try
                {
                    String s = in.readLine();
                    if (s == null) return false;
                    token = new StringTokenizer(s);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
            }
            return true;
        }

        public String next()
        {
            hasNext();
            return token.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public int[] nextInts(int n)
        {
            int[] res = new int[n];
            for (int i = 0; i < n; i++)
                res[i] = nextInt();
            return res;
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}