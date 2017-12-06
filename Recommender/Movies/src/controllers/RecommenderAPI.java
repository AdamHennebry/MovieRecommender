package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import utils.Serializer;
import models.Movie;
import models.User;
import models.Rating;

public class RecommenderAPI {
	private Serializer serializer;
	
	public List<String> 		occupation=new ArrayList<String>();
	public List<Rating> 		ratings=new ArrayList<Rating>();
	public Map<Long, User>  	userIndex       = new HashMap<>();
	public Map<Long, Movie>   	movieIndex      = new HashMap<>();
	
	public RecommenderAPI(){}

	public RecommenderAPI(Serializer serializer){
		this.serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		serializer.read();
		occupation = (List<String>)		  serializer.pop();
		ratings = (List<Rating>)		  serializer.pop();
		movieIndex = (Map<Long, Movie>)   serializer.pop();
		Movie.counter = (Long) 			  serializer.pop();
		userIndex  = (Map<Long, User>)    serializer.pop();
		User.counter = (Long)			  serializer.pop();

	}

	public void store() throws Exception{
		serializer.push(User.counter);
		serializer.push(userIndex);
		serializer.push(Movie.counter);
		serializer.push(movieIndex);
		serializer.push(ratings);
		serializer.push(occupation);
		serializer.write(); 
	}
	public Collection<User> getUsers(){
		return userIndex.values();
	}
	public Collection<Movie> getMovies(){
		return movieIndex.values();
	}

	public Movie addMovie(String title, String year, String url)throws Exception{
		Movie movie = new Movie (title, year, url);
		long ID=movie.getMovieID();
		movieIndex.put(ID, movie);
		return movie;
	}
	public void removeMovie(Long movieID){
		movieIndex.remove(movieID);		
	}
	public List<String> listMovieMatches(String matchTitle){
		List<String> matchingTitles=new ArrayList<String>();
		int counter=0;
		for (long i=1;i<movieIndex.size();i++){
			String title=movieIndex.get(i).getTitle();
			if(title.contains(matchTitle)){
				matchingTitles.add(title);
				System.out.println(title + " " + counter);
			}
		}		
		return matchingTitles;
	}
	public Long getMovieByTitle(String searchTitle){
		Long ID=0l;
		for (long i=1;i<movieIndex.size();i++){
			String title=movieIndex.get(i).getTitle();
			if((title.equalsIgnoreCase(searchTitle))){
				ID =movieIndex.get(i).getMovieID();
			}
		}
		return ID;
	}

	
	public User addUser(String firstName, String lastName, int age, String gender, String occupation)throws Exception{
		User user =new User(firstName, lastName, age, gender,occupation);
		long ID=user.getUserID();
		userIndex.put(ID, user);
		return user;
	}
	public void removeUser(Long userID){
		userIndex.remove(userID);		
	}
	public Long getUserByName(String userName, String userLastName){
		Long ID=0l;
		for (long i=1;i<userIndex.size();i++){
			String firstName=userIndex.get(i).getFirstName();
			String lastName=userIndex.get(i).getLastName();
			if((userName.equalsIgnoreCase(firstName))&&(userLastName.equalsIgnoreCase(lastName))){
				ID =userIndex.get(i).getUserID();
			}
		}
		return ID;
	}
	public int getMovieAverageRating(Long movieID){
		int totalRating=0;
		int count=0;
		for (Rating r :ratings){
			if(r.getMovieID().equals(movieID)){
				totalRating+=r.getRating();
				count++;
			}
		}
		
		return totalRating/count;		
	}
	
	public void topRated() {
	}
}

	}
