/**
 * 
 */
package com.polarbookshop.catalogservice.domain;

/**
 * @author arjun
 *
 */
public record Book(
		String isbn,
		String title,
		String author,
		Double price) {}
