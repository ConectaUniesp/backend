package br.com.redeuniesp.api.controller;

import br.com.redeuniesp.api.model.Conteudo;
import br.com.redeuniesp.api.service.ConteudoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/publicar")
public class ConteudoController {
    private ConteudoService service;

    @PostMapping
    public Conteudo salvar(@RequestBody Conteudo conteudo){
        return service.salvar(conteudo);
    }
}
