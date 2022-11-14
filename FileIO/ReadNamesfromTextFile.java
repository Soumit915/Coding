package FileIO;

import java.util.*;
import java.io.*;
public class ReadNamesfromTextFile {
    public static void main(final String[] args) throws IOException {
        final Scanner sc = new Scanner(System.in);
        final FileReader fr = new FileReader("Student.txt");
        final BufferedReader br = new BufferedReader(fr);

        String a;
        while((a = br.readLine()) != null)
        {
            System.out.println(a);
        }

        br.close();
        fr.close();
    }
}
