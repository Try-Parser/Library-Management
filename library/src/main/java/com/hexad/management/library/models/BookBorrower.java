package com.hexad.management.library.models;

import com.hexad.management.library.utils.Stamp;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookBorrower extends Stamp {
    private UUID transactionId;
    private UUID bookId;
    private UUID memberId;
    private Date barrowDate;
    private Date returnDate;
    private Date close;
}
