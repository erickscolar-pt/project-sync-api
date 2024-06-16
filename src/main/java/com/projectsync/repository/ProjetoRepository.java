package com.projectsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectsync.entity.Projeto;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByStatus(String status);
    List<Projeto> findByClienteId(Long clienteId);
    // Aqui você pode adicionar métodos personalizados, se necessário
}