package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.util.*;

public class HashingWithChains {
    static long p = (long) 1e9+7;
    static long getHash(String s)
    {
        long hashVal = 0;
        long x = 263;
        for(int i=s.length()-1;i>=0;i--)
        {
            long ch = s.charAt(i);
            hashVal = ((hashVal*x)%p + ch)%p;
        }
        return hashVal;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        ArrayList<LinkedList<Long>> bucketlist = new ArrayList<>(m);
        for(int i=0;i<m;i++)
            bucketlist.add(new LinkedList<>());

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        HashMap<Long, String> hash = new HashMap<>();
        while(q-->0)
        {
            String type = sc.next();
            switch (type) {
                case "add": {
                    String s = sc.next();
                    long val = getHash(s);
                    if(!hash.containsKey(val))
                    {
                        hash.put(val, s);

                        int bucketmod = (int) (val % m);
                        LinkedList<Long> bucket = bucketlist.get(bucketmod);
                        bucket.offerFirst(val);
                    }
                    break;
                }
                case "del": {
                    String s = sc.next();
                    long val = getHash(s);
                    hash.remove(val);

                    int bucketmod = (int) (val % m);
                    LinkedList<Long> bucket = bucketlist.get(bucketmod);
                    bucket.removeFirstOccurrence(val);
                    break;
                }
                case "find": {
                    String s = sc.next();
                    long val = getHash(s);
                    if (hash.containsKey(val)) {
                        sb.append("yes\n");
                    } else {
                        sb.append("no\n");
                    }
                    break;
                }
                default: {
                    int index = sc.nextInt();
                    LinkedList<Long> bucket = bucketlist.get(index);

                    for (Long aLong : bucket) sb.append(hash.get(aLong)).append(" ");
                    sb.append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}
