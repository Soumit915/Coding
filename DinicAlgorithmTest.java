import java.util.*;
import java.io.*;

public class DinicAlgorithmTest {
    static class City
    {
        int id;
        boolean isVisited;
        int levelOrder;
        boolean isDeadEnd;
        ArrayList<City> adjacentcities;
        Map<City, Road> connectingMap;
        City(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.isDeadEnd = false;
            this.levelOrder = -1;
            this.adjacentcities = new ArrayList<>();
            this.connectingMap = new HashMap<>();
        }
    }

    static class Road
    {
        City fromCity;
        City targetCity;
        int capacity;
        Road(City fromCity, City targetCity, int capacity)
        {
            this.fromCity = fromCity;
            this.targetCity = targetCity;
            this.capacity = capacity;
            //this.flow = 0;
        }
    }

    static class FlowNetwork
    {
        ArrayList<City> citylist;
        ArrayList<Road> roadlist;
        FlowNetwork(int n, int m)
        {
            citylist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                this.addCity(i+1);
            }
            roadlist = new ArrayList<>(m);
        }
        public void addCity(int id)
        {
            this.citylist.add(new City(id));
        }
        public void addRoad(City start, City end, int capacity)
        {
            if(start.adjacentcities.contains(end))
            {
                Road existingRoad = start.connectingMap.get(end);
                existingRoad.capacity+=capacity;
                return;
            }
            Road r = new Road(start, end, capacity);
            this.roadlist.add(r);

            start.adjacentcities.add(end);
            start.connectingMap.put(end, r);
        }
        public void addRoad(int start, int end, int capacity)
        {
            if(start == end)
                return;
            this.addRoad(this.citylist.get(start-1), this.citylist.get(end-1), capacity);
            this.addRoad(this.citylist.get(end-1), this.citylist.get(start-1), 0);
        }
    }

    static class DinicMaximumEvacuation
    {
        int maxFlow;
        boolean reachedsink;
        DinicMaximumEvacuation()
        {
            this.maxFlow = 0;
            this.reachedsink = false;
        }
        public void maxEvacuate(FlowNetwork fn, City source, City sink)
        {
            //perform bfs to find the level ordering and if there exists a augmenting path from source to sink
            //perform multiple dfs in each call
            //after reaching the sink each time update the maxflow and keep performing dfs untill u find a deadend
            //keep a well account of the dead lock to save time of dfs traversal

            while(hasAugmentingPath(source, sink))
            {
                boolean reachedDeadEnd = false;
                while(!reachedDeadEnd)
                {
                    int flow = performDfs(source, sink);
                    if(flow == 0)
                        reachedDeadEnd = true;
                    this.maxFlow += flow;
                    reachedsink = false;

                }

                for(City c: fn.citylist)
                {
                    c.isDeadEnd = false;
                    c.levelOrder = -1;
                    c.isVisited = false;
                }
            }
        }
        public boolean hasAugmentingPath(City source, City sink)
        {
            Queue<City> queue = new LinkedList<>();
            queue.add(source);
            source.isVisited = true;
            source.levelOrder = 0;
            boolean flag = false;

            while(!queue.isEmpty())
            {
                City cur = queue.remove();
                for(City c: cur.adjacentcities)
                {
                    Road r = cur.connectingMap.get(c);
                    if(r.capacity == 0)
                        continue;
                    if(c == sink && !sink.isVisited)       //we will not traverse any nodes from the sink
                    {
                        flag = true;
                        c.isVisited = true;
                        c.levelOrder = cur.levelOrder+1;
                    }
                    else if(!c.isVisited)
                    {
                        c.isVisited = true;
                        queue.add(c);
                        c.levelOrder = cur.levelOrder+1;
                    }
                }
            }

            return flag;
        }
        public int performDfs(City source, City sink)
        {
            Stack<City> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            int flow=0;

            stk.push(source);
            ptrstk.push(-1);

            City adcity=null;
            boolean localflag;
            while(!reachedsink && !stk.isEmpty())
            {
                City cur = stk.pop();
                int nptr = ptrstk.pop();
                localflag = false;

                while(cur.adjacentcities.size()>nptr+1)
                {
                    nptr++;
                    adcity = cur.adjacentcities.get(nptr);
                    if(cur.connectingMap.get(adcity).capacity == 0)
                        continue;

                    if(adcity == sink && adcity.levelOrder==cur.levelOrder+1)
                    {
                        reachedsink = true;
                        localflag = true;
                        break;
                    }
                    if(!adcity.isDeadEnd && adcity.levelOrder==cur.levelOrder+1)
                    {
                        localflag = true;
                        break;
                    }
                }
                if(localflag)
                {
                    stk.push(cur);
                    ptrstk.push(nptr);
                    stk.push(adcity);
                    ptrstk.push(-1);
                }
                else
                {
                    cur.isDeadEnd = true;
                }
                if(reachedsink)
                {
                    break;
                }
            }

            if(reachedsink) {
                //Curently our stack consists of only the path from the source to the sink
                ArrayList<City> path = new ArrayList<>(stk);

                City par = path.get(0);
                int min = Integer.MAX_VALUE;
                for (int i = 1; i < path.size(); i++) {
                    City cur = path.get(i);
                    Road r = par.connectingMap.get(cur);
                    min = Math.min(r.capacity, min);
                    par = cur;
                }

                par = path.get(0);
                for (int i = 1; i < path.size(); i++) {
                    City cur = path.get(i);
                    Road r = par.connectingMap.get(cur);
                    Road revr = cur.connectingMap.get(par);
                    r.capacity -= min;
                    revr.capacity += min;
                    par = cur;
                }

                flow+=min;
            }
            return flow;
        }
    }

    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        FlowNetwork fn = new FlowNetwork(n,m);
        for(int i=0;i<m;i++)
        {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int capacity = sc.nextInt();

            fn.addRoad(start, end, capacity);
        }

        DinicMaximumEvacuation dme = new DinicMaximumEvacuation();
        dme.maxEvacuate(fn, fn.citylist.get(0), fn.citylist.get(n-1));
        System.out.println(dme.maxFlow);
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
