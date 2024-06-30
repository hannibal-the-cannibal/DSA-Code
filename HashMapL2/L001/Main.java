package HashMapL2.L001;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    // int n = scn.nextInt();
    
    //  HashMap<String,String> map= new HashMap<String, String>();
    //  for(int i=0;i<n;i++)
    //  {
    //     map.put(scn.next(), scn.next());
    //  }

    //  findcount(map);

    int noofpairs_src_des = scn.nextInt();
    HashMap<String, String> map = new HashMap<>();
    for (int i = 0; i < noofpairs_src_des; i++) {
        String s1 = scn.next();
        String s2 = scn.next();
        map.put(s1, s2);	
    }

    itenary(map);
  }

  public static void findcount(HashMap<String, String> map)
  {
     HashMap<String, HashSet<String>> tree= new HashMap<>();
     String ceo="";

     for(String emp: map.keySet()){
        String man=map.get(emp);

        if(man.equals(emp))
        {
            ceo=man;
        }
        else
        {
            if(tree.containsKey(man))
            {
                HashSet<String> emps= tree.get(man);
                emps.add(emp);
            }
            else
            {
                HashSet<String> emps= new HashSet<>();
                emps.add(emp);
                tree.put(man,emps);
            }
        }
     }

     HashMap<String,Integer> result= new HashMap<>();
     getsize(tree,ceo, result);
     System.out.println(result);
  }

  public static int getsize( HashMap<String, HashSet<String>> tree, String man,HashMap<String,Integer> result)
  {
    if(tree.containsKey(man)==false)
    {
        result.put(man,0);
        return 1;
    }
     int size=0;
     for(String emp: tree.get(man))
     {
        int cs= getsize(tree,emp,result);
        size+=cs;
     }
     result.put(man,size);
     return size+1;
  }

  //////////////////////////////////////////////////////////////////////////////////////////

  public static void itenary(HashMap<String, String> map)
  {
    HashMap<String, Boolean> parent= new HashMap<>();
    for(String src: map.keySet())
    {
        String dest= map.get(src);

        parent.put(dest,false);
        if(parent.containsKey(src)==false)
        {
            parent.put(src,true);
        }
    }

    String src="";
    for(String pot: parent.keySet())
    {
        Boolean val= parent.get(pot);
        if(val==true)
        {
            src=pot;
            break;
        }
    }

    while(true)
    {
        if(map.containsKey(src))
        {
            System.out.print(src+" -> ");
            src=map.get(src); // making cureent destination point as next starting point 
        }
        else
        {
            System.out.print(src+" . ");
            break;
        }
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////





}