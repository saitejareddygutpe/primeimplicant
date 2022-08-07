package PISolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
public class McCluskeyAlgorithm extends Solver implements Requirements
{
	private static HashMap<String,String> fin_eprime_result = new HashMap<>();
	private static HashMap<String,String> eprime_res = new HashMap<>();
	static ArrayList<Integer> list=new ArrayList<>();
	static HashMap<String,String> fin_ ;
	static HashMap<Integer,Integer> eprime = new HashMap<>();
	public static void boolform(String s,int ord,int a)
	{
		System.out.print("(");
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='0')
			{
				System.out.print((char)a+"'");
			}
			else if(s.charAt(i)=='1')
			{
				System.out.print((char)a);
			}
			a+=1;
		}
		System.out.print(") ");
	}
	static HashMap<Integer,Integer> geteprime()
	{
		HashMap<Integer,Integer> tempmap = new HashMap<>();
		for(int i=0;i<list.size();i++)
		{
			tempmap.put(list.get(i),0);
		}
		return tempmap;
	}
	static String[] getfinlist(HashMap<String,String> fin)
	{
		int i=0;
		String[] finlist = new String[fin.size()];
		for(Entry<String,String> j : fin.entrySet())
		{
			finlist[i]=j.getKey();
			i++;
		}
		return finlist;
	}
	static ArrayList<Integer> getlist(String s)
	{
		ArrayList<Integer> ret = new ArrayList<>();
		s=s+",";
		String[] x = s.split(",");
		for(int i=0;i<x.length;i++)
		{
			ret.add(Integer.parseInt(x[i]));
		}
		return ret;
	}
	public static boolean comparator(String a,String b)
	{
		char A[] = a.toCharArray();
		char B[] = b.toCharArray();
		Arrays.sort(A);
		Arrays.sort(B);
		return Arrays.equals(A,B);
	}
	public static HashMap<String,String> eprimesolver(HashMap<String,String> fin)
	{
		HashMap<Integer,Integer> eprime =geteprime();
		ArrayList<String> ret = new ArrayList<>();
		String[] s = getfinlist(fin);
		String x = null;
		for( Entry<Integer,Integer> j : eprime.entrySet() )
		{
			for(int i=0;i<s.length;i++)
			{
				if(getlist(s[i]).contains(j.getKey()))
				{
					x=s[i];
					j.setValue(j.getValue()+1);
				}
			}
			if(j.getValue()==1)
			{
				if(!ret.contains(x))
				{
					ret.add(x);
				}
			}
		}
		for(Entry<String,String> j : fin.entrySet())
		{
			if(ret.contains(j.getKey()))
			{
				fin_eprime_result.put(j.getKey(),j.getValue());
				eprime_res.put(j.getKey(),j.getValue());
			}
		}
		return eprime_res;
	}
	public static HashMap<String,String> eprime_checker(HashMap<String,String> inp)
	{
		int c=0;
		HashMap<String,String> reteprime_checker = new HashMap<>();
		HashMap<String,String> checker=getfin();
		ArrayList<String> remained= new ArrayList<>();
		ArrayList<String> returned= new ArrayList<>();
		HashMap<Integer,Integer> eprime = geteprime();
		String[] s = new String[inp.size()];
		for(Entry<String,String> i :inp.entrySet())
		{
			s[c]=i.getKey();
			c++;
		}
		for(Entry<Integer,Integer> i :eprime.entrySet())
		{
			for(int j=0;j<s.length;j++)
			{
				if(getlist(s[j]).contains(i.getKey()))
				{
					i.setValue(i.getValue()+1);
				}
			}
		}
		for(Entry<Integer,Integer> i :eprime.entrySet())
		{
			if(i.getValue()==0)
			{
				remained.add(Integer.toString(i.getKey()));
			}
		}
		returned=combination.getcombinations(remained);
		if(returned.size()==1)
		{
			for(Entry<String,String> i :checker.entrySet())
			{	
				String[] x_= i.getKey().split(",");
				for(int j=0;j<x_.length;j++)
				{
					if(Integer.parseInt(x_[j])==Integer.parseInt(returned.get(0)))
					{
						fin_eprime_result.put(i.getKey(),i.getValue());
					return reteprime_checker;
					}
					
				}
			}
		}
		for(Entry<String,String> i :checker.entrySet())
		{	
			for(int j=0;j<returned.size();j++)
			{
				if(comparator(i.getKey(),returned.get(j)))
				{
					reteprime_checker.put(i.getKey(),i.getValue());
				}
			}
		}
		return reteprime_checker;
	}
	public static void main(String[]args)
	{
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("\t\t\tPRIME-IMPLICANT SOLVER\n\nNotation:\n (i)  <**> Implicant\n (ii)        PrimeImplicant\n\n");
			System.out.println("Enter order");
			int ord=in.nextInt();
			System.out.println("Enter Minterms");
			String input_= in.next();
			input_+=",x";
			String[] input=input_.split(",");
			int n= input.length;
			for(int i=0;i<n-1;i++)
			{
				int temp=Integer.parseInt(input[i]);
				list.add(temp);
			}
			Mapping a =new Mapping(list,ord);
			HashMap<String,String> x_=a.mapping();
			Solver b = new Solver(list);
			System.out.println("STEP 1:\nMinterms\n");
			HashMap<String,String> x =b.solver(x_);
			x_.clear();
			for(int i=0;i<10;i++)
			{
				if(b.getcount()!=0)
					break;
				System.out.println("\nSTEP "+(i+2)+":\n");
				System.out.println("Minterms");
				HashMap<String,String> f =new HashMap<>();
				f=b.solver(x);
				x=f;
			}
			fin_= Solver.getfin();
			HashMap<String,String> temp0=eprimesolver(fin_); 
			HashMap<String,String> temp;
			int ctemp=0;
			for(int i=0;i<3;i++)
			{	
				temp = eprimesolver(temp0);
				temp0=temp;
				if(i>0 && ctemp==temp.size())
				{
					break;
				}
				HashMap<String,String> temp1=eprime_checker(temp0);
				if(i==0 && temp1.size()==1)
				{
					Map.Entry<String, String> entry = temp1.entrySet().iterator().next();
					fin_eprime_result.put(entry.getKey(),entry.getValue());
					break;
				}
				ctemp=temp1.size();
				temp0=temp1;
			}
			System.out.println("Extracted Essential Primes:\n\nMinterms");
			for(Entry<String,String> j : fin_eprime_result.entrySet())
			{
				System.out.println(j.getKey()+"\t\t"+ ""+j.getValue());
			}
			int c=0;
			System.out.println("-------------------------------------");
			System.out.println("Total No of Essential prime implicants:"+fin_eprime_result.size());
			System.out.println("--------------------------------------");
			int choice = Requirements.choicepreference(ord);
			System.out.println("\nMINIMAL BOOLEAN FORM: ");
			for( Map.Entry<String,String> j : fin_eprime_result.entrySet())
				{
					c+=1;
					boolform(j.getValue(),ord-1,choice);
					if(c!=fin_eprime_result.size())
						System.out.print("+ ");
				}
		}
		System.out.println();
	}	
}

//3,4,5,6,8,13,14
//0,1,4,9,10,13,14,15
//0,1,2,3,8,9,10,13,14
//0,1,2,8,10,11,14,15 -- MorrisMano prob(1)
//1,4,6,7,8,9,10,11,15 ---MorrisMano prob(2)