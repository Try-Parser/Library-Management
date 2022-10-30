package com.hexad.management.library.services;

import com.hexad.management.library.models.BookBorrower;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/***
 * TransactionService holds
 *  - Transaction per borrow of books in library
 *  - note (only like this because there is no database)
 */
@Service
@Scope("singleton")
public class TransactionService {

    private final List<BookBorrower> borrowers = new ArrayList<>();

    public TransactionService() {
        //ignore constructor
    }

    /***
     * Add borrower transaction
     * @param tx BookBorrower
     * @return BookBorrower
     */
    public BookBorrower addBorrower(BookBorrower tx) {
        tx.setTransactionId(UUID.randomUUID());
        tx.setBarrowDate(new Date());
        borrowers.add(tx);

        return tx;
    }

    /***
     * findByMemberIdBookId
     * @param memberId UUID
     * @param bookId UUID
     * @return List<BookBorrower>
     */
    public List<BookBorrower> findByMemberIdBookId(UUID memberId, UUID bookId) {
        return borrowers
            .parallelStream()
            .filter(tx -> memberId.equals(tx.getMemberId()) && bookId.equals(tx.getBookId()))
            .collect(Collectors.toList());
    }

    /***
     * findSingleByMemberIdBookId
     * @param memberId UUID
     * @param bookId UUID
     * @return Optional<BookBorrower>
     */
    public Optional<BookBorrower> findSingleByMemberIdBookId(UUID memberId, UUID bookId) {
        return findByMemberIdBookId(memberId, bookId)
                .stream()
                .findFirst();
    }

    /***
     * getAllTransactions - return all transaction
     * @return List<BookBorrower>
     */
    public List<BookBorrower> getAllTransactions() {
        return borrowers;
    }

    /***
     * Get All Transaction That are not closed
     * @param id UUID
     * @return List<BookBorrower>
     */
    public List<BookBorrower> getAllMemberTransaction(UUID id) {
        return borrowers.parallelStream()
            .filter(tx -> tx.getMemberId().equals(id) && tx.getClose() == null)
            .collect(Collectors.toList());
    }

    /***
     * Get transaction by id
     * @param id UUID
     * @return Optional<BookBorrower>
     */
    public Optional<BookBorrower> getTransactionById(UUID id) {
        return getAllTransactions()
            .parallelStream()
            .filter(tx -> tx.getTransactionId().equals(id) && tx.getClose() == null)
            .findFirst();
    }
}
