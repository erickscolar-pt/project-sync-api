package com.projectsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectsync.entity.Address;
import com.projectsync.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT a FROM Address a WHERE a.client.id = :clientId")
    List<Address> findAddressesByClientId(Long clientId);
}