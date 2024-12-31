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
        ModelAndView mv = new ModelAndView("administrativo/tipoSacramento/cadastro"); //redireciona para o html
        mv.addObject("tipoSacramento", tipoSacramento);
        return mv;
    }

    @PostMapping("/salvarTipoSacramento")
    public ModelAndView salvar(@Valid TipoSacramento tipoSacramento, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(tipoSacramento);
        }

        tipoSacramentoRepositorio.saveAndFlush(tipoSacramento);
        return cadastrar(new TipoSacramento());
    }



        @GetMapping("/editarTipoSacramento/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<TipoSacramento> tipoSacramento = tipoSacramentoRepositorio.findById(id);
            return cadastrar(tipoSacramento.get());
        }


        @GetMapping("/listarTipoSacramento")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/tipoSacramento/lista");
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerTipoSacramento/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<TipoSacramento> tipoSacramento = tipoSacramentoRepositorio.findById(id);
        tipoSacramentoRepositorio.delete(tipoSacramento.get());
        return listar();
        }

}
