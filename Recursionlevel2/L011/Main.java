package Recursionlevel2.L011;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        boolean[][] chess = new boolean[n][n];

        // boolean[] queens = new boolean[n];

        
        
        // queensCombinations1(0, n, 0, 0, "");
        // queensPermutations1(0, n, chess);
        // queensPermutations2(0, n, 0, 0, "", queens);
        // nqueenscombination(0, n, chess, -1);

    }

    
    public static void queensCombinations1(int qpsf, int tq, int row, int col, String asf)
    {
        if(row==tq)
        {
            if(qpsf==tq)
            {
                System.out.println(asf);
            }
            return ;
        }
     
        int nr=0;
        int nc=0;
        String yasf="";
        String nasf="";
        if(col==tq-1)
        {
            nr=row+1;
            nc=0;
            yasf=asf+"q\n";
            nasf=asf+"-\n";
        }
        else
        {
            nr=row;
            nc=col+1;
            yasf=asf+"q";
            nasf=asf+"-";
        }

        queensCombinations1(qpsf+1,tq, nr, nc, yasf); //including
        queensCombinations1(qpsf, tq, nr, nc, nasf); // excluding 
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public static void queensPermutations1(int qpsf, int tq, int[][] chess)
    {
        if(qpsf==tq)
        {
            for(int i=0;i<chess.length;i++)
            {
                for(int j=0;j<chess[0].length;j++)
                {
                    if(chess[i][j]==0)
                    {
                        System.out.print("-\t");
                    }
                    else
                    {
                        System.out.print("q"+chess[i][j]+"\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return ;
        }
        for(int i=0;i<chess.length;i++)
        {
            for(int j=0;j<chess[0].length;j++)
            {
                if(chess[i][j]==0)
                {
                    chess[i][j]=qpsf+1;
                    queensPermutations1(qpsf+1, tq, chess);
                    chess[i][j]=0; //backtrack 
                }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void queensPermutations2(int qpsf, int tq, int row, int col, String asf, boolean[] queens)
     {
        if(row==tq)
        {
            if(qpsf==tq)
            {
                System.out.println(asf);
            }
            return ;
        }
     
        int nr=0;
        int nc=0;
        char sep= '\0';
        
        if(col==tq-1)
        {
            nr=row+1;
            nc=0;
            sep='\n';
            
        }
        else
        {
            nr=row;
            nc=col+1;
            sep='\t';
            
        }

        for(int i=0;i<queens.length;i++)
        {
            if(queens[i]==false)
            {
                queens[i]=true;
                queensPermutations2(qpsf+1, tq, nr, nc, asf+"q"+(i+1)+sep, queens); //include
                queens[i]=false;
            }
        }
        queensPermutations2(qpsf, tq, nr, nc, asf+"-"+sep, queens); //exclude 
     }

     //////////////////////////////////////////////////////////////////////////////////////////////////

     public static void nqueenscombination(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] == false && IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueenscombination(qpsf + 1, tq, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) 
    {
        for(int i=row,j=col; i>=0;i--)
        {
            if(chess[i][j])
            {
                return false;
            }
        }

        for(int i=row,j=col; j>=0;j--)
        {
            if(chess[i][j])
            {
                return false;
            }
        }

        for(int i=row,j=col; i>=0 && j>=0 ; i--,j--)
        {
            if(chess[i][j])
            {
                return false;
            }
        }

        for(int i=row,j=col; i>=0 && j<chess[0].length; i--,j++)
        {
            if(chess[i][j])
            {
                return false;
            }
        }

        return true;
    }
     
     /////////////////////////////////////////////////////////////////////////////////////////////////

}