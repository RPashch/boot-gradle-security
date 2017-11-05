package qrok.services;

import java.util.List;

import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.entitties.Reward;

public interface AuthorService {
	
	public Author getAuthorById(long id);

	public Author add(Author author);

	public void delete(long id);
	
	public void deleteAll();

	public Author edit(Author author);

	public List<Author> getAll();
	
	public List<Book> getBooksByAuthorId(long id);
	
	public List<Reward> getRewardsByAuthorId(long id);

}
