package services;

import java.util.List;

import entitties.Author;
import entitties.Book;
import entitties.Reward;

public interface AuthorService {
	
	public Author getAuthorById(long id);

	public Author add(Author author);

	public void delete(long id);

	public Author edit(Author author);

	public List<Author> getAll();
	
	public List<Book> getBooksByAuthorId(long id);
	
	public List<Reward> getRewardsByAuthorId(long id);

}
