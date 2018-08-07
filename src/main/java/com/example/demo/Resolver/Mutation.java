package com.example.demo.Resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Exception.BookNotFoundException;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;

    public Mutation(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepo.save(author);

        return author;
    }

    public Book newBook(String title, String isbn, int pageCount, Long id) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount);
        book.setAuthor(new Author(id));
        bookRepo.save(book);

        return book;
    }

    public Boolean deleteBook(Long id) {
        bookRepo.deleteById(id);
        return true;
    }

    public Book updatePageCount(int pageCount, Long id) {
        Book book = bookRepo.getOne(id);
        if (book == null) {
            throw new BookNotFoundException("The book you want to update isn't found", id);
        }
        book.setPageCount(pageCount);
        bookRepo.save(book);

        return book;
    }
}
