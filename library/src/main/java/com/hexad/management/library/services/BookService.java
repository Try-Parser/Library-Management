package com.hexad.management.library.services;

import com.hexad.management.library.models.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class BookService {
    private final List<Book> books = new ArrayList<>();


    /***
     * Add new book or update the copies
     * @param book Book
     * @return Book
     */
    public Book addBook(Book book) {
        book.setBookId(UUID.randomUUID());
        book.setCreatedAt(new Date());
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    /***
     * find the book with book id
     * @param bookId UUID
     * @return Optional<Book>
     */
    public Optional<Book> findBookWithBookId(UUID bookId) {
        return books.parallelStream()
            .filter(stackBook -> stackBook.getBookId()
            .equals(bookId)).findFirst();
    }

    /***
     * Find all books with Subject and Title
     * @param subject String
     * @param title String
     * @return List<Book>
     */
    public List<Book> findBookWithSubjectAndTitle(String subject, String title) {
        return books.parallelStream()
            .filter(stackBook -> stackBook.getSubject().equals(subject) && stackBook.getTitle().equals(title))
            .collect(Collectors.toList());
    }
}
