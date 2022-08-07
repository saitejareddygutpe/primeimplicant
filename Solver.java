package PISolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Solver implements Requirements
{
	static HashMap<String,String> fin = new HashMap<>();
	int countfin=0;
	ArrayList<Integer> list;
	ArrayList<String> list1;
	static HashMap<String,String> map1 = new HashMap<>();
	 public Solver(ArrayList<Integer> x){list=x;}
	 public Solver(){}
     public static boolean strdif(String s1,String s2)
     { 
    	 int count=0;
    	 for(int i=0;i<s1.length();i++)
    	 {
    		 if(s1.charAt(i)!=s2.charAt(i))
    		 {
    			 count++;
    		 }
    	 }
    	return count==1;
     }
     public HashMap<String,String> solver(HashMap<String,String> a)
     {
    	 int i,j,q=0;
    	 ArrayList<String> val = new ArrayList<>();
    	 ArrayList<String> key = new ArrayList<>();
    	 HashMap<String,String> ret = new HashMap<>();
    	 HashMap<String,String> temp = new HashMap<>();
    	 for( Map.Entry<String,String> x : a.entrySet() )
 		{
    		 key.add(x.getKey());
    		 val.add(x.getValue());
 		}
    	 for(i=0;i<val.size();i++)
   		{
    		int count=0;
   			for(j=0;j<val.size();j++)
   			{
   				if(strdif(val.get(i),val.get(j)))
   				{	
   					count+=1;
   					ret.put(key.get(i)+","+key.get(j),index(val.get(i),val.get(j)));
   				}
   			}
   			if(count==0)
   			{
   				val.set(i,val.get(i)+" ");
   				if(!fin.containsValue(val.get(i)))
   				{
   					fin.put(key.get(i),val.get(i));
   				}
   				if(!temp.containsValue(val.get(i)))
   				System.out.println(key.get(i)+"\t  "+val.get(i));
   				temp.put(key.get(i),val.get(i));
   			}
   			else
   			{
   				q+=1;
   				val.set(i,val.get(i)+"  <**>");
   				if(!temp.containsValue(val.get(i)))
   				System.out.println(key.get(i)+"\t  "+val.get(i));
   				temp.put(key.get(i),val.get(i));
   			}
   		}
    	 if(q==0)
			{
				System.out.println("-----------------------------------\nPRIME IMPLICANTS OF GIVEN MINTERMS:\n");
				System.out.println("Minterms");
				for( Map.Entry<String,String> x : fin.entrySet() )
	 			{
					System.out.println(x.getKey()+"\t\t"+x.getValue());
	 			}
				System.out.println("------------------------------");
				System.out.println("Total No of prime implicants:"+fin.size());
				System.out.println("------------------------------");
				countfin+=1;
			}
    	return ret;
     }
     public int getcount()
     {
    	 return countfin;
     }
     public static HashMap<String,String> getfin()
     {
    	 return fin;
     }
}


