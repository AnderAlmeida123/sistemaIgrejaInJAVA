package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Pessoa;
import com.projeto.sistemaIgreja.repository.*;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
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
public class PessoaControle {

    @Autowired   // faz a conexao com PessoaRepositorio
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;
    @Autowired
    private TipoSacramentoRepositorio tipoSacramentoRepositorio;


    @GetMapping("/cadastroPessoa")
    public ModelAndView cadastrar(Pessoa pessoa) {
        if (pessoa == null){
            pessoa = new Pessoa();
        }
        ModelAndView mv = new ModelAndView("administrativo/pessoa/cadastro");
        mv.addObject("pessoa", pessoa);
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
    }


    @PostMapping("/salvarPessoa")
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/pessoa/cadastro"); //redireciona para o html
            mv.addObject("pessoa", pessoa);
            mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
            mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        pessoaRepositorio.saveAndFlush(pessoa);
        return new ModelAndView("redirect:/listarPessoa");
    }


    @GetMapping("/editarPessoa/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
        if (!pessoa.isPresent()) {
            return listar(); // Redirecionar para a lista se o ID não for encontrado
        }
        return cadastrar(pessoa.get());
    }

    @GetMapping("/listarPessoa")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/pessoa/lista");
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerPessoa/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepositorio.delete(pessoa.get());
        }
        return listar();
    }

}
