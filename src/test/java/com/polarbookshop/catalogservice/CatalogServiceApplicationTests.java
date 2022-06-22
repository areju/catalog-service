package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.polarbookshop.catalogservice.domain.Book;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1234567890123", "title", "author", 9.00);
		
		webTestClient.post().uri("/books").bodyValue(expectedBook)
		.exchange().expectStatus().isCreated().expectBody(Book.class).value(
				actualBook -> { 
						assertThat(actualBook).isNotNull();
						assertEquals(actualBook.isbn(),expectedBook.isbn());
						});
	}
	
	
	

}
