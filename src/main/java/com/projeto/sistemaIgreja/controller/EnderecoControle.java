package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Endereco;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EnderecoControle {

    @Autowired   // faz a conexao com EnderecoRepositorio
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @GetMapping("/cadastroEndereco")
    public ModelAndView cadastrar(Endereco endereco) {
        ModelAndView mv = new ModelAndView("administrativo/endereco/cadastro"); //redireciona para o html
        mv.addObject("endereco", endereco);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarEndereco")
    public ModelAndView salvar(Endereco endereco, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(endereco);
        }

        enderecoRepositorio.saveAndFlush(endereco);
        return cadastrar(new Endereco());
    }



        @GetMapping("/editarEndereco/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Endereco> endereco = enderecoRepositorio.findById(id);
            return cadastrar(endereco.get());
        }


        @GetMapping("/listarEndereco")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/endereco/lista");
        mv.addObject("listaEndereco", enderecoRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerEndereco/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);
        enderecoRepositorio.delete(endereco.get());
        return listar();
        }

}
