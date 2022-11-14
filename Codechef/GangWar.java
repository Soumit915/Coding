package Codechef;

import java.util.*;
import java.io.*;

public class GangWar {
    static class Member
    {
        int id;
        boolean isVisited;
        boolean set;
        ArrayList<Member> adjacentList;
        Member(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.adjacentList = new ArrayList<>();
        }
        public void addFriend(Member friend)
        {
            this.adjacentList.add(friend);
        }
    }
    static class Gang
    {
        ArrayList<Member> memberlist;
        Gang(int n)
        {
            memberlist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                memberlist.add(new Member(i));
            }
        }
        public void addEdge(final int u, final int v)
        {
            final Member f1 = this.memberlist.get(u);
            final Member f2 = this.memberlist.get(v);

            f1.addFriend(f2);
            f2.addFriend(f1);
        }
    }
    static class Bipartite
    {
        boolean isBipartite;
        Bipartite()
        {
            isBipartite = true;
        }
        public void checkBipartite(final Gang g)
        {
            for(final Member m: g.memberlist)
            {
                this.bfs(m);
                if(!isBipartite)
                    return;
            }
        }
        public void bfs(final Member source)
        {
            final Queue<Member> queue = new LinkedList<>();
            queue.add(source);
            source.set = true;
            source.isVisited = true;

            while(!queue.isEmpty())
            {
                final Member cur = queue.remove();

                for(final Member i: cur.adjacentList)
                {
                    if(i.isVisited)
                    {
                        if(i.set==cur.set)
                        {
                            isBipartite = false;
                            return;
                        }
                    }
                    else
                    {
                        i.set = !cur.set;
                        queue.add(i);
                        i.isVisited = true;
                    }
                }
            }
        }
    }
    public static void main(final String[] args) throws IOException
    {
        final Reader sc = new Reader();
        int t = sc.nextInt();

        while (t-->0)
        {
            final int n = sc.nextInt();
            final int f = sc.nextInt();

            final Gang g = new Gang(n);
            for(int i=0;i<f;i++)
            {
                final int u = sc.nextInt();
                final int v = sc.nextInt();
                g.addEdge(u,v);
            }

            final Bipartite bp = new Bipartite();
            bp.checkBipartite(g);
            if(bp.isBipartite)
            {
                System.out.println("YeS");
            }
            else
            {
                System.out.println("No");
            }
        }

    }
    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            this.din = new DataInputStream(System.in);
            this.buffer = new byte[this.BUFFER_SIZE];
            this.bufferPointer = this.bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = this.read();
            while (c <= ' ')
                c = this.read();
            final boolean neg = (c == '-');
            if (neg)
                c = this.read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = this.read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            this.bytesRead = this.din.read(this.buffer, this.bufferPointer = 0, this.BUFFER_SIZE);
            if (this.bytesRead == -1)
                this.buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (this.bufferPointer == this.bytesRead)
                this.fillBuffer();
            return this.buffer[this.bufferPointer++];
        }
    }
}
