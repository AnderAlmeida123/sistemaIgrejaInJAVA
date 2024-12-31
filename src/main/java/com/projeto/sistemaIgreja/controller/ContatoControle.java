package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Contato;
import com.projeto.sistemaIgreja.repository.ContatoRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
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
        ModelAndView mv = new ModelAndView("administrativo/contato/cadastro"); //redireciona para o html
        mv.addObject("contato", contato);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarContato")
    public ModelAndView salvar(Contato contato, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(contato);
        }

        contatoRepositorio.saveAndFlush(contato);
        return cadastrar(new Contato());
    }



        @GetMapping("/editarContato/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Contato> contato = contatoRepositorio.findById(id);
            return cadastrar(contato.get());
        }


        @GetMapping("/listarContato")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/contato/lista");
        mv.addObject("listaContato", contatoRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerContato/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Contato> contato = contatoRepositorio.findById(id);
        contatoRepositorio.delete(contato.get());
        return listar();
        }

}
