package Leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MaxAndSum {

    static int max = 0;
    static Map<String, Integer> map;

    static String getString(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<arr.length;i++){
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    static int get(int[] nums, int[] hash, int sum, int index){
        if(index >= nums.length){
            max = Math.max(max, sum);
            return 0;
        }

        if(map.containsKey(getString(hash))){
            max = Math.max(max, map.get(getString(hash)) + sum);
            return map.get(getString(hash));
        }

        int cmax = 0;
        for(int i=1;i<hash.length;i++){
            if(hash[i] == 2){
                continue;
            }

            hash[i]++;
            sum = sum + (i & nums[index]);

            int val = get(nums, hash, sum, index+1);
            cmax = Math.max(cmax, val + (nums[index] & i));

            sum = sum - (i & nums[index]);
            hash[i]--;
        }

        map.put(getString(hash), cmax);
        return cmax;
    }

    public static int maximumANDSum(int[] nums, int numSlots) {
        int[] hash = new int[numSlots+1];
        map = new HashMap<>();
        max = 0;
        get(nums, hash, 0, 0);

        return max;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int slots = sc.nextInt();

        System.out.println(maximumANDSum(arr, slots));
    }

}
