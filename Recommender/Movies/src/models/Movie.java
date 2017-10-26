package models;

public class Movie {
	public static Long   counter = 1l;
	  
	private Long  movieID;
	private String title;
	private String year;
	private String url;
	
	public Movie(String title, String year, String url){
		this.movieID=counter++;
		this.title=title;
		this.year=year;
		this.url=url;
	}
	
	public void setTitle(String title){
			this.title=title;
	}
	public void setYear(String year){
		this.year=year;
		
	}
	public void setUrl(String url){
		this.url=url;
	}
	
	public long getMovieID(){
		return movieID;
	}
	public String getTitle(){
		return title;
	}
	public String getYear(){
		return year;
	}
	public String getUrl(){
		return url;
	}
	
	public String toString(){
		return 	"Movie ID : " + movieID
				+ "\nMovie Title : " + title
				+ "\nMovie year : " + year
				+ "\nMovie url : " + url;
	} 
}
