package com.projectsync.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projectsync.entity.Client;
import com.projectsync.repository.AddressRepository;
import com.projectsync.repository.ClientRepository;
import com.projectsync.dto.AddressDTO;
import com.projectsync.entity.Address;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Address address1 = new Address();
        address1.setId(1L);
        address1.setAddress("Main St");
        address1.setNumber("123");
        address1.setCity("Springfield");

        Address address2 = new Address();
        address2.setId(2L);
        address2.setAddress("2nd St");
        address2.setNumber("456");
        address2.setCity("Springfield");

        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        List<AddressDTO> addresses = addressService.findAll();

        assertEquals(2, addresses.size());
        assertEquals("Main St", addresses.get(0).getAddress());
        assertEquals("2nd St", addresses.get(1).getAddress());
    }

    @Test
    void testFindById() {
        Address address = new Address();
        address.setId(1L);
        address.setAddress("Main St");
        address.setNumber("123");
        address.setCity("Springfield");

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        AddressDTO addressDTO = addressService.findById(1L);

        assertNotNull(addressDTO);
        assertEquals("Main St", addressDTO.getAddress());
    }

    @Test
    void testSave() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmail("john.doe@example.com");

        Address address = new Address();
        address.setId(1L);
        address.setAddress("Main St");
        address.setNumber("123");
        address.setCity("Springfield");
        address.setClient(client);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setIdClient(1L);
        addressDTO.setAddress("Main St");
        addressDTO.setNumber("123");
        addressDTO.setCity("Springfield");

        AddressDTO savedAddress = addressService.save(addressDTO);

        assertNotNull(savedAddress);
        assertEquals("Main St", savedAddress.getAddress());
    }

    @Test
    void testDeleteById() {
        addressService.deleteById(1L);
        verify(addressRepository, times(1)).deleteById(1L);
    }
}