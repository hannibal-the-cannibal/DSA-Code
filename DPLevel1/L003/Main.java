package DPLevel1.L003;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int arr[]= new int [n];
        for(int i=0;i<n;i++)
        {
            arr[i]=scn.nextInt();
        }
        int tar= scn.nextInt();

        // System.out.println(tss_rec(arr,0,tar,0));
        // System.out.println(tss_tab(arr, tar));

        // System.out.println(ccc_rec(arr,tar,0,0));
        // System.out.println(ccc_tab(arr, tar));

        System.out.println(ccp_tb(arr, tar));
        
    }

    public static boolean tss_rec(int []arr, int idx, int tar, int sos)
    {
        if(idx>=arr.length)
        {
            return false;
        }
        if(sos>tar)
        {
            return false;
        }
        if(tar==sos)
        {
            return true;
        }

        boolean inc= tss_rec(arr, idx+1, tar, sos+arr[idx]);
        if(inc)
        {
            return true;
        }
        boolean exc= tss_rec(arr, idx+1, tar, sos);
        if(exc){
            return true;
        }

        return false;


    }

    public static boolean tss_tab(int coins[], int tar)
    {
        boolean dp[][]= new boolean [coins.length+1][tar+1];
        for(int i=0;i<dp.length;i++)
        {
            for(int j=0;j<dp[0].length;j++)
            {
                if(i==0 && j==0)
                {
                    dp[i][j]=true;
                }
                else if(i==0)
                {
                    dp[i][j]=false;
                }
                else if(j==0)
                {
                    dp[i][j]=true;
                }
                else
                {
                    if(dp[i-1][j]==true)
                    {
                        dp[i][j]=true;
                    }
                    else
                    {
                        int val= coins[i-1];
                        if(j>=val)
                        {
                            if(dp[i-1][j-val]==true)
                            {
                                dp[i][j]=true;
                            }
                        }
                    }
                }
            }
        }
        return dp[coins.length][tar];
    }

    ///////////////////////////////////////////////////////////////////////////

    public static int ccc_rec(int arr[], int tar, int idx, int sos)
    {
        if(idx>=arr.length)
        {
            return 0;
        }

        if(sos>tar)
        {
            return 0;
        }
        
        if(sos==tar)
        {
            return 1;
        }

        int a1= ccc_rec(arr, tar, idx, sos+arr[idx]);
        int a2= ccc_rec(arr, tar, idx+1, sos);
        return a1+a2;
    }

    public static int ccc_tab(int arr[], int tar)
    {
        int mem[]= new int[tar+1];
        mem[0]=1;

        for(int i=0;i<arr.length;i++) //coins loop
        {
            for(int j=arr[i];j<mem.length;j++) //spots-amount val 
            {
                mem[j]=mem[j]+mem[j-arr[i]];
            }
        }
        return mem[tar];
    }

    ///////////////////////////////////////////////////////////////////////

    public static int ccp_tb(int arr[], int tar)
    {
        int mem[]= new int[tar+1];
        mem[0]=1;

        for(int i=1;i<mem.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(i>=arr[j])
                {
                    mem[i]=mem[i]+mem[i-arr[j]];
                }
            }
        }
        return mem[tar];
    }
    

    


}
