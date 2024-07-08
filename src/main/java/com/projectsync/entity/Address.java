package com.projectsync.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String number;
    
    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}
