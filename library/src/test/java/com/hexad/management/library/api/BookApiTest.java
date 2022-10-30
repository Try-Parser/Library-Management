package com.hexad.management.library.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.management.library.models.Book;
import com.hexad.management.library.services.BookService;
import com.hexad.management.library.util.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookApi.class)
class BookApiTest extends TestData {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private BookService service;

    @Test
    void getAll() throws Exception {
        List<Book> records = Collections.singletonList(book);

        Mockito.when(service.getAllBooks()).thenReturn(records);

        mvc.perform(MockMvcRequestBuilders
            .get("/api/book/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].subject", is(book.getSubject())))
            .andExpect(jsonPath("$[0].bookId", is(book.getBookId().toString())))
            .andExpect(jsonPath("$[0].pages", is(book.getPages())))
            .andExpect(jsonPath("$[0].author", is(book.getAuthor())))
            .andExpect(jsonPath("$[0].copyRight", is(book.getCopyRight())))
            .andExpect(jsonPath("$[0].editionNumber", is(book.getEditionNumber())))
            .andExpect(jsonPath("$[0].nameOfLibrary", is(book.getNameOfLibrary())))
            .andExpect(jsonPath("$[0].numberOfCopies", is(book.getNumberOfCopies())))
            .andExpect(jsonPath("$[0].publisher", is(book.getPublisher())));
    }

    @Test
    void addBook() throws Exception {
        Mockito.when(service.addBook(book)).thenReturn(book);

        mvc.perform(MockMvcRequestBuilders
            .post("/api/book/add")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(book)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.subject", is(book.getSubject())))
            .andExpect(jsonPath("$.bookId", is(book.getBookId().toString())))
            .andExpect(jsonPath("$.pages", is(book.getPages())))
            .andExpect(jsonPath("$.author", is(book.getAuthor())))
            .andExpect(jsonPath("$.copyRight", is(book.getCopyRight())))
            .andExpect(jsonPath("$.editionNumber", is(book.getEditionNumber())))
            .andExpect(jsonPath("$.nameOfLibrary", is(book.getNameOfLibrary())))
            .andExpect(jsonPath("$.numberOfCopies", is(book.getNumberOfCopies())))
            .andExpect(jsonPath("$.publisher", is(book.getPublisher())));
    }

}