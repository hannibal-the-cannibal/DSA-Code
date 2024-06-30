package HashMapL2.L006;

import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Main {
    
	public static int SubstringKuniquechar(String str, int k){
		int ans=0;
        if(k==1)
        {
            return solutionfork1(str);
        }
        HashMap<Character,Integer> bmap= new HashMap<>();
        HashMap<Character,Integer> smap= new HashMap<>();
        int is=-1;
        int ib=-1;
        int j=-1;

        while(true)
        {
            boolean f1=false;
            boolean f2= false;
            boolean f3= false;

            while(ib<str.length()-1)
            {
                f1=true;
                ib++;
                char ch= str.charAt(ib);
                bmap.put(ch, bmap.getOrDefault(ch, 0)+1);

                if(bmap.size()==k+1)
                {
                    removeinmap(bmap, ch);
                    ib--;
                    break;
                }
            }

            while(is<ib)
            {
                f2=true;
                is++;
                char ch= str.charAt(is);
                smap.put(ch, smap.getOrDefault(ch, 0)+1);

                if(smap.size()==k)
                {
                    removeinmap(smap, ch);
                    is--;
                    break;
                }
            }

            while(j<is)
            {
                f3=true;
                if(bmap.size()==k && smap.size()==k-1)
                {
                    ans+=(ib-is);
                }

                j++;
                char ch = str.charAt(j);
                removeinmap(smap, ch);
                removeinmap(bmap, ch);

                if(smap.size()<k-1 || bmap.size()<k)
                {
                    break;
                }
            }

            if(f1==false && f2==false && f3==false)
            {
                break;
            }
        }
        return ans;

	}

    public static int solutionfork1 (String str)
    {
        HashMap<Character,Integer> map = new HashMap<>();
        int i=-1;
        int j=-1;
        int ans=0;
        while(true)
        {
            boolean f1= false;
            boolean f2= false;

           
            while(i<str.length()-1)
            {
                f1=true;
                i++;
                char ch= str.charAt(i);
                map.put(ch,map.getOrDefault(ch, 0)+1);

                if(map.size()==2)
                {
                    removeinmap(map, ch);
                    i--;
                    break;
                }
            }

            while(j<i)
            {
                f2=true;
                if(map.size()==1)
                {
                    ans+=(i-j);
                }
                j++;
                char ch= str.charAt(j);
                removeinmap(map, ch);

                if(map.size()==0)
                {
                    break;
                }

            }

            if(f1==false && f2==false)
            {
                break;
            }


        }
        return ans;
    }

    public static void removeinmap(HashMap<Character,Integer> map , char ch)
    {
        if(map.get(ch)==1)
        {
            map.remove(ch);
        }
        else{
            map.put(ch,map.get(ch)-1);
        }
    }
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
		System.out.println(SubstringKuniquechar(str,k));


        // int n = scn.nextInt();
        // int[] arr = new int[n];
        // for(int i = 0 ; i  < n; i++){
        //     arr[i] = scn.nextInt();
        // }

        // System.out.println(equivalentsubarray(arr));
	}

    public static int equivalentsubarray(int arr[])
    {
        HashSet<Integer> uni= new HashSet<>();
        for(int val: arr)
        {
            uni.add(val);
        }

        int k= uni.size();
        int i=-1;
        int j=-1;
        int ans=0;
        HashMap<Integer,Integer> map = new HashMap<>();

         while(true)
         {
            boolean f1=false;
            boolean f2= false;
             while(i<arr.length-1)
             {
                f1=true;
                i++;
                map.put(arr[i], map.getOrDefault(arr[i],0)+1);

                if(map.size()==k)
                {
                    ans+= (arr.length-i);
                    break;
                }
             }

             while(j<i)
             {
                f2=true;
                j++;
                if(map.get(arr[j])==1)
                {
                    map.remove(arr[j]);
                }
                else
                {
                    map.put(arr[j], map.get(arr[j])-1);
                }

                if(map.size()==k)
                {
                    ans+= (arr.length-i);
                }
                else
                {
                    break;
                }
             }

             if(f1==false && f2==false)
             {
                break;
             }
         }
         return ans;
    }

}


