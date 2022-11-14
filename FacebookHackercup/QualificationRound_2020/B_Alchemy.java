package FacebookHackercup.QualificationRound_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class B_Alchemy {
    public static boolean isValid(String s)
    {
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        set.add(s.charAt(1));
        set.add(s.charAt(2));

        return set.size()==2;
    }
    public static char deduce(String s)
    {
        int a=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='A')
                a++;
        }

        return (a==2)?'A':'B';
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                String s = sc.next();

                int i=2;
                while (s.length()>1 && i<s.length())
                {

                    String subs = s.substring(i-2, i+1);
                    if(isValid(subs))
                    {
                        char c = deduce(subs);
                        s = s.substring(0, i - 2) +
                                c +
                                s.substring(i + 1);
                        i--;
                    }
                    else
                        i++;
                    i = Math.max(2, i);
                }

                pw.print("Case #"+testi+": ");
                if(s.length()==1)
                    pw.println("Y");
                else pw.println("N");
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}