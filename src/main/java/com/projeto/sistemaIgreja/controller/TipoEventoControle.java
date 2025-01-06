package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Sacramento;
import com.projeto.sistemaIgreja.models.TipoEvento;
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
public class TipoEventoControle {

    @Autowired   // faz a conexao com TipoEventoRepositorio
    private TipoEventoRepositorio tipoEventoRepositorio;

    @GetMapping("/cadastroTipoEvento")
    public ModelAndView cadastrar(TipoEvento tipoEvento) {
        if (tipoEvento == null) {
            tipoEvento = new TipoEvento();
        }
        ModelAndView mv = new ModelAndView("administrativo/tipoEvento/cadastro");
        mv.addObject("tipoEvento", tipoEvento);
        return mv;
    }

    @PostMapping("/salvarTipoEvento")
    public ModelAndView salvar(@Valid TipoEvento tipoEvento, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/tipoEvento/cadastro");
            mv.addObject("tipoEvento", tipoEvento);
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        tipoEventoRepositorio.saveAndFlush(tipoEvento);
        return new ModelAndView("redirect:/listarTipoEvento");
    }

    @GetMapping("/editarTipoEvento/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<TipoEvento> tipoEvento = tipoEventoRepositorio.findById(id);
        // Caso não encontre o tipoEvento, redireciona para a lista
        if (!tipoEvento.isPresent()) {
            return listar();
        }
        return cadastrar(tipoEvento.get());
    }

    @GetMapping("/listarTipoEvento")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/tipoEvento/lista");
        mv.addObject("listaTipoEvento", tipoEventoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerTipoEvento/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<TipoEvento> tipoEvento = tipoEventoRepositorio.findById(id);
        // Verifica se o tipoEvento existe antes de excluir
        if (tipoEvento.isPresent()) {
            tipoEventoRepositorio.delete(tipoEvento.get());
        }
        return listar();
    }

}
