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
		Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
		if(existingBook.isEmpty()) {
			return bookRepository.save(book);
		}
		
		var bookUpdate = new Book(existingBook.get().isbn(), book.title(), book.author(), book.price());
		return bookRepository.save(bookUpdate);
	}
	

}
