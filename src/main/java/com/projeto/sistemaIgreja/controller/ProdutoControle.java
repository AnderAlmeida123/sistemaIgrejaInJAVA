package com.projeto.sistemaIgreja.controller;


import com.projeto.sistemaIgreja.models.Produto;
import com.projeto.sistemaIgreja.repository.ProdutoRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProdutoControle {

    @Autowired   // faz a conexao com ProdutoRepositorio
    private ProdutoRepositorio produtoRepositorio;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoControle.class);


    @GetMapping("/cadastroProduto")
    public ModelAndView cadastrar(Produto produto) {
        if (produto == null) {
            produto = new Produto();
        }
        logger.info("Produto recebido para cadastro: {}", produto); // Loga o objeto Produto
        ModelAndView mv = new ModelAndView("administrativo/produto/cadastro");
        mv.addObject("produto", produto);
        return mv;
    }


    @PostMapping("/salvarProduto")
    public ModelAndView salvar(@Valid Produto produto, BindingResult result) {
        logger.info("Produto recebido para salvar: {}", produto);  // Loga o objeto Produto
        if (result.hasErrors()) {
            logger.error("Erros de validação no produto: {}", result.getAllErrors());  // Loga os erros de validação
            ModelAndView mv = new ModelAndView("administrativo/produto/cadastro");
            mv.addObject("produto", produto);
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }
//
//        if (produto.getId() == null || produto.getId() == 0) {
//            produto.setId(null);
//        }

        produtoRepositorio.saveAndFlush(produto);
        logger.info("Produto salvo: {}", produto);
        return new ModelAndView("redirect:/listarProduto");
    }




    @GetMapping("/editarProduto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        logger.info("ID do produto recebido para edição: {}", id);  // Loga o ID recebido
        Optional<Produto> produto = produtoRepositorio.findById(id);
        if (!produto.isPresent()) {
            logger.warn("Produto não encontrado com ID: {}", id);  // Loga quando o produto não é encontrado
            return listar();
        }
        logger.info("Produto encontrado para edição: {}", produto.get());
        return cadastrar(produto.get());
    }


    @GetMapping("/listarProduto")
    public ModelAndView listar() {
        logger.info("Listando todos os produtos");
        ModelAndView mv = new ModelAndView("administrativo/produto/lista");
        mv.addObject("listaProduto", produtoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/removerProduto/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        logger.info("ID do produto recebido para remoção: {}", id);  // Loga o ID do produto
        Optional<Produto> produto = produtoRepositorio.findById(id);
        if (produto.isPresent()) {
            logger.info("Produto encontrado para remoção: {}", produto.get());
            produtoRepositorio.delete(produto.get());
        } else {
            logger.warn("Produto não encontrado para remoção com ID: {}", id);
        }
        return listar();
    }


}
