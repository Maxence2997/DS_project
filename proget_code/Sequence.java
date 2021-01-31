

import java.util.*;

public class Sequence {
	private List<UrlResult> lst;
	
	public Sequence(){
		this.lst = RequestGoogle.Results;
		
			
    }
	
	//quick sort
	public void sortAndOutput(){
		quickSort(0, lst.size()-1);
		//output();
		//output20();
		outputAllInfos();
		//System.out.println("Done");
	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm
		
			if  (leftbound > rightbound) 
			    return;
				
		
			double pivot = lst.get(rightbound).weight;
			int right = rightbound -1;
			int left = leftbound;
			int swapindex = leftbound;
			for(int i = left; i<=right;i++) {
				if (lst.get(i).weight > pivot) {    // key comparator
					swap(swapindex,i);
					swapindex++;
					
				}
				
			}
			swap(swapindex,rightbound);
			quickSort(leftbound,swapindex-1);
			quickSort(swapindex+1,rightbound);			
	}
	
	
	private void swap(int aIndex, int bIndex){
		UrlResult temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<lst.size();i++){
			UrlResult temp = lst.get(i);
			if(i>0)sb.append(" ");
			sb.append(temp.toString());
		}
		
		System.out.println(sb.toString());	
	}


	public void output20(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
	
		for(int i=0; i<lst.size();i++){
			UrlResult temp = lst.get(i);
			
			if(i>0)
				sb.append(" ");
			
			sb.append(temp.toString());
			
			if (i>=20) 
				break;
		}
		
		System.out.println(sb.toString());	
	}
	
	
	public void outputAllInfos() {
		
		System.out.print("Director(s):");
		for(int i = 0; i < IMDBQuery.Directors.size(); i++) {
			
			if(i==0) {
			System.out.print(IMDBQuery.Directors.get(i).name);
			}
			else {
				System.out.print(","+IMDBQuery.Directors.get(i).name);
			}
			
		}
		System.out.print("\n");
		
		
		System.out.print("Writer(s):");
		for(int i = 0; i < IMDBQuery.Writers.size(); i++) {
			
			if(i==0) {
			System.out.print(IMDBQuery.Writers.get(i).name);
			}
			else {
				System.out.print(","+IMDBQuery.Writers.get(i).name);
			}
		}
		System.out.print("\n");
		
		
		System.out.print("Stars(s):");
		for(int i = 0; i < IMDBQuery.Stars.size(); i++) {
			
			if(i==0) {
			System.out.print(IMDBQuery.Stars.get(i).name);
			}
			else {
				System.out.print(","+IMDBQuery.Stars.get(i).name);
			}
		}
		System.out.print("\n");
		output20();
	}
}
