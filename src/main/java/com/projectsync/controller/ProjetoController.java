package com.projectsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectsync.dto.ProjetoDTO;
import com.projectsync.service.ProjetoService;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<ProjetoDTO> getAllProjetos() {
        return projetoService.getAllProjetos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> getProjetoById(@PathVariable Long id) {
        return projetoService.getProjetoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProjetoDTO createProjeto(@RequestBody ProjetoDTO projetoDTO) {
        return projetoService.createProjeto(projetoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> updateProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.ok(projetoService.updateProjeto(id, projetoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
    }
}