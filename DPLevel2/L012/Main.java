package DPLevel2.L012;
import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int k = scn.nextInt();
        int r = scn.nextInt();
        int c = scn.nextInt();
        System.out.println(kglasswater(k, r, c));
	}

    
	public static int DistinctTransformations(String s, String t, int si, int ti) 
    {
        if(si==s.length())
        {
            if(ti<t.length())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if(ti==t.length())
        {
            return 1;
        }
        char c1= s.charAt(si);
        char c2= t.charAt(ti);
        int tw=0;
        if(c1!=c2)
        {
            tw=DistinctTransformations(s, t, si+1, ti);
        }
        else
        {
            int tw1= DistinctTransformations(s, t, si+1, ti);
            int tw2= DistinctTransformations(s, t, si+1, ti+1);
            tw= tw1+tw2;
        }
        return tw;
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    	
	public static int totaloffering(int[] height)
    {
        int larr[]= new int[height.length];
        larr[0]=1;
        for(int i=1;i<larr.length;i++)
        {
            if(height[i]>height[i-1])
            {
                larr[i]=larr[i-1]+1;
            }
            else
            {
                larr[i]=1;
            }
        }

        int rarr[]= new int[height.length];
        rarr[rarr.length-1]=1;
        for(int i=rarr.length-2;i>=0;i--)
        {
            if(height[i]>height[i+1])
            {
                rarr[i]=rarr[i+1]+1;
            }
            else
            {
                rarr[i]=1;
            }
        }

        int ans=0;
        for(int i=0;i<height.length;i++)
        {
            ans+=Math.max(larr[i],rarr[i]);
        }
        return ans;

	}

      ////////////////////////////////////////////////////////////////////////////////////////////////

      public static double kglasswater(int k, int r, int c) {
        double dp[][]= new double [k+1][k+1];
        dp[0][0]=k;
        for(int i=0;i<dp.length;i++)
        {
            for(int j=0;j<=i;j++)
            {
                if(dp[i][j]>1.0)
                {
                    double spare = dp[i][j]-1.0;
                    dp[i][j]=1.0;
                    dp[i+1][j]=spare/2.0;
                    dp[i+1][j+1]=spare/2.0;
                }
            }
        }
        return dp[r][c];
      }

      

}