package ebookshop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ebookshop.domain.Book;

@FeignClient(name = "books-service")
public interface BookClient {

	@GetMapping("/api/books/{bookId}")
	Book getByNumber(@PathVariable("bookId") String bookId);
}
