package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Endereco;
import com.projeto.sistemaIgreja.models.Sacramento;
import com.projeto.sistemaIgreja.models.TipoSacramento;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.EnderecoRepositorio;
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
public class EnderecoControle {

    @Autowired   // faz a conexao com EnderecoRepositorio
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @GetMapping("/cadastroEndereco")
    public ModelAndView cadastrar(Endereco endereco) {
        if (endereco == null) {
            endereco = new Endereco();
        }
        ModelAndView mv = new ModelAndView("administrativo/endereco/cadastro"); //redireciona para o html
        mv.addObject("endereco", endereco);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarEndereco")
    public ModelAndView salvar(@Valid Endereco endereco, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/endereco/cadastro"); //redireciona para o html
            mv.addObject("endereco", endereco);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        enderecoRepositorio.saveAndFlush(endereco);
        return new ModelAndView("redirect:/listarEndereco");
    }


    @GetMapping("/editarEndereco/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);
        // Caso não encontre o endereco, redireciona para a lista
        if (!endereco.isPresent()) {
            return listar();
        }
        return cadastrar(endereco.get());
    }


    @GetMapping("/listarEndereco")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/endereco/lista");
        mv.addObject("listaEndereco", enderecoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerEndereco/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = enderecoRepositorio.findById(id);
        // Verifica se o endereco existe antes de excluir
        if (endereco.isPresent()) {
            enderecoRepositorio.delete(endereco.get());
        }
        return listar();
    }

}
