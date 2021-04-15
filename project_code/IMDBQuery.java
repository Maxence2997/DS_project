import java.io.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;


public class IMDBQuery {
	
	/** This class is the first step to deal with the keyword(s) from user.
	 * We post the keywords to IMDB.com for the information and store them in static array-lists. 
	 */

	public static String searchKeyword;
	
	public String url;
	
	public String content;
	
	public String IMDb = "https://www.imdb.com";
	
	public static List<Keyword> Directors = new ArrayList<Keyword>();
	
	public static List<Keyword> Writers = new ArrayList<Keyword>();
	
	public static List<Keyword> Stars = new ArrayList<Keyword>();
	

	
	public IMDBQuery(String searchKeyword){
		/** 
		 *  Constructor
		 *  Initialize the class with inputed keyword
		 */
		
		IMDBQuery.searchKeyword=searchKeyword;

		this.url = "https://www.imdb.com/find?q="+searchKeyword+"&oe=utf8&num=20";

		
	}

	
	 public String fetchContent_url() throws IOException{
		 /**
		  *  This method is used to post the url with keyword to google ( as searching in IMDB page)
		  *  @return  the first result(URL) of searching 
		  */
		 
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String retVal = "";
			String line = null;
			
			while ((line = br.readLine()) != null){
				
			    retVal = retVal + line + "\n";
			    
			}
	
			int indexOfPhoto = retVal.indexOf("primary_photo");
			int indexOfhref = retVal.indexOf("href",indexOfPhoto);
			int indexOfclose = retVal.indexOf(">",indexOfhref);
			 
			String searchResult = IMDb + retVal.substring(indexOfhref+5, indexOfclose).replace("\"","");
			
			return searchResult; 
			
	    }
	    
	private String fetchContent() throws IOException{
		
		/**
		 * This method is used to acquire the source code of the page which we founded by fetchContent_url()
		 * @throws if no found in IMDB.com, throw to a specific page which is the movie we love the most
		 * @return the source code of the page
		 */
		
		String retVal = "";
		URL u;
		URLConnection conn;
		InputStream in;
		InputStreamReader inReader;
		BufferedReader bufReader ;
		try {
			u = new URL(fetchContent_url());
			conn = u.openConnection();
			conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
			in = conn.getInputStream();
			inReader = new InputStreamReader(in,"utf-8");
			bufReader = new BufferedReader(inReader);
		}
		catch(UnknownHostException e) {
			u = new URL("https://www.imdb.com/title/tt0465602/");
			conn = u.openConnection();
			conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
			in = conn.getInputStream();
			inReader = new InputStreamReader(in,"utf-8");

			bufReader = new BufferedReader(inReader);
		}
		
		String line = null;

		while((line=bufReader.readLine())!=null){
			
			retVal += line;

		}
		
		return retVal;
	}
	
	public void query() throws IOException{
		
		/** 
		 * This method is used to analyze the content of the page.
		 * Get the infos of directors, writers and stars and store them in temporary arrays.
		 * Then, call addList() to add infos into static array-lists with given weight. 
		 * 
		 */
		
		if(content==null) {
			
			content=fetchContent();
			
		}
	
		Document doc=Jsoup.parse(content);
		
		
		
		Elements doc2=doc.select(".credit_summary_item");
		String doc3=doc2.text();
														
		
		String director = "";
		String writers = "";
		String stars = "";
		String[] arrayDirector = new String[3];
		String[] arrayWriter = new String[3];
		String[] arrayStar = new String[3];
		
		try {
			if (doc.select(".inline").text().contains("Director")) {
				if (doc.select(".inline").text().contains("Writer")) {
					director = doc3.substring(doc3.indexOf(":")+1,doc3.indexOf("Writer"));
				}
				else {
					director = doc3.substring(doc3.indexOf(":")+1,doc3.indexOf("Star"));
				}
			}
			
			if (doc.select(".inline").text().contains("Writer")) {
				writers = doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,doc3.indexOf("Stars"));
				
				if(writers.contains("|")) {
					writers=doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,
							doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":")+1)+1));
				}
			
				while (writers.contains("(")){
				
					int index1=writers.indexOf("(");
					int index2=writers.indexOf(")");
					String doc4=writers.substring(0,index1);
					String doc7=writers.substring(index2+1, writers.length());
					writers=doc4+doc7;	
					}
			}
			
			if (doc.select(".inline").text().contains("Star")) {
				stars=doc3.substring(doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1,
					  doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1));
			}
			
			
			arrayDirector = director.split(",");
			arrayWriter = writers.split(",");
			arrayStar = stars.split(",");
		}catch(StringIndexOutOfBoundsException e) {
			arrayDirector[0] = "Michael Davis";
			arrayWriter[0] = "Michael Davis";
			arrayStar[0] = "Clive Owen";
			arrayStar[1] = "Monica Bellucci";
			arrayStar[2] = "Paul Giamatti";
			
		}
							
		addList(arrayDirector,Directors,"directors");
		addList(arrayWriter,Writers,"writer");
		addList(arrayStar,Stars,"stars");
		  			
		return;
	}
	
		private void addList(String [] inputArray, List<Keyword> outputList,
												String type){
			/**
			 * This method is used to add item which are in the temporary array into the static array-lists with given weight.
			 * @param inputArray a temporary array to store the infos caught from movie's infos page.
			 * @param outputList a static array-list to store movie's infos 
			 * @param the type of infos decide which the weight are going to be inputed. 
			 */
			
			double typeWeight;
			double[] weightDir = {1, 0.7, 0.65};
			double[] weightWri = {1, 0.85, 0.8};
			double[] weightSta = {1, 0.9, 0.85};
			
			if (type.equals("directors")) {
				
				typeWeight = 2;
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightDir[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
			}
			
			else if (type.equals("writer")) {
				
				typeWeight = 1.75;	
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightWri[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
			}
			
			else {
				
				typeWeight = 1.66;
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightSta[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
				
			}
			return;
		}
			
}
	