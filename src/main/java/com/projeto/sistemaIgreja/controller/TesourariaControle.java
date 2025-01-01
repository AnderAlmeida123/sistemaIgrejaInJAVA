package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Tesouraria;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import com.projeto.sistemaIgreja.repository.TesourariaRepositorio;
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
public class TesourariaControle {

    @Autowired   // faz a conexao com TesourariaRepositorio
    private TesourariaRepositorio tesourariaRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;
    @Autowired
    private SetorRepositorio setorRepositorio;


    @GetMapping("/cadastroTesouraria")
    public ModelAndView cadastrar(Tesouraria tesouraria) {
        ModelAndView mv = new ModelAndView("administrativo/tesouraria/cadastro"); //redireciona para o html
        mv.addObject("tesouraria", tesouraria);
        mv.addObject("listaSetor", setorRepositorio.findAll());
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarTesouraria")
    public ModelAndView salvar(Tesouraria tesouraria, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(tesouraria);
        }

        tesourariaRepositorio.saveAndFlush(tesouraria);
        return cadastrar(new Tesouraria());
    }



        @GetMapping("/editarTesouraria/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Tesouraria> tesouraria = tesourariaRepositorio.findById(id);
            return cadastrar(tesouraria.get());
        }


        @GetMapping("/listarTesouraria")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/tesouraria/lista");
        mv.addObject("listaTesouraria", tesourariaRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerTesouraria/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Tesouraria> tesouraria = tesourariaRepositorio.findById(id);
        tesourariaRepositorio.delete(tesouraria.get());
        return listar();
        }

}
