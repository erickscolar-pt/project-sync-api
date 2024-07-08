package com.projectsync.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projectsync.entity.Client;
import com.projectsync.dto.ClientDTO;
import com.projectsync.repository.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setEmail("john.doe@example.com");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Jane");
        client2.setLastName("Doe");
        client2.setEmail("jane.doe@example.com");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<ClientDTO> clients = clientService.findAll();

        assertEquals(2, clients.size());
        assertEquals("John", clients.get(0).getFirstName());
        assertEquals("Jane", clients.get(1).getFirstName());
    }

    @Test
    void testFindById() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmail("john.doe@example.com");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientDTO clientDTO = clientService.findById(1L);

        assertNotNull(clientDTO);
        assertEquals("John", clientDTO.getFirstName());
    }

    @Test
    void testSave() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmail("john.doe@example.com");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setEmail("john.doe@example.com");

        ClientDTO savedClient = clientService.save(clientDTO);

        assertNotNull(savedClient);
        assertEquals("John", savedClient.getFirstName());
    }

    @Test
    void testDeleteById() {
        clientService.deleteById(1L);
        verify(clientRepository, times(1)).deleteById(1L);
    }
}