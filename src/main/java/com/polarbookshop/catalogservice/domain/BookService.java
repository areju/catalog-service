/**
 * 
 */
package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author arjun
 *
 */
@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Iterable<Book> viewBookList() {
		return bookRepository.findAll();
	}
	
	public Book viewBookDetails(String isbn) {
		 return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
	}
	
	public Book addBookToCatalog(Book book) {
		if(bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}else {
			return bookRepository.save(book);
		}
	}
	
	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}
	
	public Book editBookDetails(String isbn, Book book) {
		
		return bookRepository.findByIsbn(isbn).map(existingBook -> {
			var newBook = new Book(existingBook.id(), 
					existingBook.isbn(),
					book.author(),
					book.title(),
					book.price(),
					book.version());
			return bookRepository.save(newBook);
		}).orElseGet(() ->  addBookToCatalog(book));
	}
	

}
