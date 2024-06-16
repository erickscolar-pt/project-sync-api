package com.projectsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectsync.dto.AtividadeDTO;
import com.projectsync.entity.Atividade;
import com.projectsync.entity.Projeto;
import com.projectsync.repository.AtividadeRepository;
import com.projectsync.repository.ProjetoRepository;
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
        return convertToDTO(atividadeRepository.save(atividade));
    }

    public AtividadeDTO updateAtividade(Long id, AtividadeDTO atividadeDTO) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setDataInicio(atividadeDTO.getDataInicio());
        atividade.setDataFim(atividadeDTO.getDataFim());

        return convertToDTO(atividadeRepository.save(atividade));
    }

    public void deleteAtividade(Long id) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        atividadeRepository.delete(atividade);
    }

    private AtividadeDTO convertToDTO(Atividade atividade) {
        AtividadeDTO dto = new AtividadeDTO();
        dto.setId(atividade.getId());
        dto.setNome(atividade.getNome());
        dto.setDescricao(atividade.getDescricao());
        dto.setProjetoId(atividade.getProjeto().getId());
        dto.setDataInicio(atividade.getDataInicio());
        dto.setDataFim(atividade.getDataFim());
        return dto;
    }

    private Atividade convertToEntity(AtividadeDTO dto) {
        Atividade atividade = new Atividade();
        atividade.setId(dto.getId());
        atividade.setNome(dto.getNome());
        atividade.setDescricao(dto.getDescricao());
        atividade.setDataInicio(dto.getDataInicio());
        atividade.setDataFim(dto.getDataFim());

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        atividade.setProjeto(projeto);

        return atividade;
    }
}