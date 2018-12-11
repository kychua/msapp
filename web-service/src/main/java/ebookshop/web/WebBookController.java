package ebookshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebookshop.domain.Book;
import ebookshop.service.WebBookService;

@RestController
public class WebBookController {

	protected WebBookService bookService;

	@Autowired
	public WebBookController(WebBookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/books/{id}")
	public Book byNumber(@PathVariable("id") String id) {
		Book book = bookService.getByNumber(id);
		return book;
	}
}
