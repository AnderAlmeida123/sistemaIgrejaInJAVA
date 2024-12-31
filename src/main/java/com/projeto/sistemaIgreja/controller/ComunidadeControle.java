package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Comunidade;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
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
public class ComunidadeControle {

    @Autowired   // faz a conexao com ComunidadeRepositorio
    private ComunidadeRepositorio comunidadeRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @GetMapping("/cadastroComunidade")
    public ModelAndView cadastrar(Comunidade comunidade) {
        ModelAndView mv = new ModelAndView("administrativo/comunidade/cadastro"); //redireciona para o html
        mv.addObject("comunidade", comunidade);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarComunidade")
    public ModelAndView salvar(Comunidade comunidade, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(comunidade);
        }

        comunidadeRepositorio.saveAndFlush(comunidade);
        return cadastrar(new Comunidade());
    }



        @GetMapping("/editarComunidade/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Comunidade> comunidade = comunidadeRepositorio.findById(id);
            return cadastrar(comunidade.get());
        }


        @GetMapping("/listarComunidade")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/comunidade/lista");
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerComunidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Comunidade> comunidade = comunidadeRepositorio.findById(id);
        comunidadeRepositorio.delete(comunidade.get());
        return listar();
        }

}
