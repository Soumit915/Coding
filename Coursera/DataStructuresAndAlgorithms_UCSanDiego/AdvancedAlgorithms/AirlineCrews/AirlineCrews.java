package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AdvancedAlgorithms.AirlineCrews;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

class Node
{
    int id;
    String type;
    boolean isMatched;
    ArrayList<Node> adjacencylist;
    Map<Node, Edge> connectingMap;
    Node(int id, String type)
    {
        this.id = id;
        this.type = type;
        this.isMatched = false;
        this.adjacencylist = new ArrayList<>();
        this.connectingMap = new HashMap<>();
    }
}

class Edge
{
    Node pilot;
    Node flight;
    boolean isMatched;
    Edge(Node pilot, Node flight)
    {
        this.pilot = pilot;
        this.flight = flight;
        this.isMatched = false;
    }
}

class FlowNetwork
{
    ArrayList<Node> crewlist;
    ArrayList<Node> flightlist;
    ArrayList<Edge> matchlist;
    FlowNetwork(int n, int m)
    {
        crewlist = new ArrayList<>(m);
        for(int i=0;i<m;i++)
        {
            crewlist.add(new Node(i, "crew"));
        }
        flightlist = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            flightlist.add(new Node(i, "airline"));
        }
        matchlist = new ArrayList<>();
    }
    public void addEdge(int flight, int crew)
    {
        Node a = this.flightlist.get(flight);
        Node c = this.crewlist.get(crew);
        Edge e = new Edge(c,a);

        this.matchlist.add(e);
        a.adjacencylist.add(c);
        a.connectingMap.put(c,e);
        c.adjacencylist.add(a);
        c.connectingMap.put(a,e);
    }
}

class BipartiteMatch
{
    int maxMatch;
    BipartiteMatch()
    {
        this.maxMatch = 0;
    }
    public void findMaxMatch(FlowNetwork fn)
    {
        for(Node f: fn.flightlist)
        {
            for(Node c: f.adjacencylist)
            {
                if(!c.isMatched)
                {
                    //match.addEdge(f.connectingMap.get(c));
                    f.connectingMap.get(c).isMatched = true;
                    f.isMatched = true;
                    c.isMatched = true;
                    this.maxMatch += 1;
                    break;
                }
            }
        }

        for(Node a: fn.flightlist)
        {
            if(!a.isMatched)
            {
                hasAugmentingPath(fn, a);
            }
        }
    }
    public void hasAugmentingPath(FlowNetwork fn, Node a)
    {
        Stack<Node> stk = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();

        boolean[] isVisitedCrew = new boolean[fn.crewlist.size()];
        //boolean[] isVisitedFlight = new boolean[fn.flightlist.size()];

        stk.push(a);
        ptrstk.push(-1);
        //isVisitedFlight[a.id] = true;

        while(!stk.isEmpty()) {
            Node cur = stk.pop();
            int ptr = ptrstk.pop();

            if (cur.type.equals("crew") && !cur.isMatched) {
                //perform stack pop operation and then perform perfect matching operations
                stk.push(cur);
                while (!stk.isEmpty()) {
                    Node crew = stk.pop();
                    Node airline = stk.pop();
                    airline.connectingMap.get(crew).isMatched = true;
                    crew.isMatched = true;
                    airline.isMatched = true;
                    if (!stk.isEmpty())
                        airline.connectingMap.get(stk.peek()).isMatched = false;
                }
                this.maxMatch += 1;
                break;
            } else if (cur.type.equals("crew") && !isVisitedCrew[cur.id]) {
                for (Node matchingnode : cur.adjacencylist) {

                    if (cur.connectingMap.get(matchingnode).isMatched) {
                        stk.push(cur);
                        ptrstk.push(ptr);
                        stk.push(matchingnode);
                        ptrstk.push(-1);
                        //isVisitedFlight[matchingnode.id] = true;
                        isVisitedCrew[cur.id] = true;
                        break;
                    }
                }
            } else if (cur.type.equals("airline") && cur.adjacencylist.size() > ptr + 1) {
                ptr++;
                Node adnode = cur.adjacencylist.get(ptr);
                stk.push(cur);
                ptrstk.push(ptr);

                if (!isVisitedCrew[adnode.id]) {
                    //perform ordinary dfs operations
                    stk.push(adnode);
                    ptrstk.push(-1);
                    //isVisitedCrew[adnode.id] = true;
                }
            }
        }
    }
}

public class AirlineCrews {
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        FlowNetwork fn = new FlowNetwork(n,m);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(sc.nextInt() == 1)
                    fn.addEdge(i,j);
            }
        }

        BipartiteMatch bm = new BipartiteMatch();
        bm.findMaxMatch(fn);
        //System.out.println(bm.maxMatch);

        boolean flag;
        for(Node airline: fn.flightlist)
        {
            flag = false;
            for(Node crew: airline.adjacencylist)
            {
                if(airline.connectingMap.get(crew).isMatched)
                {
                    System.out.println(crew.id+1);
                    flag = true;
                    break;
                }
            }
            if(!flag)
                System.out.println(-1);
        }
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
