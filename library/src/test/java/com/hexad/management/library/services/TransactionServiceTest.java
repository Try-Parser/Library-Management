package com.hexad.management.library.services;

import com.hexad.management.library.models.BookBorrower;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    TransactionService service = new TransactionService();

    private UUID memberId = UUID.fromString("cae8c306-1f6d-4561-9bea-9abead8015f4");
    private UUID bookId = UUID.fromString("dd9cabcb-7f17-4429-b191-495b9b1edc11");
    private UUID txId = UUID.fromString("6dda73a0-decc-4335-be3a-7f52f594f05f");

    private BookBorrower addBorrowerHelper() {
        BookBorrower tx = BookBorrower
                .builder()
                .memberId(memberId)
                .bookId(bookId)
                .transactionId(txId)
                .build();

        service.addBorrower(tx);
        return tx;
    }

    @Test
    public void addBorrower() {
        BookBorrower tx = addBorrowerHelper();

        assertNotNull(service.getAllTransactions());
        BookBorrower borrower = service.getAllTransactions().get(0);

        assertEquals(tx.getBookId(), borrower.getBookId());
        assertEquals(tx.getMemberId(), borrower.getMemberId());
        assertEquals(tx.getTransactionId(), borrower.getTransactionId());
    }

    @Test
    public void findByMemberIdBookId() {
        BookBorrower tx = addBorrowerHelper();

        List<BookBorrower> bookBorrower = service.findByMemberIdBookId(memberId, bookId);

        assertNotNull(bookBorrower);
        assertEquals(memberId, tx.getMemberId());
        assertEquals(bookId, tx.getBookId());
        assertEquals(1, bookBorrower.size());
    }

    @Test
    public void findSingleByMemberIdBookId() {
        addBorrowerHelper();

        Optional<BookBorrower> bookBorrower = service.findSingleByMemberIdBookId(memberId, bookId);
        assertTrue(bookBorrower.isPresent());

        Optional<BookBorrower> bookBorrower2 = service.findSingleByMemberIdBookId(bookId, memberId);
        assertFalse(bookBorrower2.isPresent());
    }

    @Test
    public void getAllTransactions() {
        addBorrowerHelper();
        assertEquals(1, service.getAllTransactions().size());
    }

    @Test
    public void getAllMemberTransaction() {
        BookBorrower tx = addBorrowerHelper();
        List<BookBorrower> txB = service.getAllMemberTransaction(tx.getMemberId());
        assertNotNull(txB);
    }

    @Test
    public void getTransactionById() {
        BookBorrower tx = addBorrowerHelper();

        Optional<BookBorrower> txB = service.getTransactionById(tx.getTransactionId());
        assertFalse(txB.isEmpty());

        assertEquals(txB.get().getTransactionId(), tx.getTransactionId());
    }
}