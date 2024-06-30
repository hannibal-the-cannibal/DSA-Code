package Recursionlevel2.L003;
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);

    char[][] arr = new char[10][10];
    for (int i = 0 ; i < arr.length; i++) {
      String str = scn.next();
      arr[i] = str.toCharArray();
    }
    int n = scn.nextInt();
    String[] words = new String[n];
    for (int i = 0 ; i  < words.length; i++) {
      words[i] = scn.next();
    }
    solution(arr, words, 0);


    // int[][] arr = new int[9][9];
    // for (int i = 0; i < 9; i++) {
    //   for (int j = 0; j < 9; j++) {
    //     arr[i][j] = scn.nextInt();
    //   }
    // }

    // solveSudoku(arr, 0, 0);
  }

  public static void display(int[][] board){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void solveSudoku(int[][] board, int i, int j)
  {
     if(i==board.length)
     {
        display(board);
        return ;
     }

     int ni=0;
     int nj=0;

     if(j==board[0].length-1)
     {
        ni=i+1;
        nj=0;
     }
     else
     {
        ni=i;
        nj=j+1;
     }

     if(board[i][j]!=0)
     {
        solveSudoku(board,ni,nj);
     }
     else
     {
        for(int po=1;po<=9;po++)
        {
            if(issafe(board,i,j,po))
            {
                board[i][j]=po;
                solveSudoku(board,ni,nj);
                board[i][j]=0;//backtrack 
            }
        }
     }
  }

  public static boolean issafe(int [][]board, int x, int y, int val)
  {
    for(int j=0;j<board[0].length;j++)
    {
        if(board[x][j]==val)
        {
            return false;
        }
    }

    for(int i=0;i<board.length;i++)
    {
        if(board[i][y]==val)
        {
            return false;
        }
    }

    int smi=x/3 *3;
    int smj=y/3 *3;

    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            if(board[smi+i][smj+j]==val)
            {
                return false;
            }
        }
    }

    return true;
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////

  public static void print(char[][] arr) {
    for (int i = 0 ; i < arr.length; i++) {
      for (int j = 0 ; j < arr.length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }

  }


  public static void solution(char[][] arr, String[] words, int vidx) 
  {
    if(vidx==words.length)
    {
      print(arr);
      return ;
    }
    String word= words[vidx];
    for(int i=0;i<arr.length;i++)
    {
      for(int j=0;j<arr[0].length;j++)
      {
        if(arr[i][j]=='-' || arr[i][j]==word.charAt(0))
        {
          if(canplacehorizontally(arr,word,i,j))
          {
            boolean weplaced[]= placewordhorizontally(arr,word,i,j);
            solution(arr, words, vidx+1);
            unplacehorizontally(arr,weplaced, i, j);//backtrack
          }

          if(canplacevertically(arr,word,i,j))
          {
            boolean weplaced[]= placewordvertically(arr,word,i,j);
            solution(arr, words, vidx+1);
            unplacevertically(arr,weplaced, i, j);//backtrack
          }
        }
      }
    }
  }


  public static boolean canplacehorizontally( char[][]arr, String word, int i, int j)
  {
    if(j-1>=0 && arr[i][j-1]!='+') //left side check
    {
      return false;
    }
    else if(j+word.length()<arr[0].length && arr[i][j+word.length()]!='+') //right side check
    {
      return false;
    }
    for(int jj=0;jj<word.length();jj++) 
    {
      if(j+jj>arr[0].length) // checking if word has length more than board 
      {
        return false;
      }

      if(arr[i][j+jj]=='-' || arr[i][j+jj]==word.charAt(jj))// checking if there is a blank space or the char which our word has
      {
        continue;
      }
      else
      {
        return false;
      }
    }
    return true;
  }

  public static boolean canplacevertically( char[][]arr, String word, int i, int j)
  {
    if(i-1>=0 && arr[i-1][j]!='+')
    {
      return false;
    }
    else if(i+word.length()<arr[0].length && arr[i+word.length()][j]!='+')
    {
      return false;
    }
    for(int ii=0;ii<word.length();ii++)
    {
      if(i+ii>arr[0].length)
      {
        return false;
      }

      if(arr[i+ii][j]=='-' || arr[i+ii][j]==word.charAt(ii))
      {
        continue;
      }
      else
      {
        return false;
      }
    }
    return true;
  }

  public static boolean[] placewordhorizontally( char[][]arr, String word, int i, int j )
  {
    boolean[] weplaced= new boolean[word.length()]; // already checked if we can place it or not 

    for(int jj=0;jj<word.length();jj++)
    {
      if(arr[i][j+jj]=='-') // checking if its blank then we are going to place our char
      {
        arr[i][j+jj]=word.charAt(jj);
        weplaced[jj]=true; // tracking our char 
      }
    }
    return weplaced;
  }


  public static void unplacehorizontally(char[][]arr, boolean[] weplaced, int i, int j)
  {
    for(int jj=0;jj< weplaced.length;jj++)
    {
      if(weplaced[jj]==true) // our char deletion while backtracking 
      {
        arr[i][j+jj]='-';
      }
    }
  }

  public static void unplacevertically(char[][]arr, boolean[] weplaced, int i, int j)
  {
    for(int ii=0;ii<weplaced.length;ii++)
    {
      if(weplaced[ii]==true)
      {
        arr[i+ii][j]='-';
      }
    }
  }

  public static boolean[] placewordvertically( char[][]arr, String word, int i, int j )
  {
    boolean[] weplaced= new boolean[word.length()];

    for(int ii=0;ii<word.length();ii++)
    {
      if(arr[i+ii][j]=='-')
      {
        arr[i+ii][j]=word.charAt(ii);
        weplaced[ii]=true;
      }
    }
    return weplaced;
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////

}
