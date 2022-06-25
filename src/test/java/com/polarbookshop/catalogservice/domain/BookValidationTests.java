package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookValidationTests {
	
	private static Validator validator;
	
	@BeforeAll
	static void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book =  Book.of("1234567890","title", "author", 9.00);
		Set<ConstraintViolation<Book>> errors = validator.validate(book);
		assertThat(errors.isEmpty());
	}
	
	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book =  Book.of("a1234567890","title", "author", 9.00);
		Set<ConstraintViolation<Book>> errors = validator.validate(book);
		assertFalse(errors.isEmpty());
		assertEquals(errors.iterator().next().getMessage(), "Not a valid isbn");
	}

}
