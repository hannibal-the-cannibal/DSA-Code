package DPLevel1.Practice;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        
        
        String str= scn.nextLine();
        printPermutations(str, "");


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
}