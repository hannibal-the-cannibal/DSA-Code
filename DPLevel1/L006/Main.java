package DPLevel1.L006;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        // int n =scn.nextInt();
        // int cost[][]= new int[n][3];

        // for(int i=0;i<n;i++)
        // {
        //     cost[i][0]=scn.nextInt();
        //     cost[i][1]=scn.nextInt();
        //     cost[i][2]=scn.nextInt();
        // }

        // System.out.println(painthouse(cost));

        // int n =scn.nextInt();
        // int k=scn.nextInt();
        // int cost[][]= new int[n][k];
        // for(int i=0;i<n;i++)
        // {
        //     for(int j=0;j<k;j++)
        //     {
        //         cost[i][j]=scn.nextInt();
        //     }
        // }
        // System.out.println(painthousemany(cost));

        // String str= scn.next();
        // System.out.println(countabc(str));


        int n=scn.nextInt();
        int k=scn.nextInt();
        System.out.println(paintfence(n,k));




    }
    public static int painthouse(int cost[][])
    {
        int n=cost.length;
        int mem[][]= new int[n][3];
        for(int i=0;i<n;i++)
        {
            if(i==0)
            {
                mem[i][0]=cost[i][0];
                mem[i][1]=cost[i][1];
                mem[i][2]=cost[i][2];
            }
            else
            {
                mem[i][0]= Math.min(mem[i-1][1],mem[i-1][2])+cost[i][0];
                mem[i][1]= Math.min(mem[i-1][0],mem[i-1][2])+cost[i][1];
                mem[i][2]= Math.min(mem[i-1][0],mem[i-1][2])+cost[i][2];
            }
        }
        return Math.min(Math.min(mem[n-1][1],mem[n-1][2]), mem[n-1][0]);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public static int painthousemany(int [][]cost)
    {
        int n = cost.length;
        int k= cost[0].length;
        int mem[][]= new int [n][k];

        int min=Integer.MAX_VALUE;
        int smin= Integer.MAX_VALUE;

        for(int i=0;i<n;i++)
        {
            int nmin=Integer.MAX_VALUE, nsmin= Integer.MAX_VALUE;
            for(int j=0;j<k;j++)
            {
                if(i==0)
                {
                    mem[i][j]=cost[i][j];
                }
                else if(mem[i-1][j]==min)
                {
                    mem[i][j]=cost[i][j]+smin;
                }
                else
                {
                    mem[i][j]=cost[i][j]+min;
                }

                if(mem[i][j]<nmin)
                {
                    nsmin=nmin;
                    nmin=mem[i][j];
                }
                else if(mem[i][j]<nsmin)
                {
                    nsmin=mem[i][j];
                }
            }
            min=nmin;
            smin=nsmin;
        }
        return min;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public static int countabc(String str)
    {
        int aplus=0;
        int abplus=0;
        int abcplus=0;

        for(int i=0;i<str.length();i++)
        {
            char ch= str.charAt(i);
            if(ch=='a')
            {
                aplus= 2*aplus+1;
            }
            else if(ch=='b')
            {
                abplus=2*abplus+aplus;
            }
            else if(ch=='c')
            {
                abcplus=2*abcplus+abplus;
            }
        }
        return abcplus;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public static double paintfence(int n, int k )
    {
        double same= k;
        double diff= k*(k-1);
        double total = same +diff;

        for(int i=3;i<=n;i++)
        {
            same = diff*1;
            diff=total*(k-1);
            total=same+diff;
        }
        return total;
    }




}
