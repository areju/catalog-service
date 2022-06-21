package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {
	
	public BookAlreadyExistsException(String isbn) {
		super("Book with Isbn " + isbn + " already exists !!" );
	}

}
