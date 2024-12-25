package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Sacramento;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SacramentoRepositorio;
import com.projeto.sistemaIgreja.repository.TipoSacramentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class SacramentoControle {

    @Autowired   // faz a conexao com SacramentoRepositorio
    private SacramentoRepositorio sacramentoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private TipoSacramentoRepositorio tipoSacramentoRepositorio;

    @GetMapping("/cadastroSacramento")
    public ModelAndView cadastrar(Sacramento sacramento) {
        ModelAndView mv = new ModelAndView("administrativo/sacramento/cadastro"); //redireciona para o html
        mv.addObject("sacramento", sacramento);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarSacramento")
    public ModelAndView salvar(Sacramento sacramento, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(sacramento);
        }

        sacramentoRepositorio.saveAndFlush(sacramento);
        return cadastrar(new Sacramento());
    }



        @GetMapping("/editarSacramento/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Sacramento> sacramento = sacramentoRepositorio.findById(id);
            return cadastrar(sacramento.get());
        }


        @GetMapping("/listarSacramento")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/sacramento/lista");
        mv.addObject("listaSacramento", sacramentoRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerSacramento/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Sacramento> sacramento = sacramentoRepositorio.findById(id);
        sacramentoRepositorio.delete(sacramento.get());
        return listar();
        }

}
