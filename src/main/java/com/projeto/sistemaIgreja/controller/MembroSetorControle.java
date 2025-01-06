package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.MembroSetor;
import com.projeto.sistemaIgreja.repository.MembroSetorRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
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
public class MembroSetorControle {

    @Autowired   // faz a conexao com MembroSetorRepositorio
    private MembroSetorRepositorio membroSetorRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private SetorRepositorio setorRepositorio;

    @GetMapping("/cadastroMembroSetor")
    public ModelAndView cadastrar(MembroSetor membroSetor) {
        if (membroSetor == null) {
            membroSetor = new MembroSetor();
        }
        ModelAndView mv = new ModelAndView("administrativo/membroSetor/cadastro"); //redireciona para o html
        mv.addObject("membroSetor", membroSetor);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarMembroSetor")
    public ModelAndView salvar(@Valid MembroSetor membroSetor, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/membroSetor/cadastro"); //redireciona para o html
            mv.addObject("membroSetor", membroSetor);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("listaSetor", setorRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        membroSetorRepositorio.saveAndFlush(membroSetor);
        return new ModelAndView("redirect:/listarMembroSetor");
    }


    @GetMapping("/editarMembroSetor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<MembroSetor> membroSetor = membroSetorRepositorio.findById(id);
        // Caso não encontre o membroSetor, redireciona para a lista
        if (!membroSetor.isPresent()) {
            return listar();
        }
        return cadastrar(membroSetor.get());
    }


    @GetMapping("/listarMembroSetor")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/membroSetor/lista");
        mv.addObject("listaMembroSetor", membroSetorRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerMembroSetor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<MembroSetor> membroSetor = membroSetorRepositorio.findById(id);
        // Verifica se o endereco existe antes de excluir
        if (membroSetor.isPresent()) {
            membroSetorRepositorio.delete(membroSetor.get());
        }
        return listar();
    }

}
