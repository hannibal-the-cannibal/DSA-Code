package DPLevel2.L007;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < n; i++){
			arr[i] = scn.nextInt();
		}
		System.out.println(mcm(arr));
    }

    public static int minPalindromicCut(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];

        for (int g = 0; g < n; g++) {
            for (int i = 0, j = g; j < n; i++, j++) {
                if (g == 0) {
                    dp[i][j] = true;
                } else if (g == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
                }
            }
        }

        int mem[] = new int[n];
        mem[0] = 0;

        for (int j = 1; j < n; j++) {
            if (dp[0][j]) {
                mem[j] = 0;
            } else {
                int min = Integer.MAX_VALUE;
                for (int i = j; i >= 1; i--) {
                    if (dp[i][j]) {
                        if (mem[i - 1] < min) {
                            min = mem[i - 1];
                        }
                    }
                }
                mem[j] = min + 1;
            }
        }
        return mem[n - 1];
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public static int mcm(int[] arr){
		int n =arr.length;
        int dp[][]= new int[n-1][n-1];

        for(int g=0;g<dp.length;g++)
        {
            for(int i=0,j=g; j<dp.length;j++)
            {
                if(g==0)
                {
                    dp[i][j]=0;
                }
                else if(g==1)
                {
                    dp[i][j]=arr[i]*arr[j]*arr[j+1];
                }
                else
                {
                    int min=Integer.MAX_VALUE;
                    for(int k=i;k<j;k++)
                    {
                        int lc=dp[i][k];
                        int rc=dp[k+1][j];
                        int mc= arr[i]*arr[k+1]*arr[j+1];
                        int tc= lc+rc+mc;
                        if(tc<min)
                        {
                            min=tc;
                        }
                    }
                    dp[i][j]=min;
                }
            }
        }
        return dp[0][dp.length-1];
	}
}
