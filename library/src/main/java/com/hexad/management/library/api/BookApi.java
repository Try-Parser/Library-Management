package com.hexad.management.library.api;

import com.hexad.management.library.models.Book;
import com.hexad.management.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * Book API
 */
@RestController
@RequestMapping("/api/book/")
public class BookApi {

    @Autowired
    BookService service;

    /***
     * Get all books
     * @return List<Book>
     */
    @GetMapping("all")
    @ResponseBody
    public List<Book> getAll() {
        return service.getAllBooks() ;
    }

    /***
     * Add new Book
     * @param book Book
     * @return Book
     */
    @PostMapping("add")
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }
}
