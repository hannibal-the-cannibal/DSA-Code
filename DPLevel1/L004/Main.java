package DPLevel1.L004;
import java.io.*;
import java.util.*;

public class Main {
   

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();


        // int val[]= new int[n];
        // for(int i=0;i<n;i++)
        // {
        //     val[i]=scn.nextInt();
        // }
        // int wt[]= new int[n];
        // for(int i=0;i<n;i++)
        // {
        //     wt[i]=scn.nextInt();
        // }
        // int cap=scn.nextInt();

       
        // System.out.println( knap_rec(val,wt,cap,0,0,0));
        // System.out.println(knap_tab(val,wt,cap));

        // System.out.println(unbknap_tab(val,wt,cap));

        System.out.println(bstring_rec(n,0, new StringBuilder(0)));
        
        // System.out.println(bstring_tab(n));
        

    }

    public static int knap_rec(int val[], int wt[], int cap, int vsf,int wsf, int idx)
    {
        if(idx>=val.length)
        {
            return 0;
        }
        if(wsf>cap)
        {
            return 0;
        }
        if(wsf==cap)
        {
            return vsf;
        }
        int exc=Integer.MIN_VALUE;
        int inc = Integer.MIN_VALUE;
        
             exc=knap_rec(val, wt, cap, vsf, wsf, idx+1);
             inc=knap_rec(val, wt, cap, vsf+val[idx], wsf+wt[idx], idx+1);
        return Math.max(exc, inc);
        
        
    }

    public static int knap_tab(int []val, int wt[], int cap)
    {
        int n =val.length;
        int mem[][]= new int [n+1][cap+1];
        for(int i=1;i<mem.length;i++)
        {
            for(int j=1;j<mem[0].length;j++)
            {
                if(j>=wt[i-1])
                {
                    if(mem[i-1][j-wt[i-1]]+val[i-1]> mem[i-1][j])
                    {
                        mem[i][j]= mem[i-1][j-wt[i-1]]+val[i-1];
                    }
                    else
                    {
                        mem[i][j]= mem[i-1][j]; // i does not get a chance to bat
                    }
                }
                else
                {
                    mem[i][j]= mem[i-1][j]; // i decide not to bat 
                }
            }
        }
        return mem[n][cap];

        
    
}

////////////////////////////////////////////////////////////////////////

public static int unbknap_tab(int []val, int wt[], int cap)
{
    int n = val.length;
    int mem[]= new int[cap+1];
    mem[0]=1;

    for(int c=1;c<=cap;c++)
    {
        int max=0;
        for(int i=0;i<n;i++)
        {
            if(wt[i]<=c)
            {
                int rbagc= c-wt[i];
                int rbagv= mem[rbagc];
                int totalv= rbagv+val[i];

                if(max<totalv)
                {
                    max=totalv;
                }
            }
        }
        mem[c]=max;
    }
    return mem[cap];
}

////////////////////////////////////////////////////////////////////////

public static int bstring_rec(int n , int idx, StringBuilder sb)
{
    if(idx>n)
    {
        return 0;
    }
    if(idx==n)
    {
        return 1;
    }
    int zerocall=0;
    int onecall=0;
    if(sb.length()==0)
    {
        String str0="1";
        String str1= "0";
        zerocall=bstring_rec(n, idx+1, sb.append(str0));
        onecall=bstring_rec(n, idx+1, sb.append(str1));
    }
    else
    {
        String str0="1";
        String str1= "0";
        int size= sb.length()-1;
        if(sb.charAt(size)=='0')
        {
            onecall=bstring_rec(n, idx+1, sb.append(str0));
        } 
        else
        {
            zerocall=bstring_rec(n, idx+1, sb.append(str0));
            onecall=bstring_rec(n, idx+1, sb.append(str1)); 
        }
    }
    return onecall+zerocall;
}

public static int bstring_tab(int n)
{
    int mem[][]= new int [2][n+1];
    for(int c=1;c<=n;c++)
    {
        if(c==1)
        {
            mem[0][c]=mem[1][c]=1;
        }
        else
        {
            mem[0][c]= mem[1][c-1];
            mem[1][c]=mem[0][c-1]+mem[1][c-1];
        }
    }
    return mem[0][n]+mem[1][n];

}



}