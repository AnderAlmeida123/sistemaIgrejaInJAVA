package com.projeto.sistemaIgreja.controller;

import com.projeto.sistemaIgreja.models.MovimentacaoProduto;
import com.projeto.sistemaIgreja.models.Produto;
import com.projeto.sistemaIgreja.models.TipoMovimentacao;
import com.projeto.sistemaIgreja.repository.MovimentacaoProdutoRepositorio;
import com.projeto.sistemaIgreja.repository.ProdutoRepositorio;
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
public class MovimentacaoProdutoControle {

    @Autowired
    private MovimentacaoProdutoRepositorio movimentacaoProdutoRepository;

    @Autowired
    private ProdutoRepositorio produtoRepository;

    @Autowired
    private SetorRepositorio setorRepository;

    @GetMapping("/cadastroMovimentacao")
    public ModelAndView cadastrar(MovimentacaoProduto movimentacaoProduto) {
        if (movimentacaoProduto == null) {
            movimentacaoProduto = new MovimentacaoProduto();
        }
        ModelAndView mv = new ModelAndView("administrativo/movimentacaoProduto/cadastro");
        mv.addObject("movimentacaoProduto", movimentacaoProduto);
        mv.addObject("produtos", produtoRepository.findAll());
        mv.addObject("setores", setorRepository.findAll());
        return mv;
    }

    @PostMapping("/salvarMovimentacao")
    public ModelAndView salvar(@Valid MovimentacaoProduto movimentacaoProduto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("administrativo/movimentacaoProduto/cadastro");
            mv.addObject("movimentacaoProduto", movimentacaoProduto);
            mv.addObject("produtos", produtoRepository.findAll());
            mv.addObject("setores", setorRepository.findAll());
            mv.addObject("erros", result.getAllErrors());
            return mv;
        }

        // Busca o produto relacionado à movimentação
        Produto produto = movimentacaoProduto.getProduto();
        if (produto == null) {
            result.rejectValue("produto", "error.movimentacaoProduto", "Produto não encontrado.");
            return cadastrar(movimentacaoProduto);
        }

        // Atualiza a quantidade de acordo com o tipo da movimentação
        if (movimentacaoProduto.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            if (produto.getQuantidade() < movimentacaoProduto.getQuantidade()) {
                result.rejectValue("quantidade", "error.movimentacaoProduto", "Quantidade insuficiente no estoque.");
                return cadastrar(movimentacaoProduto);
            }
            produto.setQuantidade(produto.getQuantidade() - movimentacaoProduto.getQuantidade());
        } else if (movimentacaoProduto.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + movimentacaoProduto.getQuantidade());
        } else {
            result.rejectValue("tipoMovimentacao", "error.movimentacaoProduto", "Tipo de movimentação inválido.");
            return cadastrar(movimentacaoProduto);
        }

        // Salva a movimentação e atualiza o produto no banco
        produtoRepository.saveAndFlush(produto);
        movimentacaoProdutoRepository.saveAndFlush(movimentacaoProduto);

        return new ModelAndView("redirect:/listarMovimentacao");
    }

    @GetMapping("/editarMovimentacao/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<MovimentacaoProduto> movimentacaoProduto = movimentacaoProdutoRepository.findById(id);
        if (!movimentacaoProduto.isPresent()) {
            return listar();
        }
        return cadastrar(movimentacaoProduto.get());
    }

    @GetMapping("/listarMovimentacao")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/movimentacaoProduto/lista");
        mv.addObject("listaMovimentacao", movimentacaoProdutoRepository.findAll());
        return mv;
    }

    @GetMapping("/removerMovimentacao/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<MovimentacaoProduto> movimentacaoProduto = movimentacaoProdutoRepository.findById(id);
        if (movimentacaoProduto.isPresent()) {
            movimentacaoProdutoRepository.delete(movimentacaoProduto.get());
        }
        return listar();
    }
}
