package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.MembroTurma;
import com.projeto.sistemaIgreja.repository.MembroTurmaRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import com.projeto.sistemaIgreja.repository.TurmaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MembroTurmaControle {

    @Autowired   // faz a conexao com MembroTurmaRepositorio
    private MembroTurmaRepositorio membroTurmaRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private TurmaRepositorio turmaRepositorio;

    @GetMapping("/cadastroMembroTurma")
    public ModelAndView cadastrar(MembroTurma membroTurma) {
        ModelAndView mv = new ModelAndView("administrativo/membroTurma/cadastro"); //redireciona para o html
        mv.addObject("membroTurma", membroTurma);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaTurma", turmaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarMembroTurma")
    public ModelAndView salvar(MembroTurma membroTurma, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(membroTurma);
        }

        membroTurmaRepositorio.saveAndFlush(membroTurma);
        return cadastrar(new MembroTurma());
    }


    @GetMapping("/editarMembroTurma/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<MembroTurma> membroTurma = membroTurmaRepositorio.findById(id);
        return cadastrar(membroTurma.get());
    }


    @GetMapping("/listarMembroTurma")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/membroTurma/lista");
        mv.addObject("listaMembroTurma", membroTurmaRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerMembroTurma/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<MembroTurma> membroTurma = membroTurmaRepositorio.findById(id);
        membroTurmaRepositorio.delete(membroTurma.get());
        return listar();
    }

}
