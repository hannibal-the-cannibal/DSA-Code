package DPLevel1.L008;
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
        int fee= scn.nextInt();

        // System.out.println(bnss_1t(cost));
        // System.out.println(bnss_infit(cost));

        System.out.println(bnss_fee(cost,fee));
    }

    public static int bnss_1t(int cost[])
    {
        int lsf= Integer.MAX_VALUE;
        int op=0;
        int pifst=0;

        for(int i=0;i<cost.length;i++)
        {
            if(cost[i]<lsf)
            {
                lsf=cost[i];
            }

            pifst=cost[i]-lsf;
            if(pifst>op)
            {
                op=pifst;
            }
        }
        return op;
    }

    /////////////////////////////////////////////////////////////////////////

    public static int bnss_infit(int cost[])
    {
        int bd=0;
        int sd=0;
        int profit=0;

        for(int i=1;i<cost.length;i++)
        {
            if(cost[i]>=cost[i-1])
            {
                sd++;
            }
            else
            {
                profit=profit+cost[sd]-cost[bd];
                bd=sd=i;
            }
        }
        profit=profit+cost[sd]-cost[bd]; // if last dip did no come we collect the last profit
        return profit;
    }
    //////////////////////////////////////////////////////////////////////////

    public static int bnss_fee(int cost[], int fee)
    {
        int obsp=-cost[0];
        int ossp= 0;

        for(int i=1;i<cost.length;i++)
        {
            int nbsp=0;
            int nssp=0;
            if(ossp-cost[i]>obsp)
            {
                nbsp=ossp-cost[i];
            }
            else
            {
                nbsp=obsp;
            }

            if(obsp+cost[i]-fee>ossp)
            {
                nssp=obsp+cost[i]-fee;
            }
            else
            {
                nssp=ossp;
            }


            obsp=nbsp;
            ossp=nssp;
        }
        return ossp;
    }



}