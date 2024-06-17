// AtividadeService.java
package com.projectsync.service;

import com.projectsync.dto.AtividadeDTO;
import com.projectsync.entity.Atividade;
import com.projectsync.entity.Projeto;
import com.projectsync.repository.AtividadeRepository;
import com.projectsync.repository.ProjetoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<AtividadeDTO> getAllAtividades() {
        return atividadeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<AtividadeDTO> getAtividadeById(Long id) {
        return atividadeRepository.findById(id).map(this::convertToDTO);
    }

    public AtividadeDTO createAtividade(AtividadeDTO atividadeDTO) {
        Atividade atividade = convertToEntity(atividadeDTO);
        atividade = atividadeRepository.save(atividade);
        return convertToDTO(atividade);
    }

    public AtividadeDTO updateAtividade(Long id, AtividadeDTO atividadeDTO) {
        Atividade atividade = convertToEntity(atividadeDTO);

        atividade.setId(id);
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setNome(atividadeDTO.getNome());
        atividade.getProjeto().setStatus(atividadeDTO.getStatus());
        atividade.setDataFim(LocalDateTime.now());
        atividade = atividadeRepository.save(atividade);
        return convertToDTO(atividade);
    }

    public void deleteAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }

    private AtividadeDTO convertToDTO(Atividade atividade) {

        return new AtividadeDTO(atividade.getId(), atividade.getNome(), atividade.getDescricao(), atividade.getProjeto().getId(), atividade.getDataInicio(), atividade.getDataFim(),atividade.getProjeto().getStatus());
    }

    private Atividade convertToEntity(AtividadeDTO atividadeDTO) {
        Projeto projeto = projetoRepository.findById(atividadeDTO.getProjetoId()).orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        return new Atividade(atividadeDTO.getId(), atividadeDTO.getNome(), atividadeDTO.getDescricao(), projeto, atividadeDTO.getDataInicio(), atividadeDTO.getDataFim());
    }
}
