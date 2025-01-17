package com.projeto.sistemaIgreja.controller;

import com.projeto.sistemaIgreja.models.Estoque;
import com.projeto.sistemaIgreja.models.Setor;
import com.projeto.sistemaIgreja.repository.EstoqueRepositorio;
import com.projeto.sistemaIgreja.repository.SetorRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class EstoqueControle {

    @Autowired
    private EstoqueRepositorio estoqueRepositorio;

    @Autowired
    private SetorRepositorio setorRepositorio;

    /**
     * Exibe o formulário de cadastro de estoque.
     */
    @GetMapping("/cadastroEstoque")
    public ModelAndView cadastrar(Estoque estoque) {
        if (estoque == null) {
            estoque = new Estoque();
        }
        ModelAndView mv = new ModelAndView("administrativo/estoque/cadastro");
        mv.addObject("estoque", estoque);
        mv.addObject("listaSetor", setorRepositorio.findAll());
        return mv;
    }

    /**
     * Salva ou atualiza um estoque.
     */
    @PostMapping("/salvarEstoque")
    public ModelAndView salvar(@Valid Estoque estoque, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/estoque/cadastro");
            mv.addObject("estoque", estoque);
            mv.addObject("listaSetor", setorRepositorio.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }
        estoqueRepositorio.saveAndFlush(estoque);
        return new ModelAndView("redirect:/listarEstoque");
    }

    /**
     * Lista todos os estoques.
     */
    @GetMapping("/listarEstoque")
    public ModelAndView listar() {
        List<Estoque> estoques = estoqueRepositorio.findAll();

        // Substituir setores nulos por objetos vazios para evitar erros no template
        estoques.forEach(estoque -> {
            if (estoque.getSetor() == null) {
                estoque.setSetor(new Setor()); // Setor vazio com valores padrão
            }
        });

        ModelAndView mv = new ModelAndView("administrativo/estoque/lista");
        mv.addObject("listaEstoque", estoques);
        return mv;
    }

    /**
     * Edita um estoque pelo ID.
     */
    @GetMapping("/editarEstoque/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estoque> estoque = estoqueRepositorio.findById(id);
        if (!estoque.isPresent()) {
            return listar(); // Caso não encontre o estoque, redireciona para a lista
        }
        return cadastrar(estoque.get());
    }

    /**
     * Remove um estoque pelo ID.
     */
    @GetMapping("/removerEstoque/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estoque> estoque = estoqueRepositorio.findById(id);
        if (estoque.isPresent()) {
            estoqueRepositorio.delete(estoque.get());
        }
        return listar();
    }

    /**
     * Adiciona ou remove uma quantidade ao estoque (Entrada ou Saída).
     */
    @PostMapping("/atualizarEstoque/{id}")
    public ModelAndView atualizarEstoque(@PathVariable("id") Long id,
                                         @RequestParam("quantidade") Integer quantidade,
                                         @RequestParam("entradaSaida") String entradaSaida) {
        Optional<Estoque> estoqueOptional = estoqueRepositorio.findById(id);

        if (estoqueOptional.isPresent()) {
            Estoque estoque = estoqueOptional.get();

            // Verifica se é entrada ou saída e realiza a operação
            if ("entrada".equals(entradaSaida)) {
                estoque.adicionarQuantidade(quantidade);  // Adiciona a quantidade
            } else if ("saida".equals(entradaSaida)) {
                try {
                    estoque.removerQuantidade(quantidade);  // Remove a quantidade
                } catch (IllegalArgumentException e) {
                    ModelAndView mv = listar();
                    mv.addObject("erro", "Erro ao remover itens: " + e.getMessage());
                    return mv;
                }
            }

            estoqueRepositorio.saveAndFlush(estoque);
        }
        return listar();
    }
}
