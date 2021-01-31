
public class UrlTempResult {


		
		public String topName;
		public String title;
		public String url;
		public double urlWeight;
		public String intros;
		
	    public UrlTempResult(String topname, String title, String url, double weight, String intros){
	    	
	    	this.topName = topname;
	    	this.title = title;
	    	this.url = url;
	    	this.urlWeight = weight;
	    	this.intros = intros;
	    	
	    }
	    
	    @Override
	    public String toString(){
	    	
	    	return "["+topName+","+title+","+url+","+urlWeight+"]";
	    	
	    }
	    
	    
	    public String getTopName(){
	    	
	    	return topName;
	    	
	    }
	    
	    public String getTitle() {
	    	
	    	return title;
	    }
	    
	    public String getUrl(){
	    	
	    	return url;
	    	
	    }
	    
	    
	    public double getWeight(){
	    	
	    	return urlWeight;
	    	
	    }
	    
	    public String getIntros(){
	    	
	    	return intros;
	    	
	    }
	}

