package PISolver;
import java.util.ArrayList; 
public class combination 
{ 
	public static ArrayList<String> retcomb = new ArrayList<>();
	static void combinations(int arr[], int data[], int start,int end, int index, int r) 
	{ 
		String x="";
		if (index == r) 
		{ 
			for (int j=0; j<r; j++) 
			{
				x+=Integer.toString(data[j]);
				if(j!=r-1)
				{
					x+=",";
				}
			}
			retcomb.add(x);
			return; 
		} 
		for (int i=start; i<=end && end-i+1 >= r-index; i++) 
		{ 
			data[index] = arr[i]; 
			combinations(arr, data, i+1, end, index+1, r); 
		} 
	} 
	static void printCombination(int arr[], int n, int r) 
	{ 
		int data[]=new int[r]; 
		combinations(arr, data, 0, n-1, 0, r); 
	} 
	public static ArrayList<String> getcombinations(ArrayList<String> s)
	{
		int n=s.size();
		int arr[]= new int[n];
		for(int i=0;i<n;i++)
		{
			arr[i]=Integer.parseInt(s.get(i));
		}
		for(int i=1;i<=n;i++)
		{
			printCombination(arr,n,i);
		}
		return retcomb;
	}
} 

