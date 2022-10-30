package com.hexad.management.library.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Response<T> {
    private T response;
    private boolean success;
}
