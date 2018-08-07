package com.example.demo;

import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Resolver.BookResolver;
import com.example.demo.Resolver.Mutation;
import com.example.demo.Resolver.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public BookResolver authorResolver(AuthorRepository authorRepo) {
		return new BookResolver(authorRepo);
	}

	@Bean
	public Query query(AuthorRepository authorRepo, BookRepository bookRepo) {
		return new Query(authorRepo, bookRepo);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepo, BookRepository bookRepo) {
		return new Mutation(authorRepo, bookRepo);
	}

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepo, BookRepository bookRepo) {
		return args -> {
			Author author = new Author("Herbert", "Schildt");
			authorRepo.save(author);

			Book book = new Book("Java : Java kroco jadi jago", "abc123", 123, author);
			bookRepo.save(book);
		};
	}
}
