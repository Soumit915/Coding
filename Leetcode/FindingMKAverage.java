package Leetcode;

import java.io.*;
import java.util.*;

public class FindingMKAverage {

    static class MKAverage {

        static class Pair implements Comparable<Pair>{
            int id;
            int val;
            Pair(int id, int val){
                this.id = id;
                this.val = val;
            }
            public int compareTo(Pair p){
                int c = Integer.compare(this.val, p.val);
                if(c==0)
                    return Integer.compare(this.id, p.id);
                else return c;
            }
            public String toString(){
                return this.id+": "+this.val;
            }
        }

        int m;
        int k;
        long sum;
        Queue<Pair> pairList;
        PriorityQueue<Pair> lesser;
        TreeSet<Pair> bst;
        PriorityQueue<Pair> higher;

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
            pairList = new LinkedList<>();
            lesser = new PriorityQueue<>(Collections.reverseOrder());
            bst = new TreeSet<>();
            higher = new PriorityQueue<>();
        }

        public void addElement(int num) {
            if(pairList.size()<m){
                Pair toAdd = new Pair(pairList.size()+1, num);
                pairList.add(toAdd);

                if(pairList.size()==m){
                    ArrayList<Pair> pairs = new ArrayList<>(pairList);
                    Collections.sort(pairs);

                    lesser.addAll(pairs.subList(0, k));
                    bst.addAll(pairs.subList(k, m - k));
                    higher.addAll(pairs.subList(m - k, m));

                    for(int i=k;i<m-k;i++){
                        sum += pairs.get(i).val;
                    }
                }

                return;
            }

            Pair toRemove = pairList.remove();

            int curid = toRemove.id + m;
            Pair toAdd = new Pair(curid, num);
            pairList.add(toAdd);

            while(lesser.peek().id < toRemove.id){
                lesser.remove();
            }
            while(bst.first().id < toRemove.id){
                bst.pollFirst();
            }
            while(bst.last().id < toRemove.id){
                bst.pollLast();
            }
            while(higher.peek().id < toRemove.id){
                higher.remove();
            }

            ArrayList<Pair> pairs = new ArrayList<>();
            pairs.add(lesser.remove());
            pairs.add(bst.pollFirst());
            if(m-2*k > 1)
                pairs.add(bst.pollLast());
            pairs.add(higher.remove());

            sum -= pairs.get(1).val;
            if(m-2*k > 1)
                sum -= pairs.get(2).val;

            if(pairs.contains(toRemove)){
                pairs.remove(toRemove);
                pairs.add(toAdd);

                Collections.sort(pairs);

                lesser.add(pairs.get(0));
                bst.add(pairs.get(1));
                if(m-2*k > 1)
                    bst.add(pairs.get(2));
                higher.add(pairs.get(pairs.size()-1));

                sum += pairs.get(1).val;
                if(m-2*k > 1)
                    sum += pairs.get(2).val;
            }
            else{
                if(toRemove.compareTo(pairs.get(0)) < 0){
                    pairs.add(toAdd);
                    Collections.sort(pairs);

                    lesser.add(pairs.get(0));
                    lesser.add(pairs.get(1));
                    bst.add(pairs.get(2));
                    if(m-2*k > 1)
                        bst.add(pairs.get(3));
                    higher.add(pairs.get(pairs.size()-1));

                    sum += pairs.get(2).val;
                    if(m-2*k > 1)
                        sum += pairs.get(3).val;
                }
                else if(toRemove.compareTo(pairs.get(3)) > 0){
                    pairs.add(toAdd);
                    Collections.sort(pairs);

                    lesser.add(pairs.get(0));
                    bst.add(pairs.get(1));
                    if(m-2*k > 1)
                        bst.add(pairs.get(2));
                    higher.add(pairs.get(pairs.size()-2));
                    higher.add(pairs.get(pairs.size()-1));

                    sum += pairs.get(1).val;
                    if(m-2*k > 1)
                        sum += pairs.get(2).val;
                }
                else{
                    pairs.add(toAdd);
                    Collections.sort(pairs);

                    lesser.add(pairs.get(0));
                    bst.add(pairs.get(1));
                    bst.add(pairs.get(2));
                    bst.add(pairs.get(3));
                    higher.add(pairs.get(4));

                    sum -= toRemove.val;
                    sum += pairs.get(1).val;
                    sum += pairs.get(2).val;
                    sum += pairs.get(3).val;
                }
            }
        }

        public int calculateMKAverage() {
            if(pairList.size()<m)
                return -1;
            long avg = sum / (m - 2L *k);
            return (int) avg;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int k = sc.nextInt();

        MKAverage mka = new MKAverage(m, k);
        sc.next();
        while(sc.hasNextLine()){
            String line = sc.nextLine();

            Scanner linesc = new Scanner(line);
            int type = linesc.nextInt();

            if(type==1){
                int num = sc.nextInt();
                mka.addElement(num);
            }
            else{
                System.out.println(mka.calculateMKAverage());
            }
        }
    }
}
