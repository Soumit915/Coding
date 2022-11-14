package Leetcode;

import java.io.*;
import java.util.*;

public class WordLadder {

    static class Node{
        int id;
        String word;
        boolean isVisited;
        int dist;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id, String word){
            this.id = id;
            this.word = word;
            this.isVisited = false;
            this.dist = 0;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist;
        Graph(int n, List<String> wordlist){
            this.nodelist = new ArrayList<>();
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, wordlist.get(i)));
            }
        }
        public void addEdge(int i, int j){
            Node nu = nodelist.get(i);
            Node nv = nodelist.get(j);
            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public void bfs(ArrayList<Integer> startlist){

            Queue<Node> q = new LinkedList<>();
            for(int si: startlist){
                Node start = nodelist.get(si);
                q.add(start);
                start.isVisited = true;
                start.dist = 2;
            }

            while(!q.isEmpty()){
                Node cur = q.remove();
                for(Node node: cur.adlist){
                    if(!node.isVisited){
                        node.isVisited = true;
                        q.add(node);
                        node.dist = cur.dist + 1;
                    }
                }
            }
        }
    }

    static void addEdge(Graph gr, String s, Map<String, Integer> wordlist){
        char[] str = s.toCharArray();
        int cur = wordlist.get(s);

        for(int i=0;i<str.length;i++){
            for(int j=str[i]+1;j<='z';j++){

                StringBuilder sb = new StringBuilder();
                for(int k=0;k<str.length;k++){
                    if(k==i)
                        sb.append(((char) j));
                    else sb.append(str[k]);
                }

                if(wordlist.containsKey(sb.toString())){
                    gr.addEdge(cur, wordlist.get(sb.toString()));
                }
            }
        }
    }

    static ArrayList<Integer> getFirstIndices(String s, Map<String, Integer> wordlist){
        char[] str = s.toCharArray();
        ArrayList<Integer> arlist = new ArrayList<>();

        for(int i=0;i<str.length;i++){
            for(int j='a';j<='z';j++){

                if(j==str[i])
                    continue;

                StringBuilder sb = new StringBuilder();
                for(int k=0;k<str.length;k++){
                    if(k==i)
                        sb.append(((char) j));
                    else sb.append(str[k]);
                }

                if(wordlist.containsKey(sb.toString())){
                    arlist.add(wordlist.get(sb.toString()));
                }
            }
        }

        return arlist;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordlist) {
        int n = wordlist.size();

        Graph gr = new Graph(n, wordlist);

        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(wordlist.get(i), i);
        }

        for (String s : wordlist) {
            addEdge(gr, s, map);
        }

        if(map.containsKey(beginWord)){
            Node begin = gr.nodelist.get(map.get(beginWord));
            begin.dist = 1;
            begin.isVisited = true;
        }

        ArrayList<Integer> arlist = getFirstIndices(beginWord, map);
        gr.bfs(arlist);

        if(!map.containsKey(endWord)){
            return 0;
        }
        return gr.nodelist.get(map.get(endWord)).dist;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        String beginWord = sc.next();
        String endWord = sc.next();

        List<String> wordlist = new ArrayList<>();
        while(sc.hasNext()){
            wordlist.add(sc.next());
        }

        System.out.println(ladderLength(beginWord, endWord, wordlist));

        sc.close();
    }
}
