package FileIO;

import java.util.*;
import java.io.*;
public class WritetoBinaryFiles {
    public static void main(final String[] args) throws IOException
    {
        final Scanner sc = new Scanner(System.in);

        final FileOutputStream fos = new FileOutputStream("Student.dat");
        final DataOutputStream dos = new DataOutputStream(fos);

        String a;
        int i;
        for(i=1;i<=5;i++)
        {
            a = sc.nextLine();
            dos.writeBytes(a);
            dos.writeBytes("\n");
        }

        dos.close();
        fos.close();
    }
}
