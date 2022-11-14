package Leetcode;

import java.io.*;
import java.util.*;

public class LargestComponentSizeByCommonFactor {
    static class Node{
        int id;
        boolean isVisited;
        int count;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
            this.count = 0;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist = new ArrayList<>();
        Graph(int n){
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public int getMaxCompoSize(){
            int max = 0;
            for(Node node: this.nodelist){
                if(node.count>0 && !node.isVisited){
                    int size = this.dfs(node);
                    max = Math.max(max, size);
                }
            }

            return max;
        }
        public int dfs(Node source){
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;
            int c = source.count;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(!next.isVisited){
                        if(next.count>0){
                            c += next.count;
                        }

                        stk.push(next);
                        ptrstk.push(-1);
                        next.isVisited = true;
                    }
                }
            }

            return c;
        }
    }

    ArrayList<Boolean> isPrime;
    ArrayList<Integer> primes;
    ArrayList<Integer> spf;
    public void preComputePrimes(int n){
        isPrime = new ArrayList<>();
        primes = new ArrayList<>();
        spf = new ArrayList<>();

        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++){
            if(isPrime.get(i)){
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf.get(i);j++){
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }

    public ArrayList<Integer> getPFactors(int n){
        Set<Integer> set = new HashSet<>();
        while(n>1){
            int pf = spf.get(n);
            set.add(pf);

            n /= pf;
        }

        return new ArrayList<>(set);
    }

    public int largestComponentSize(int[] nums) {
        int n = 0;
        for (int k : nums) n = Math.max(n, k);
        Graph gr = new Graph(n);

        preComputePrimes(n);

        for (int num : nums) {
            ArrayList<Integer> pfactors = getPFactors(num);

            Node node = gr.nodelist.get(num);
            node.count++;
            if (node.count>1) {
                continue;
            }

            for (int j : pfactors) {
                gr.addEdge(num, j);
            }
        }

        return gr.getMaxCompoSize();
    }

    public static void main(String[] args) throws IOException {
        int[] nums = {4,6,15,35};
        LargestComponentSizeByCommonFactor lcscf = new LargestComponentSizeByCommonFactor();
        System.out.println(lcscf.largestComponentSize(nums));
    }
}
