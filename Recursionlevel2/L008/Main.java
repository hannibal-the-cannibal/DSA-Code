package Recursionlevel2.L008;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);

    int n = scn.nextInt();
    boolean[] used = new boolean[n + 1];
    friendspairing(1, n, used, "");

    // String s1 = scn.nextLine();
    // String s2 = scn.nextLine();
    // String s3 = scn.nextLine();

    // HashMap<Character, Integer> charIntMap = new HashMap<>();
    // String unique = "";
    // for (int i = 0; i < s1.length(); i++) {
    //   if (!charIntMap.containsKey(s1.charAt(i))) {
    //     charIntMap.put(s1.charAt(i), -1);
    //     unique += s1.charAt(i);
    //   }
    // }

    // for (int i = 0; i < s2.length(); i++) {
    //   if (!charIntMap.containsKey(s2.charAt(i))) {
    //     charIntMap.put(s2.charAt(i), -1);
    //     unique += s2.charAt(i);
    //   }
    // }

    // for (int i = 0; i < s3.length(); i++) {
    //   if (!charIntMap.containsKey(s3.charAt(i))) {
    //     charIntMap.put(s3.charAt(i), -1);
    //     unique += s3.charAt(i);
    //   }
    // }

    // boolean[] usedNumbers = new boolean[10];
    // Cryaritmatic(unique, 0, charIntMap, usedNumbers, s1, s2, s3);
  }

  public static int getnum(String str, HashMap<Character, Integer> charIntMap)
  {
    String num="";
    for(int i=0;i<str.length();i++)
    {
        num=num+charIntMap.get(str.charAt(i));
    }
    return Integer.parseInt(num);
  }

  
  public static void Cryaritmatic(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,  String s1, String s2, String s3)
   {

    if(idx==unique.length())
    {
        int num1= getnum(s1, charIntMap);
        int num2= getnum(s2, charIntMap);
        int num3= getnum(s3, charIntMap);

        if(num1+num2==num3)
        {
            for(int i=0;i<26;i++)
            {
                char ch= (char)('a'+i);
                if(charIntMap.containsKey(ch))
                {
                    System.out.print(ch+" - "+ charIntMap.get(ch)+" ");
                }
            }
            System.out.println();
        }
        return ;
    
    }
	  char ch = unique.charAt(idx);
      for(int i=0;i<=9;i++)
      {
        if(usedNumbers[i]==false)
        {
            usedNumbers[i]=true;
            charIntMap.put(ch,i);
            Cryaritmatic(unique, idx+1, charIntMap, usedNumbers, s1, s2, s3);
            usedNumbers[i]=false; //backtrack
            charIntMap.put(ch,-1); //backtrack

        }
      }
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   static int counter=1;
   public static void friendspairing(int i, int n, boolean[] used, String asf) 
   {
    if(i>n)
    {
        System.out.println(counter+"."+ asf+" ");
        counter++;
        return ;
    }
    if(used[i]==true)
    {
        friendspairing(i+1, n, used, asf); //already used
    }
    else
    {
        used[i]=true; // node pre
        friendspairing(i+1, n, used, asf+"("+ i + ") "); // alone 
        for(int j=i+1;j<=n;j++) // avoiding permuatation by looping from i+1-> from next element && pairing 
        {
            if(used[j]==false)
            {
                used[j]=true; //edge pre
                friendspairing(i+1, n, used, asf+"("+i+","+j+")"); // coming in group 
                used[j]=false; //backtrack  edge post
            }
        }
        used[i]=false; //backtrack  node post 
    }
   }

}
