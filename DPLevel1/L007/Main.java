package DPLevel1.L007;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);


        // int n = scn.nextInt();
        // System.out.println(friends(n));

        int n = scn.nextInt();
        int k = scn.nextInt();
        
        long res = partitionKSubset(n, k);
        System.out.println(res);
    }
    public static int friends(int n)
    {
        int mem[]= new int[n+1];
        mem[1]=1;
        mem[2]=2;

        for(int i=3;i<=n;i++)
        {
            mem[i]=mem[i-1]+ (i-1)*mem[i-2];
        }

        return mem[n];
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static long partitionKSubset(int n, int k) {
        int mem[][]= new int [k+1][n+1];
        for(int t=1;t<=k;t++)
        {
            for(int p=1;p<=n;p++)
            {
                if(t>p)
                {
                    mem[t][p]=0;
                }
                else if(t==p)
                {
                    mem[t][p]=1;
                }
                else
                {
                    mem[t][p]= mem[t-1][p-1] + mem[t][p-1]*t;
                }
            }
        }
        return mem[k][n];
    }

    

}