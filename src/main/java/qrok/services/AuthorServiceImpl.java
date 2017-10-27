package qrok.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.entitties.Reward;
import qrok.repositories.AuthorRepository;
import qrok.repositories.BookRepository;
import qrok.repositories.RewardRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	RewardRepository rewardRepository;

	@Override
	public Author getAuthorById(long id) {
		return authorRepository.getOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Author add(Author author) {
		return authorRepository.saveAndFlush(author);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		authorRepository.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Author edit(Author author) {
		return authorRepository.saveAndFlush(author);
	}

	@Override
	public List<Author> getAll() {
		return authorRepository.findAll();
	}

	@Override
	public List<Book> getBooksByAuthorId(long id) {
		return authorRepository.getOne(id).getBooks();
	}

	@Override
	public List<Reward> getRewardsByAuthorId(long id) {
		return rewardRepository.getRewardsByAuthorId(id);
	}
}
