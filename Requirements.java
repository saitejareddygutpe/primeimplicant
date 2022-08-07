package PISolver;
import java.util.Scanner;

public interface Requirements 
{
	default String index(String s1,String s2)
    {
   	 int i;
   	 String x="";
   	 for(i=0;i<s1.length();i++)
   	 {
   		 if(s1.charAt(i)!=s2.charAt(i))
   		 {
   			 
   			 x=x+s1.substring(0,i)+"-";
   			 x=x+s1.substring(i+1,s1.length());
   			 break;
   		 }
   	 }
   	 return x;
    }
	public static void boolform(String s,int ord,int a)
	{
		
	}
	public static int choicepreference(int ord)
	{
		int a=0;
		try (Scanner in = new Scanner(System.in)) 
		{
			do {
			if(ord<=4)
				System.out.println("Select preference of Output:\n1.(a)- series\n2.(p)-series\n3.(w)-series\n");
				else
					System.out.println("Select preference of Output:\n1.(a)- series\n2.(p)-series\n");
				int choice=in.nextInt();
				switch(choice)
				{
					case 1:
							a=97;
							break;
					case 2:
							a=112;
							break;
					case 3:
							if(ord<=4)
							{
								a=119;
								break;
							}
							else
							{
								a=-1;
								break;
							}
					default:
							a=-1;
							break;
				}
			}
			while(a==-1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return a;
	}
}
