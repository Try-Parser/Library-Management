package com.hexad.management.library.models;

import com.hexad.management.library.utils.Stamp;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends Stamp {
    private UUID id;
    private String password;
    private String name;
    private String email;
    private String address;
    private boolean admin;
}
