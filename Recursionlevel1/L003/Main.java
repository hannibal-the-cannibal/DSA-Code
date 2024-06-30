package Recursionlevel1.L003;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        // String str= scn.nextLine();
        // ArrayList<String> ans= gss(str);
        // System.out.println(ans);

        // int n= scn.nextInt();
        // ArrayList<String> ans= getStairPaths(n);
        // System.out.println(ans);

        int n = scn.nextInt();
        int m = scn.nextInt();

        ArrayList<String> ans= getMazePathsjumps(0, 0, n-1, m-1);
        System.out.println(ans);

        // String str= scn.nextLine();
        // ArrayList<String> ans= getKPC(str);
        // System.out.println(ans);


    }
    static String codes[]= {".,","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};

    public static ArrayList<String> gss(String str) {
        if(str.length()==0)
        {
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }
       char ch= str.charAt(0);
       String rems= str.substring(1);
       ArrayList<String> rres= gss(rems);

       ArrayList<String> mylist= new ArrayList<>();
       for(String f: rres)
       {
        mylist.add(f);
       }
       for(String f: rres)
       {
        mylist.add(ch+f);
       }

       return mylist;
    }

    public static ArrayList<String> getStairPaths(int n) {
      
        if(n==0)
        {
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> mylist= new ArrayList<>();
        if(n>=1)
        {
            ArrayList<String> ans1= getStairPaths(n-1); 
            for(String f: ans1)
            {
                 mylist.add(1+f);
            }  
        }
        if(n>=2)
        {
            ArrayList<String> ans2= getStairPaths(n-2);
            for(String f: ans2)
            {
                 mylist.add(2+f);
            } 
        }
        if(n>=3)
        {
            ArrayList<String> ans3= getStairPaths(n-3);
            for(String f: ans3)
            {
                 mylist.add(3+f);
            } 
        }
       
        return mylist;
    }

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        
        if(sr==dr && sc==dc)
        {
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }
        if( sr>dr || sc>dc)
        {
            ArrayList<String> base= new ArrayList<>();
            return base;
        }

        ArrayList<String> hpath= getMazePaths(sr, sc+1, dr, dc);
        ArrayList<String> vpath= getMazePaths(sr+1, sc, dr, dc);

        ArrayList<String> mylist= new ArrayList<>();

        for(String path: hpath)
        {
            mylist.add("h"+path);
        }
        for(String path: vpath)
        {
            mylist.add("v"+path);
        }

        return mylist;
    }

    public static ArrayList<String> getMazePathsjumps(int sr, int sc, int dr, int dc) {
        if(sr==dr && sc==dc)
        {
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> mylist= new ArrayList<>();
        for(int jump=1;jump+sc<=dc;jump++)
        {
            ArrayList<String> hpath= getMazePathsjumps(sr, sc+1, dr, dc);
            for(String path: hpath)
            {
                mylist.add("h"+jump+path);
            }

        }
        for(int jump=1;jump+sr<=dr;jump++)
        {
            ArrayList<String> vpath= getMazePathsjumps(sr+1, sc, dr, dc);
            for(String path: vpath)
            {
                mylist.add("v"+jump+path);
            }

        }
        for(int jump=1;jump+sc<=dc && jump+sc<=dc;jump++)
        {
            ArrayList<String> dpath= getMazePathsjumps(sr+1, sc+1, dr, dc);
            for(String path: dpath)
            {
                mylist.add("d"+jump+path);
            }

        }

        return mylist;
    }

    public static ArrayList<String> getKPC(String str) {
        if(str.length()==0)
        {
            ArrayList<String> base= new ArrayList<>();
            base.add("");
            return base;
        }

        char ch= str.charAt(0);
        String rem = str.substring(1);
        ArrayList<String> rres= getKPC(rem);

        ArrayList<String> mylist= new ArrayList<>();

        String codeforch= codes[ch-'0'];
        for(int i=0;i<codeforch.length();i++)
        {
            char ch1= codeforch.charAt(i);
            for(String p: rres)
            {
                mylist.add(ch1+p);
            }
        }

        return mylist;
    }
    

}