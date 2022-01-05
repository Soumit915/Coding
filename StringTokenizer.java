import java.util.*;

public class StringTokenizer
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence : ");
        String sentence = sc.nextLine();

        java.util.StringTokenizer st = new java.util.StringTokenizer(sentence,", ");
        System.out.println(st.countTokens());
        int i;
        int l = st.countTokens();
        for(i=0;i<l;i++)
        {
            System.out.println(st.nextToken());
        }
    }
}
