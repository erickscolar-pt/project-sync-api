// AtividadeService.java
package com.projectsync.service;

import com.projectsync.dto.AddressDTO;
import com.projectsync.entity.Address;
import com.projectsync.entity.Client;
import com.projectsync.repository.AddressRepository;
import com.projectsync.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO findById(Long id) {
        return addressRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AddressDTO save(AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);
        return convertToDTO(savedAddress);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO convertToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setIdClient(address.getClient().getId());
        addressDTO.setAddress(address.getAddress());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        Client client = clientRepository.findById(addressDTO.getIdClient()).orElseThrow(() -> new RuntimeException("Client not found"));
        address.setClient(client);
        address.setAddress(addressDTO.getAddress());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        return address;
    }
}