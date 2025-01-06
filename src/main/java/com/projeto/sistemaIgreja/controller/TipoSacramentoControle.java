package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.TipoSacramento;
import com.projeto.sistemaIgreja.repository.TipoSacramentoRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class TipoSacramentoControle {

    @Autowired   // faz a conexao com TipoSacramentoRepositorio
    private TipoSacramentoRepositorio tipoSacramentoRepositorio;

    @GetMapping("/cadastroTipoSacramento")
    public ModelAndView cadastrar(TipoSacramento tipoSacramento) {
        if (tipoSacramento == null) {
            tipoSacramento = new TipoSacramento();
        }
        ModelAndView mv = new ModelAndView("administrativo/tipoSacramento/cadastro"); //redireciona para o html
        mv.addObject("tipoSacramento", tipoSacramento);
        return mv;
    }

    @PostMapping("/salvarTipoSacramento")
    public ModelAndView salvar(@Valid TipoSacramento tipoSacramento, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/tipoSacramento/cadastro"); //redireciona para o html
            mv.addObject("tipoSacramento", tipoSacramento);
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        tipoSacramentoRepositorio.saveAndFlush(tipoSacramento);
        return new ModelAndView("redirect:/listarTipoSacramento");
    }


    @GetMapping("/editarTipoSacramento/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<TipoSacramento> tipoSacramento = tipoSacramentoRepositorio.findById(id);
        // Caso não encontre o tipoSacramento, redireciona para a lista
        if (!tipoSacramento.isPresent()) {
            return listar();
        }
        return cadastrar(tipoSacramento.get());
    }


    @GetMapping("/listarTipoSacramento")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/tipoSacramento/lista");
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerTipoSacramento/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<TipoSacramento> tipoSacramento = tipoSacramentoRepositorio.findById(id);
        // Verifica se o tipoSacramento existe antes de excluir
        if (tipoSacramento.isPresent()) {
            tipoSacramentoRepositorio.delete(tipoSacramento.get());
        }
        return listar();
    }

}
