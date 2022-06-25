package com.polarbookshop.catalogservice.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Component
@Profile(value = "testdata")
public class BookDataLoader {
	
	private final BookRepository bookRepository;
	
	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
	    Book book1 = Book.of("1234567891", "Northern Lights", "Lyra Silvertongue", 9.90);
	    Book book2 =  Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90);
	    bookRepository.save(book1);
	    bookRepository.save(book2);

	}

}
