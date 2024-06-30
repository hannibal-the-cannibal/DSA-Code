package Recursionlevel2.L002;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);

    int n = scn.nextInt();
		int m = scn.nextInt();
		int[][] arr = new int[m][n];
		for(int i = 0; i < arr.length; i++){
			for(int j = 0 ; j  < arr[0].length; j++){
				arr[i][j] = scn.nextInt();
			}
		}
		
		System.out.println(getMaxGold(arr));



    // int n = scn.nextInt();
    // for(int i=1;i<=9;i++)
    // {
    //     lexi(i,n);
    // }

    // int n = scn.nextInt();
    // int k = scn.nextInt();
    // System.out.println(joseph(n,k));
  }

  public static int joseph(int n, int k)
  {
    if(n==1)
    {
        return 0;
    }
    int x= joseph(n-1, k);
    int y= (x+k)%n;
    return y;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static void lexi(int i, int n)
  {
    if(i>n)
    {
        return ;
    }
    System.out.println(i);
    for(int j=0;j<=9;j++)
    {
        lexi(10*i +j, n);
    }
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static int max = 0;
	public static int getMaxGold(int[][] arr)
    {
        boolean visited[][]= new boolean[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                if(arr[i][j]!=0 && visited[i][j]==false)
                {
                    
                    int compgold= travelandcollect(arr, visited,i,j);
                    if(compgold>max)
                    {
                        max=compgold;
                    }
                }
            }
        }
        return max;
    }

    public static int  travelandcollect(int arr[][], boolean visited[][], int i, int j)
    {
        if(i<0 || i>=arr.length || j<0 || j>=arr[0].length || arr[i][j]==0 || visited[i][j]==true)
        {
            return 0;
        }
        visited[i][j]=true;
        int sum=arr[i][j];
        int a1=travelandcollect(arr, visited, i-1, j); //checking which one direction will bring max sum 
        int a2=travelandcollect(arr, visited, i, j+1);
        int a3=travelandcollect(arr, visited, i, j-1);
        int a4=travelandcollect(arr, visited, i+1, j);
        visited[i][j]=false; //backtrack 
        return Math.max(Math.max(Math.max(a1,a2),a3),a4)+sum;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////


  

}