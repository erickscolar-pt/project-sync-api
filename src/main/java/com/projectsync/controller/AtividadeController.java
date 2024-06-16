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

import com.projectsync.dto.AtividadeDTO;
import com.projectsync.service.AtividadeService;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<AtividadeDTO> getAllAtividades() {
        return atividadeService.getAllAtividades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeDTO> getAtividadeById(@PathVariable Long id) {
        return atividadeService.getAtividadeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AtividadeDTO createAtividade(@RequestBody AtividadeDTO atividadeDTO) {
        return atividadeService.createAtividade(atividadeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> updateAtividade(@PathVariable Long id, @RequestBody AtividadeDTO atividadeDTO) {
        return ResponseEntity.ok(atividadeService.updateAtividade(id, atividadeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }
}