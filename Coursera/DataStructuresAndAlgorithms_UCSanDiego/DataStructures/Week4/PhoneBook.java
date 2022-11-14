package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.util.HashMap;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int q = sc.nextInt();
        HashMap<Long, String> hash = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++)
        {
            String type = sc.next();

            if(type.equals("add"))
            {
                long key = sc.nextLong();
                String val = sc.next();

                hash.put(key, val);
            }
            else if(type.equals("find"))
            {
                long key = sc.nextLong();
                sb.append(hash.getOrDefault(key, "not found")).append("\n");
            }
            else
            {
                long key = sc.nextLong();
                hash.remove(key);
            }
        }

        System.out.println(sb);
    }
}
