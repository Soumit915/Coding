package Codeforces;

import java.util.*;

public class StringReversal{

    static int getNextPowerOf2(int n){
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>25);

        return n+1;
    }

    static void update(int[] segTree, int si, int start, int end, int l, int r){
        //no overlap
        if(l > end || r < start)
            return;

        //total overlap
        if(l >= start && r <= end){
            segTree[si] += 1;
            return;
        }

        //partial overlap
        int mid = (l + r) / 2;
        segTree[2 * si + 1] += segTree[si];
        segTree[2 * si + 2] += segTree[si];
        segTree[si] = 0;

        update(segTree, 2*si + 1, start, end, l, mid);
        update(segTree, 2*si + 2, start, end, mid+1, r);
    }

    static int query(int[] segTree, int si, int index, int l, int r){
        if(l == r){
            return segTree[si];
        }

        int mid = (l + r) / 2;
        segTree[2 * si + 1] += segTree[si];
        segTree[2 * si + 2] += segTree[si];
        segTree[si] = 0;

        if(index <= mid)
            return query(segTree, 2*si + 1, index, l, mid);
        else return query(segTree, 2*si + 2, index, mid+1, r);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        String rev = reverse(s);

        HashMap<Character, Queue<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);

            Queue<Integer> li = map.getOrDefault(ch, new LinkedList<>());
            li.add(i);

            map.put(ch, li);
        }

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] segTree = new int[sn];

        long ans = 0;
        for(int i=0;i<n;i++){
            char ch = rev.charAt(i);

            int index = map.get(ch).remove();

            int si = query(segTree, 0, index, 0, n-1);
            update(segTree, 0, 0, index, 0, n-1);

            long diff = index +si - i;
            ans += diff;
        }

        System.out.println(ans);
    }

    static String reverse(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}