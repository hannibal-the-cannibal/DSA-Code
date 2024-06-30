package Recursionlevel2.L004;
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    String str = scn.nextLine();
    int k = scn.nextInt();


    // HashSet<Character> unique = new HashSet<>();
    // String ustr = "";
    // for (char ch : str.toCharArray()) {
    //   if (unique.contains(ch) == false) {
    //     unique.add(ch);
    //     ustr += ch;
    //   }
    // }

    HashMap <Character ,Integer> unique1 = new HashMap<>();
    String ustr1 = "";
    for (char ch : str.toCharArray()) {
      if (unique1.containsKey(ch) == false) {
        unique1.put(ch,1);
        ustr1 += ch;
      }
      else{
        unique1.put(ch,unique1.get(ch)+1); 
      }
    }
   

    // combination1(0, ustr, 0, k, "");
    // combination2(ustr, 1, k, -1,"");
    combination3(ustr1, unique1, 0, k, "");
    
  }


  public static void combination1(int i, String ustr, int ssf, int ts, String asf ) {
    if(i==ustr.length())
    {
        if(ssf==ts)
        {
            System.out.println(asf);
        }
        return ;
    }
    char ch= ustr.charAt(i);
    
    combination1(i+1, ustr, ssf+1, ts, asf+ch);//include
    combination1(i+1, ustr, ssf, ts, asf);//exclude 

  }
  /////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void combination2(String ustr, int cs, int ts, int lc ,String asf )
  {
    if(cs>ts)
    {
        System.out.println(asf);
        return ;
    }

    for(int i=lc+1;i<ustr.length();i++)
    {
        char ch = ustr.charAt(i);
        combination2(ustr, cs+1, ts, i, asf+ch);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static void combination3(String ustr1, HashMap<Character,Integer> fmap, int idx, int k, String asf)
  {
    if(k<0)
    {
      return ;
    }
    if(idx==ustr1.length())
    {
      if(k==0)
      {
        System.out.println(asf);
      }
      return ;
    }
      char ch = ustr1.charAt(idx);
      if(fmap.get(ch)>0)
      {
         int currf= fmap.get(ch);
         fmap.put(ch,currf-1);
         combination3(ustr1, fmap, idx, k-1, asf+ch);
         fmap.put(ch,currf);
      }
      combination3(ustr1, fmap, idx+1, k, asf);


  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////

  




}