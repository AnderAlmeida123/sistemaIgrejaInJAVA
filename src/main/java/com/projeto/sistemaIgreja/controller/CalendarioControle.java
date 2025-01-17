package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Calendario;
import com.projeto.sistemaIgreja.repository.CalendarioRepositorio;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.TipoEventoRepositorio;
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
public class CalendarioControle {

    @Autowired   // faz a conexao com CalendarioRepositorio
    private CalendarioRepositorio calendarioRepositorio;

    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;
    @Autowired
    private TipoEventoRepositorio tipoEventoRepositorio;

    @GetMapping("/cadastroCalendario")
    public ModelAndView cadastrar(Calendario calendario) {
        if (calendario == null) {
            calendario = new Calendario();
        }
        ModelAndView mv = new ModelAndView("administrativo/calendario/cadastro"); //redireciona para o html
        mv.addObject("calendario", calendario);
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        mv.addObject("listaTipoEvento", tipoEventoRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarCalendario")
    public ModelAndView salvar(@Valid Calendario calendario, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/calendario/cadastro"); //redireciona para o html
            mv.addObject("calendario", calendario);
            mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
            mv.addObject("listaTipoEvento", tipoEventoRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        calendarioRepositorio.saveAndFlush(calendario);
        return new ModelAndView("redirect:/listarCalendario");
    }


    @GetMapping("/editarCalendario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Calendario> calendario = calendarioRepositorio.findById(id);
        // Caso não encontre o calendario, redireciona para a lista
        if (!calendario.isPresent()) {
            return listar();
        }
        return cadastrar(calendario.get());
    }


    @GetMapping("/listarCalendario")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/calendario/lista");
        mv.addObject("listaCalendario", calendarioRepositorio.findAll());
        return mv;
    }


    @GetMapping("/removerCalendario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Calendario> calendario = calendarioRepositorio.findById(id);
        // Verifica se o calendario existe antes de excluir
        if (calendario.isPresent()) {
            calendarioRepositorio.delete(calendario.get());
        }
        return listar();
    }

}
