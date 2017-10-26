package models;

public class User {
	public static Long   counter = 1l;
	
	private long   userID;
	private String firstName;
	private String lastName;
	private int	   age;
	private String gender;
	private String occupation;
	
	public User(String firstName, String lastName, int age, String gender, String occupation){
		this.userID=counter++;
		this.firstName=firstName;
		this.lastName=lastName;
		if (age>=0)	this.age=age; else	this.age=0;
		this.gender=gender;
		this.occupation=occupation;
	}
	
	//Setters
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	public void setAge(int age){
		if (age>=0)	this.age=age; else	this.age=0;
	}
	public void setGender(String gender){
		this.gender=gender;
	}
	public void setOccupation(String occupation){
		this.occupation=occupation;
	}
	
	/Getters
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public int getAge(){
		return age;
	}
	public String getGender(){
		return gender;
	}
	public String getOccupation(){
		return occupation;
	}
	public Long getUserID(){
		return userID;
	}
	
	public String toString(){
		return 	"User ID : " + userID
				+ "\nUser first name : " + firstName
				+ "\nUser last name : " + lastName
				+ "\nUser age : " + age
				+ "\nUser gender : " + gender
				+ "\nUser occupation : " + occupation;
	} 

}
