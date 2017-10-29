package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import controllers.RecommenderAPI;
import models.Movie;
import models.Rating;
import models.User;

public class ReadFile {
	
	
	private String[] splitString;
	File  datastore = new File(" ");
    Serializer serializer = new XMLSerializer(datastore);
    RecommenderAPI recommenderAPI = new RecommenderAPI(serializer);
	
    public void readMovies() throws Exception{		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(new File("")));
		try {
			String inputLine;			
			while ((inputLine = in.readLine()) != null){
				splitString = inputLine.split("[|]");
				Long movieID=Long.parseLong(splitString[0]);
				String title=splitString[1];
				String year=splitString[2];
				String url=splitString[3];
				recommenderAPI.movieIndex.put(movieID, new Movie(title, year, url));
				recommenderAPI.store();
			}
		} finally {
			if (in != null)
				in.close();
		}	
	}
    public void readUsers() throws Exception{		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(new File(""));
			String inputLine;			
			while ((inputLine = in.readLine()) != null){
				splitString = inputLine.split("[|]");
				Long userID=Long.parseLong(splitString[0]);
				String firstName=splitString[1];
				String lastName=splitString[2];
				int age=Integer.parseInt(splitString[3]);
				String gender=splitString[4];
				String occupation=splitString[5];
				User user = new User(firstName, lastName, age, gender, occupation);
				recommenderAPI.userIndex.put(userID, user);
				recommenderAPI.store();
			}
		} finally {
			if (in != null)
				in.close();
		}	
	}
    public void readRatings() throws Exception{		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(new File(")));
		try {
			String inputLine;			
			while ((inputLine = in.readLine()) != null){
				splitString = inputLine.split("[|]");
				Long userID=Long.parseLong(splitString[0]);
				Long movieID=Long.parseLong(splitString[1]);
				int rating=Integer.parseInt(splitString[0]);
				recommenderAPI.ratings.add(new Rating(userID,movieID,rating));
				recommenderAPI.store();
			}
		} finally {
			if (in != null)
				in.close();
		}	
	}
    public void readOccupation() throws Exception{		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(new File(" ")));
		try {
			String inputLine;			
			while ((inputLine = in.readLine()) != null){
				
				recommenderAPI.occupation.add(inputLine);
				recommenderAPI.store();
			}
		} finally {
			if (in != null)
				in.close();
		}	
	}
	
}
