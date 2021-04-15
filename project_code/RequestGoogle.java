import java.io.IOException;
import java.util.*;


public class RequestGoogle {
	
	/**
	 * Second step to deal with inputed keyword(s)
	 * We add some infos to the keyword and make a url to post to Google.
	 */
	
	private String movie;
	
	private String infoOfMovie ="";
	
	private String keywordToGoogle;
	
	public static List<UrlResult> Results= new ArrayList<UrlResult>();
	
	
	public RequestGoogle() {
		
		/**
		 * Constructor
		 * only function is to assign keyword to movie
		 */
		this.movie = IMDBQuery.searchKeyword;
		
	}
	
	public void Request() {
		
		/**
		 * Main method of this class.
		 * Calling other method is his only function.
		 */
		
		RequestDir();
		RequestWri();
		RequestSta();
		removeSimilar();
		
		
	}
	
	private void SetKeywordToGoogle() {
		
		/**
		 * This method is used to produce the composite and multiple keyword through adding modifying words and infos of movie.
		 */
		
		String erasesite1 = "+-site:www.imdb.com";
		
		String erasesite2 = "+-site:www.rottentomatoes.com";
		
		String erasesite3 = "+-site:wikipedia.org";
		
		String erasesite4 = "+-site:www.pinterest.com";
		
		String erasesite5 = "+-site:www.amazon.com";
		
		String decorations = "+\'film\'or\'series\'or\'television\'or\'movie\'";
		
		this.keywordToGoogle = movie + "+" + infoOfMovie.replace(" ", "+") + decorations 
				+ erasesite1 + erasesite2 + erasesite3 + erasesite4 + erasesite5;
		
	}
	
	private void RequestDir() {
		
		/**
		 * The method works on the Director data particularly
		 * 
		 * 1. set the directors' infos of the movie and call GoogleQuery, 
		 * 2. take the temporary data from it to clean and modify
		 * 3. transfer them into result array-list
		 * 
		 */
		
		int countOfElements = IMDBQuery.Directors.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Directors.get(i).name;
			double weightOfInfo = IMDBQuery.Directors.get(i).weight;
			SetKeywordToGoogle();
			
				try {
					List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
					
					for(int j =0; j<tempList.size();j++) {
						
						String topNameOfUrl = tempList.get(j).topName;
						String titleOfUrl = tempList.get(j).title;
						String url = tempList.get(j).url;
						String introsOfUrl = tempList.get(j).intros;
						double weightOfUrl = tempList.get(j).urlWeight;
						double finalWeight = weightOfUrl*weightOfInfo;
						
						//System.out.println(tempList);															//test line
						UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
						Results.add(tmpVar);
					}
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	private void RequestWri() {
		
		/**
		 * Same as above part, only different in working part.
		 */
		
		int countOfElements = IMDBQuery.Writers.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Writers.get(i).name;
			double weightOfInfo = IMDBQuery.Writers.get(i).weight;
			SetKeywordToGoogle();
			
			try {
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
				
				for(int j =0; j<tempList.size();j++) {
					
					String topNameOfUrl = tempList.get(j).topName;
					String titleOfUrl = tempList.get(j).title;
					String url = tempList.get(j).url;
					String introsOfUrl = tempList.get(j).intros;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
																			
					UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
					Results.add(tmpVar);
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void RequestSta() {
		
		/**
		 * Same as above part, only different in working part.
		 */
		
		
		int countOfElements = IMDBQuery.Stars.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Stars.get(i).name;
			double weightOfInfo = IMDBQuery.Stars.get(i).weight;
			SetKeywordToGoogle();
			
			try {
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
				
				for(int j =0; j<tempList.size();j++) {
					
					String topNameOfUrl = tempList.get(j).topName;
					String titleOfUrl = tempList.get(j).title;
					String url = tempList.get(j).url;
					String introsOfUrl = tempList.get(j).intros;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
																	
					UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
					Results.add(tmpVar);	
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void removeSimilar() {
		
		/**
		 * This method is going to remove the similar result in our data.
		 * While there are two same URL data in different result, we remove the weight-lighter one.
		 */
		
    	//***call this before rank elements
		
		
    	int index = 0;
    
    	while(index < Results.size()) {
    		int check = index+1;
    		
    		while (check < Results.size()) {
    			if(Results.get(index).topName.equals(Results.get(check).topName)) {
    				if(Results.get(index).weight >= Results.get(check).weight){
    					Results.remove(check);
    				}
    				else {
    					Results.remove(index);
    				}
    			}
    			else {
    				check++;
    				
    			}	
    		}
    		index++;
    	}
    }
}
