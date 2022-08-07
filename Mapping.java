package PISolver;
import java.util.ArrayList; 
import java.util.HashMap;
public class Mapping
{

	static ArrayList<Integer> list;
	static int ord;
	public Mapping(ArrayList<Integer> x,int order){list=x;ord=order-1;}
	public Mapping(){}
	static String bin(int n) 
	{ 
		String s="";
		for (int i =ord; i >= 0; i--) 
		{ 
			int k = n >> i; 
			if ((k & 1) > 0) 
				s+=1;
			else
				s+=0; 
		}
		return s;
	}
	public  HashMap<String,String>  mapping()
	{
		HashMap<String,String> map = new HashMap<>();
		for(int i=0;i<list.size();i++)
		{
			String x=list.get(i).toString();
			map.put(x,bin(list.get(i)));
		}
		return map;
	}
}
