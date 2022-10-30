package com.hexad.management.library.util;

import com.hexad.management.library.models.Book;
import com.hexad.management.library.models.BookBorrower;
import com.hexad.management.library.models.Member;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestData {

    public Member member = Member.builder()
            .id(UUID.randomUUID())
            .password("a")
            .email("frank@gmail.com")
            .name("Frank")
            .admin(true)
            .build();

    public Book book = Book.builder()
            .subject("Hello World")
            .bookId(UUID.randomUUID())
            .pages(200)
            .author("Franklin Embate")
            .copyRight("Franklin Embate")
            .editionNumber(1)
            .nameOfLibrary("World of Books")
            .numberOfCopies(10)
            .publisher("Book of wonders")
            .build();

    public BookBorrower tx = BookBorrower.builder()
            .memberId(member.getId())
            .transactionId(UUID.randomUUID())
            .bookId(book.getBookId())
            .barrowDate(new Date())
            .build();
}
