import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		long  time1, time2, time3, time4;											 //test line
		//String keyword = "top+gun";   									//test line
									  //test line
		
		Scanner sc = new Scanner(System.in);
		String keyword ="";
		while (sc.hasNextLine()) {
			keyword = sc.nextLine();
			time1 = System.currentTimeMillis();
			keyword = keyword.trim().replace(" ","+");
			//System.out.println(keyword);								   	   //test line
			try {
				try {
					IMDBQuery X = new IMDBQuery(keyword);
					X.query();
					time2 = System.currentTimeMillis();							   //test line
					
				}
				catch(FileNotFoundException fileNotFound) {
					keyword = "Shootem+Up";
					IMDBQuery X = new IMDBQuery(keyword);
					X.query();
				}
				time2 = System.currentTimeMillis();							   //test line
				//System.out.println(IMDBQuery.Directors);     				   //test line
				//System.out.println(IMDBQuery.Writers); 
				//System.out.println(IMDBQuery.Stars); 					   //test line
				System.out.println();								   		   //test line
				System.out.println("X Done");								   //test line
				System.out.println("第一階段"+(time2-time1)/1000+"秒");		   //test line
				System.out.println(); 									   //test line
				//GoogleQuery Y = new GoogleQuery();     					       //test line
				//Y.query();					   					   //test line
				//System.out.println("Done");
				//System.out.println();                                        //test line
				RequestGoogle Y = new RequestGoogle();
				Y.Request();
				time3 = System.currentTimeMillis();	
				System.out.println("Y Done");								   //test line
				System.out.println("第二階段"+(time3-time2)/1000+"秒");		   //test line
				System.out.println();						   		   		   //test line
				//System.out.println(RequestGoogle.Results); 				   //test line
				//System.out.println(RequestGoogle.Results.size()); 		   //test line
				//System.out.println();								   		   //test line
				//System.out.println("Z Start");								   //test line
				//System.out.println();								   		   //test line
				Sequence Z = new Sequence();
				Z.sortAndOutput();  
				//System.out.println(); 
				System.out.println("Z Done");							  	   //test line
				time4 = System.currentTimeMillis();	
				System.out.println("第三階段"+(time4-time3)/1000+"秒");		   //test line
				System.out.println("總共"+(time4-time1)/1000+"秒");            //test line
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sc.close();
	}
		
}
