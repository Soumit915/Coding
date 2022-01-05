package FileIO;

import java.util.*;
import java.io.*;
public class ReadingfromBinaryFiles {
    public static void main(final String[] args) throws IOException
    {
        final FileInputStream fis = new FileInputStream("Student.dat");
        final DataInputStream dis = new DataInputStream(fis);

        boolean eof = false;
        String a;
        while(!eof)
        {
            try{
                a = dis.readLine();
                if(a == null){
                    eof = true;
                    break;
                }
                System.out.println(a);
            }
            catch(final Exception e)
            {
                eof = true;
            }
        }
    }
}
