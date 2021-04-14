import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;



import java.util.*;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class GoogleQuery {

	public String url_google;
	
	public String Goolge = "https://www.google.com/search?q=";

	public String content;
	
	public GoogleQuery(){
		
		this.url_google = "https://www.google.com/search?q=top+gun+Tom+Scott+'film'or'series'or'television'or'movie'+-site:www.imdb.com+-site:www.rottentomatoes.com+-site:wikipedia.org+-site:www.pinterest.com+-site:www.amazon.com";	
	}
    
	
	public GoogleQuery(String searchKeyword){
		
		this.url_google ="https://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=20";	
	}

	

		private String fetchContent() throws IOException

		{
			String retVal = "";
	
			URL u = new URL(url_google);
	
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-agent","Chrome/7.0.517.44 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2)");
			
			
	
	
			InputStream in = conn.getInputStream();
	
			InputStreamReader inReader = new InputStreamReader(in,"utf-8");
	
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;
	
			while((line=bufReader.readLine())!=null)
			{
				retVal += line;
	
			}
			return retVal;
		}
	 
	public List<UrlTempResult> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}
		Document doc = Jsoup.parse(content);				
		Elements doc2 = doc.select("div.kCrYT");
		
		
		
		List<UrlTempResult> tempList = new ArrayList<UrlTempResult>();
		
		int numberOfUrl = 10;
		double count = 1;
		String url = "";
		String titleOfUrl = "";
		String topNameOfUrl = "";
		String introOfUrl = "";
		double urlWeight = (11-count)/10;
		for(Element li : doc2) {
			UrlTempResult temp;
			
			
			if (count %2 !=0) {
				url = li.select("a").attr("href");
				titleOfUrl = li.select("a").select(".vvjwJb").text();
				topNameOfUrl = li.select(".UPmit").text().substring(0,li.select(".UPmit").text().indexOf("›"));
				url = url.substring(url.indexOf("=")+1,url.indexOf("&sa"));
				
			}
			else{
				introOfUrl = li.select(".s3v9rd").text();
				
				temp = new UrlTempResult(topNameOfUrl, titleOfUrl, url, urlWeight, introOfUrl);
				tempList.add(temp);
			}
			if(count==numberOfUrl) {
				break;
			}
			count++;
		}
			
			return tempList; 
	}
}

