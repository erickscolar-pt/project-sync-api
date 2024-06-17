package com.projectsync.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projetos", schema = "projectsync")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_seq")
    @SequenceGenerator(name = "projeto_seq", sequenceName = "projetos_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

}
