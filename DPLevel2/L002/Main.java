package DPLevel2.L002;
import java.io.*;
import java.util.*;

public class Main {

   private static class Pair {
      String psf;
      int i;
      int j;

      public Pair(String psf, int i, int j) {
         this.psf = psf;
         this.i = i;
         this.j = j;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int m = Integer.parseInt(br.readLine());
      int[][] arr = new int[n][m];

      for (int i = 0; i < n; i++) {
         String str = br.readLine();
         for (int j = 0; j < m; j++) {
            arr[i][j] = Integer.parseInt(str.split(" ")[j]);
         }
      } 
    //   mincostpath(arr);
    maxgoldpath(arr);



   }

   public static void mincostpath(int arr[][])
   {
    int mem[][]= new int[arr.length][arr[0].length];
      for(int i=mem.length-1;i>=0;i--)
      {
        for(int j=mem[0].length-1;j>=0;j--)
        {
            if(i==mem.length-1 && j==mem[0].length-1)
            {
                mem[i][j]=arr[i][j];
            }
            else if(i==mem.length-1)
            {
                mem[i][j]= arr[i][j]+mem[i][j+1];
            }
            else if(j==mem[0].length-1)
            {
                mem[i][j]=arr[i][j]+mem[i+1][j];
            }
            else
            {
                mem[i][j]=arr[i][j]+Math.min(mem[i+1][j],mem[i][j+1]);
            }
        }
      }
      System.out.println(mem[0][0]);

      ArrayDeque<Pair> qu= new ArrayDeque<>();
      qu.add(new Pair("", 0, 0));
      while(qu.size()!=0)
      {
        Pair rem= qu.remove();

        if(rem.i==mem.length-1 && rem.j==mem[0].length-1)
        {
            System.out.println(rem.psf);
        }
        else if(rem.i==mem.length-1)
        {
            qu.add(new Pair(rem.psf+"H",rem.i, rem.j+1));
        }
        else if(rem.j==mem[0].length-1)
        {
            qu.add(new Pair(rem.psf+"V",rem.i+1, rem.j));
        }
        else
        {
            if(mem[rem.i][rem.j+1]> mem[rem.i+1][rem.j]) //vertical one is less
            {
                qu.add(new Pair(rem.psf+"V",rem.i+1, rem.j));
            }
            else if(mem[rem.i][rem.j+1]< mem[rem.i+1][rem.j]) //Horizontal one is less
            {
                qu.add(new Pair(rem.psf+"H",rem.i, rem.j+1));
            }
            else //both equal
            {
                qu.add(new Pair(rem.psf+"V",rem.i+1, rem.j));
                qu.add(new Pair(rem.psf+"H",rem.i, rem.j+1));
            }
        }
      }
   }
   ////////////////////////////////////////////////////////////////////////////////////////

   public static void maxgoldpath(int arr[][])
   {
     int mem[][]= new int[arr.length][arr[0].length];
     for(int j=arr[0].length-1;j>=0;j--)
     {
        for(int i=arr.length-1;i>=0;i--)
        {
            if(j==arr[0].length-1)
            {
                mem[i][j]=arr[i][j];
            }
            else if(i==0)
            {
                mem[i][j]=arr[i][j]+Math.max(mem[i][j+1],mem[i+1][j+1]);
            }
            else if(i==arr.length-1)
            {
                mem[i][j]=arr[i][j]+Math.max(mem[i][j+1],mem[i-1][j+1]);
            }
            else
            {
                mem[i][j]=arr[i][j]+ Math.max(Math.max(mem[i][j+1],mem[i-1][j+1]),mem[i+1][j+1]);
            }
        }
     }
     int max= Integer.MIN_VALUE;
     for(int i=0;i<mem.length;i++)
     {
        if(mem[i][0]>max)
        {
            max=mem[i][0];
        }
     }
     System.out.println(max);

     ArrayDeque<Pair> qu= new ArrayDeque<>();
     for(int i=0;i<mem.length;i++)
     {
        if(mem[i][0]==max)
        {
            qu.add(new Pair(i +" ",i,0));
        }
     }

     while(qu.size()!=0)
     {
        Pair rem = qu.remove();

        if(rem.j==arr[0].length-1)
        {
            System.out.println(rem.psf);
        }
        else if(rem.i==0)
        {
            int g=Math.max(mem[rem.i][rem.j+1],mem[rem.i+1][rem.j]);

            if(g==mem[rem.i][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d2",rem.i,rem.j+1));
            }
            if(g==mem[rem.i+1][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d3",rem.i+1,rem.j+1));
            }
        }
        else if(rem.i==arr.length-1)
        {
            int g=Math.max(mem[rem.i][rem.j+1],mem[rem.i-1][rem.j]);

            if(g==mem[rem.i][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d2",rem.i,rem.j+1));
            }
            if(g==mem[rem.i-1][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d1",rem.i-1,rem.j+1));
            }
        }
        else
        {
            int g=Math.max(Math.max(mem[rem.i][rem.j+1],mem[rem.i+1][rem.j]),mem[rem.i-1][rem.j+1]);

            if(g==mem[rem.i-1][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d1",rem.i-1,rem.j+1));
            }
            if(g==mem[rem.i][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d2",rem.i,rem.j+1));
            }
            if(g==mem[rem.i+1][rem.j+1])
            {
                qu.add(new Pair(rem.psf+" d3",rem.i+1,rem.j+1));
            }
        }
     }
   }


}