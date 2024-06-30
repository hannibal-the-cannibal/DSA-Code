package DPLevel1.L009;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        int n =scn.nextInt();
        int cost[]= new int[n];
        for(int i=0;i<n;i++)
        {
            cost[i]=scn.nextInt();
        }
        int k=scn.nextInt();

        // System.out.println(bnss_cooldown(cost));
        // System.out.println(bnss_twotrans(cost));
        System.out.println(bnss_ktrans(cost,k));
        System.out.println(bnss_ktransopti(cost,k));
    }
    public static int bnss_cooldown(int []cost)
    {
        int obsp=-cost[0];
        int ossp=0;
        int ocsp=0;

        for(int i=1;i<cost.length;i++)
        {
            int nbsp=0;
            int nssp=0;
            int ncsp=0;

            if(ocsp-cost[i]>obsp)
            {
                nbsp=ocsp-cost[i];
            }
            else
            {
                nbsp=obsp;
            }

            if(obsp+cost[i]>ossp)
            {
                nssp=obsp+cost[i];
            }
            else
            {
                nssp=ossp;
            }


            if(ossp>ocsp)
            {
                ncsp=ossp;
            }
            else
            {
                ncsp=ocsp;
            }

            obsp=nbsp;
            ossp=nssp;
            ocsp=ncsp;

        }
        return ossp;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public static int bnss_twotrans(int [] cost)
    {
       int mpist=0;
       int lsf=cost[0];
       int dpl[]= new int[cost.length];
       
       for(int i=1;i<cost.length;i++)
       {
        if(cost[i]<lsf)
        {
            lsf=cost[i];
        }
        mpist= cost[i]-lsf;
        if(mpist>dpl[i-1])
        {
            dpl[i]=mpist;
        }
        else
        {
            dpl[i]=dpl[i-1];
        }
       }

       int mpibt=0;
       int maxsf= cost[cost.length-1];
       int dpr[]= new int[cost.length];

       for(int i=cost.length-2;i>=0;i--)
       {
        if(cost[i]>maxsf)
        {
            maxsf= cost[i];
        }
        mpibt=maxsf-cost[i];

        if(mpibt>dpr[i+1])
        {
            dpr[i]=mpibt;
        }
        else
        {
            dpr[i]=dpr[i+1];
        }
       }

       int op=0;
       for(int i=0;i<cost.length;i++)
       {
        if(dpl[i]+dpr[i]>op)
        {
            op=dpl[i]+dpr[i];
        }
       }

       return op;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public static int bnss_ktrans(int cost[], int k)
    {
        int dp[][]= new int [k+1][cost.length];

        for(int t=1;t<=k;t++) //transition 
        {
            for(int d=1;d<cost.length;d++) //day
            {
                int max= dp[t][d-1];

                for(int pd=0;pd<d;pd++)
                {
                    int prevdaystrans=dp[t-1][pd];
                    int tthtrans= cost[d]-cost[pd];

                    if(prevdaystrans+tthtrans>max)
                    {
                        max=prevdaystrans+tthtrans;
                    }
                }
                dp[t][d]=max;
            }
        }
        return dp[k][cost.length-1];
    }

    public static int bnss_ktransopti(int cost[], int k)
    {
        int dp[][]= new int [k+1][cost.length];

        for(int t=1;t<=k;t++)
        {
            int max= Integer.MIN_VALUE;
            for(int d=1;d<cost.length;d++)
            {
                if(dp[t-1][d-1]-cost[d-1]>max)
                {
                    max=dp[t-1][d-1]-cost[d-1];
                }

                if(max+cost[d]>dp[t][d-1])
                {
                    dp[t][d]=max+cost[d];
                }
                else
                {
                    dp[t][d]=dp[t][d-1];   
                }
            }
        }
        return dp[k][cost.length-1];
    }



}