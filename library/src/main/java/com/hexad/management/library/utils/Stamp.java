package com.hexad.management.library.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Stamp {
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
