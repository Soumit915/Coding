import java.io.*;
import java.util.*;

public class WarOnNinjaLand {
    static class Node
    {
        int id;
        boolean isRationalised;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.isRationalised = false;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist = new ArrayList<>();
        public Node getNode(int i)
        {
            Node node = new Node(i);
            nodelist.add(node);
            return node;
        }
        public void addEdge(Node u, Node v)
        {
            u.adlist.add(v);
            v.adlist.add(u);
        }
        public int createTree(int[] arr)
        {
            Queue<Node> q = new LinkedList<>();
            q.add(getNode(nodelist.size()));

            int i = 1;
            while(!q.isEmpty())
            {
                Node nd = q.remove();
                int val = arr[i];
                for(int j=1;j<=val;j++)
                {
                    Node node = new Node(nodelist.size());
                    nodelist.add(node);
                    q.add(node);
                    addEdge(nd, node);
                }
                i+=val;
                i++;
            }

            q = new LinkedList<>();
            for(Node node: nodelist)
            {
                if(node.adlist.size()==1) {
                    q.add(node.adlist.get(0));
                }
            }

            int count = 0;

            while(!q.isEmpty())
            {
                Node cur = q.remove();
                if(!cur.isRationalised)
                {
                    cur.isRationalised = true;
                    count++;
                    System.out.println(cur.id);
                    for(Node nd: cur.adlist)
                    {
                        if(nd.isRationalised)
                            continue;
                        nd.isRationalised = true;
                        boolean hasLeaf = false;
                        for(Node adnd: nd.adlist)
                        {
                            if(adnd.adlist.size()==1)
                                hasLeaf = true;
                        }
                        if(hasLeaf)
                        {
                            q.add(nd);
                            nd.isRationalised = false;
                            continue;
                        }
                        for(Node adnd : nd.adlist)
                            if(!adnd.isRationalised)
                                q.add(adnd);
                    }
                }
            }

            return count;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String[] linearr = line.split(" ");
        int[] arr = new int[linearr.length];
        for(int i=0;i<linearr.length;i++)
        {
            arr[i] = Integer.parseInt(linearr[i]);
        }
        Tree tr = new Tree();
        int noh = tr.createTree(arr);

        if(noh>sc.nextInt())
        {
            System.out.println(noh+" Mission Unsuccessful");
        }
        else System.out.println(noh+" Mission Successful");
    }
}
