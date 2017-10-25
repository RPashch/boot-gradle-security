package qrok.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import qrok.entitties.Author;
import qrok.services.AuthorService;
import qrok.services.BookService;
import qrok.services.RewardService;

//@RestController
@Controller
@RequestMapping(value = "/authors")
public class AuthorController {
	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;
	
	@Autowired
	RewardService rewardService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String listAuthors(Model model) {
		model.addAttribute("author", new Author());
		//model.addAttribute("book", new Book());
		//model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("authors", authorService.getAll());
		return "authors";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveAuthor(@ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
        	System.out.println(author.getBirthDate());
        	System.out.println(author.getFirstName());
            return new ModelAndView("redirect:/authors/all");
        }
		authorService.add(author);
		// return "redirect:/all";
		return new ModelAndView("redirect:/authors/all");//return new ModelAndView("redirect:/authors/all");
	}
	
	@RequestMapping(value = "/remove/{id}") // method = RequestMethod.GET
	public String removeAuthor(@PathVariable("id") long id) {
		authorService.delete(id);
		return "redirect:/authors/all";

	}
	
	@RequestMapping(value = "/edit/{id}") // method = RequestMethod.GET
	public String editAuthor(@PathVariable("id") long id, Model model) {
		model.addAttribute("author", authorService.getAuthorById(id));
		//model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("authors", authorService.getAll());
		return "authors";
	}
	
	@RequestMapping(value = "/data/{id}") // method = RequestMethod.GET
	public String dataAuthor(@PathVariable("id") long id, Model model) {
		model.addAttribute("author", authorService.getAuthorById(id));
		return "authorData";
	}
	
	@RequestMapping(value = "/data/detail/{id}") // method = RequestMethod.GET
	public String dataAuthorDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("author", authorService.getAuthorById(id));
		model.addAttribute("allBooks", authorService.getBooksByAuthorId(id));
		return "authorData";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
}
