package FileIO;

import java.io.*;
import java.util.*;
public class FileIo{
    public static int[] getInput(String s){
        Scanner sc=new Scanner(s);
        sc.useDelimiter("#");
        ArrayList<Integer> arlist= new ArrayList<>();
        while(sc.hasNext()){
            String str=sc.next();
            Scanner ob=new Scanner(str);
            ob.useDelimiter("\\s*");
            StringBuilder sb=new StringBuilder();
            while(ob.hasNext()){
                sb.append(ob.next());
            }
            arlist.add(Integer.parseInt(sb.toString()));
        }
        int[] arr =new int[arlist.size()];
        for(int i=0;i<arlist.size();i++){
            arr[i]=arlist.get(i);
        }
        return arr;
    }
    public static void main(String[] args) throws IOException{
        FileReader fr=new FileReader("file3.txt");
        BufferedReader br=new BufferedReader(fr);
        String a;
        long sum=0;
        while((a=br.readLine())!=null){
            int[] nums =getInput(a);
            long linesum=0;
            for(int i : nums){
                linesum+=i;
            }
            System.out.println(linesum);
            sum+=linesum;
        }
        System.out.println(sum);
        br.close();
        fr.close();
    }
}