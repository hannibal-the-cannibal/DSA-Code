package Recursionlevel2.L015;
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int k = Integer.parseInt(br.readLine());
    HashMap<Character,Integer> map = new HashMap<>();
    for(int i=0;i<str.length();i++)
    {
        char ch= str.charAt(i);
        map.put(ch, -1);
    }

    // HashSet<Character> unique = new HashSet<>();
    // String ustr = "";
    // for (char ch : str.toCharArray()) {
    //   if (unique.contains(ch) == false) {
    //     unique.add(ch);
    //     ustr += ch;
    //   }
    // }
    Character spots[]= new Character[k];
    // klengeth1(0,ustr,0,k,spots);
    // klenegth2(1,k,ustr, new HashSet<>(),"");
    klength3(str, map,spots,0,0,k);

    
  }

  public static void klengeth1(int cc, String ustr, int ssf, int ts,  Character spots[] )
  {
    if(cc==ustr.length())
    {
        if(ssf==ts)
        {
            for(int i=0;i<spots.length;i++)
            {
                System.out.print(spots[i]);
            }
            System.out.println();
        }
        return ;
    }
    char ch = ustr.charAt(cc);
    for(int i=0;i<spots.length;i++) // if we wanted to avoid permutation we start loop from lastoccurance+1
    {
        if(spots[i]==null)
        {
            spots[i]=ch;
            klengeth1(cc+1, ustr, ssf+1, ts, spots);
            spots[i]=null; //backtrack
        }
    }
    klengeth1(cc+1, ustr, ssf+0, ts, spots); //no selection call
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////

  public static void klenegth2(int cc, int tc, String ustr, HashSet<Character> used, String asf)
  {
    if(cc>tc)
    {
        System.out.println(asf);
        return ;
    }
    for(int i=0;i<ustr.length();i++)
    {
        char ch = ustr.charAt(i);
        if(used.contains(ch)==false)
        {
            used.add(ch);
            klenegth2(cc+1, tc, ustr, used, asf+ch);
            used.remove(ch); //backtrack
        }
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void klength3(String str, HashMap<Character,Integer> map, Character spots[], int idx, int ssf, int ts)
  {
    if(idx==str.length())
    {
        if(ssf==ts)
        {
            for(int i=0;i<spots.length;i++)
            {
                System.out.print(spots[i]);
            }
            System.out.println();
        }
        return ;
    }
    char ch = str.charAt(idx);
    int lo = map.get(ch);
    for(int i=lo+1;i<spots.length;i++)
    {
        if(spots[i]==null)
        {
            spots[i]=ch;
            map.put(ch,i);
            klength3(str, map, spots, idx+1, ssf+1, ts);
            spots[i]=null;
            map.put(ch,lo);
        }
    }
    if(lo==-1)
    {
        klength3(str, map, spots, idx+1, ssf, ts);  // no call only if it hasnot appread in ans till now 
    }
  }




}