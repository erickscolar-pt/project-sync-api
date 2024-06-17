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

    @Autowired
    private AtividadeService atividadeService;

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

    public Projeto updateProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setStatus(projetoDTO.getStatus());

        return projetoRepository.save(projeto);
    }

    public boolean deleteProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projetoRepository.delete(projeto);
        return true;
    }

    private ProjetoDTO convertToDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setClienteId(projeto.getCliente().getId());
        dto.setStatus(projeto.getStatus());
        dto.setClienteNome(projeto.getCliente().getNome());
        dto.setAtividades(atividadeService.convertToDTOList(projeto.getAtividades()));
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