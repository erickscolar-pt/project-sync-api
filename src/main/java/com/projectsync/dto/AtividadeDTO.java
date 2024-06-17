package com.projectsync.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long projetoId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private String clienteNome;

}