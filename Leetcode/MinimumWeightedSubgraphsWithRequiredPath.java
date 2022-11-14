package Leetcode;

import java.util.*;

public class MinimumWeightedSubgraphsWithRequiredPath {

    static class Node implements Comparable<Node>
    {
        int id;
        long distance;
        Node(int id, long distance)
        {
            this.id = id;
            this.distance = distance;
        }
        public int compareTo(Node n)
        {
            return Long.compare(this.distance, n.distance);
        }
    }

    static class Vertex{
        int id;
        boolean isVisited1, isVisited2, isVisited_dest;
        long dest_dist;
        long todist1, todist2;

        List<Edge> outedgelist = new ArrayList<>();
        List<Edge> inedgelist = new ArrayList<>();

        Vertex(int id){
            this.id = id;
            this.dest_dist = Long.MAX_VALUE;
            this.todist1 = Long.MAX_VALUE;
            this.todist2 = Long.MAX_VALUE;
        }
    }

    static class Edge{
        Vertex u, v;
        long weight;

        Edge(Vertex u, Vertex v, long weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Graph{
        List<Vertex> vertexlist = new ArrayList<>();

        Graph(int n){
            for(int i=0;i<n;i++){
                vertexlist.add(new Vertex(i));
            }
        }

        public void addEdge(int u, int v, long weight){
            Vertex nu = vertexlist.get(u);
            Vertex nv = vertexlist.get(v);

            Edge e = new Edge(nu, nv, weight);

            nu.outedgelist.add(e);
            nv.inedgelist.add(e);
        }

        public void shortestReach_dest(int s) {

            vertexlist.get(s).dest_dist = 0;
            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            Set<Integer> isRelaxed = new HashSet<>();
            for(Vertex v: vertexlist) {
                minHeap.add(new Node(v.id, v.dest_dist));
            }

            while(!minHeap.isEmpty())
            {
                Node node = minHeap.remove();
                if(isRelaxed.contains(node.id))
                    continue;
                isRelaxed.add(node.id);
                Vertex cur = vertexlist.get(node.id);
                if(cur.dest_dist == Long.MAX_VALUE)
                    continue;
                for(Edge e: cur.inedgelist)
                {
                    Vertex v;
                    if(e.u==cur)
                        v = e.v;
                    else v = e.u;

                    if(isRelaxed.contains(v.id))
                        continue;
                    if(v.dest_dist>cur.dest_dist+e.weight)
                    {
                        v.dest_dist = cur.dest_dist+e.weight;
                        Node newnode = new Node(v.id, v.dest_dist);
                        minHeap.add(newnode);
                    }
                }
            }

            for(Vertex v: vertexlist){
                v.isVisited_dest = v.dest_dist != Long.MAX_VALUE;
            }
        }

        public void shortestReach_src1(int s) {

            vertexlist.get(s).todist1 = 0;
            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            Set<Integer> isRelaxed = new HashSet<>();
            for(Vertex v: vertexlist) {
                minHeap.add(new Node(v.id, v.todist1));
            }

            while(!minHeap.isEmpty())
            {
                Node node = minHeap.remove();
                if(isRelaxed.contains(node.id))
                    continue;
                isRelaxed.add(node.id);
                Vertex cur = vertexlist.get(node.id);
                if(cur.todist1 == Long.MAX_VALUE)
                    continue;
                for(Edge e: cur.outedgelist)
                {
                    Vertex v;
                    if(e.u==cur)
                        v = e.v;
                    else v = e.u;

                    if(isRelaxed.contains(v.id))
                        continue;
                    if(v.todist1>cur.todist1+e.weight)
                    {
                        v.todist1 = cur.todist1+e.weight;
                        Node newnode = new Node(v.id, v.todist1);
                        minHeap.add(newnode);
                    }
                }
            }

            for(Vertex v: vertexlist){
                v.isVisited1 = v.todist1 != Long.MAX_VALUE;
            }
        }

        public void shortestReach_src2(int s) {

            vertexlist.get(s).todist2 = 0;
            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            Set<Integer> isRelaxed = new HashSet<>();
            for(Vertex v: vertexlist) {
                minHeap.add(new Node(v.id, v.todist2));
            }

            while(!minHeap.isEmpty())
            {
                Node node = minHeap.remove();
                if(isRelaxed.contains(node.id))
                    continue;
                isRelaxed.add(node.id);
                Vertex cur = vertexlist.get(node.id);
                if(cur.todist2 == Long.MAX_VALUE)
                    continue;
                for(Edge e: cur.outedgelist)
                {
                    Vertex v;
                    if(e.u==cur)
                        v = e.v;
                    else v = e.u;

                    if(isRelaxed.contains(v.id))
                        continue;
                    if(v.todist2>cur.todist2+e.weight)
                    {
                        v.todist2 = cur.todist2+e.weight;
                        Node newnode = new Node(v.id, v.todist2);
                        minHeap.add(newnode);
                    }
                }
            }

            for(Vertex v: vertexlist){
                v.isVisited2 = v.todist2 != Long.MAX_VALUE;
            }
        }
    }

    public static long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        Graph gr = new Graph(n);

        for (int[] edge : edges) {
            gr.addEdge(edge[0], edge[1], edge[2]);
        }

        gr.shortestReach_dest(dest);
        gr.shortestReach_src1(src1);
        gr.shortestReach_src2(src2);

        Vertex src1node = gr.vertexlist.get(src1);
        Vertex src2node = gr.vertexlist.get(src2);

        if(!src1node.isVisited_dest || !src2node.isVisited_dest){
            return -1;
        }

        long min = src1node.dest_dist + src2node.dest_dist;
        for(Vertex v: gr.vertexlist){
            if(v.isVisited1 && v.isVisited2 && v.isVisited_dest){
                min = Math.min(min, v.dest_dist+v.todist1+v.todist2);
            }
        }

        return min;
    }

    public static void main(String[] args){

    }
}
