package qrok;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import qrok.controllers.BookController;
import qrok.entities.enums.Genre;
import qrok.entities.enums.Sex;
import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.entitties.Role;
import qrok.entitties.User;
import qrok.services.AuthorService;
import qrok.services.BookService;
import qrok.services.RewardService;
import qrok.services.UserService;

public class TestController {

	@Mock
	private BookService mockBookService;

	@Mock
	private AuthorService mockAuthorService;

	@Mock
	private RewardService mockRewardService;

	@Mock
	private UserService mockUserService;

	@InjectMocks
	private BookController bookController = new BookController();

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();

		// setting up mocking of authorization to be used in all of the tests
		Authentication authentication = mock(Authentication.class);
		// Mockito.whens() for authorization object if needed
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn("username");
		User newUser = new User();
		newUser.setId(1L);
		newUser.setPassword("password");
		newUser.setUsername("username");
		Role roleUser = new Role();
		roleUser.setId(2L);
		roleUser.setName("USER");
		;
		Set<Role> roles = new HashSet<>();
		roles.add(roleUser);
		newUser.setRoles(roles);
		when(mockUserService.findByUsername("username")).thenReturn(newUser);
	}

	@Test
	public void testListBooks() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);

		Author authorTestFirst = new Author();
		authorTestFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorTestFirst.setFirstName("authorFirst");
		authorTestFirst.setLastName("authorFirstLastName");
		authorTestFirst.setSex(Sex.MAN);

		Author authorTestSecond = new Author();
		calendar.set(1972, 02, 01);
		authorTestSecond.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorTestSecond.setFirstName("authorSecond");
		authorTestSecond.setLastName("authorSecondLastName");
		authorTestSecond.setSex(Sex.WOMAN);

		List<Author> authors = new ArrayList<>();
		authors.add(authorTestFirst);
		authors.add(authorTestSecond);

		Book firstBook = new Book();
		firstBook.setTitle("firstBook");
		firstBook.setIsbn("123-123");
		firstBook.setGenre(Genre.CRIME);
		firstBook.setAuthors(authors);

		Book secondBook = new Book();
		secondBook.setTitle("secondBook");
		secondBook.setIsbn("333-123");
		secondBook.setGenre(Genre.FANTASY);
		secondBook.setAuthors(authors);

		when(mockBookService.getAllBooks()).thenReturn(Arrays.asList(firstBook, secondBook));

		mockMvc
		.perform(get("/books/all"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("books"))
				.andExpect(model().attributeExists("book"))
				.andExpect(model().attributeExists("books"))
				// .andExpect(model().attribute("loggedinuser", is("sam")))
				.andExpect(model().attribute("books", Arrays.asList(firstBook, secondBook)));

		verify(mockBookService, times(1)).getAllBooks();
		verifyNoMoreInteractions(mockBookService);
	}

	@Test
	public void testSaveBook() throws Exception {
		Book firstBook = new Book();
		firstBook.setTitle("firstBook");
		firstBook.setIsbn("123-123");
		firstBook.setGenre(Genre.CRIME);
		// firstBook.setAuthors(authors);

		mockMvc.perform(post("/books/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "firstBook").param("isbn", "123-123").param("genre", "CRIME")).andDo(print())
				.andExpect(view().name("redirect:/books/all")).andExpect(model().attribute("book", firstBook));

	}

	@Test
	public void testDeleteBook() throws Exception {

		mockMvc.perform(get("/books/remove/1")).andDo(print()).andExpect(view().name("redirect:/books/all"));

		verify(mockBookService, times(1)).delete(1);

		verifyNoMoreInteractions(mockBookService);

	}
	
	@Test
	public void testDataBook() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 05, 05);
		
		Author authorTestFirst = new Author();
		authorTestFirst.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorTestFirst.setFirstName("authorFirst");
		authorTestFirst.setLastName("authorFirstLastName");
		authorTestFirst.setSex(Sex.MAN);

		Author authorTestSecond = new Author();
		calendar.set(1972, 02, 01);
		authorTestSecond.setBirthDate(new Date(calendar.getTimeInMillis()));
		authorTestSecond.setFirstName("authorSecond");
		authorTestSecond.setLastName("authorSecondLastName");
		authorTestSecond.setSex(Sex.WOMAN);

		List<Author> authors = new ArrayList<>();
		authors.add(authorTestFirst);
		authors.add(authorTestSecond);

		Book firstBook = new Book();
		firstBook.setTitle("firstBook");
		firstBook.setIsbn("123-123");
		firstBook.setGenre(Genre.CRIME);
		firstBook.setAuthors(authors);
		
		when(mockBookService.getBookById(1)).thenReturn(firstBook);

		mockMvc
		.perform(get("/books/data/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("bookData"))
				.andExpect(model().attributeExists("author"))
				.andExpect(model().attribute("book", firstBook));
		verify(mockBookService, times(1)).getBookById(1);

		verifyNoMoreInteractions(mockBookService);

	}

}
