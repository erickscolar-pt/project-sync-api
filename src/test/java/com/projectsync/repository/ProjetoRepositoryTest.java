package com.projectsync.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.projectsync.entity.Atividade;
import com.projectsync.entity.Cliente;
import com.projectsync.entity.Projeto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    @DirtiesContext
    public void testSaveProjeto() {
        // Dado de exemplo
        Projeto projeto = new Projeto(null, "Novo Projeto", "Descrição do Novo Projeto", null, null, null);
        Cliente cliente = createCliente((List<Projeto>) projeto);
        Atividade atividade = createAtividade(projeto);

        projeto.setCliente(cliente);
        projeto.setAtividades((List<Atividade>) atividade);
        // Salvar o projeto
        Projeto savedProjeto = projetoRepository.save(projeto);

        // Verificar se o projeto foi salvo corretamente e possui ID atribuído
        assertNotNull(savedProjeto.getId());
        assertEquals(projeto.getNome(), savedProjeto.getNome());
        assertEquals(projeto.getDescricao(), savedProjeto.getDescricao());
    }

    @Test
    public void testFindById() {
        // Dado de exemplo
        Projeto projeto = new Projeto(null, "Novo Projeto", "Descrição do Novo Projeto", null, null, null);
        Cliente cliente = createCliente((List<Projeto>) projeto);
        Atividade atividade = createAtividade(projeto);

        projeto.setCliente(cliente);
        projeto.setAtividades((List<Atividade>) atividade);

        Projeto savedProjeto = projetoRepository.save(projeto);

        // Buscar o projeto pelo ID
        Optional<Projeto> foundProjeto = projetoRepository.findById(savedProjeto.getId());

        // Verificar se o projeto foi encontrado corretamente
        assertTrue(foundProjeto.isPresent());
        assertEquals(savedProjeto.getId(), foundProjeto.get().getId());
        assertEquals(savedProjeto.getNome(), foundProjeto.get().getNome());
        assertEquals(savedProjeto.getDescricao(), foundProjeto.get().getDescricao());
    }

    @Test
    @DirtiesContext
    public void testUpdateProjeto() {
        // Dado de exemplo
        Projeto projeto = new Projeto(null, "Novo Projeto", "Descrição do Novo Projeto", null, null, null);
        Cliente cliente = createCliente((List<Projeto>) projeto);
        Atividade atividade = createAtividade(projeto);

        projeto.setCliente(cliente);
        projeto.setAtividades((List<Atividade>) atividade);

        Projeto savedProjeto = projetoRepository.save(projeto);

        // Modificar o projeto
        savedProjeto.setNome("Projeto Atualizado");
        savedProjeto.setDescricao("Descrição Atualizada");
        projetoRepository.save(savedProjeto);

        // Buscar o projeto atualizado pelo ID
        Optional<Projeto> updatedProjeto = projetoRepository.findById(savedProjeto.getId());

        // Verificar se o projeto foi atualizado corretamente
        assertTrue(updatedProjeto.isPresent());
        assertEquals("Projeto Atualizado", updatedProjeto.get().getNome());
        assertEquals("Descrição Atualizada", updatedProjeto.get().getDescricao());
    }

    @Test
    @DirtiesContext
    public void testDeleteProjeto() {
        // Dado de exemplo
        Projeto projeto = new Projeto(null, "Novo Projeto", "Descrição do Novo Projeto", null, null, null);
        Cliente cliente = createCliente((List<Projeto>) projeto);
        Atividade atividade = createAtividade(projeto);
        
        projeto.setCliente(cliente);
        projeto.setAtividades((List<Atividade>) atividade);

        Projeto savedProjeto = projetoRepository.save(projeto);

        // Deletar o projeto pelo ID
        projetoRepository.deleteById(savedProjeto.getId());

        // Verificar se o projeto foi deletado corretamente
        assertFalse(projetoRepository.findById(savedProjeto.getId()).isPresent());
    }

    public Projeto createProjeto(Cliente cliente) {
        Projeto projeto = new Projeto();

        projeto.setNome("Projeto 1");
        projeto.setCliente(cliente);
        projeto.setDescricao("Projeto teste");
        projeto.setStatus("pendente");
        List<Atividade> atividade = (List<Atividade>) this.createAtividade(projeto);
        projeto.setAtividades(atividade);

        return projeto;
    }

    public Atividade createAtividade(Projeto projeto) {
        Atividade atividade = new Atividade();
        atividade.setProjeto(projeto);
        atividade.setNome(projeto.getNome());
        atividade.setDescricao(projeto.getDescricao());
        atividade.setDataInicio(LocalDateTime.now());
        atividade.setDataFim(null);

        return atividade;

    }

    public Cliente createCliente(List<Projeto> projeto){
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        cliente.setEmail("teste@teste.com");
        cliente.setTelefone("11912345678");
        cliente.setProjetos(projeto);
        return cliente;

    }
}