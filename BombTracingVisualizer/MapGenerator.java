package BombTracingVisualizer;

import java.io.*;

public class MapGenerator {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("Output.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            int nr=42;
            int nc=165;
            char[][] mat = new char[nr][nc];
            for(int i=0;i<nr;i++)
            {
                for(int j=0;j<nc;j++)
                {
                    if(j==0 || j==nc-1)
                        mat[i][j] = '|';
                    else if(i==0 || i==nr-1)
                        mat[i][j] = '=';
                    else
                        mat[i][j] = ' ';
                }
            }

            //Putting bombs
            for(int i=0;i<15;i++)
            {
                int row = (int) (Math.random()*nr);
                int col = (int) (Math.random()*nc);
                while(row==0 || row==nr-1 || col==0 || col==nc-1)
                {
                    row = (int) (Math.random()*nr);
                    col = (int) (Math.random()*nc);
                }
                mat[row][col] = 'B';
            }

            //Putting police
            for(int i=0;i<15;i++)
            {
                int row = (int) (Math.random()*nr);
                int col = (int) (Math.random()*nc);
                while(row==0 || row==nr-1 || col==0 || col==nc-1)
                {
                    row = (int) (Math.random()*nr);
                    col = (int) (Math.random()*nc);
                }
                mat[row][col] = 'P';
            }

            //Putting important places
            for(int i=0;i<15;i++)
            {
                int row = (int) (Math.random()*nr);
                int col = (int) (Math.random()*nc);
                while(row==0 || row==nr-1 || col==0 || col==nc-1)
                {
                    row = (int) (Math.random()*nr);
                    col = (int) (Math.random()*nc);
                }
                mat[row][col] = 'I';
                System.out.println(row+" "+col);
            }

            //Horizontal obstruction
            for(int i=0;i<25;i++)
            {
                int row = (int) (Math.random()*nr);
                while(row==0 || row==nr-1)
                    row = (int) (Math.random()*nr);
                int cinit = (int) (Math.random()*nc);
                cinit = Math.max(cinit,1);
                cinit = Math.min(cinit,nc-2);
                int cfin = (int) (Math.random()*nc);
                cfin = Math.min(cfin, nc-2);
                cfin = Math.max(cfin,1);
                if(cinit>cfin)
                    cinit = (cinit+cfin)-(cfin=cinit);
                for(int j=cinit;j<=cfin;j++)
                {
                    mat[row][j] = '_';
                }
            }

            //Vertical obstruction
            for(int i=0;i<30;i++)
            {
                int row = (int) (Math.random()*nc);
                while(row==0 || row==nc-1)
                    row = (int) (Math.random()*nc);
                int cinit = (int) (Math.random()*nr);
                cinit = Math.max(cinit,1);
                cinit = Math.min(cinit,nr-2);
                int cfin = (int) (Math.random()*nr);
                cfin = Math.min(cfin, nr-2);
                cfin = Math.max(cfin,1);
                if(cinit>cfin)
                    cinit = (cinit+cfin)-(cfin=cinit);
                for(int j=cinit;j<=cfin;j++)
                {
                    mat[j][row] = '|';
                }
            }

            for(int i=0;i<nr;i++)
            {
                for(int j=0;j<nc;j++)
                {
                    pw.print(mat[i][j]);
                }
                pw.println();
            }

            pw.close();
            bw.close();
            fw.close();

            System.out.println("Test");
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}
