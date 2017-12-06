package controllers;
import java.util.Scanner;
import java.io.File;
import java.util.List;

import utils.ReadFile;
import utils.Serializer;
import utils.XMLSerializer;

public class Main {
	//datastore filename needed..
	File  datastore = new File("");
    Serializer serializer = new XMLSerializer(datastore);
    
    RecommenderAPI recommenderAPI = new RecommenderAPI(serializer);

	private Scanner input;
	private ReadFile read;
	Main(){	
		input=new Scanner(System.in)
		read=new ReadFile();
	}
	
	public static void main (String args[])throws Exception{
		Main run =new Main();		
		run.run();
		
    }
	private void run()throws Exception{		
		if (datastore.isFile())	    {
			recommenderAPI.load();
	    }
		else{
			read.readMovies();
			read.readOccupation();
			read.readUsers();
			read.readRatings();
		}
		runMenu();
		recommenderAPI.store();
	}
	
	public int initialMenu(){
		System.out.println("Menu");
		System.out.println("----");
		System.out.println("1) Log in");
		System.out.println("2) Create a new account");
		System.out.println("0) Exit");
		System.out.println("------------------");
		int option=input.nextInt();	    
		return option;
		
	}
	
	private void runMenu() throws Exception{
        int option = initialMenu();
        while (option != 0){

            switch (option){
               case 1:    login();
               			  break;
               case 2:    addUser();
               			  try {
               				  recommenderAPI.store();
               			  } catch (Exception e) {
               				  e.printStackTrace();
               			  }	
		    }
            
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
            //display the main menu again
            option = initialMenu();
        }
    }
	public void addUser() throws Exception{
		input.nextLine();
		System.out.println("first name");
		String firstName=input.nextLine();
		System.out.println("last name");
		String lastName =input.nextLine();
		System.out.println("age");
		int age=input.nextInt();
		input.nextLine();
		System.out.println("gender");
		String gender =input.nextLine();
		System.out.println("occupation");
		String occupation =input.nextLine();
		recommenderAPI.addUser(firstName, lastName, age, gender,occupation);
		recommenderAPI.store();
		runMenu();
	}
	
	public void login() throws Exception{
		input.nextLine();
		//System.out.println(recommenderAPI.getUsers());
		System.out.println("first name");
		String firstName=input.nextLine();
		System.out.println("last name");
		String lastName =input.nextLine();
		Long userID=recommenderAPI.getUserByName(firstName, lastName);
		runUserMenu(userID);
	}
	public int userMenu(){
		// user menu
		System.out.println("User Menu");
		System.out.println("---------");
		System.out.println("1) Your Top 10");
		System.out.println("2) Add Movie");
		System.out.println("3) Rate Movie");
		System.out.println("4) Movie Average Rating");
		System.out.println("5) Remove a movie");
		System.out.println("__________________");
		System.out.println("Account Options");
		System.out.println("-----------------");
		System.out.println("6) Delete Account");
		System.out.println("7) Edit Details");
		System.out.println("0) Exit");
		int option=input.nextInt();	    
		return option;
		
	}
	
	private void runUserMenu(Long userID) throws Exception{
        int option = userMenu();
        while (option != 0){

            switch (option){
               case 1:    ;
               			  break;
               case 2:    addMovie();
               			  try {
               				  recommenderAPI.store();
               			  } catch (Exception e) {
               				  e.printStackTrace();
               			  }	
               case 3:    rateMovie(userID);
               			  break;
               case 4:    avgRating(userID);
               			  break;
               case 5:    removeMovie(userID);	
               			  try {
               				  recommenderAPI.store();
               			  } catch (Exception e) {
               				  e.printStackTrace();
               			  }
               			  break;
               case 6:    deleteUser(userID);
               			  break;
               case 7:    ;
               			  break;
		    }
            
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
            //display the main menu again
            option = userMenu();
        }
        
    }
	public void addMovie() throws Exception{
		input.nextLine();
		System.out.println("title");
		String title=input.nextLine();
		System.out.println("date");
		String date =input.nextLine();
		System.out.println("url");
		String url=input.nextLine();
		recommenderAPI.addMovie(title, date, url);
		recommenderAPI.store();
	}
	
	public void rateMovie(Long userID) throws Exception{
		Long movieID=searchForMovie(userID);
		System.out.println("Rating (from -5 to 5):");
		int rating=input.nextInt();
		recommenderAPI.addRating(userID, movieID, rating);
	}
	
	public void avgRating(Long userID) throws Exception{
		System.out.println();recommenderAPI.getMovieAverageRating(searchForMovie(userID));
	}
	public void removeMovie(Long userID) throws Exception{
		input.nextLine();
		Long ID= searchForMovie(userID);
		recommenderAPI.removeMovie(ID);
	}
	
	public void deleteUser(Long userID) throws Exception{
		recommenderAPI.removeUser(userID);
		input.nextLine();
		runMenu();
	}
	
	
	public Long searchForMovie(Long userID) throws Exception{

		input.nextLine();
		System.out.println("Enter movie to search for?(Enter title)");
		String match=input.nextLine();
		List<String> matches=recommenderAPI.listMovieMatches(match);
		if(matches!=null){
			System.out.println("Chose correct title(enter index value)");
			match=matches.get(input.nextInt());
		}else runUserMenu(userID);
		Long movieID=recommenderAPI.getMovieByTitle(match);
		return movieID;
	}
	
}

   
