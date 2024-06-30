package DPLevel2.L008;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
	int[] keys = new int[n];
	int[] frequency = new int[n];
    for(int i= 0 ;i < n ; i++) {
		keys[i] = scn.nextInt();
	}
	for(int i = 0 ; i < n; i++){
      frequency[i] = scn.nextInt();
    }
	optimalbst(keys,frequency,n);
	}

    
	public static int booleanpar(String str1, String str2) {
		int n = str1.length();
        int dpt[][]= new int[n][n];
        int dpf[][]= new int[n][n];

        for(int g=0;g<n;g++)
        {
            for(int i=0,j=g;j<n;i++,j++)
            {
                if(g==0)
                {
                    char ch= str1.charAt(i);

                    if(ch=='T')
                    {
                        dpt[i][j]=1;
                        dpf[i][j]=0;
                    }
                    else
                    {
                        dpt[i][j]=0;
                        dpf[i][j]=1;
                    }
                }
                else
                {
                    for(int k=i;k<j;k++)
                    {
                        char op= str2.charAt(k);
                        int ltc= dpt[i][k];
                        int rtc= dpt[k+1][j];
                        int lfc= dpf[i][k];
                        int rfc= dpf[k+1][j];

                        if(op=='&')
                        {
                            dpt[i][j]+=ltc*rtc;
                            dpf[i][j]+=ltc*rfc + lfc*rfc + lfc*rtc;
                        }
                        else if(op=='|')
                        {
                            dpt[i][j]+=ltc*rtc +ltc*rfc +lfc*rtc;
                            dpf[i][j]= lfc*rfc;
                        }
                        else
                        {
                            dpt[i][j]+= ltc*rfc +lfc*rtc;
                            dpf[i][j]+= ltc*rtc +lfc*rfc;
                        }
                    }
                    
                }
            }
        }
        return dpt[0][n-1];
	}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void optimalbst(int[] keys, int[] frequency, int n) 
    {
        int dp[][]= new int[n][n];
        for(int g=0;g<n;g++)
        {
            for(int i=0,j=g;j<dp.length;i++,j++)
            {
                if(g==0)
                {
                    dp[i][j]=frequency[i];
                }
                else if(g==1)
                {
                    int f1= frequency[i];
                    int f2= frequency[i+1];
                    dp[i][j]=Math.min(f1+2*f2, f2+2*f1);
                }
                else
                {
                    int min=Integer.MAX_VALUE;
                    int fs=0;
                    for(int x=i;x<=j;x++)
                    {
                        fs=fs+frequency[x]; // increasing subtree height by 1 as when we add parent node height increases
                    }

                    for(int k=i;k<=j;k++) // every node will become root 
                    {
                        int left=(k==i?0:dp[i][k-1]);
                        int right= (k==j?0:dp[k+1][j]);

                        if(left+right+fs<min)
                        {
                            min=fs+left+right;
                        }

                    }
                    dp[i][j]=min;
                }
            }
        }
        System.out.println(dp[0][n-1]);
    }

}