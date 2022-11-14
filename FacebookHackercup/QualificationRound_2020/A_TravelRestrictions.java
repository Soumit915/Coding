package FacebookHackercup.QualificationRound_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class A_TravelRestrictions {
    static class Node
    {
        int id;
        ArrayList<Node> connectedlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
        public void addLink(Node n)
        {
            this.connectedlist.add(n);
        }
    }
    static class Graph
    {
        ArrayList<Node> country;
        boolean[][] isVisited;
        Graph(int n)
        {
            this.country = new ArrayList<>(n);
            this.isVisited = new boolean[n][n];
            for(int i=0;i<n;i++)
            {
                country.add(new Node(i));
            }
        }
        public void dfs(int source)
        {
            Node src = country.get(source);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(src);
            ptrstk.push(-1);
            isVisited[source][source] = true;

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.connectedlist.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);
                    Node nxt = cur.connectedlist.get(ptr);
                    if(!isVisited[source][nxt.id])
                    {
                        stk.push(nxt);
                        ptrstk.push(-1);
                        isVisited[source][nxt.id] = true;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                String incoming = sc.next();
                char[] in = incoming.toCharArray();
                String outgoing = sc.next();
                char[] out = outgoing.toCharArray();

                Graph gr = new Graph(n);
                for(int i=0;i<n;i++)
                {
                    if(out[i]=='Y')
                    {
                        if(i-1>=0 && in[i-1]=='Y')
                        {
                            gr.country.get(i).addLink(gr.country.get(i-1));
                        }
                        if(i+1<n && in[i+1]=='Y')
                        {
                            gr.country.get(i).addLink(gr.country.get(i+1));
                        }
                    }
                }

                for(int i=0;i<n;i++)
                {
                    gr.dfs(i);
                }

                pw.println("Case #"+testi+":");
                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++)
                    {
                        if(gr.isVisited[i][j])
                        {
                            pw.print("Y");
                        }
                        else
                        {
                            pw.print("N");
                        }
                    }
                    pw.println();
                }
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}