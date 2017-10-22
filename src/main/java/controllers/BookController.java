package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import entitties.Author;
import entitties.Book;
import services.AuthorService;
import services.BookService;

@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String listBooks(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("author", new Author());
		model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("authors", authorService.getAll());
		return "books";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/all");
        }
		bookService.add(book);
		// return "redirect:/all";
		return new ModelAndView("redirect:/all");
	}

	@RequestMapping(value = "/remove/{id}") // method = RequestMethod.GET
	public String removeBook(@PathVariable("id") long id) {
		bookService.delete(id);
		return "redirect:/all";

	}

	@RequestMapping(value = "/edit/{id}") // method = RequestMethod.GET
	public String editBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("authors", authorService.getAll());
		return "books";
	}

	@RequestMapping(value = "/data/{id}") // method = RequestMethod.GET
	public String dataBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));
		return "bookData";
	}
	
	@RequestMapping(value = "/data/detail/{id}") // method = RequestMethod.GET
	public String dataBookDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));
		model.addAttribute("allAuthors", bookService.getAuthorsByBookId(id));
		return "bookData";
	}
}