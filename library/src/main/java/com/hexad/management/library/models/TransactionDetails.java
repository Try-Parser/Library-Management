package com.hexad.management.library.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransactionDetails {
    private Book bookDetails;
    private BookBorrower transactionDetails;
    private UUID memberId;
}
