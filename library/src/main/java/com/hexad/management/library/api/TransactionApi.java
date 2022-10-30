package com.hexad.management.library.api;

import com.hexad.management.library.models.Book;
import com.hexad.management.library.models.BookBorrower;
import com.hexad.management.library.models.TransactionDetails;
import com.hexad.management.library.services.BookService;
import com.hexad.management.library.services.TransactionService;
import com.hexad.management.library.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * Transaction API
 */
@RestController
@RequestMapping("/api/tx/")
public class TransactionApi {

    @Autowired
    TransactionService service;

    @Autowired
    BookService bookService;

    /***
     * Get All transaction for the member
     * @param id String
     * @return List<TransactionDetails>
     */
    @GetMapping("mine")
    public List<TransactionDetails> borrow(@RequestParam String id) {
        UUID memberId = UUID.fromString(id);

        return service.getAllMemberTransaction(memberId).parallelStream().map(tx -> {
            TransactionDetails details = TransactionDetails.builder()
                    .memberId(memberId)
                    .transactionDetails(tx)
                    .build();

            bookService.findBookWithBookId(tx.getBookId()).ifPresent(details::setBookDetails);

            return details;
        }).collect(Collectors.toList());
    }

    /***
     * Return books to the library
     * @param txId String TransactionId
     * @return Response<?>
     */
    @GetMapping("return")
    public Response<?> returnBook(@RequestParam String txId) {
        UUID txUUID = UUID.fromString(txId);
        service.getTransactionById(txUUID).ifPresent(tx -> {
            tx.setReturnDate(new Date());
            tx.setClose(new Date());

            bookService
                .findBookWithBookId(tx.getBookId())
                .ifPresent(detail -> detail.setNumberOfCopies(detail.getNumberOfCopies() + 1));
        });

        return Response.builder().response("Book returned").success(true).build();
    }

    /***
     * Add book to my borrow list
     * @param tx BookBorrower
     * @return Response<?>
     */
    @PostMapping("borrow")
    public Response<?> borrow(@RequestBody BookBorrower tx) {
        List<BookBorrower> unClosedTx = service.getAllMemberTransaction(tx.getMemberId());

        if(unClosedTx.size() >= 2) {
            return Response
                .builder()
                .success(false)
                .build();
        }

        Optional<Book> book = bookService.findBookWithBookId(tx.getBookId());
        book.ifPresent(detail -> detail.setNumberOfCopies(detail.getNumberOfCopies() - 1));

        return Response.builder()
                .response(service.addBorrower(tx))
                .success(true)
                .build();
    }
}
