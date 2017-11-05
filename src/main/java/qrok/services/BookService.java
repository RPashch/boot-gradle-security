package qrok.services;

import java.util.List;

import qrok.entitties.Author;
import qrok.entitties.Book;

public interface BookService {
	
	public Book getBookById(long id);

	public Book add(Book book);

	public void delete(long id);
	
	public void deleteAll();

	public Book edit(Book book);

	public List<Book> getAllBooks();
	
	public List<Author> getAuthorsByBookId(long id);
}
