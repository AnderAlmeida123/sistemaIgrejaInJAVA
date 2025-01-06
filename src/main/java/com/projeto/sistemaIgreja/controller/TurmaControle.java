package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Turma;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import com.projeto.sistemaIgreja.repository.TurmaRepositorio;
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
public class TurmaControle {

    @Autowired   // faz a conexao com TurmaRepositorio
    private TurmaRepositorio turmaRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private SetorRepositorio setorRepositorio;

    @GetMapping("/cadastroTurma")
    public ModelAndView cadastrar(Turma turma) {
        if (turma == null) {
            turma = new Turma();
        }
        ModelAndView mv = new ModelAndView("administrativo/turma/cadastro"); //redireciona para o html
        mv.addObject("turma", turma);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarTurma")
    public ModelAndView salvar(@Valid Turma turma, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/turma/cadastro"); //redireciona para o html
            mv.addObject("turma", turma);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("listaSetor", setorRepositorio.findAll());
            return mv;
        }

        turmaRepositorio.saveAndFlush(turma);
        return new ModelAndView("redirect:/listarTurma");
    }


    @GetMapping("/editarTurma/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Turma> turma = turmaRepositorio.findById(id);
        if (!turma.isPresent()) {
            return listar();
        }
        return cadastrar(turma.get());
    }


    @GetMapping("/listarTurma")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/turma/lista");
        mv.addObject("listaTurma", turmaRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerTurma/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Turma> turma = turmaRepositorio.findById(id);
        if (turma.isPresent()) {
            turmaRepositorio.delete(turma.get());
        }
        return listar();
    }

}
