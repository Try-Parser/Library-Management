package com.hexad.management.library.services;

import com.hexad.management.library.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private final BookService service = new BookService();
    private UUID bookId = UUID.fromString("cae8c306-1f6d-4561-9bea-9abead8015f4");

    private Book addBookHelper() {
        Book book = service.addBook(Book.builder()
            .subject("Hello World")
            .bookId(bookId)
            .pages(200)
            .author("Franklin Embate")
            .copyRight("Franklin Embate")
            .editionNumber(1)
            .nameOfLibrary("World of Books")
            .numberOfCopies(10)
            .publisher("Book of wonders")
            .build());

        book.setBookId(bookId);

        return book;
    }

    @Test
    void addBook() {
        Book book = addBookHelper();

        assertNotNull(book);
        assertEquals(book.getBookId(), bookId);
        assertEquals(book.getSubject(), "Hello World");
        assertEquals(book.getPages(), 200);
        assertEquals(book.getAuthor(), "Franklin Embate");
        assertEquals(book.getCopyRight(), "Franklin Embate");
        assertEquals(book.getEditionNumber(), 1);
        assertEquals(book.getNumberOfCopies(), 10);
        assertEquals(book.getPublisher(), "Book of wonders");
    }

    @Test
    void findBookWithBookId() {
        Book book = addBookHelper();

        Optional<Book> result = service.findBookWithBookId(bookId);

        assertNotNull(result);

        Book singleBook = result.get();

        assertEquals(book.getBookId(), singleBook.getBookId());
        assertEquals(book.getSubject(), singleBook.getSubject());
        assertEquals(book.getPages(), singleBook.getPages());
        assertEquals(book.getAuthor(), singleBook.getAuthor());
        assertEquals(book.getCopyRight(), singleBook.getCopyRight());
        assertEquals(book.getEditionNumber(), singleBook.getEditionNumber());
        assertEquals(book.getNumberOfCopies(), singleBook.getNumberOfCopies());
        assertEquals(book.getPublisher(), singleBook.getPublisher());

    }

    @Test
    void findBookWithSubjectAndTitle() {
    }
}