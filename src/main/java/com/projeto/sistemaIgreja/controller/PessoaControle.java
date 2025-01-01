package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Pessoa;
import com.projeto.sistemaIgreja.repository.*;
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
public class PessoaControle {

    @Autowired   // faz a conexao com PessoaRepositorio
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;
    @Autowired
    private TipoSacramentoRepositorio tipoSacramentoRepositorio;


    @GetMapping("/cadastroPessoa")
    public ModelAndView cadastrar(Pessoa pessoa) {
        ModelAndView mv = new ModelAndView("administrativo/pessoa/cadastro");
        mv.addObject("pessoa", pessoa);
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
    }


    @PostMapping("/salvarPessoa")
    public ModelAndView salvar(Pessoa pessoa, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(pessoa);
        }

        // Ajustar relações
        if (pessoa.getTipoSacramento() != null) {
            pessoa.setTipoSacramento(tipoSacramentoRepositorio.findById(pessoa.getTipoSacramento().getId()).orElse(null));
        }
        if (pessoa.getComunidade() != null) {
            pessoa.setComunidade(comunidadeRepositorio.findById(pessoa.getComunidade().getId()).orElse(null));
        }

        pessoaRepositorio.saveAndFlush(pessoa);
        return cadastrar(new Pessoa());
    }



//    @PostMapping("/salvarPessoa")
//    public ModelAndView salvar(Pessoa pessoa, BindingResult result) {
//        if (result.hasErrors()) {
//            return cadastrar(pessoa);
//        }
//
//        pessoaRepositorio.saveAndFlush(pessoa);
//        return cadastrar(new Pessoa());
//    }



    @GetMapping("/editarPessoa/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
        if (pessoa.isEmpty()) {
            return listar(); // Redirecionar para a lista se o ID não for encontrado
        }
        return cadastrar(pessoa.get());
    }

        @GetMapping("/listarPessoa")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/pessoa/lista");
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerPessoa/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
        pessoaRepositorio.delete(pessoa.get());
        return listar();
        }

}
