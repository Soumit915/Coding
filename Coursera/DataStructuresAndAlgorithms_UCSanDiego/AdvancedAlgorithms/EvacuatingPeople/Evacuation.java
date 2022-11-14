package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AdvancedAlgorithms.EvacuatingPeople;

import java.util.*;
import java.io.*;

class City
{
    int id;
    boolean isVisited;
    int bfsFlow;
    City previous;
    ArrayList<City> adjacentcities;
    Map<City, Road> connectingMap;
    City(int id)
    {
        this.id = id;
        this.isVisited = false;
        this.bfsFlow = -1;
        this.previous = null;
        this.adjacentcities = new ArrayList<City>();
        this.connectingMap = new HashMap<City, Road>();
    }
}

class Road
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

class FlowNetwork
{
    ArrayList<City> citylist;
    ArrayList<Road> roadlist;
    FlowNetwork(int n, int m)
    {
        citylist = new ArrayList<City>(n);
        for(int i=0;i<n;i++)
        {
            this.addCity(i+1);
        }
        roadlist = new ArrayList<Road>(m);
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

class MaximumEvacuation
{
    int maxFlow;
    MaximumEvacuation()
    {
        this.maxFlow = 0;
    }
    public void maxEvacuate(FlowNetwork fn, City source, City sink)
    {
        while(hasAugmentingPath(source, sink))
        {
            //find the minimum capacity
            City pre = sink;
            int min = Integer.MAX_VALUE;
            while(pre.previous != null)
            {
                min = Math.min(min, pre.bfsFlow);
                pre = pre.previous;
            }

            //then increase the flow of each edge by the minimum value O(n)
            City target = sink;
            while((pre = target.previous)!=null)
            {
                Road r = pre.connectingMap.get(target);
                //r.flow += min;
                r.capacity -= min;
                Road revr = target.connectingMap.get(pre);
                revr.capacity += min;
                target = pre;
            }

            //initialize previous, bfsFlow and Visited to their default values  O(n)
            for(City c: fn.citylist)
            {
                c.previous = null;
                c.bfsFlow = 0;
                c.isVisited = false;
            }

            //increase the value of the maxFlow by the minimum value    O(1)
            this.maxFlow += min;
        }
    }
    public boolean hasAugmentingPath(City source, City sink)
    {
        Queue<City> queue = new LinkedList<City>();
        queue.add(source);
        source.isVisited = true;

        while(!queue.isEmpty())
        {
            City cur = queue.remove();
            for(City c: cur.adjacentcities)
            {
                Road r =  cur.connectingMap.get(c);
                City targetCity = r.targetCity;
                int curCapacity = r.capacity;
                if(curCapacity == 0)
                    continue;
                if(targetCity == sink)
                {
                    sink.isVisited = true;
                    sink.previous = cur;
                    sink.bfsFlow = curCapacity;
                    return true;
                }
                else if(!targetCity.isVisited)
                {
                    queue.add(targetCity);
                    targetCity.isVisited = true;
                    targetCity.previous = cur;
                    targetCity.bfsFlow = curCapacity;
                }
            }
        }

        return false;
    }
}

public class Evacuation {
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

        MaximumEvacuation me = new MaximumEvacuation();
        me.maxEvacuate(fn, fn.citylist.get(0), fn.citylist.get(n-1));
        System.out.println(me.maxFlow);
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
