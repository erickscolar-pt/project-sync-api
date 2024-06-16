package com.projectsync.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.projectsync.entity.Atividade;
import com.projectsync.entity.Cliente;
import com.projectsync.entity.Projeto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DirtiesContext
    public void testSaveCliente() {
        // Dado de exemplo
        Cliente cliente = new Cliente(null, "Novo Cliente", "cliente@example.com", null, null);

        // Salvar o cliente
        Cliente savedCliente = clienteRepository.save(cliente);

        // Verificar se o cliente foi salvo corretamente e possui ID atribuído
        assertNotNull(savedCliente.getId());
        assertEquals(cliente.getNome(), savedCliente.getNome());
        assertEquals(cliente.getEmail(), savedCliente.getEmail());
    }

    @Test
    public void testFindById() {
        // Dado de exemplo
        Cliente cliente = new Cliente(null, "Novo Cliente", "cliente@example.com", null, null);
        Cliente savedCliente = clienteRepository.save(cliente);

        // Buscar o cliente pelo ID
        Optional<Cliente> foundCliente = clienteRepository.findById(savedCliente.getId());

        // Verificar se o cliente foi encontrado corretamente
        assertTrue(foundCliente.isPresent());
        assertEquals(savedCliente.getId(), foundCliente.get().getId());
        assertEquals(savedCliente.getNome(), foundCliente.get().getNome());
        assertEquals(savedCliente.getEmail(), foundCliente.get().getEmail());
    }

    @Test
    @DirtiesContext
    public void testUpdateCliente() {
        // Dado de exemplo
        Cliente cliente = new Cliente(null, "Novo Cliente", "cliente@example.com", null, null);
        Cliente savedCliente = clienteRepository.save(cliente);

        // Modificar o cliente
        savedCliente.setNome("Cliente Atualizado");
        savedCliente.setEmail("atualizado@example.com");
        clienteRepository.save(savedCliente);

        // Buscar o cliente atualizado pelo ID
        Optional<Cliente> updatedCliente = clienteRepository.findById(savedCliente.getId());

        // Verificar se o cliente foi atualizado corretamente
        assertTrue(updatedCliente.isPresent());
        assertEquals("Cliente Atualizado", updatedCliente.get().getNome());
        assertEquals("atualizado@example.com", updatedCliente.get().getEmail());
    }

    @Test
    @DirtiesContext
    public void testDeleteCliente() {
        // Dado de exemplo
        Cliente cliente = new Cliente(null, "Novo Cliente", "cliente@example.com", "119123456789", null);
        Projeto projeto = createProjeto(cliente);

        cliente.setProjetos((List<Projeto>) projeto);
        Cliente savedCliente = clienteRepository.save(cliente);

        // Deletar o cliente pelo ID
        clienteRepository.deleteById(savedCliente.getId());

        // Verificar se o cliente foi deletado corretamente
        assertFalse(clienteRepository.findById(savedCliente.getId()).isPresent());
    }

    public Projeto createProjeto(Cliente cliente){
        Projeto projeto = new Projeto();

        projeto.setNome("Projeto 1");
        projeto.setCliente(cliente);
        projeto.setDescricao("Projeto teste");
        projeto.setStatus("pendente");
        List<Atividade> atividade = (List<Atividade>) this.createAtividade(projeto);
        projeto.setAtividades(atividade);
        
        return projeto;
    }

    public Atividade createAtividade(Projeto projeto){
        Atividade atividade = new Atividade();
        atividade.setProjeto(projeto);
        atividade.setNome(projeto.getNome());
        atividade.setDescricao(projeto.getDescricao());
        atividade.setDataInicio(LocalDateTime.now());
        atividade.setDataFim(null);

        return atividade;

    }
}