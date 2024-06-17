package com.projectsync.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividades", schema = "projectsync")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividade_seq")
    @SequenceGenerator(name = "atividade_seq", sequenceName = "atividades_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFim;

    @Column(nullable = false)
    private String status;
}
