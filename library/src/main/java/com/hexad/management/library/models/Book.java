package com.hexad.management.library.models;

import com.hexad.management.library.utils.Stamp;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book extends Stamp {
    private UUID bookId;
    private String subject;
    private String title;
    private String author;
    private String publisher;
    private String copyRight;
    private int editionNumber;
    private int pages;
    private int numberOfCopies;
    private String nameOfLibrary;
    private int selfNo;
}
