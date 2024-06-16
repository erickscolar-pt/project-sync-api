package com.projectsync.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<ProjetoDTO> projetos;

}