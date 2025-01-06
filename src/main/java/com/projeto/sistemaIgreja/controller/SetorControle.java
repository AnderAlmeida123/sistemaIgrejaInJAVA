package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Setor;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
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
public class SetorControle {

    @Autowired   // faz a conexao com SetorRepositorio
    private SetorRepositorio setorRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;

    @GetMapping("/cadastroSetor")
    public ModelAndView cadastrar(Setor setor) {
        if (setor == null) {
            setor = new Setor();
        }
        ModelAndView mv = new ModelAndView("administrativo/setor/cadastro"); //redireciona para o html
        mv.addObject("setor", setor);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarSetor")
    public ModelAndView salvar(@Valid Setor setor, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/setor/cadastro"); //redireciona para o html
            mv.addObject("setor", setor);
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
            return mv;
        }

        setorRepositorio.saveAndFlush(setor);
        return new ModelAndView("redirect:/listarSetor");
    }


    @GetMapping("/editarSetor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Setor> setor = setorRepositorio.findById(id);
        // Caso não encontre o endereco, redireciona para a lista
        if (!setor.isPresent()) {
            return listar();
        }
        return cadastrar(setor.get());
    }


    @GetMapping("/listarSetor")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/setor/lista");
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerSetor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Setor> setor = setorRepositorio.findById(id);
        // Verifica se o endereco existe antes de excluir
        if (setor.isPresent()) {
            setorRepositorio.delete(setor.get());
        }
        return listar();
    }

}
