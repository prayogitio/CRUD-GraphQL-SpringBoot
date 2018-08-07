package com.example.demo.Resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {
    @Autowired
    private AuthorRepository authorRepo;

    public BookResolver(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Author getAuthor(Book book) {
        return authorRepo.getOne(book.getAuthor().getId());
    }
}
