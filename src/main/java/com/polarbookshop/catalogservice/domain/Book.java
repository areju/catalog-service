/**
 * 
 */
package com.polarbookshop.catalogservice.domain;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
/**
 * @author arjun
 *
 */
public record Book(
		@Id
		Long id,
		@NotBlank(message = "ISBN shouble be either 10 digits or 13 digits")
		@Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "Not a valid isbn")
		String isbn,
		@NotBlank(message = "The title should not be blank")
		String title,
		@NotBlank(message = "The author field should not be blank")
		String author,
		@NotNull(message = "The price should not be blank")
		@Positive(message = "The price should be greater than zero")
		Double price,
		@CreatedDate
		Instant createdDate,
		@LastModifiedDate
		Instant lastModifiedDate,		
		@Version
		int version
		
		) {
		public static Book of(String isbn, String title, String author, Double price) {
			return new Book(null,isbn,title,author,price,null,null,0);
		}
}
