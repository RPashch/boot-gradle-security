package qrok.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AuthorDTO {
	private String firstName;
	private String lastName;
	private Integer age;
	private List<String> books;

	public AuthorDTO(){
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public AuthorDTO setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public AuthorDTO setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public AuthorDTO setAge(Integer age) {
		this.age = age;
		return this;
	}

	public List<String> getBooks() {
		return books;
	}

	public AuthorDTO setBooks(List<String> books) {
		this.books = books;
		return this;
	}
	
	public Integer getQuantityAuthorYers(Date date){
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTimeInMillis(date.getTime());
		
		long currentTime = System.currentTimeMillis();
	    Calendar now = Calendar.getInstance();
	    now.setTimeInMillis(currentTime);
	    
	    int years = now.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	    return years;
		//Period.between(LocalDate.of(, month, dayOfMonth), now);
	}
}
