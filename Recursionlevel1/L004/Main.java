package Recursionlevel1.L004;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        // String str= scn.nextLine();
        // printSS(str, "");

        // int n = scn.nextInt();
        // printStairPaths(n,"");

        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // printMazePaths(0, 0, n-1, m-1,"");

        // String str= scn.nextLine();
        // printKPC(str,"");

        // int n = scn.nextInt();
        // int m= scn.nextInt();
        // printMazePathsjumps(0, 0, n-1, m-1,"");

        // String str= scn.nextLine();
        // printPermutations(str, "");

        String str= scn.nextLine();
        printEncodings( str,"");


    }

    static String codes[]= {".,","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};

    public static void printSS(String str, String asf) {
        if(str.length()==0)
        {
            System.out.println(asf);
            return;
        }
        char ch = str.charAt(0);
        String rem= str.substring(1);
        printSS(rem, asf+ch);
        printSS(rem, asf);
        
    }
    public static void printStairPaths(int n, String psf) {
        if(n==0)
        {
            System.out.println(psf);
            return ;
        }
        if(n<0)
        {
            return ;
        }
        printStairPaths(n-1, psf+1);
        printStairPaths(n-2, psf+2);
        printStairPaths(n-3, psf+3);

    }
    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if(sr>dr || sc>dc )
        {
            return;
        }
        if(sr==dr && sc==dc)
        {
            System.out.println(psf);
            return;
        }
        printMazePaths(sr, sc+1, dr, dc, psf+"h");
        printMazePaths(sr+1, sc, dr, dc, psf+"v");

	        
    }
    public static void printKPC(String str, String asf) {
        if(str.length()==0)
        {
            System.out.println(asf);
            return;
        }
        char ch= str.charAt(0);
        String rem= str.substring(1);
        String charray = codes[ch-'0'];
        for(int i=0;i<charray.length();i++)
        {
            char ch1= charray.charAt(i);
            printKPC(rem, asf+ch1);
        }

        
    }
    public static void printMazePathsjumps(int sr, int sc, int dr, int dc, String psf) {
        if(sr==dr && sc==dc)
        {
            System.out.println(psf);
            return ;
        }
        for(int jump=1;jump+sc<=dc;jump++)
        {
            printMazePathsjumps(sr, sc+jump, dr, dc, psf+"h"+jump);
        }
        for(int jump=1;jump+sr<=dr;jump++)
        {
            printMazePathsjumps(sr+jump, sc, dr, dc, psf+"v"+jump);
        }
        for(int jump=1;jump+sc<=dc&& jump+sr<=dr;jump++)
        {
            printMazePathsjumps(sr+jump, sc+jump, dr, dc, psf+"d"+jump);
        }
 
    }

    public static void printPermutations(String str, String asf) {
        if(str.length()==0)
        {
            System.out.println(asf);
            return;
        }
        for(int i=0;i<str.length();i++)
        {
            char ch= str.charAt(i);
            StringBuilder sb = new StringBuilder(str);
            sb.deleteCharAt(i);
            String roq= sb.toString();
            printPermutations(roq, asf+ch);
        }
        
    }

    public static void printEncodings(String qsf, String asf) {
        if(qsf.length()==0)
        {
            System.out.println(asf);
            return;
        }

        //single
        char n1= qsf.charAt(0);
        if(n1=='0')
        {
            return ;
        }
        else
        {
            char enc1= (char)('a'+(n1-'1'));
            printEncodings(qsf.substring(1), asf+enc1);
        }

        //multiple
        if(qsf.length()>1)
        {
            String temp = qsf.substring(0,2);
            int n2= Integer.parseInt(temp);

            if(n2<=26)
            {
                char enc2= (char)('a'+(n2-1));
                printEncodings(qsf.substring(2), asf+enc2);
            }
        }
        
    }



}