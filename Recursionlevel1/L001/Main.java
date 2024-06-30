package Recursionlevel1.L001;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        // printDecreasing(n);
        // printIncreasing(n);
        // System.out.println(factorial(n));
        // pzz(n);
        // int src= scn.nextInt();
        // int dest= scn.nextInt();
        // int hlp=scn.nextInt();
        // toh(n,src,dest,hlp);
    }

    public static void printDecreasing(int n){
        if(n==0)
        {
            return ;
        }
        System.out.println(n);
        printDecreasing(n-1);
        
    }
    public static void printIncreasing(int n){
        if(n==0)
        {
            return;
        }
        printIncreasing(n-1);
        System.out.println(n);
        
    }
    public static int factorial(int n){
        if(n==0)
        {
            return 1;
        }
        return n*factorial(n-1);

    }
    public static void pzz(int n){
        if(n==0)
        {
            return ;
        }
        System.out.print(n+" ");
        pzz(n-1);
        System.out.print(n+" ");
        pzz(n-1); 
        System.out.print(n+" ");
    }

    public static void toh(int n, int src, int dest, int hlp){
        if(n==0)
        {
            return ;
        }
        toh(n-1, src, hlp,dest);
        System.out.println(n+"["+src + " -> "+ dest+"]");
        toh(n-1,hlp,dest,src);
        
    }


}
