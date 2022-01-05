package Codechef;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

class RoadTripsAndMuseums {
    static class Vertex
    {
        List<Integer> edgelist;
        Vertex()
        {
            edgelist = new LinkedList<>();
        }
        public void addNeighbour(int i)
        {
            this.edgelist.add(i);
        }
    }
    static class FindTotMuseums
    {
        TreeSet<Integer> trs = new TreeSet<>();
        FindTotMuseums(int n)
        {
            int i;
            for(i=0;i<n;i++)
            {
                trs.add(i);
            }
        }
        public void calculateTotalMuseums(Vertex[] gr, int k, int[] museums)
        {
            List<Long> totmus = new ArrayList<>();
            int source;
            while(!trs.isEmpty())
            {
                source = trs.pollFirst();
                totmus.add(dfs(gr,source,museums));

            }

            Collections.sort(totmus);
            int rear=totmus.size()-1,front=0,i;
            long totmusvisited= 0L;
            if(totmus.size()<k)
            {
                System.out.println(-1);
            }
            else
            {
                for(i=0;i<k;i++)
                {
                    if(i%2==0)
                    {
                        totmusvisited += totmus.get(rear);
                        rear--;
                    }
                    else
                    {
                        totmusvisited += totmus.get(front);
                        front++;
                    }
                }
                System.out.println(totmusvisited);
            }

        }
        HashSet<Integer> visited = new HashSet<>();
        /*public long bfs(Vertex[] gr, int source, int[] museums)
        {
            Queue<Integer> queue = new LinkedList<Integer>();
            HashSet<Integer> visited = new HashSet<Integer>();
            queue.add(source);
            visited.add(source);
            int cur;
            long sum=museums[source];
            while(!queue.isEmpty())
            {
                cur = queue.remove();
                for(int i:gr[cur].edgelist)
                {
                    if(!visited.contains(i))
                    {
                        queue.add(i);
                        visited.add(i);
                        sum += museums[i];
                        trs.remove(i);
                    }
                }
            }

            return sum;
        }*/
        public long dfs(Vertex[] gr, int source, int[] museums)
        {
            visited.add(source);
            trs.remove(source);
            long sum=museums[source];
            for(int i: gr[source].edgelist)
            {
                if(!visited.contains(i)) {
                    sum += dfs(gr, i, museums);
                }
            }
            return sum;
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        Vertex[] gr;
        int n,m,k;
        while (t>0)
        {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();

            gr = new Vertex[n];
            int start,end,i;
            for(i=0;i<n;i++)
            {
                gr[i] = new Vertex();
            }
            for(i=0;i<m;i++)
            {
                start = sc.nextInt();
                end = sc.nextInt();
                gr[start-1].addNeighbour(end-1);
                gr[end-1].addNeighbour(start-1);
            }

            int[] museums = new int[n];
            for(i=0;i<n;i++)
            {
                museums[i] = sc.nextInt();
            }

            FindTotMuseums ftm = new FindTotMuseums(n);
            ftm.calculateTotalMuseums(gr, k, museums);
            t--;
        }
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
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
