package com.polarbookshop.catalogservice.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookservice) {
		this.bookService = bookservice;
	}
	
	@GetMapping
	public Iterable<Book> get() {
		return bookService.viewBookList();
	}
	
	@GetMapping("{isbn}")
	public Book getByIsbn(@PathVariable String isbn) {
		return bookService.viewBookDetails(isbn);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book post(@RequestBody @Valid Book book) {
		return bookService.addBookToCatalog(book);
	}
	
	@DeleteMapping("{isbn}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable String isbn) {
		 bookService.removeBookFromCatalog(isbn);
	}
	
	@PutMapping("{isbn}")
	public void updateBook(@PathVariable String isbn, @RequestBody @Valid Book book) {
		bookService.editBookDetails(isbn, book);
	}

}
