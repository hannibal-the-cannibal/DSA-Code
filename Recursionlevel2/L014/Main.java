package Recursionlevel2.L014;
import java.io.*;
import java.util.*;

public class Main {


	public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int m = scn.nextInt();
		int n = scn.nextInt();
		char[][] arr = new char[m][n];
		for (int i = 0; i < arr.length; i++) {
			String str = scn.next();
			arr[i] = str.toCharArray();
		}
		int[] top = new int[n];
		for (int i = 0; i < n; i++) {
			top[i] = scn.nextInt();
		}
		int[] left = new int[m];
		for (int i = 0; i < m; i++) {
			left[i] = scn.nextInt();
		}
		int[] right = new int[m];
		for (int i = 0; i < m; i++) {
			right[i] = scn.nextInt();
		}
		int[] bottom = new int[n];
		for (int i = 0; i < n; i++) {
			bottom[i] = scn.nextInt();
		}

		char ans[][]= new char[n][m];
        for(int i=0;i<m;i++)
        {
            Arrays.fill(ans[i], 'X');
        }
        solution(arr, top, left, right, bottom, ans, 0, 0);

        print(ans);

	}

    public static boolean isvalid(char[][]arr, int []top, int[] left, int[] right, int[] bottom, char[][] ans)
    {
        for(int i=0;i<left.length;i++)
        {
            int cinr= countinrow(ans, i, '+');
            if(left[i]!=-1 && left[i]!=cinr)
            {
                return false;
            }
            
        }

        for(int i=0;i<top.length;i++)
        {
            int cinc= countincol(ans, i, '+');
            if(top[i]!=-1 && top[i]!=cinc)
            {
                return false;
            }
            
        }

        for(int i=0;i<right.length;i++)
        {
            int cinr= countinrow(ans, i, '+');
            if(right[i]!=-1 && right[i]!=cinr)
            {
                return false;
            }
            
        }

        for(int i=0;i<bottom.length;i++)
        {
            int cinc= countincol(ans, i, '+');
            if(bottom[i]!=-1 && bottom[i]!=cinc)
            {
                return false;
            }
            
        }

        return true;


    }

    
	public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans, int row, int col)
     {
        if(col==arr[0].length)
        {
            col=0;
            row++;
        }
        if(row==arr.length)
        {
            boolean isans= isvalid(arr,top,left,right,bottom,ans);
            if(isans)
            {
                return true;
            }
            return false;
            
        }
            if(arr[row][col]=='L')
            {
                if(issafe(arr,top,left,right,bottom,ans,row,col,'+') && issafe(arr,top,left,right,bottom,ans,row,col+1,'-'))
                {
                    ans[row][col]='+';
                    ans[row][col+1]='-';
                    if(solution(arr, top, left, right, bottom, ans, row, col+2))
                    {
                        return true;
                    }
                    ans[row][col]='X';
                    ans[row][col+1]='X';
                }
                else if(issafe(arr,top,left,right,bottom,ans,row,col,'-') && issafe(arr,top,left,right,bottom,ans,row,col+1,'+'))
                {
                    ans[row][col]='-';
                    ans[row][col+1]='+';
                    if(solution(arr, top, left, right, bottom, ans, row, col+2))
                    {
                        return true;
                    }
                    ans[row][col]='X';
                    ans[row][col+1]='X';
                }
            }
            else if(arr[row][col]=='T')
            {
                if(issafe(arr,top,left,right,bottom,ans,row,col,'+') && issafe(arr,top,left,right,bottom,ans,row+1,col,'-'))
                {
                    ans[row][col]='+';
                    ans[row+1][col]='-';
                    if(solution(arr, top, left, right, bottom, ans, row, col+1))
                    {
                        return true;
                    }
                    ans[row][col]='X';
                    ans[row+1][col]='X';
                }
                else if(issafe(arr,top,left,right,bottom,ans,row,col,'-') && issafe(arr,top,left,right,bottom,ans,row+1,col,'+'))
                {
                    ans[row][col]='-';
                    ans[row+1][col]='+';
                    if(solution(arr, top, left, right, bottom, ans, row, col+1))
                    {
                        return true;
                    }
                    ans[row][col]='X';
                    ans[row+1][col]='X';
                }
            }

            boolean f2= solution(arr, top, left, right, bottom, ans, row, col+1); // not placing magnet here call
            if(f2)
            {
                return true;
            }

            return false;
     }



     public static boolean issafe(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans, int row, int col, char sign)
     {
        if(col-1>=0 && ans[row][col-1]==sign || row-1>=0 && ans[row-1][col]==sign || col+1<ans[0].length && ans[row][col+1]==sign)
        {
            return false;
        }

        if(sign=='+')
        {
            int cinr= countinrow(ans, row, sign);
            int cinc= countincol(ans, col, sign);
            if(left[row]!=-1 && cinr>=left[row])
            {
                return false;
            }
            

            if(top[col]!=-1 && cinc>=top[col])
            {
                return false;
            }
        }

        else
        {
            int cinr= countinrow(ans, row, sign);
            int cinc= countincol(ans, col, sign);
            if(right[row]!=-1 && cinr>=right[row])
            {
                return false;
            }
            

            if(bottom[col]!=-1 && cinc>=bottom[col])
            {
                return false;
            }
        }
        return true;
     }

     public static int countinrow( char[][]ans, int rowno, char sign)
     {
        int counter=0;
        for(int col=0;col<ans[0].length;col++)
        {
            if(ans[rowno][col]==sign)
            {
                counter++;
            }
        }
        return counter;
     }
     public static int countincol( char[][]ans, int colno, char sign)
     {
        int counter=0;
        for(int row=0;row<ans.length;row++)
        {
            if(ans[row][colno]==sign)
            {
                counter++;
            }
        }
        return counter;
     }

	
}