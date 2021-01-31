

public class UrlResult {
	
	public String topName;
	public String title;
	public String url;
	public String intros;
	public double weight;
	
	
    
    public UrlResult(String topName,String title,String url, String intros, double weight){
		
    	this.topName = topName;
    	this.title = title;
    	this.url = url;
    	this.intros = intros;
    	this.weight = weight;
    	
    }
    
    @Override
    public String toString(){
    	return "["+topName+","+title+","+url+","+intros+","+weight+"]";
    }
    
    public String gettopName()
    {
    	return topName;
    }
    
    public String getTitle()
    {
    	return title;
    }
    
    public String getUrl()
    {
    	return url;
    }
    
   
    public String getIntros()
    {
    	return intros;
    }
    
    public double getWeight()
    {
    	return weight;
    }
}