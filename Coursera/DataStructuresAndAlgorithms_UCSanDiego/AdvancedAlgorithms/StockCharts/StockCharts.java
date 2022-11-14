package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AdvancedAlgorithms.StockCharts;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

class Node
{
    int id;
    int column;
    boolean isMatched;
    ArrayList<Node> adjacencyList;
    Map<Node, Edge> connectingMap;
    Node(int id, int column)
    {
        this.id = id;
        this.column = column;
        this.isMatched = false;
        adjacencyList = new ArrayList<>();
        connectingMap = new HashMap<>();
    }
}

class Edge
{
    Node column1Node;
    Node column2Node;
    boolean isMatched;
    Edge(Node column1Node, Node column2Node)
    {
        this.column1Node = column1Node;
        this.column2Node = column2Node;
        this.isMatched = false;
    }
}

class BipartiteNetwork
{
    ArrayList<Node> column1;
    ArrayList<Node> column2;
    BipartiteNetwork(int n)
    {
        column1 = new ArrayList<>(n);
        column2 = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            column1.add(new Node(i, 1));
            column2.add(new Node(i, 2));
        }
    }
    public void addEdge(int i, int j)
    {
        Node ni = this.column1.get(i);
        Node nj = this.column2.get(j);
        Edge e = new Edge(ni, nj);

        ni.adjacencyList.add(nj);
        ni.connectingMap.put(nj, e);
        nj.adjacencyList.add(ni);
        nj.connectingMap.put(ni,e);
    }
}

class BipartiteMatching
{
    int maxMatch;
    BipartiteMatching()
    {
        this.maxMatch = 0;
    }
    public void findMaxMatch(BipartiteNetwork bn)
    {
        for(Node n1: bn.column1)
        {
            for(Node adn1: n1.adjacencyList)
            {
                if(!adn1.isMatched)
                {
                    adn1.isMatched = true;
                    n1.isMatched = true;
                    n1.connectingMap.get(adn1).isMatched = true;
                    maxMatch += 1;
                    break;
                }
            }
        }

        for(Node n1: bn.column1)
        {
            if(!n1.isMatched)
            {
                hasAugmentingPath(bn, n1);
            }
        }
    }
    public void hasAugmentingPath(BipartiteNetwork bn, Node n1)
    {
        Stack<Node> stk = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();
        boolean[] isVisited2 = new boolean[bn.column1.size()];

        stk.push(n1);
        ptrstk.push(-1);

        while(!stk.isEmpty())
        {
            Node cur = stk.pop();
            int ptr = ptrstk.pop();

            if(cur.column == 2 && !cur.isMatched)
            {
                //empty the stack and perform perfect matching operations
                stk.push(cur);
                while(!stk.isEmpty())
                {
                    Node col2 = stk.pop();
                    Node col1 = stk.pop();
                    col2.isMatched = true;
                    col1.isMatched = true;
                    col1.connectingMap.get(col2).isMatched = true;
                    if(!stk.isEmpty())
                        col1.connectingMap.get(stk.peek()).isMatched = false;
                }
                maxMatch += 1;
                break;
            }
            else if(cur.column == 2 && !isVisited2[cur.id])
            {
                //get to its matcher from node1
                for(Node matchingnode: cur.adjacencyList)
                {
                    if(cur.connectingMap.get(matchingnode).isMatched)
                    {
                        stk.push(cur);
                        ptrstk.push(ptr);
                        stk.push(matchingnode);
                        ptrstk.push(-1);
                        isVisited2[cur.id] = true;
                        break;
                    }
                }
            }
            else if(cur.column == 1 && cur.adjacencyList.size()>ptr+1)
            {
                //this is node1
                //So, perform normal dfs operation
                ptr++;
                Node adnode = cur.adjacencyList.get(ptr);
                stk.push(cur);
                ptrstk.push(ptr);
                if(!isVisited2[adnode.id])
                {
                    stk.push(adnode);
                    ptrstk.push(-1);
                }
            }
        }
    }
}

public class StockCharts
{
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] data = new int[n][k];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<k;j++)
            {
                data[i][j] = sc.nextInt();
            }
        }

        BipartiteNetwork bn = new BipartiteNetwork(n);

        boolean flag=false,conditionFlag;
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                conditionFlag = true;
                for(int i1=0;i1<k;i1++)
                {
                    if(i1==0)
                    {
                        if(data[i][i1]<data[j][i1])
                        {
                            flag = true;
                            continue;
                        }
                        else if(data[i][i1]>data[j][i1])
                        {
                            flag = false;
                            continue;
                        }
                        else
                        {
                            conditionFlag = false;
                            break;
                        }
                    }

                    if(data[i][i1]<data[j][i1] && !flag)
                    {
                        conditionFlag = false;
                        break;
                    }
                    else if(data[i][i1]>data[j][i1] && flag)
                    {
                        conditionFlag = false;
                        break;
                    }
                    else if(data[i][i1]==data[j][i1])
                    {
                        conditionFlag = false;
                        break;
                    }
                }
                if(conditionFlag)
                {
                    if(flag)
                    {
                        //add edge from i to j
                        bn.addEdge(i,j);
                    }
                    else
                    {
                        //add edge from j to i
                        bn.addEdge(j,i);
                    }
                }
            }
        }

        BipartiteMatching bm = new BipartiteMatching();
        bm.findMaxMatch(bn);
        System.out.println(n-bm.maxMatch);
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
