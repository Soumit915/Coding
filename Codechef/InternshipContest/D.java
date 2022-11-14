package Codechef.InternshipContest;

import java.io.*;
import java.util.*;

public class D {
    static class Node
    {
        int id;
        int start;
        int end;
        int level;
        ArrayList<Edge> neighbour = new ArrayList<>();
        HashMap<Integer, Edge> edgemap = new HashMap<>();
        Node(int id)
        {
            this.id = id;
        }
        public int compareTo(Node n)
        {
            return Integer.compare(this.level, n.level);
        }
        public Node min(Node a)
        {
            if(this.compareTo(a)<0)
            {
                return this;
            }
            else
            {
                return a;
            }
        }
        public Node max(Node a)
        {
            if(this.compareTo(a)>0)
            {
                return this;
            }
            else
            {
                return a;
            }
        }
    }

    static class Edge
    {
        Node u;
        Node v;
        long weight;
        Edge(Node u, Node v, long weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Tree
    {
        Node[] nodelist;
        int[] flatTree;
        Node[][] ancestors;
        int[] level;
        int log;
        ArrayList<Node> eulerWalk;
        Tree(int n)
        {
            nodelist = new Node[n];
            for(int i=0;i<n;i++)
            {
                nodelist[i] = new Node(i);
            }
            log = (int) Math.ceil(Math.log(n)/Math.log(2));
            ancestors = new Node[n][log+1];
            level = new int[n];
        }
        public void addEdge(int u, int v, long weight)
        {
            Node nu = nodelist[u];
            Node nv = nodelist[v];

            Edge e = new Edge(nu, nv, weight);
            nu.neighbour.add(e);
            nv.neighbour.add(e);

            nu.edgemap.put(nv.id, e);
            nv.edgemap.put(nu.id, e);
        }
        public void flattenTree()
        {
            flatTree = new int[nodelist.length*2];

            int globalcount = 0;
            Node source = nodelist[0];
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            boolean[] isVisited = new boolean[nodelist.length];
            stk.push(source);
            ptrstk.push(-1);
            source.start = globalcount;
            flatTree[globalcount] = source.id;
            isVisited[0] = true;

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.neighbour.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);
                    Node nxt;
                    Edge e = cur.neighbour.get(ptr);

                    if(e.u == cur)
                        nxt = e.v;
                    else nxt = e.u;

                    if(!isVisited[nxt.id])
                    {
                        globalcount++;
                        nxt.start = globalcount;
                        flatTree[globalcount] = nxt.id;
                        stk.push(nxt);
                        ptrstk.push(-1);
                        isVisited[nxt.id] = true;
                    }
                }
                else
                {
                    globalcount++;
                    cur.end = globalcount;
                    flatTree[globalcount] = cur.id;
                }
            }
        }
        public void setAncestors(Node source, Node parent)
        {
            ancestors[source.id][0] = parent;
            for(int i=1;i<=log;i++)
                if(ancestors[source.id][i-1] == null)
                    ancestors[source.id][i] = null;
                else
                    ancestors[source.id][i] = ancestors[ancestors[source.id][i-1].id][i-1];

            for(Edge e: source.neighbour) {
                Node node;
                if(e.u == source)
                    node = e.v;
                else node = e.u;
                if (node != parent) {
                    level[node.id] = level[source.id] + 1;
                    setAncestors(node, source);
                }
            }
        }
        public Node lca(Node u, Node v)
        {
            if(level[u.id]<level[v.id])
            {
                Node node = u;
                u = v;
                v = node;
            }

            for(int i=log;i>=0;i--)
                if(level[u.id]-(1 << i)>=level[v.id])
                    u = ancestors[u.id][i];

            if(u==v)
                return u;

            for(int i=log;i>=0;i--)
            {
                if(ancestors[u.id][i]!=null && ancestors[u.id][i]!=ancestors[v.id][i])
                {
                    u = ancestors[u.id][i];
                    v = ancestors[v.id][i];
                }
            }

            return ancestors[u.id][0];
        }
        public void eulerWalk(Node source)
        {
            eulerWalk = new ArrayList<>();
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            source.level = 0;

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                eulerWalk.add(cur);

                if(ptr<cur.neighbour.size()-1)
                {
                    ptr++;
                    Node parent = null;
                    if(!stk.isEmpty())
                        parent = stk.peek();
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next;
                    Edge e = cur.neighbour.get(ptr);
                    if(cur == e.u)
                        next = e.v;
                    else next = e.u;

                    if(next == parent)
                        continue;

                    next.level = cur.level+1;
                    stk.push(next);
                    ptrstk.push(-1);
                }
            }
        }
    }

    static class Query implements Comparable<Query>
    {
        int id;
        int startnode;
        int endnode;
        int start;
        int end;
        int leftblockno;
        Query(int id, int startnode, int endnode, int start, int end)
        {
            this.id = id;
            this.startnode = startnode;
            this.endnode = endnode;
            this.start = start;
            this.end = end;
        }
        public int compareTo(Query q)
        {
            int c = Integer.compare(this.leftblockno, q.leftblockno);
            if(c==0 && this.leftblockno%2==0)
            {
                c = Integer.compare(this.end, q.end);
                if(c==0)
                {
                    return Integer.compare(this.start, q.start);
                }
                else return c;
            }
            else if(c==0)
            {
                c = Integer.compare(q.end, this.end);
                if(c==0)
                {
                    return Integer.compare(this.start, q.start);
                }
                else return c;
            }
            else return c;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            int q = sc.nextInt();
            int r = sc.nextInt()-1;

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++)
            {
                tr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
            }

            tr.flattenTree();
            tr.setAncestors(tr.nodelist[r], null);
            int[] flatTree = tr.flatTree;
            int blocksize = (int) Math.sqrt(flatTree.length);
            Query[] querylist = new Query[q];
            for(int i=0;i<q;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                if(tr.nodelist[u].start>tr.nodelist[v].start)
                {
                    v = (u+v)-(u=v);
                }
                int leftblockno = (tr.nodelist[u].start-1)/blocksize;
                Query query = new Query(i, u, v, tr.nodelist[u].start, tr.nodelist[v].start);
                query.leftblockno = leftblockno;
                querylist[i] = query;
            }

            Arrays.sort(querylist);
            long[] ans = new long[q];

            if(n==1)
            {
                for(int i=0;i<q;i++)
                    sb.append(0).append("\n");
                continue;
            }

            boolean[] set = new boolean[n];
            set[r] = true;

            long sum = tr.nodelist[r].edgemap.get(flatTree[1]).weight;
            int gl=0, gr=0;
            Deque<Edge> dq = new LinkedList<>();
            Deque<Integer> dqnode = new LinkedList<>();
            Stack<Integer> stk = new Stack<>();
            dqnode.add(r);
            for(Query query: querylist)
            {
                Node lnode = tr.nodelist[query.startnode];
                Node rnode = tr.nodelist[query.endnode];

                if(lnode == rnode)
                {
                    ans[query.id] = 0;
                    continue;
                }

                Node lcanode = tr.lca(lnode, rnode);
                if(lcanode==lnode)
                {
                    while(gr<rnode.start)
                    {
                        gr++;
                        if(set[flatTree[gr]])
                        {
                            Edge e = dq.removeLast();
                            sum -= e.weight;
                            dqnode.removeLast();
                            set[flatTree[gr]] = false;
                        }
                        else
                        {
                            Edge e = tr.nodelist[dqnode.getLast()].edgemap.get(flatTree[gr]);
                            sum += e.weight;
                            dqnode.add(flatTree[gr]);
                            dq.add(e);
                            set[flatTree[gr]] = true;
                        }
                    }
                    while (gr>rnode.start)
                    {
                        if(set[flatTree[gr]])
                        {
                            Edge e = dq.removeLast();
                            sum -= e.weight;
                            dqnode.removeLast();
                            set[flatTree[gr]] = false;
                        }
                        else
                        {
                            Edge e = tr.nodelist[dqnode.getLast()].edgemap.get(flatTree[gr]);
                            sum += e.weight;
                            dqnode.add(flatTree[gr]);
                            dq.add(e);
                            set[flatTree[gr]] = true;
                        }
                        gr--;
                    }

                    while(gl<lnode.start)
                    {

                        if(tr.nodelist[flatTree[gl]].start == gl)
                        {
                            if(!stk.isEmpty())
                            {
                                dqnode.removeFirst();
                            }
                            stk.add(flatTree[gl]);
                            Edge e = dq.removeFirst();
                            sum -=  e.weight;
                        }
                        else if(tr.nodelist[flatTree[gl]].end == gl && tr.nodelist[flatTree[gl]].id==stk.peek())
                        {
                            stk.pop();
                            dq.removeFirst();
                        }
                        else
                        {
                            Edge e = tr.nodelist[dqnode.getFirst()].edgemap.get(flatTree[gl]);
                            sum += e.weight;
                            dqnode.add(flatTree[gl]);
                            dq.add(e);
                            set[flatTree[gl]] = true;
                        }
                        gl++;
                    }

                    while(gl>lnode.start)
                    {
                        gl--;
                        if(tr.nodelist[flatTree[gl]].end == gl)
                        {
                            dqnode.add(flatTree[gl]);
                        }
                        else if(tr.nodelist[flatTree[gl]].start == gl && tr.nodelist[flatTree[gl]].id==dqnode.getFirst())
                        {
                            dqnode.removeFirst();
                        }
                        else
                        {
                            Edge e = tr.nodelist[dqnode.getFirst()].edgemap.get(flatTree[gl]);
                            sum += e.weight;
                            dqnode.add(flatTree[gl]);
                            dq.add(e);
                            set[flatTree[gl]] = true;
                        }
                    }

                }
                else
                {

                }
            }
        }

        System.out.println(sb);

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
