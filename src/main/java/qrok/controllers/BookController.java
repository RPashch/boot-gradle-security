package qrok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import qrok.entitties.Author;
import qrok.entitties.Book;
import qrok.services.AuthorService;
import qrok.services.BookService;

@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)//, method = RequestMethod.GET
	@ResponseStatus(HttpStatus.OK)
	public String listBooks(Model model) {
		model.addAttribute("book", new Book());
		//model.addAttribute("author", new Author());
		model.addAttribute("books", bookService.getAllBooks());
		//model.addAttribute("authors", authorService.getAll());
		return "books";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/books/all");
        }
		bookService.add(book);
		// return "redirect:/all";
		return new ModelAndView("redirect:/books/all");
	}

	@RequestMapping(value = "/remove/{id}") // method = RequestMethod.GET
	public String removeBook(@PathVariable("id") long id) {
		bookService.delete(id);
		return "redirect:/books/all";

	}

	@RequestMapping(value = "/edit/{id}") // method = RequestMethod.GET
	public String editBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("books", bookService.getAllBooks());
		//model.addAttribute("authors", authorService.getAll());
		return "books";
	}

	@RequestMapping(value = "/data/{id}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String dataBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("author", new Author());
		model.addAttribute("book", bookService.getBookById(id));
		return "bookData";
	}
	
	@RequestMapping(value = "/data/detail/{id}") // method = RequestMethod.GET
	public String dataBookDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("author", new Author());
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("allAuthorsThisBook", bookService.getAuthorsByBookId(id));
		model.addAttribute("allAuthors", authorService.getAll());
		return "bookData";
	}
	
	@RequestMapping(value = "/data/detail/{id}", method = RequestMethod.POST)
	public String addAuthorToBook(@PathVariable("id") long id, @ModelAttribute("author") Author author, BindingResult result) {
		if (result.hasErrors()) {
            return "redirect:/books/data/detail/" + id;
        }
		
		Author authorToThisBook = authorService.getAuthorById(author.getId());
		Book book = bookService.getBookById(id);
		if(!book.getAuthors().contains(authorToThisBook)){
			book.getAuthors().add(authorToThisBook);
			bookService.add(book);
		}
		
		//model.addAttribute("allAuthors", bookService.getAuthorsByBookId(id));
		//return "bookData";
		
		return "redirect:/books/data/detail/" + id;
	}
}