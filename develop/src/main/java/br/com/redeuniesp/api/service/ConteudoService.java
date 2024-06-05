package br.com.redeuniesp.api.service;

import br.com.redeuniesp.api.model.Conteudo;
import br.com.redeuniesp.api.repository.ConteudoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ConteudoService {
    private ConteudoRepository repository;

    public Conteudo salvar(Conteudo conteudo){
        return repository.save(conteudo);
    }

    public List<Conteudo> listarPosts(){
        return repository.findAll();
    }

    public void delete(Conteudo conteudo){
        repository.delete(conteudo);
    }

    public Conteudo atualizar(Conteudo conteudo){
        if (conteudo.getUser()==null){
            throw new RuntimeException("Usuario nao esta logado");
        }
        return repository.save(conteudo);
    }
}