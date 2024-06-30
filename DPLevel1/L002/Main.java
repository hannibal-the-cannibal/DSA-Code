package DPLevel1.L002;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        // int n =scn.nextInt();
        // int arr[]= new int[n];
        // for(int i=0;i<n;i++)
        // {
        //     arr[i]=scn.nextInt();
        // }
        // climbmin_rec(arr, 0, n-1, 0);

        // System.out.println(minstep);
        // System.out.println(climbmin_tab(arr));

        // int n=scn.nextInt();
        // int m=scn.nextInt();
        // int maze[][]= new int[n][m];
        // for(int i=0;i<n;i++)
        // {
        //     for(int j=0;j<m;j++)
        //     {
        //         maze[i][j]=scn.nextInt();
        //     }
        // }
        // System.out.println(maze_rec(0, 0, n-1, m-1, maze));
        // System.out.println(maze_tab(maze));

        int n = scn.nextInt();
        int m = scn.nextInt();
        int arr[][]= new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                arr[i][j]=scn.nextInt();
            }
        }
        System.out.println(goldmine_tab(arr));


        int maxofcol= Integer.MIN_VALUE;
        for(int i=0;i<n;i++)
        {
            int ans= goldmine_rec(arr,i,0);
            maxofcol= Math.max(maxofcol,ans);
        }

        System.out.println(maxofcol);





    }
    static int minstep =Integer.MAX_VALUE;

    public static void climbmin_rec(int arr[], int currstep, int totalstep, int count)
    {
        
        if(currstep==totalstep)
        {
            minstep = Math.min(minstep,count);
        }
        int maxjump=arr[currstep];
        for(int jump=1;jump<=maxjump;jump++)
        {
            if(jump+currstep<=totalstep)
            {
                climbmin_rec(arr, currstep+jump, totalstep, count+1);
            }
        }
    }

    public static Integer climbmin_tab(int arr[])
    {
        int n = arr.length;
        Integer mem[]= new Integer[n+1];
        mem[n]=0;

        for(int stair=n-1;stair>=0;stair--)
        {
            if(arr[stair]>0)
            {
                int maxjump=arr[stair];
                int min= Integer.MAX_VALUE;
                for(int jump=1;jump<=maxjump;jump++)
                {
                    if(jump+stair<=n)
                    {
                        if(mem[stair+jump]!=null)
                        {
                            min =Math.min(min, mem[stair+jump]);
                        }
                       
                    }
                }
                if(min!=Integer.MAX_VALUE)
                {
                    mem[stair]=min+1;
                }
               
            }
        }
        return mem[0];

    }

    /////////////////////////////////////////////////////////

    public static int maze_rec(int sr, int sc, int dr, int dc, int[][]maze)
    {
        
        if(sr==dr && sc==dc)
        {
            return maze[dr][dc];

        }
        int hpath= Integer.MAX_VALUE;
        int vpath = Integer.MAX_VALUE;
        if(sc+1<=dc)
        {
            hpath= maze_rec(sr, sc+1, dr, dc, maze);
        }
        if(sr+1<=dr)
        {
            vpath =maze_rec(sr+1, sc, dr, dc, maze);
        }
        
        return Math.min(hpath,vpath)+maze[sr][sc];


    }

    public static int maze_tab(int maze[][])
    {
        int nr= maze.length;
        int nc= maze[0].length;
        int mem[][]= new int [nr][nc];

        for(int i= nr-1;i>=0;i--)
        {
            for(int j= nc-1;j>=0;j--)
            {
                if(i==nr-1 && j==nc-1)
                {
                    mem[i][j]=maze[i][j];
                }
                else if(i==nr-1)
                {
                    mem[i][j]= mem[i][j+1]+maze[i][j];
                }
                else if( j==nc-1)
                {
                    mem[i][j]= mem[i+1][j]+maze[i][j];
                }
                else
                {
                    mem[i][j]= Math.min(mem[i][j+1], mem[i+1][j])+maze[i][j];
                }
            }
        }
        return mem[0][0];
    }


    ////////////////////////////////////////////////////////

    public static int goldmine_rec(int arr[][], int sr, int sc) 
    {
        if(sc==arr[0].length-1)
        {
            return arr[sr][sc];
        }
        int a1=0,a2=0,a3=0;
         if(sc+1<arr[0].length)
         {
            a1= goldmine_rec(arr,sr,sc+1);
         }
         if(sr+1<arr.length && sc+1<arr[0].length)
         {
            a2=goldmine_rec(arr, sr+1, sc+1);
         }
         if(sr-1>=0&& sc+1<arr[0].length)
         {
            a3=goldmine_rec(arr, sr-1, sc+1);
         }

         return Math.max(Math.max(a1,a2),a3)+arr[sr][sc];
    }

    public static int goldmine_tab(int arr[][])
    {
        int n=arr.length;
        int m=arr[0].length;
        int mem[][]= new int [n][m];

        for(int j=m-1;j>=0;j--)
        {
            for(int i=n-1;i>=0;i--)
            {
                if(j==m-1)
                {
                    mem[i][j]=arr[i][j];
                }
                else if(i==0)
                {
                    mem[i][j]=arr[i][j]+ Math.max(mem[i][j+1], mem[i+1][j+1]);
                }
                else if(i==n-1)
                {
                    mem[i][j]=arr[i][j]+ Math.max(mem[i][j+1], mem[i-1][j+1]);
                }
                else
                {
                    mem[i][j]=arr[i][j]+ Math.max(Math.max(mem[i][j+1], mem[i-1][j+1]),mem[i+1][j+1]);
                }
            }
        }

        int max= mem[0][0];
        for(int i=1;i<n;i++)
        {
            if(mem[i][0]>max)
            {
                max=mem[i][0];
            }
        }
        return max;
    }

}
