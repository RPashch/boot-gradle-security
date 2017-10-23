package qrok.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.repositories.AuthorRepository;
import qrok.repositories.BookRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService{

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book getBookById(long id) {
		return bookRepository.getOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Book add(Book book) {
		return bookRepository.saveAndFlush(book);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		bookRepository.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Book edit(Book book) {
		return bookRepository.saveAndFlush(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Author> getAuthorsByBookId(long id) {
		return authorRepository.findAll();
	}
}

