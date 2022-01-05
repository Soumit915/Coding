import java.util.*;

public class NQueens {
    static class Queens
    {
        int[][] chessBoard;
        int numOfQueens;
        Queens(int n)
        {
            this.numOfQueens = n;
            this.chessBoard = new int[n][n];
        }
        public boolean findSol(int row)
        {
            if(row==numOfQueens)
                return true;
            for(int i=0;i<numOfQueens;i++)
            {
                if(isValidPos(row, i))
                {
                    //System.out.println(row+" "+i);
                    chessBoard[row][i] = 1;
                    if(findSol(row+1))
                    {
                        return true;
                    }

                    chessBoard[row][i] = 0;
                }
            }
            return false;
        }
        public boolean isValidPos(int row, int col)
        {
            for(int i=0;i<row;i++)  //Checking for the vertical or the column
            {
                if(chessBoard[i][col]==1)
                    return false;
            }

            for(int i=0;i<row;i++)
            {
                int c = Math.abs(row-i);
                if(col-c>=0 && chessBoard[i][col-c] == 1)
                    return false;
            }

            for(int i=0;i<row;i++)
            {
                int c = Math.abs(row-i);
                if(col+c<numOfQueens && chessBoard[i][col+c] == 1)
                    return false;
            }

            return true;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of queens : ");
        int n = sc.nextInt();

        Queens q = new Queens(n);
        boolean ans = q.findSol(0);
        if(ans)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    System.out.print(q.chessBoard[i][j]);
                }
                System.out.println();
            }
        }
        else
        {
            System.out.println("No solution");
        }

    }
}
