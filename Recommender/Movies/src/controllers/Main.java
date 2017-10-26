package controllers;
import java.util.Scanner;
import java.io.File;
import java.util.List;

import utils.ReadFile;
import utils.Serializer;
import utils.XMLSerializer;

public class Main {
	
	File  datastore = new File("datastore3.xml");
    Serializer serializer = new XMLSerializer(datastore);
    
    RecommenderAPI recommenderAPI = new RecommenderAPI(serializer);

	private Scanner input;
	private ReadFile read;
	Main(){	
		input=new Scanner(System.in);
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
		System.out.println("first name");
		String firstName=input.nextLine();
		System.out.println("last name");
		String lastName =input.nextLine();
		Long userID=recommenderAPI.getUserByName(firstName, lastName);
		runUserMenu(userID);
	
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

   
