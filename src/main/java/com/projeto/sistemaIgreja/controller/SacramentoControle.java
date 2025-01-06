package com.projeto.sistemaIgreja.controller;

import com.projeto.sistemaIgreja.models.Sacramento;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.PessoaRepositorio;
import com.projeto.sistemaIgreja.repository.SacramentoRepositorio;
import com.projeto.sistemaIgreja.repository.TipoSacramentoRepositorio;
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
public class SacramentoControle {

    @Autowired
    private SacramentoRepositorio sacramentoRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private TipoSacramentoRepositorio tipoSacramentoRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;

    // Cadastro de sacramento
    @GetMapping("/cadastroSacramento")
    public ModelAndView cadastrar(Sacramento sacramento) {
        if (sacramento == null){
            sacramento = new Sacramento();
        }
        ModelAndView mv = new ModelAndView("administrativo/sacramento/cadastro");
        mv.addObject("sacramento", sacramento);
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
        return mv;
    }

    // Salvar sacramento com validação
    @PostMapping("/salvarSacramento")
    public ModelAndView salvar(@Valid Sacramento sacramento, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/sacramento/cadastro");
            mv.addObject("sacramento", sacramento);
            mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
            mv.addObject("listaPessoa", pessoaRepositorio.findAll());
            mv.addObject("listaTipoSacramento", tipoSacramentoRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        sacramentoRepositorio.saveAndFlush(sacramento);
        return new ModelAndView("redirect:/listarSacramento");
    }


    // Editar sacramento
    @GetMapping("/editarSacramento/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Sacramento> sacramento = sacramentoRepositorio.findById(id);
        // Caso não encontre o sacramento, redireciona para a lista
        if (!sacramento.isPresent()) {
            return listar();
        }
        return cadastrar(sacramento.get());
    }

    // Listar todos os sacramentos
    @GetMapping("/listarSacramento")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/sacramento/lista");
        mv.addObject("listaSacramento", sacramentoRepositorio.findAll());
        return mv;
    }

    // Remover sacramento
    @GetMapping("/removerSacramento/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Sacramento> sacramento = sacramentoRepositorio.findById(id);
        // Verifica se o sacramento existe antes de excluir
        if (sacramento.isPresent()) {
            sacramentoRepositorio.delete(sacramento.get());
        }
        return listar();
    }
}
