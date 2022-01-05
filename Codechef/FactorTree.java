package Codechef;

import java.util.*;
import java.io.*;

public class FactorTree {
    static int mod = (int) 1e9+7;
    static int[] pow = new int[31];
    static void computePower()
    {
        int n = 30;
        pow[0] = 1;
        for(int i=1;i<=n;i++)
        {
            pow[i] = pow[i-1]*2;
        }
    }
    static class Node
    {
        int id;
        int tq;
        int start;
        int end;
        int level;
        ArrayList<Node> neighbour = new ArrayList<>();
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
        public void addEdge(int u, int v)
        {
            Node nu = nodelist[u];
            Node nv = nodelist[v];

            nu.neighbour.add(nv);
            nv.neighbour.add(nu);
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
                    Node nxt = cur.neighbour.get(ptr);
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
        /*public void setAncestors(Node source, Node parent)
        {
            ancestors[source.id][0] = parent;
            for(int i=1;i<=log;i++)
                if(ancestors[source.id][i-1] == null)
                    ancestors[source.id][i] = null;
                else
                    ancestors[source.id][i] = ancestors[ancestors[source.id][i-1].id][i-1];

            for(Node node: source.neighbour)
                if(node!=parent)
                {
                    level[node.id] = level[source.id]+1;
                    setAncestors(node, source);
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
        }*/
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

                    Node next = cur.neighbour.get(ptr);
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

    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> prime;
    static int[] spf;
    public static void spf()
    {
        int n = (int) 1e6;
        isPrime = new ArrayList<>(n+1);
        prime = new ArrayList<>(n+1);
        spf = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            isPrime.add(true);
            spf[i] = 2;
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++)
        {
            if(isPrime.get(i))
            {
                prime.add(i);
                spf[i] = i;
            }

            for(int j=0;j<prime.size() && i*prime.get(j)<=n && prime.get(j)<=spf[i];j++)
            {
                isPrime.set(prime.get(j)*i, false);
                spf[prime.get(j)*i] = prime.get(j);
            }
        }
    }
    static long x, y;
    static long[] inverse;
    public static void gcdExtended(int a, int b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = ((a/b)*y);
        y = (y>mod)?y%mod:y;
        y = x-y;
        x = t;
    }
    public static long inverseModulo(int a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod);
        x = (x>mod)?x%mod:x;
        return x;
    }
    public static void preComputeInverse()
    {
        inverse = new long[100001];
        for(int i=1;i<100001;i++)
        {
            inverse[i] = inverseModulo(i);
        }
    }
    public static long addFactors(int val, int[] primecounthash, long sum)
    {
        while(val>1)
        {
            int div = spf[val];
            sum = (sum*inverse[primecounthash[div]+1]);
            sum = (sum>mod)?sum%mod:sum;
            primecounthash[div] = (primecounthash[div]+1);
            sum = (sum*(primecounthash[div]+1));
            sum = (sum>mod)?sum%mod:sum;
            val = val/div;
        }
        return sum;
    }
    public static long removeFactors(int val, int[] primecounthash, long sum)
    {
        while(val>1)
        {
            int div = spf[val];
            sum = (sum*inverse[primecounthash[div]+1]);
            sum = (sum>mod)?sum%mod:sum;
            primecounthash[div] = (primecounthash[div]-1);
            sum = (sum*(primecounthash[div]+1));
            sum = (sum>mod)?sum%mod:sum;
            val = val/div;
        }
        return sum;
    }
    public static int log(int n)
    {
        if(n==0)
            return n;
        return (int) (Math.log(n)/Math.log(2));
    }
    public static void built(Node[][] sparseTables, ArrayList<Node> eulerWalk)
    {
        int n = eulerWalk.size();
        int cols = sparseTables[0].length;
        for(int i=0;i<n;i++)
        {
            sparseTables[i][0] = eulerWalk.get(i);
        }

        for(int i=1;i<cols;i++)
        {
            for(int j=0;j<n;j++)
            {
                int val = (int) Math.pow(2, i-1);
                if(j+val<n)
                {
                    sparseTables[j][i] = sparseTables[j][i-1].min(sparseTables[j+val][i-1]);
                }
            }
        }
    }
    public static Node query(Node[][] sparseTable, int l, int r)
    {
        int diff = log(r-l);
        Node min = sparseTable[l][diff];

        int left = (r-l+1) - (int) Math.pow(2, diff);
        min = min.min(sparseTable[l+left][diff]);

        return min;
    }
    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");
        int t = sc.nextInt();
        spf();
        computePower();
        preComputeInverse();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();

            Tree tr = new Tree(n);
            for(int i=1;i<n;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                tr.addEdge(u, v);
            }

            for(int i=0;i<n;i++)
            {
                tr.nodelist[i].tq = sc.nextInt();
            }

            tr.flattenTree();
            tr.eulerWalk(tr.nodelist[0]);
            ArrayList<Node> eulerWalk = tr.eulerWalk;

            int cols = (int) (Math.ceil(Math.log(eulerWalk.size())/Math.log(2)));
            Node[][] sparseTables = new Node[eulerWalk.size()][cols];
            for(int i=0;i<eulerWalk.size();i++)
            {
                for(int j=0;j<cols;j++)
                {
                    sparseTables[i][j] = new Node(0);
                    sparseTables[i][j].level = Integer.MAX_VALUE;
                }
            }
            built(sparseTables, eulerWalk);

            int[] fi = new int[n];
            for(int i=eulerWalk.size()-1;i>=0;i--)
            {
                fi[eulerWalk.get(i).id] = i;
            }
            int[] flatTree = tr.flatTree;
            int blocksize = (int) Math.sqrt(flatTree.length);
            int q = sc.nextInt();
            Query[] querylist = new Query[q];
            for(int i=0;i<q;i++)
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;
                if(tr.nodelist[l].start>tr.nodelist[r].start)
                {
                    r = (l+r)-(l=r);
                }
                int leftblockno = (tr.nodelist[l].start-1)/blocksize;
                Query query = new Query(i, l, r, tr.nodelist[l].start, tr.nodelist[r].start);
                query.leftblockno = leftblockno;
                querylist[i] = query;
            }

            Arrays.sort(querylist);
            long[] ans = new long[q];

            int gl=0, gr=0;
            int[] primecounthash = new int[1000000];
            long sum = addFactors(tr.nodelist[0].tq, primecounthash, 1);
            boolean[] set = new boolean[n];
            set[0] = true;

            /*long end = System.currentTimeMillis();
            System.out.println(((double)end-start)/1000);*/

            for(Query query: querylist)
            {
                Node lnode = tr.nodelist[query.startnode];
                Node rnode = tr.nodelist[query.endnode];

                Node lcanode = query(sparseTables, fi[lnode.id], fi[rnode.id]);
                if(lcanode==lnode)
                {
                    while(gl<lnode.start)
                    {
                        if(set[flatTree[gl]])
                        {
                            sum = removeFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = false;
                        }
                        else
                        {
                            sum = addFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = true;
                        }
                        gl++;
                    }

                    while(gl>lnode.start)
                    {
                        gl--;
                        if(set[flatTree[gl]])
                        {
                            sum = removeFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = false;
                        }
                        else
                        {
                            sum = addFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = true;
                        }
                    }

                }
                else
                {
                    while(gl<lnode.end)
                    {
                        if(set[flatTree[gl]])
                        {
                            sum = removeFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = false;
                        }
                        else
                        {
                            sum = addFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = true;
                        }
                        gl++;
                    }

                    while(gl>lnode.end)
                    {
                        gl--;
                        if(set[flatTree[gl]])
                        {
                            sum = removeFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = false;
                        }
                        else
                        {
                            sum = addFactors(tr.nodelist[flatTree[gl]].tq, primecounthash, sum);
                            set[flatTree[gl]] = true;
                        }
                    }

                }
                while(gr<rnode.start)
                {
                    gr++;
                    if(set[flatTree[gr]])
                    {
                        sum = removeFactors(tr.nodelist[flatTree[gr]].tq, primecounthash, sum);
                        set[flatTree[gr]] = false;
                    }
                    else
                    {
                        sum = addFactors(tr.nodelist[flatTree[gr]].tq, primecounthash, sum);
                        set[flatTree[gr]] = true;
                    }
                }
                while (gr>rnode.start)
                {
                    if(set[flatTree[gr]])
                    {
                        sum = removeFactors(tr.nodelist[flatTree[gr]].tq, primecounthash, sum);
                        set[flatTree[gr]] = false;
                    }
                    else
                    {
                        sum = addFactors(tr.nodelist[flatTree[gr]].tq, primecounthash, sum);
                        set[flatTree[gr]] = true;
                    }
                    gr--;
                }

                if(lcanode!=lnode)
                {
                    sum = addFactors(lcanode.tq, primecounthash, sum);
                    ans[query.id] = sum;
                    sum = removeFactors(lcanode.tq, primecounthash, sum);
                }
                else
                {
                    ans[query.id] = sum;
                }
            }

            for(long answer: ans)
                sb.append(answer).append("\n");
        }
        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/1000);
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
