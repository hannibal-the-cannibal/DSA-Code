package Recursionlevel2.L001;
import java.io.*;
import java.util.*;

public class Main {

    
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        // String str = scn.nextLine();
		// solution3(str, "");


        
        // int n = scn.nextInt();
        // boolean[][] board = new boolean[n][n];
        // boolean cols[]= new boolean[n];
        // boolean ndiag[]= new boolean [2*n-1];
        // boolean rdiag[]= new boolean[2*n-1];
        // solve(board, 0, cols, ndiag,rdiag,"");


        // String str = scn.nextLine();
        // solution(str,"",0,0);

		// int nofWords = scn.nextInt();
		// String[] words = new String[nofWords];
		// for(int i = 0 ; i < words.length; i++) {
		// 	words[i] = scn.next();
		// }
		// int nofLetters = scn.nextInt();
		// char[] letters = new char[nofLetters];
		// for (int i = 0; i < letters.length; i++) {
		// 	letters[i] = scn.next().charAt(0);
		// }
		// int[] score = new int[26];
		// for (int i = 0; i < score.length; i++) {
		// 	score[i] = scn.nextInt();
		// }
		// if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null
		// 		|| score.length == 0) {
		// 	System.out.println(0);
		// 	return;
		// }
		// int[] farr = new int[score.length];
		// for (char ch : letters) {
		// 	farr[ch - 'a']++;
		// }
		// System.out.println(solution2(words, farr, score, 0));

	}
    

    public static void solution(String str, String asf,int count, int pos){
        if(pos==str.length())
        {
            if(count==0)
            {
                System.out.println(asf);
                return;
            }
            else
            {
                System.out.println(asf+count);
                return;
            }
        }

        if(count!=0)
        {
            solution(str, asf+count+str.charAt(pos), 0, pos+1);//including
        }
        else
        {
            solution(str, asf+str.charAt(pos), 0, pos+1);//including
        }
        
        solution(str, asf, count+1, pos+1);//excluding 
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static int solution2(String[] words, int[] farr, int[] score, int idx) {
        if(idx>=words.length)
        {
            return 0;
        }
        int scorenot= solution2(words, farr, score, idx+1);// no call excluding

        int myscore=0;
        String word= words[idx];
        boolean flag=true;
        for(int i=0;i<word.length();i++)   
        {
            char ch= word.charAt(i);

            if(farr[ch-'a']==0)
            {
                flag=false;
            }
            farr[ch-'a']--;
            myscore+= score[ch-'a'];

        }
        int scoreyes= 0;
        if(flag)
        {
            scoreyes=myscore+ solution2(words, farr, score, idx+1);
        }

        for(int i=0;i<word.length();i++) // backtracking
        {
            char ch = word.charAt(i);
            farr[ch-'a']++;
        }
        return Math.max(scorenot,scoreyes);
		
	}

    //////////////////////////////////////////////////////////////////////////////////////
    public static void solution3(String str, String asf) {
        if(str.length()==0)
        {
            System.out.println(asf);
            return ;
        }
        for(int i=0;i<str.length();i++)
        {
            String prefix= str.substring(0,i+1);
            String roq= str.substring(i+1);
            if(isPali(prefix))
            {
                solution3(roq, asf+"("+ prefix+") ");
            }
        }

		
	}
    public static boolean isPali(String str)
    {
        int l=0;
        int r=str.length()-1;
        while(l<r)
        {
            char left= str.charAt(l);
            char right= str.charAt(r);
            if(left!=right)
            {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static void solve(boolean[][]board, int row, boolean[]cols, boolean[] ndiag, boolean[] rdiag, String asf)
    {
        if(row==board.length)
        {
            System.out.println(asf +" .");
            return ;
        }

        for(int col=0;col<board[0].length;col++)
        {
            if( cols[col]==false  && ndiag[row+col]==false && rdiag[row-col +board.length-1]==false) 
            {
                board[row][col]=true;
                cols[col]=true;
                ndiag[row+col]=true;
                rdiag[row-col+board.length-1]=true;
                solve(board, row+1, cols, ndiag, rdiag, asf+ row+" -> "+ col+ " ,");
                board[row][col]=false;
                cols[col]=false;
                ndiag[row+col]=false;
                rdiag[row-col+board.length-1]=false;

            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////




    
    
}