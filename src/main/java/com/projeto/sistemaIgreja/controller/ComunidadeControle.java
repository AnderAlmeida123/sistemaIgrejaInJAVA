package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Comunidade;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
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
public class ComunidadeControle {

    @Autowired   // faz a conexao com ComunidadeRepositorio
    private ComunidadeRepositorio comunidadeRepositorio;


    @GetMapping("/cadastroComunidade")
    public ModelAndView cadastrar(Comunidade comunidade) {
        if (comunidade == null) {
            comunidade = new Comunidade();
        }
        ModelAndView mv = new ModelAndView("administrativo/comunidade/cadastro"); //redireciona para o html
        mv.addObject("comunidade", comunidade);
        return mv;
    }

    @PostMapping("/salvarComunidade")
    public ModelAndView salvar(@Valid Comunidade comunidade, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/comunidade/cadastro"); //redireciona para o html
            mv.addObject("comunidade", comunidade);
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        comunidadeRepositorio.saveAndFlush(comunidade);
        return new ModelAndView("redirect:/listarComunidade");
    }


    @GetMapping("/editarComunidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Comunidade> comunidade = comunidadeRepositorio.findById(id);
        // Caso não encontre o endereco, redireciona para a lista
        if (!comunidade.isPresent()) {
            return listar();
        }
        return cadastrar(comunidade.get());
    }


    @GetMapping("/listarComunidade")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/comunidade/lista");
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerComunidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Comunidade> comunidade = comunidadeRepositorio.findById(id);
        // Verifica se o endereco existe antes de excluir
        if (comunidade.isPresent()) {
            comunidadeRepositorio.delete(comunidade.get());
        }
        return listar();
    }

}
