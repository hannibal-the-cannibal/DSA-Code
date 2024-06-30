package HashMapL2.L005;
import java.util.*;

public class Main {

	public static int Countsubstringunique(String s) {
		int n = s.length();
        int maxlen=0;
        HashSet<Character> set= new HashSet<>();
        int i=0, j=0;

        while(j<n)
        {
            while(set.contains(s.charAt(j)))
            {
                set.remove(s.charAt(i));
                i++;
            }

            maxlen+=(j-i+1);
            set.add(s.charAt(j));
            j++;
        }
        return maxlen;
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int kdistinctchar(String str, int k){
		int ans=0;
        HashMap<Character,Integer> map= new HashMap<>();
        int i=-1;
        int j=-1;
        boolean f1,f2;

        while(true)
        {
            f1=false;
            f2=false;
            while(i<str.length()-1)
            {
                f1=true;
                i++;
                char ch= str.charAt(i);
                map.put(ch,map.getOrDefault(ch, 0)+1);

                if(map.size()<k)
                {
                    continue;
                }
                else if( map.size()==k)
                {
                    int len=i-j;
                    ans=Math.max(ans,len);
                }
                else
                {
                    break;
                }
            }

            while(j<i)
            {
                f2=true;
                j++;
                char ch= str.charAt(j);
                if(map.get(ch)==1)
                {
                    map.remove(ch);
                }
                else
                {
                    map.put(ch,map.get(ch)-1);
                }

                if(map.size()>k)
                {
                    continue;
                }
                else if(map.size()==k)
                {
                    int len=i-j;
                    ans=Math.max(ans,len);
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
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
        int k = scn.nextInt();
		// System.out.println(Countsubstringunique(str));
        System.out.println(kdistinctchar(str,k));
	}

}
