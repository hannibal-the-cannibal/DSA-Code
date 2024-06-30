package DPLevel2.L010;
import java.io.*;
import java.util.*;

public class Main {

 
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = scn.nextInt();
		}
		optimalStrategy(a);
  }

  public static int subarraywithkelement(int[] arr, int k)
   {
      int csum=arr[0];
      int ans=0;
      int maxsumarr[]= new int[arr.length];
      maxsumarr[0]=csum;
      for(int i=0;i<arr.length;i++)
      {
        if(csum>0)
        {
            csum+=arr[i];
        }
        else
        {
            csum=arr[i];
        }
        maxsumarr[i]=csum;
      }

      int exactk=0;
      for(int i=0;i<k;i++)
      {
        exactk=exactk+arr[i];
      }

      if(exactk>ans)
      {
        ans=exactk;
      }

      for(int i=k;i<arr.length;i++)
      {
        exactk=exactk+arr[i]-arr[i-k];

        if(exactk>ans)
        {
            ans=exactk;
        }

        int morethank= maxsumarr[i-k]+exactk;
        if(morethank>ans)
        {
            ans=morethank;
        }
      }

      return ans;

   }
   /////////////////////////////////////////////////////////////////////////////////////////////

   public static void optimalStrategy(int[] arr) 
   {
    int n=arr.length;
     int dp[][]= new int [n][n];
     for(int g=0;g<dp.length;g++)
     {
        for(int i=0,j=g;j<dp.length;j++,i++)
        {
            if(g==0)
            {
                dp[i][j]=arr[i];
            }
            if(g==1)
            {
                dp[i][j]=Math.max(arr[i],arr[j]);
            }
            else
            {
                int v1 = arr[i] + Math.min((i + 2 <= j) ? dp[i + 2][j] : 0, (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0);
                int v2 = arr[j] + Math.min((i <= j - 2) ? dp[i][j - 2] : 0, (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0);
                dp[i][j] = Math.max(v1, v2);
            }
        }
     }
     System.out.println(dp[0][n-1]);
   }


}