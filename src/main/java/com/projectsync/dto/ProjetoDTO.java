package com.projectsync.dto;

import java.util.List;
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
    private List<AtividadeDTO> atividades;

}