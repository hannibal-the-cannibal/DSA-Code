package DPLevel1.L001;
import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();

    // System.out.println(fib_rec(n));
    // System.out.println(fib_mem(n, new int[n+1]));
    // System.out.println(fib_tab(n));
    // System.out.println(fib_opt(n));

    // System.out.println(climb_rec(n));
    // System.out.println(climb_memo(n, new int[n+1]));
    // System.out.println(climb_tab(n));
    // System.out.println(climb_otab(n));

    int arr[]= new int[n];
    for(int i=0;i<n;i++)
    {
        arr[i]=scn.nextInt();
    }

    System.out.println(climbjump_rec(0,n,arr));
    System.out.println(climbjump_memo(0, n, arr, new int [n+1]));
    System.out.println(climbjum_tab(arr));
    
 }
 public static int fib_rec(int n)
 {
    if(n==0 || n==1)
    {
        return n;
    }
    return fib_rec(n-1)+fib_rec(n-2);
 }
 public static int fib_mem(int n , int mem[])
 {
    if(n==0 ||n==1)
    {
        return mem[n]=n;
    }
    if(mem[n]!=0)
    {
        return mem[n];
    }

    return mem[n]= fib_mem(n-1, mem)+fib_mem(n-2, mem);
 }
 public static int fib_tab(int n )
 {
    int mem[]=new int[n+1];
    for(int i=0;i<=n;i++)
    {
        if(i==0 ||i==1)
        {
            mem[i]=i;
        }
        else
        {
            mem[i]=mem[i-1]+mem[i-2];
        }
    }
    return mem[n];
 }
 public static int fib_opt(int n)
 {
    if(n==0|| n==1)
    {
        return n;
    }
    int f=0,s=1;
    for(int i=2;i<=n;i++)
    {
        int t=f+s;
        f=s;
        s=t;
    }
    return s;
 }



 ////////////////////////////////////////////////////////////////

 public static int climb_rec(int n)
 {
    if(n==0)
    {
        return 1;
    }
    if(n<0)
    {
        return 0;
    }
    return climb_rec(n-1)+climb_rec(n-2)+climb_rec(n-3);
 }

 public static int climb_memo(int n , int mem[])
 {
    if(n==0)
    {
        return mem[n]=1;
    }
    if(n<0)
    {
        return 0;
    }
    if(mem[n]!=0)
    {
        return mem[n];
    }
    return mem[n]= climb_memo(n-1, mem)+climb_memo(n-2, mem)+climb_memo(n-3, mem);
 }

 public static int climb_tab(int n)
 {
    int mem[]= new int [n+1];
    for(int i=0;i<=n;i++)
    {
        if(i==0|| i==1)
        {
            mem[i]=1;
        }
        else if(i==2)
        {
            mem[i]=i;
        }
        else
        {
            mem[i]=mem[i-1]+mem[i-2]+mem[i-3];
        }
    }
    return mem[n];
 }

 public static int climb_otab(int n)
 {
    if(n==0|| n==1)
    {
        return 1;
    }
    if(n==2)
    {
        return 2;
    }
    int f=1,s=1,t=2;
    for(int i=3;i<=n;i++)
    {
        int fo=f+s+t;
        f=s;
        s=t;
        t=fo;
    }
    return t;
 }

 ////////////////////////////////////////////////////////////////
 public static int climbjump_rec(int currstep, int totalstep, int []arr)
 {
    if(currstep==totalstep)
    {
        return 1;
    }
    int jump=arr[currstep];
    int ans=0;
    for(int i=1;i<=jump;i++)
    {
        if(currstep+i<=totalstep)
        {
            ans=ans+climbjump_rec(currstep+i, totalstep, arr);
        }
    }
    return ans;
 }

 public static int climbjump_memo(int currStep, int totalStep, int []moves, int mem[])
 {
    if(currStep == totalStep){
        return mem[currStep] = 1;
    }
    if(mem[currStep] != 0){
        return mem[currStep];
    }
    int maxJump = moves[currStep];
    int ans = 0;
    for(int step = 1 ; step <= maxJump ; step++){
        if(currStep + step <= totalStep){
            ans += climbjump_memo(currStep + step,totalStep,moves,mem);
        }
    }
    return mem[currStep] = ans;
 }

 public static int climbjum_tab( int arr[])
 {
    int n = arr.length;
    int mem[]= new int[n+1];

    for(int stair=n;stair>=0; stair--)
    {
        if(stair==n)
        {
            mem[stair]=1;
            continue;
        }
        int maxjump=arr[stair];
        int ways=0;
        for(int jump=1;jump<=maxjump;jump++)
        {
            if(jump+stair<=n)
            {
                ways=ways+mem[jump+stair];
            }
            else{
                break;
            }
        }
        mem[stair]=ways;

    }
    return mem[0];
     
 }




}