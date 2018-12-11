package ebookshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebookshop.domain.Book;
import ebookshop.service.BookClient;

@RestController
public class WebBookController {

	protected BookClient bookClient;

	@Autowired
	public WebBookController(BookClient bookClient) {
		this.bookClient = bookClient;
	}

	@RequestMapping("/books/{id}")
	public Book byNumber(@PathVariable("id") String id) {
		Book book = bookClient.getByNumber(id);
		return book;
	}
}
