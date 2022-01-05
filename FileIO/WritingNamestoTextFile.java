package FileIO;

import java.io.*;
import java.util.*;
public class WritingNamestoTextFile
{
    public static void main(final String[] args) throws IOException
    {
        //try {
            final Scanner sc = new Scanner(System.in);
            final FileWriter fw = new FileWriter("Student.txt");
            final BufferedWriter bw = new BufferedWriter(fw);
            final PrintWriter pw = new PrintWriter(bw);

            String a;
            int i;
            for(i=1;i<=5;i++)
            {
                System.out.print("Enter the "+i+"th name : ");
                a = sc.nextLine();
                pw.println(a);
            }

            pw.close();
            bw.close();
            fw.close();
        //}
        //catch (IOException e)
        //{
       //     System.err.println(e);
        //}
    }
}
