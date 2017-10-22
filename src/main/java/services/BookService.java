package services;

import java.util.List;

import entitties.Author;
import entitties.Book;

public interface BookService {
	
	public Book getBookById(long id);

	public Book add(Book book);

	public void delete(long id);

	public Book edit(Book book);

	public List<Book> getAllBooks();
	
	public List<Author> getAuthorsByBookId(long id);
}
