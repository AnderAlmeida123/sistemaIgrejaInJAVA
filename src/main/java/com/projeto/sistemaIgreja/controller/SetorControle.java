package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Setor;
import com.projeto.sistemaIgreja.repository.ComunidadeRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
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
public class SetorControle {

    @Autowired   // faz a conexao com SetorRepositorio
    private SetorRepositorio setorRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private ComunidadeRepositorio comunidadeRepositorio;

    @GetMapping("/cadastroSetor")
    public ModelAndView cadastrar(Setor setor) {
        ModelAndView mv = new ModelAndView("administrativo/setor/cadastro"); //redireciona para o html
        mv.addObject("setor", setor);
        mv.addObject("listaPessoa", pessoaRepositorio.findAll());
        mv.addObject("listaComunidade", comunidadeRepositorio.findAll());
        return mv;
    }

    @PostMapping("/salvarSetor")
    public ModelAndView salvar(Setor setor, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(setor);
        }

        setorRepositorio.saveAndFlush(setor);
        return cadastrar(new Setor());
    }



        @GetMapping("/editarSetor/{id}")
        public ModelAndView editar (@PathVariable("id") Long id){
            Optional<Setor> setor = setorRepositorio.findById(id);
            return cadastrar(setor.get());
        }


        @GetMapping("/listarSetor")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("administrativo/setor/lista");
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
        }

        @GetMapping("/removerSetor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id){
        Optional<Setor> setor = setorRepositorio.findById(id);
        setorRepositorio.delete(setor.get());
        return listar();
        }

}
