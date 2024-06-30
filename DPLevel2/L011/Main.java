package DPLevel2.L011;
import java.util.Scanner;
public class Main{
   
    public static void input(int []arr,Scanner scn){
        for(int i = 0;i<arr.length;i++){
            arr[i] = scn.nextInt();
        }
    }
    public static void main(String []args){
        Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < arr.length; i++){
			arr[i] = scn.nextInt();
		}
        int m = scn.nextInt();
        int k = scn.nextInt();
        int sum=0;
        int psum[]= new int[arr.length];
        for(int i=0;i<k;i++)
        {
            sum+=arr[i];
        }
        for(int i=k ,j=0;i<arr.length;i++,j++)
        {
            sum+=arr[i]-arr[j];
            psum[i-k+1]=sum;
        }
        System.out.println(mnonoverlap(arr,0, m , k, psum, new int [arr.length][m]));
    }

    public static int highway(int m , int[] x, int[] rev, int t) 
    {
       int dp[]= new int[x.length];
       dp[0]=rev[0];
       int ans=0;
       for(int i=1;i<x.length;i++)
       {
         int max=0;
         for(int j=0;j<i;j++)
         {
            int dist= x[i]-x[j];
            if(dist>t)
            {
                max=Math.max(max,dp[j]);
            }
         }
         dp[i]=max+rev[i];
         ans=Math.max(ans,dp[i]);
       }

       return ans;
    }

    //////////////////////////////////////////////////////////////////////////

    public static int maxdiffofzeroandone(String str) 
    {
		int csum=0;
        int osum=0;
        for(int i=0;i<str.length();i++)
        {
            int val=0;
            if(str.charAt(i)=='1')
            {
                val=-1;
            }
            else
            {
                val=1;
            }

            if(csum>=0)
            {
                csum+=val;
            }
            else
            {
                csum=val;
            }

            if(csum>osum)
            {
                osum=csum;
            }
        }

        if(osum==0)
        {
            return -1;
        }
        else
        {
            return osum;
        }
	}

    //////////////////////////////////////////////////////////////////////////////////

    public static int mnonoverlap(int[] arr, int idx, int m, int k, int []psum, int dp[][])
    {
        if(m<=0)
        {
            return  0;
        }
        if(idx>=arr.length)
        {
            return  0;
        }
        if(dp[idx][m]!=0)
        {
            return  dp[idx][m];
        }
        int exc= 0+ mnonoverlap(arr, idx+1,m, k, psum,dp);
        int inc= psum[idx]+ mnonoverlap(arr, idx+k, m-1, k, psum,dp);
        int ans= Math.max(exc,inc);
        dp[idx][m]=ans;
        return ans;
	}
}