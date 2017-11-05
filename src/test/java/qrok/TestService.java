package qrok;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import qrok.entities.enums.Genre;
import qrok.entities.enums.Sex;
import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.entitties.Reward;
import qrok.services.AuthorService;
import qrok.services.BookService;
import qrok.services.RewardService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QrokApplication.class)
// @TestPropertySource(locations="classpath:test.properties")
public class TestService {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@Autowired
	private RewardService rewardService;

	Author authorFirst;
	Author authorScnd;

	Book bookFirst;
	Book bookScnd;
	Book bookThird;

	Reward rewardOne;
	Reward rewardTwo;
	Reward rewardThree;

	@Before
	public void setUp() {
		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.set(1988, 05,
		 * 05);
		 * 
		 * authorFirst = new Author(); authorFirst.setBirthDate(new
		 * Date(calendar.getTimeInMillis()));
		 * authorFirst.setFirstName("authorFirst");
		 * authorFirst.setLastName("authorFirstLastName");
		 * authorFirst.setSex(Sex.MAN);
		 * 
		 * authorScnd = new Author(); calendar.set(1972, 02, 01);
		 * authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		 * authorScnd.setFirstName("authorSecond");
		 * authorScnd.setLastName("authorSecondLastName");
		 * authorScnd.setSex(Sex.WOMAN);
		 * 
		 * List<Author> authors = new ArrayList<>(); authors.add(authorFirst);
		 * authors.add(authorScnd);
		 * 
		 * bookFirst = new Book(); bookFirst.setTitle("firstBook");
		 * bookFirst.setIsbn("123-123"); bookFirst.setGenre(Genre.CRIME);
		 * bookFirst.setAuthors(authors);
		 * 
		 * bookScnd = new Book(); bookScnd.setTitle("secondBook");
		 * bookScnd.setIsbn("333-123"); bookScnd.setGenre(Genre.FANTASY);
		 * bookScnd.setAuthors(authors);
		 * 
		 * List<Book> books = new ArrayList<>(); books.add(bookFirst);
		 * books.add(bookScnd);
		 * 
		 * authorFirst.setBooks(books); authorScnd.setBooks(books);
		 * 
		 * rewardOne = new Reward("Best book",2000,authorFirst); rewardTwo = new
		 * Reward("Best bookTwo",2002,authorScnd); rewardThree = new Reward(
		 * "Best book ten",2010,authorFirst);
		 * 
		 * authorFirst.setRewards(Arrays.asList(rewardOne,rewardThree));
		 * authorScnd.setRewards(Arrays.asList(rewardTwo));
		 */
	}

	@Test
	// @Transactional
	// @Rollback(true)
	public void testAddAuthor() {
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorService.add(authorFirst);

		List<Author> authors = authorService.getAll();

		Assert.assertEquals(1, authors.size());

		Assert.assertEquals(authorFirst.getLastName(), authors.get(0).getLastName());

	}

	@Test
	public void testGetAuthorById() {
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorScnd = new Author();
		calendar.set(1972, 02, 01);
		authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorScnd.setFirstName("authorSecond");
		authorScnd.setLastName("authorSecondLastName");
		authorScnd.setSex(Sex.WOMAN);

		authorService.add(authorFirst);
		authorService.add(authorScnd);

		Author authorFromDB = authorService.getAuthorById(authorFirst.getId());

		// Assert.assertEquals(authorFirst, authorFromDB); DATE - different
		// format
		Assert.assertEquals(authorFirst.getLastName(), authorFromDB.getLastName());
		Assert.assertEquals(authorFirst.getFirstName(), authorFromDB.getFirstName());
		Assert.assertEquals(authorFirst.getId(), authorFromDB.getId());
		// Assert.assertTrue(authorFirst.getBirthDate().equals(authorFromDB.getBirthDate()));
		Calendar dateAuthorFirst = Calendar.getInstance();
		dateAuthorFirst.setTime(authorFirst.getBirthDate());

		Calendar dateAuthorFromDB = Calendar.getInstance();
		dateAuthorFromDB.setTime(authorFromDB.getBirthDate());
		Assert.assertEquals(dateAuthorFirst.SHORT_FORMAT, dateAuthorFromDB.SHORT_FORMAT);

		// Assert.
	}

	@Test
	public void testDeleteAuthorById() {
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorScnd = new Author();
		calendar.set(1972, 02, 01);
		authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorScnd.setFirstName("authorSecond");
		authorScnd.setLastName("authorSecondLastName");
		authorScnd.setSex(Sex.WOMAN);

		authorService.add(authorFirst);
		authorService.add(authorScnd);

		authorService.delete(authorScnd.getId());

		List<Author> authors = authorService.getAll();

		Assert.assertEquals(1, authors.size());

		Assert.assertEquals(authorFirst.getLastName(), authors.get(0).getLastName());
		Assert.assertEquals(authorFirst.getFirstName(), authors.get(0).getFirstName());
	}

	@Test
	public void testGetAll() {
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorScnd = new Author();
		calendar.set(1972, 02, 01);
		authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorScnd.setFirstName("authorSecond");
		authorScnd.setLastName("authorSecondLastName");
		authorScnd.setSex(Sex.WOMAN);

		authorService.add(authorFirst);
		authorService.add(authorScnd);

		List<Author> authors = authorService.getAll();

		Assert.assertEquals(2, authors.size());

		Assert.assertEquals(authorFirst.getLastName(), authors.get(0).getLastName());
		Assert.assertEquals(authorFirst.getFirstName(), authors.get(0).getFirstName());

		Assert.assertEquals(authorScnd.getLastName(), authors.get(1).getLastName());
		Assert.assertEquals(authorScnd.getFirstName(), authors.get(1).getFirstName());

	}

	@Test
	@Transactional
	public void testGetRewardsByAuthorId() {
		
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorScnd = new Author();
		calendar.set(1972, 02, 01);
		authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorScnd.setFirstName("authorSecond");
		authorScnd.setLastName("authorSecondLastName");
		authorScnd.setSex(Sex.WOMAN);

		rewardOne = new Reward("Best book", 2000, authorFirst);
		rewardTwo = new Reward("Best bookTwo", 2002, authorScnd);
		rewardThree = new Reward("Best book ten", 2010, authorFirst);
		authorFirst.setRewards(Arrays.asList(rewardOne, rewardThree));
		authorScnd.setRewards(Arrays.asList(rewardTwo));

		authorService.add(authorFirst);
		authorService.add(authorScnd);
		
		rewardService.add(rewardOne);
		rewardService.add(rewardTwo);
		rewardService.add(rewardThree);
		
		// List <Reward> rewardsExpected = authorFirst.getRewards().;

		Author authorFromDB = authorService.getAuthorById(authorFirst.getId());

		Assert.assertEquals(2, authorFromDB.getRewards().size());

		Assert.assertEquals(authorFirst.getRewards().get(0).getTitle(), authorFromDB.getRewards().get(0).getTitle());
		Assert.assertEquals(authorFirst.getRewards().get(1).getYear(), authorFromDB.getRewards().get(1).getYear());

	}

	@Test
	@Transactional
	public void testGetBooksByAuthorId() {
		authorService.deleteAll();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		authorFirst = new Author();
		authorFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorFirst.setFirstName("authorFirst");
		authorFirst.setLastName("authorFirstLastName");
		authorFirst.setSex(Sex.MAN);

		authorScnd = new Author();
		calendar.set(1972, 02, 01);
		authorScnd.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorScnd.setFirstName("authorSecond");
		authorScnd.setLastName("authorSecondLastName");
		authorScnd.setSex(Sex.WOMAN);

		bookFirst = new Book();
		bookFirst.setTitle("firstBook");
		bookFirst.setIsbn("123-123");
		bookFirst.setGenre(Genre.CRIME);
		// bookFirst.setAuthors(authors);

		bookScnd = new Book();
		bookScnd.setTitle("secondBook");
		bookScnd.setIsbn("333-123");
		bookScnd.setGenre(Genre.FANTASY);
		// bookScnd.setAuthors(authors);

		bookThird = new Book();
		bookThird.setTitle("bookThird");
		bookThird.setIsbn("444-444");
		bookThird.setGenre(Genre.FANTASY);

		List<Book> booksOneTwo = Arrays.asList(bookFirst, bookScnd);
		List<Book> booksThree = Arrays.asList(bookThird);

		authorFirst.setBooks(booksOneTwo);
		authorScnd.setBooks(booksThree);
		
		authorService.add(authorFirst);
		authorService.add(authorScnd);

		// List <Reward> rewardsExpected = authorFirst.getRewards().;

		Author authorFromDB = authorService.getAuthorById(authorFirst.getId());

		Assert.assertEquals(2, authorFromDB.getBooks().size());

		Assert.assertEquals(authorFirst.getBooks().get(0).getTitle(), authorFromDB.getBooks().get(0).getTitle());
		Assert.assertEquals(authorFirst.getBooks().get(1).getTitle(), authorFromDB.getBooks().get(1).getTitle());
		Assert.assertEquals(authorFirst.getBooks().get(1).getIsbn(), authorFromDB.getBooks().get(1).getIsbn());
		
	}
}
