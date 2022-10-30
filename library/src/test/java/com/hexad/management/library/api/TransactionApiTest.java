package com.hexad.management.library.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.management.library.models.BookBorrower;
import com.hexad.management.library.services.BookService;
import com.hexad.management.library.services.TransactionService;
import com.hexad.management.library.util.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionApi.class)
class TransactionApiTest extends TestData {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TransactionService service;

    @MockBean
    BookService bookService;

    @Test
    void borrow() throws Exception {
        Mockito.when(service.getAllMemberTransaction(member.getId()))
                .thenReturn(Collections.singletonList(tx));

        Mockito.when(bookService.findBookWithBookId(tx.getBookId()))
                .thenReturn(Optional.ofNullable(book));

        mvc.perform(MockMvcRequestBuilders
            .get("/api/tx/mine?id="+tx.getMemberId().toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void returnBook() throws Exception {
        Mockito.when(service.getTransactionById(tx.getTransactionId()))
                .thenReturn(Optional.ofNullable(tx));

        Mockito.when(bookService.findBookWithBookId(tx.getBookId()))
                .thenReturn(Optional.ofNullable(book));

        mvc.perform(MockMvcRequestBuilders
            .get("/api/tx/return?txId="+tx.getTransactionId().toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    void testBorrowFailed() throws Exception {
        Mockito.when(service.getAllMemberTransaction(tx.getMemberId()))
            .thenReturn(Arrays.asList(tx, tx));

        mvc.perform(MockMvcRequestBuilders
            .post("/api/tx/borrow")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(tx)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    void testBorrow() throws Exception {
        Mockito.when(service.getAllMemberTransaction(tx.getMemberId()))
                .thenReturn(Collections.singletonList(tx));

        Mockito.when(bookService.findBookWithBookId(tx.getBookId()))
                .thenReturn(Optional.ofNullable(book));

        Mockito.when(service.addBorrower(tx)).thenReturn(tx);

        mvc.perform(MockMvcRequestBuilders
            .post("/api/tx/borrow")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(tx)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response.memberId", is(tx.getMemberId().toString())))
            .andExpect(jsonPath("$.response.transactionId", is(tx.getTransactionId().toString())))
            .andExpect(jsonPath("$.response.bookId", is(tx.getBookId().toString())));
    }
}