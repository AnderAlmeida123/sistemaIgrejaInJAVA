package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Estoque;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import com.projeto.sistemaIgreja.repository.EstoqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EstoqueControle {

    @Autowired   // faz a conexao com EstoqueRepositorio
    private EstoqueRepositorio estoqueRepositorio;
    @Autowired
    private SetorRepositorio setorRepositorio;

    @GetMapping("/cadastroEstoque")
    public ModelAndView cadastrar(Estoque estoque) {
        ModelAndView mv = new ModelAndView("administrativo/estoque/cadastro"); //redireciona para o html
        mv.addObject("estoque", estoque);
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarEstoque")
    public ModelAndView salvar(Estoque estoque, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(estoque);
        }

        estoqueRepositorio.saveAndFlush(estoque);
        return cadastrar(new Estoque());
    }



        @GetMapping("/editarEstoque/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Estoque> estoque = estoqueRepositorio.findById(id);
            return cadastrar(estoque.get());
        }


        @GetMapping("/listarEstoque")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/estoque/lista");
        mv.addObject("listaEstoque", estoqueRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerEstoque/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Estoque> estoque = estoqueRepositorio.findById(id);
        estoqueRepositorio.delete(estoque.get());
        return listar();
        }

}
