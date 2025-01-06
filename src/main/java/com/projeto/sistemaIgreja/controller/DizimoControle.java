package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Dizimo;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import com.projeto.sistemaIgreja.repository.DizimoRepositorio;
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
public class DizimoControle {

    @Autowired   // faz a conexao com DizimoRepositorio
    private DizimoRepositorio dizimoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;


    @GetMapping("/cadastroDizimo")
    public ModelAndView cadastrar(Dizimo dizimo) {
        if (dizimo == null) {
            dizimo = new Dizimo();
        }
        ModelAndView mv = new ModelAndView("administrativo/dizimo/cadastro"); //redireciona para o html
        mv.addObject("dizimo", dizimo);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarDizimo")
    public ModelAndView salvar(@Valid Dizimo dizimo, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/dizimo/cadastro"); //redireciona para o html
            mv.addObject("dizimo", dizimo);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        dizimoRepositorio.saveAndFlush(dizimo);
        return new ModelAndView("redirect:/listarDizimo");
    }


    @GetMapping("/editarDizimo/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Dizimo> dizimo = dizimoRepositorio.findById(id);
        if (!dizimo.isPresent()) {
            return listar();
        }
        return cadastrar(dizimo.get());
    }


    @GetMapping("/listarDizimo")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/dizimo/lista");
        mv.addObject("listaDizimo", dizimoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerDizimo/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Dizimo> dizimo = dizimoRepositorio.findById(id);
        if (dizimo.isPresent()) {
            dizimoRepositorio.delete(dizimo.get());
        }
        return listar();
    }

}
