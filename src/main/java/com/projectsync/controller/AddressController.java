package com.projectsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectsync.dto.AddressDTO;
import com.projectsync.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressDTO> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDTO findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PostMapping
    public AddressDTO save(@RequestBody AddressDTO addressDTO) {
        return addressService.save(addressDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
    }
}