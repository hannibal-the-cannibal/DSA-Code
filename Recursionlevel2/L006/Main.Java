package Recursionlevel2.L006;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		String pattern = scn.next();
		HashMap<Character,String> map = new HashMap<>();
		patternmatch(str,pattern,map,pattern);
	}

    public static void patternmatch(String str, String pattern, HashMap<Character,String> map, String op)
    {
        if(pattern.length()==0)
        {
            if(str.length()==0)
            {
                HashSet<Character> alreadypresent= new HashSet<>(); // so that we do not print duplicate
                for(int i=0;i<op.length();i++)
                {
                  char ch = op.charAt(i);
                    if(alreadypresent.contains(ch)==false)
                    {
                        System.out.print(ch +" -> "+ map.get(ch)+" , ");
                        alreadypresent.add(ch);
                    }
                }
            }
            return ;
        }
		char ch = pattern.charAt(0);
        String rop= pattern.substring(1);
        if(map.containsKey(ch))
        {
            String previousmap = map.get(ch); // checking if its has same word mapping 
            if(str.length()>=previousmap.length())
            {
                String left = str.substring(0,previousmap.length());
                String right= str.substring(previousmap.length());
                if(previousmap.equals(left))
                {
                    patternmatch(right,rop,map,op);
                }
            }
        }
        else
        {
            for(int i=0;i<str.length();i++)
            {
                String ls= str.substring(0,i+1);
                String rs= str.substring(i+1);
                map.put(ch,ls);
                patternmatch(rs,rop,map,op);
                map.remove(ch); //backtracking 

            }
        }
	}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
}