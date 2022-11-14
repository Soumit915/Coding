import java.util.*;

public class ZAlgorithm {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text: ");
        String text = sc.nextLine();

        System.out.println("Enter the pattern: ");
        String pattern = sc.nextLine();

        String pt = pattern+"$"+text;
        int ptl = pt.length();
        int[] llp = new int[ptl];
        int right;
        llp[0] = 0;
        int count = 0;
        int ind;
        for(int i=1;i<ptl;i++)
        {
            ind = i+count;
            boolean flag = false;
            while(ind<ptl && (pt.charAt(ind) == pt.charAt(count)))
            {
                count++;
                ind++;
            }
            llp[i] = count;
            right = ind-1;
            for(int j=i+1;j<=right && j<ptl;j++)
            {
                if(llp[j-i]==0)
                {
                    llp[j] = 0;
                }
                else if(llp[j-i]+j>=right)
                {
                    count = llp[j-i];
                    i=j-1;
                    flag = true;
                    break;
                }
                else
                {
                    llp[j] = llp[j-i];
                }
            }

            if(!flag)
            {
                count=0;
            }
        }

        for(int i=0;i<ptl;i++)
        {
            if(llp[i]==pattern.length())
                System.out.println("Pattern found at position: "+(i-pattern.length()));
        }
    }
}
