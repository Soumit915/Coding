package Codeforces.Round679Div2;

import java.util.*;
import java.io.*;

public class C {

    static class Node implements Comparable<Node>{
        int id;
        long val;

        Node(int id, long val){
            this.id = id;
            this.val = val;
        }

        public int compareTo(Node node){
            return Long.compare(this.val, node.val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = (br.readLine()).split(" ");
        long[] strings = new long[6];
        for(int i=0;i<6;i++){
            strings[i] = Long.parseLong(line[i]);
        }
        Arrays.sort(strings);

        int n = Integer.parseInt(br.readLine());
        line = (br.readLine()).split(" ");

        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = Long.parseLong(line[i]);
        }
        Arrays.sort(arr);

        List<Queue<Long>> fretList = new ArrayList<>();
        List<Long> allFrets = new ArrayList<>();
        for(int i=0;i<n;i++){
            Queue<Long> frets = new LinkedList<>();
            for(int j=5;j>=0;j--){
                allFrets.add(arr[i] - strings[j]);
                frets.add(arr[i] - strings[j]);
            }

            fretList.add(frets);
        }

        Collections.sort(allFrets);

        PriorityQueue<Node> heap = new PriorityQueue<>();
        long max = 0;
        for(int i=0;i<n;i++){
            long v = fretList.get(i).remove();
            max = Math.max(max, v);
            heap.add(new Node(i, v));
        }

        long min = Long.MAX_VALUE;
        boolean flag = true;
        for(long init: allFrets){
            while(true){
                assert heap.peek() != null;
                if (!(heap.peek().val < init)) break;
                Node node = heap.remove();
                int id = node.id;

                Queue<Long> frets = fretList.get(id);
                while(!frets.isEmpty() && frets.peek() < init){
                    frets.remove();
                }

                if(frets.isEmpty()){
                    flag = false;
                    break;
                }

                long v = frets.remove();
                heap.add(new Node(id, v));
                max = Math.max(max, v);
            }

            if(!flag)
                break;

            min = Math.min(min, max - init);
        }

        System.out.println(min);
    }
}