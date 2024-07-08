package com.projectsync.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private Long idClient;
    private String address;
    private String number;
    private String complement;
    private String postalCode;
    private String city;
    private String state;
    private String country;

}