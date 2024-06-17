package com.projectsync.dto;

import java.util.List;

import com.projectsync.entity.Atividade;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long clienteId;
    private String status;
    private String clienteNome;
    private List<AtividadeDTO> atividades;

}