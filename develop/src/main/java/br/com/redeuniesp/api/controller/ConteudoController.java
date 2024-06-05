package br.com.redeuniesp.api.controller;

import br.com.redeuniesp.api.model.Conteudo;
import br.com.redeuniesp.api.service.ConteudoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/publicar")
public class ConteudoController {
    private ConteudoService service;

    @PostMapping
    public Conteudo salvar(@RequestBody Conteudo conteudo){
        return service.salvar(conteudo);
    }

    @PutMapping
    public Conteudo atualizar(Conteudo conteudo){
        return service.atualizar(conteudo);
    }

    @DeleteMapping
    public void delete(Conteudo conteudo){
        service.delete(conteudo);
    }

    @GetMapping
    public List<Conteudo> listarPosts(){
        return service.listarPosts();
    }
}
