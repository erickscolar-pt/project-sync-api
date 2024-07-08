package com.projectsync.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO addressDTO;
}