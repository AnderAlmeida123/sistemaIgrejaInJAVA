package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Contato;
import com.projeto.sistemaIgreja.models.Endereco;
import com.projeto.sistemaIgreja.repository.ContatoRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ContatoControle {

    @Autowired   // faz a conexao com ContatoRepositorio
    private ContatoRepositorio contatoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @GetMapping("/cadastroContato")
    public ModelAndView cadastrar(Contato contato) {
        if (contato == null) {
            contato = new Contato();
        }
        ModelAndView mv = new ModelAndView("administrativo/contato/cadastro"); //redireciona para o html
        mv.addObject("contato", contato);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarContato")
    public ModelAndView salvar(@Valid Contato contato, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/contato/cadastro"); //redireciona para o html
            mv.addObject("contato", contato);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        contatoRepositorio.saveAndFlush(contato);
        return new ModelAndView("redirect:/listarContato");
    }


    @GetMapping("/editarContato/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Contato> contato = contatoRepositorio.findById(id);
        // Caso não encontre o contato, redireciona para a lista
        if (!contato.isPresent()) {
            return listar();
        }
        return cadastrar(contato.get());
    }


    @GetMapping("/listarContato")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/contato/lista");
        mv.addObject("listaContato", contatoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/removerContato/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Contato> contato = contatoRepositorio.findById(id);
        // Verifica se o contato existe antes de excluir
        if (contato.isPresent()) {
            contatoRepositorio.delete(contato.get());
        }
        return listar();
    }

}
