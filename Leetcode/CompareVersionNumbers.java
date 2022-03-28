package Leetcode;

import java.util.*;

public class CompareVersionNumbers {

    static void addZeros(List<Integer> list, int n){
        for(int i=0;i<n;i++)
            list.add(0);
    }

    static List<Integer> getVersionsList(String s){
        int n = s.length();
        List<Integer> vlist = new ArrayList<>();

        int j = 0;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '.'){
                String v = s.substring(j, i);
                vlist.add(Integer.parseInt(v));

                j = i+1;
            }
        }

        vlist.add(Integer.parseInt(s.substring(j)));

        return vlist;
    }

    public static int compareVersion(String version1, String version2) {

        List<Integer> vlist1 = getVersionsList(version1);
        List<Integer> vlist2 = getVersionsList(version2);

        if(vlist1.size() < vlist2.size()){
            addZeros(vlist1, vlist2.size() - vlist1.size());
        }
        else{
            addZeros(vlist2, vlist1.size() - vlist2.size());
        }

        for(int i=0;i<vlist1.size();i++){
            int diff = vlist1.get(i) - vlist2.get(i);
            if(diff < 0)
                return -1;
            else if(diff > 0)
                return 1;
        }

        return 0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String version1 = sc.next();
        String version2 = sc.next();

        System.out.println(compareVersion(version1, version2));
    }

}
