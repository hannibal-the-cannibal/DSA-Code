package DPLevel2.L009;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
		String str = scn.next();
		System.out.println(countPalindromes(str));

    }
    public static int countPalindromes(String s) {
        int dp[][]= new int[s.length()][s.length()];
        for(int g=0;g<dp.length;g++)
        {
            for(int i=0,j=g;j<dp.length;j++,i++)
            {
                if(g==0)
                {
                    dp[i][j]=1;
                }
                else if(g==1)
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        dp[i][j]=3;
                    }
                    else
                    {
                        dp[i][j]=2;
                    }
                }
                else
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        dp[i][j]=dp[i][j-1]+dp[i+1][j]+1;
                    }
                    else
                    {
                        dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    


}