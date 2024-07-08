package com.projectsync.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectsync.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}