/**
 * 
 */
package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @author arjun
 *
 */
public record Book(
		@NotBlank(message = "ISBN shouble be either 10 digits or 13 digits")
		@Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Not a valid isbn")
		String isbn,
		@NotBlank(message = "The title should not be blank")
		String title,
		@NotBlank(message = "The author field should not be blank")
		String author,
		@NotNull(message = "The price should not be blank")
		@Positive(message = "The price should be greater than zero")
		Double price
		) {}
