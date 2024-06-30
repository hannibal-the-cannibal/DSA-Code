package DPLevel1.L005;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        // String str= scn.next();
        // System.out.println(countencoding(str));

        int n = scn.nextInt();
        int arr[]= new int [n];
        for(int i=0;i<n;i++)
        {
            arr[i]=scn.nextInt();
        }

        System.out.println(houserobber2(arr));
        // System.out.println(maxsum_optab(arr));

        // int n =scn.nextInt();
        // int m=scn.nextInt();
        // System.out.println(tile2xm(n,m));



    }

    public static int countencoding(String str)
    {
        int len = str.length();
        int mem[]= new int[len+1];
        mem[len]=1;

        for(int i=len-1;i>=0;i--)
        {
            if(i==len-1)
            {
                char ch = str.charAt(i);
                if(ch!='0')
                {
                    mem[i]=1;
                }
            }
            else
            {
                char ch= str.charAt(i);
                String subs= str.substring(i, i+2);
                int temp= Integer.parseInt(subs);
                if(ch!='0' && temp<=26)
                {
                    mem[i]=mem[i+1]+mem[i+2]; // both call possible
                }
                else if(ch!='0' && temp>26)
                {
                    mem[i]=mem[i+1]; // single call possible
                }
                else
                {
                    mem[i]=0; // no valid call available 
                }
            }
        }
        return mem[0];

    }

    ///////////////////////////////////////////////////////////////////////

    public static int maxsum_tab(int arr[])
    {
        int n= arr.length;
        int mem[][]= new int [2][n];
        mem[0][0]= arr[0];
        mem[1][0]=0;
        for(int c=1;c<n;c++)
        {
            mem[0][c]= arr[c]+mem[1][c-1]; // include
            mem[1][c]= Math.max(mem[0][c-1],mem[1][c-1]); // exclude 
        }
        return Math.max(mem[0][n-1],mem[1][n-1]);

    }

    public static int maxsum_optab(int arr[])
    {
        int inc= arr[0];
        int exc=0;

        for(int c=1;c<arr.length;c++)
        {
            int ninc= exc+arr[c];
            int nexc= Math.max(inc, exc);

            inc=ninc;
            exc=nexc;
        }
        return Math.max(inc,exc);
    }

    //////////////////////////////////////////////////////////////////////////

    public static int houserobber2(int arr[])
    {
        int n= arr.length;
        int mem[][]= new int [2][n];
        mem[0][1]= arr[1];
        mem[1][1]=0;
        for(int c=2;c<n;c++)
        {
            mem[0][c]= arr[c]+mem[1][c-1];
            mem[1][c]= Math.max(mem[0][c-1],mem[1][c-1]);
        }
        int a1= (mem[0][n-1]);
        System.out.println(a1);
        int a2= mem[1][n-1]+arr[0];
        System.out.println(a2);
        return Math.max(a1,a2);
        

    }

    ////////////////////////////////////////////////////////////////////////

    public static int tile2x1(int n )
    {
        int mem []= new int[n+1];
        mem[1]=1;
        mem[2]=2;
        for(int i=3;i<=n;i++)
        {
            mem[i]=mem[i-1]+mem[i-2];
        }

        return mem[n];

    }

    public static int tile2xm(int n, int m)
    {
        int mem[]= new int[n+1];
        for(int i=1;i<=n;i++)
        {
            if(i<m)
            {
                mem[i]=1;
            }
            else if(i==m)
            {
                mem[i]=2;
            }
            else
            {
                mem[i]=mem[i-1]+mem[i-m];
            }
        }
        return mem[n];
    }





}
