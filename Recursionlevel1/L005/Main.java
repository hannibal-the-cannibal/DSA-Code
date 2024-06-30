package Recursionlevel1.L005;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        // int n = scn.nextInt();
        // int arr[]= new int[n];
        // for(int i=0;i<n;i++)
        // {
        //     arr[i]=scn.nextInt();
        // }
        // int tar= scn.nextInt();
        // printTargetSumSubsets(arr, 0, tar, "", 0);


        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // int[][] arr = new int[n][m];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         arr[i][j] = scn.nextInt();
        //     }
        // }
        // floodfill(arr, 0, 0, "", new boolean[n][m]);

        // int n = scn.nextInt();
        // printNQueens(new int[n][n], "", 0);

        int n = scn.nextInt();
        int r= scn.nextInt();
        int c= scn.nextInt();
        printKnightsTour(new int [n][n], r, c, 1);

    }

    
    public static void printTargetSumSubsets(int[] arr, int idx, int tar, String asf, int sos) {
        if(idx==arr.length)
        {
            if(sos==tar){
                System.out.println(asf);
            }
            return ;
        }

        printTargetSumSubsets(arr, idx+1, tar, asf+arr[idx]+ " - ", sos+arr[idx]);
        printTargetSumSubsets(arr, idx+1, tar, asf, sos);

        
    }
    public static void floodfill(int[][] maze, int sr, int sc, String asf, boolean visited[][]) {
        if(sr==maze.length-1 && sc==maze[0].length-1)
        {
            System.out.println(asf);
            return ;
        }
        if(sr<0 || sc<0 || sr>=maze.length ||sc>=maze[0].length || visited[sr][sc]==true || maze[sr][sc]==1)
        {
            return ;
        }
        
        visited[sr][sc]=true;
        floodfill(maze, sr-1, sc, asf+"t", visited);
        floodfill(maze, sr, sc-1, asf+"l", visited);
        floodfill(maze, sr+1, sc, asf+"d", visited);
        floodfill(maze, sr, sc+1, asf+'r', visited);
        visited[sr][sc]=false;
    
    }
    public static boolean issafe(int [][]chess, int row, int col)
    {
        for(int i=row-1,j=col; i>=0;i--)
        {
            if(chess[i][j]==1)
            {
                return false;
            }
        }
        for(int i=row-1,j=col-1;i>=0 && j>=0; i--,j--)
        {
            if(chess[i][j]==1)
            {
                return false;
            }
        }
        for(int i=row-1, j=col+1; i>=0 &&j<chess.length;i--,j++)
        {
            if(chess[i][j]==1)
            {
                return false;
            }
        }

        return true;
    }

    public static void printNQueens(int[][] chess, String asf, int row) {
        if(row==chess.length)
        {
            System.out.println(asf+".");
            return ;
        }
        for(int col=0;col<chess[0].length;col++)
        {
            if(issafe(chess,row,col))
            {
                chess[row][col]=1;
                printNQueens(chess, asf+row+" -> "+col+"  ,", row+1);
                chess[row][col]=0;
            }
        }

        
    }


    public static void printKnightsTour(int[][] chess, int r, int c, int move) {
        if(r<0|| c<0 || r>=chess.length|| c>=chess[0].length || chess[r][c]>0)
        {
            return;
        }
        else if(move==chess.length*chess[0].length-1)
        {
            chess[r][c]=move;
            displayBoard(chess);
            chess[r][c]=0;
            return;
        }

        chess[r][c]= move;
        printKnightsTour(chess, r-2, c+1, move+1);
        printKnightsTour(chess, r-1, c+2, move+1);
        printKnightsTour(chess, r+1, c+2, move+1);
        printKnightsTour(chess, r+2, c+1, move+1);
        printKnightsTour(chess, r+2, c-1, move+1);
        printKnightsTour(chess, r+1, c-2, move+1);
        printKnightsTour(chess, r-1, c-2, move+1);
        printKnightsTour(chess, r-2, c-1, move+1);
        chess[r][c]=0;
        
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}


