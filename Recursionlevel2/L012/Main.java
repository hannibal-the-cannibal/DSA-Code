package Recursionlevel2.L012;
import java.io.*;
import java.util.*;

public class Main {

  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    Character[] spots = new Character[str.length()];
    HashMap<Character, Integer> lastOccurence = new HashMap<>();
    for(char ch: str.toCharArray()){
      lastOccurence.put(ch, -1);
    }

    generateWords2(0, str, spots, lastOccurence);

    // HashMap<Character, Integer> fmap = new HashMap<>();
    // for(char ch: str.toCharArray()){
    //   if(fmap.containsKey(ch)){
    //     fmap.put(ch, fmap.get(ch) + 1);
    //   } else {
    //     fmap.put(ch, 1);
    //   }
    // }

    // generateWords1(1, str.length(), fmap, "");
  }

  public static void generateWords1(int cs, int ts, HashMap<Character, Integer> fmap, String asf) 
  {
    if(cs>ts)
    {
        System.out.println(asf);
        return ;
    }
    for(char ch: fmap.keySet())
    {
        if(fmap.get(ch)>0)
        {
            fmap.put(ch, fmap.get(ch)-1);
            generateWords1(cs+1, ts, fmap, asf+ch);
            fmap.put(ch, fmap.get(ch)+1); // backtrack

        }
    } 
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void generateWords2(int cc, String str, Character[] spots,  HashMap<Character, Integer> lastOccurence)
  {
    if(cc==str.length())
    {
        for(int i=0;i<spots.length;i++)
        {
            System.out.print(spots[i]);
        }
        System.out.println();
        return ;
    }
    char ch = str.charAt(cc);
    int lo = lastOccurence.get(ch);
    for(int i=lo+1;i<spots.length;i++)
    {
        if(spots[i]==null)
        {
            spots[i]=ch;
            lastOccurence.put(ch,i);
            generateWords2(cc+1, str, spots, lastOccurence);
            spots[i]=null;
            lastOccurence.put(ch,lo);//backtrack

        }
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////


}
