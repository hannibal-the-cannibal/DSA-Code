package HashMapL1.L001;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str= scn.nextLine();

        HashMap<Character,Integer> map= new HashMap<>();
        for(int i=0;i<str.length();i++)
        {
            char ch= str.charAt(i);
            if(map.containsKey(ch))
            {
                map.put(ch,map.get(ch)+1);
            }
            else
            {
                map.put(ch,1);
            }
        }

        char mfc= str.charAt(0);
        for(Character key: map.keySet())
        {
            if(map.get(key) > map.get(mfc))
            {
                mfc=key;
            }
        }
        System.out.print(mfc);


    }

}
