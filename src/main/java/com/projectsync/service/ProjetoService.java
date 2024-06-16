package com.projectsync.service;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectsync.dto.ProjetoDTO;
import com.projectsync.entity.Cliente;
import com.projectsync.entity.Projeto;
import com.projectsync.repository.ClienteRepository;
import com.projectsync.repository.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ProjetoDTO> getAllProjetos() {
        return projetoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ProjetoDTO> getProjetoById(Long id) {
        return projetoRepository.findById(id).map(this::convertToDTO);
    }

    public ProjetoDTO createProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = convertToEntity(projetoDTO);
        return convertToDTO(projetoRepository.save(projeto));
    }

    public ProjetoDTO updateProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setStatus(projetoDTO.getStatus());

        return convertToDTO(projetoRepository.save(projeto));
    }

    public void deleteProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projetoRepository.delete(projeto);
    }

    private ProjetoDTO convertToDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setClienteId(projeto.getCliente().getId());
        dto.setStatus(projeto.getStatus());
        return dto;
    }

    private Projeto convertToEntity(ProjetoDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setId(dto.getId());
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setStatus(dto.getStatus());

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        projeto.setCliente(cliente);

        return projeto;
    }
}