import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	/**
	 * @author Yang, Jyun-An
	 * @since JRE 14
	 */
	
	public static void main(String[] args) {
		
		//long  time1, time2, time3, time4;											 
		
		
		Scanner sc = new Scanner(System.in);
		String keyword ="";
		while (sc.hasNextLine()) {
			keyword = sc.nextLine();
			//time1 = System.currentTimeMillis();
			keyword = keyword.trim().replace(" ","+");
			
			try {
				try {
					IMDBQuery IMDB_A = new IMDBQuery(keyword);
					IMDB_A.query();
					//time2 = System.currentTimeMillis();							   
					
				}
				catch(FileNotFoundException fileNotFound) {
					keyword = "Shootem+Up";
					IMDBQuery IMDB_B = new IMDBQuery(keyword);
					IMDB_B.query();
				}
				//time2 = System.currentTimeMillis();							   
				
				//System.out.println("第一階段"+(time2-time1)/1000+"秒");		   
				
				
				RequestGoogle Google = new RequestGoogle();
				Google.Request();
				//time3 = System.currentTimeMillis();	
				
				//System.out.println("第二階段"+(time3-time2)/1000+"秒");		   
				
				Sequence Z = new Sequence();
				Z.sortAndOutput();  
				
				//time4 = System.currentTimeMillis();	
				//System.out.println("第三階段"+(time4-time3)/1000+"秒");		   
				//System.out.println("總共"+(time4-time1)/1000+"秒");           
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sc.close();
	}
		
}
