package models;



public class Rating implements Comparable<Rating>{
	private Long  userID;
	private Long  movieID;
	private int rating;
	
	public Rating(Long userID, Long movieID, int rating){
		this.userID=userID;
		this.movieID=movieID;
		this.rating=rating;
	}
	
	public void setMovieID(Long movieID){
		this.movieID=movieID;
	}
	public void setUserID(Long userID){
		this.userID=userID;
	}
	public void setRating(int rating){
		this.rating=rating;
	}
	
	public Long getMovieID(){
		return movieID;
	}
	public Long getUserID(){
		return userID;
	}
	public int getRating(){
		return rating;
	}
	public String toString(){
		return 	"Movie ID : " + movieID
				+ "\nUser ID : " + userID
				+ "\nRating : " + rating;
	}
	@Override
	public int compareTo(Rating r) throws NullPointerException{
		int sortRating=this.movieID.compareTo(r.movieID);;
		return sortRating;
	}
	

}
