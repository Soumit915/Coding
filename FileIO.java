import java.util.*;
import java.io.*;

public class FileIO
{
    /*public static int[] getInput1(String s)
    {
        ArrayList<Integer> arlist = new ArrayList<>();
        s += "#";
        int last = 0;

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='#')
            {
                String nums = s.substring(last, i);
                if(nums.length()==0)
                {
                    last = i+1;
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                for(int j=0;j<nums.length();j++)
                {
                    if(nums.charAt(j)!=' ' && nums.charAt(j)!='#')
                        sb.append(nums.charAt(j));
                }

                if(sb.toString().length()==0)
                    continue;

                arlist.add(Integer.parseInt(sb.toString()));
                last = i+1;
            }
        }

        int[] arr = new int[arlist.size()];
        for(int i=0;i<arlist.size();i++)
        {
            arr[i] = arlist.get(i);
        }
        return arr;
    }*/
    public static int[] getInput(String s)
    {
        Scanner scan = new Scanner(s);
        scan.useDelimiter("#");

        ArrayList<Integer> arlist = new ArrayList<>();
        while(scan.hasNext())
        {
            String someworddigit = scan.next();

            Scanner sc = new Scanner(someworddigit);
            sc.useDelimiter("\\s*");

            StringBuilder sb= new StringBuilder();
            while(sc.hasNext())
            {
                sb.append(sc.next());
            }

            if(sb.toString().length()==0)
                continue;

            arlist.add(Integer.parseInt(sb.toString()));
        }

        int[] arr  = new int[arlist.size()];
        for(int i=0;i<arlist.size();i++)
        {
            arr[i] = arlist.get(i);
        }
        return arr;
    }
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        String file = sc.nextLine();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String a ;
        long sum = 0;
        while((a = br.readLine())!=null)
        {
            int[] arr = getInput(a);
            /*int[] arr1 = getInput1(a);

            if(arr.length!=arr1.length)
            {
                System.out.println("Wrong Answer");
                System.exit(0);
            }

            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]!=arr1[i])
                {
                    System.out.println("Wrong Answer");
                    System.exit(0);
                }
            }*/

            long linesum = 0;
            for(int i: arr)
                linesum += i;

            System.out.println(linesum);
            sum += linesum;
        }

        System.out.println(sum);

        //System.out.println("Correct Answer");
        br.close();
        fr.close();
    }
}
