package Codechef;

import java.util.*;

public class RedBlueTrees {
    static class Node
    {
        int id;
        int color;
        ArrayList<Node> adjacentnode = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        HashMap<Integer, HashSet<Integer>> hash = new HashMap<>();
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
                hash.put(i, new HashSet<>());
            }
        }
        public void addEdge(int xi, int yi)
        {
            Node u = nodelist.get(xi);
            Node v = nodelist.get(yi);
            u.adjacentnode.add(v);
            v.adjacentnode.add(u);
        }
        public long dfs()
        {
            Node source = nodelist.get(0);
            boolean[] isFinished = new boolean[nodelist.size()];
            boolean[] isVisited = new boolean[nodelist.size()];
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            long swapcount = 0;

            stk.push(source);
            ptrstk.push(-1);
            isVisited[source.id] = true;

            int checkerd = checkisValid(source, isFinished);
            if(checkerd>=0)
            {
                source.color = (source.color==0)?1:0;
                source.adjacentnode.get(checkerd).color = (source.color==0)?1:0;
                swapcount++;

                HashSet<Integer> set = hash.get(source.id);
                set.add(source.adjacentnode.get(checkerd).id);
                hash.put(source.id, set);

                set = hash.get(source.adjacentnode.get(checkerd).id);
                set.add(source.id);
                hash.put(source.adjacentnode.get(checkerd).id, set);
            }

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adjacentnode.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);
                    Node next = cur.adjacentnode.get(ptr);
                    if(isVisited[next.id])
                        continue;

                    stk.push(next);
                    ptrstk.push(-1);
                    isVisited[next.id] = true;

                    int checker = checkisValid(next, isFinished);
                    if(checker<0)
                    {
                        continue;
                    }

                    next.color = (next.color==0)?1:0;
                    next.adjacentnode.get(checker).color = (next.color==0)?1:0;

                    if(hash.get(next.id).contains(next.adjacentnode.get(checker).id))
                    {
                        swapcount--;
                        HashSet<Integer> set = hash.get(next.id);
                        set.remove(next.adjacentnode.get(checker).id);
                        hash.put(next.id, set);

                        set = hash.get(next.adjacentnode.get(checker).id);
                        set.remove(next.id);
                        hash.put(next.adjacentnode.get(checker).id, set);
                    }
                    else
                    {
                        swapcount++;
                        HashSet<Integer> set = hash.get(next.id);
                        set.add(next.adjacentnode.get(checker).id);
                        hash.put(next.id, set);

                        set = hash.get(next.adjacentnode.get(checker).id);
                        set.add(next.id);
                        hash.put(next.adjacentnode.get(checker).id, set);
                    }
                }
                else
                {
                    int checker = checkisValid(cur, isFinished);
                    if(checker==-2)
                    {
                        return -1;
                    }
                    else if(checker==-1)
                    {
                        isFinished[cur.id] = true;
                    }
                    else
                    {
                        cur.color = (cur.color==0)?1:0;
                        cur.adjacentnode.get(checker).color = (cur.color==0)?1:0;
                        if(hash.get(cur.id).contains(cur.adjacentnode.get(checker).id))
                        {
                            swapcount--;
                            HashSet<Integer> set = hash.get(cur.id);
                            set.remove(cur.adjacentnode.get(checker).id);
                            hash.put(cur.id, set);

                            set = hash.get(cur.adjacentnode.get(checker).id);
                            set.remove(cur.id);
                            hash.put(cur.adjacentnode.get(checker).id, set);
                        }
                        else
                        {
                            swapcount++;
                            HashSet<Integer> set = hash.get(cur.id);
                            set.add(cur.adjacentnode.get(checker).id);
                            hash.put(cur.id, set);

                            set = hash.get(cur.adjacentnode.get(checker).id);
                            set.add(cur.id);
                            hash.put(cur.adjacentnode.get(checker).id, set);
                        }
                    }
                }
            }
            return swapcount;
        }
        public int checkisValid(Node node, boolean[] isRelaxed)
        {
            boolean flag = true;
            for(int i=0;i<node.adjacentnode.size();i++)
            {
                if(node.adjacentnode.get(i).color==node.color) {
                    flag = false;
                    break;
                }
            }

            if(flag)
            {
                return -1;
            }

            for(int i=0;i<node.adjacentnode.size();i++)
            {
                if(node.adjacentnode.get(i).color!=node.color && !isRelaxed[node.adjacentnode.get(i).id])
                {
                    return i;
                }
            }

            return -2;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            Tree tr = new Tree(n);
            for(int i=0;i<n-1;i++)
            {
                int xi = sc.nextInt()-1;
                int yi = sc.nextInt()-1;
                tr.addEdge(xi, yi);
            }

            String s = sc.next();
            for(int i=0;i<n;i++)
            {
                tr.nodelist.get(i).color = s.charAt(i)-48;
            }

            if(n==1)
                System.out.println(0);
            else System.out.println(tr.dfs());
        }
    }
}
