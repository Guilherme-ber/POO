package com.oo.projetocadproduto.gerenciador;

import com.oo.projetocadproduto.classes.Produto;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProduto {

    private List<Produto> produtos;

    public GerenciadorProduto() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public boolean removerProduto(String cod) {
        for (Produto produto : produtos) {
            if (produto.getCod().equals(cod)) {
                produtos.remove(produto);
                System.out.println("Produto removido com sucesso!");
                return true;
            }
        }
        System.out.println("Produto não encontrado!");
        return false;
    }

    public Produto buscarProduto(String cod) {
        for (Produto produto : produtos) {
            if (produto.getCod().equals(cod)) {
                return produto;
            }
        }
        return null;
    }

    public void atualizarProduto(String codAntigo, Produto produtoNovo) {
        Produto produtoExistente = buscarProduto(codAntigo);
        
        if (produtoExistente != null) {
            int indice = produtos.indexOf(produtoExistente);
            produtos.set(indice, produtoNovo);
            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto com o nome " + codAntigo + " não encontrado.");
        }
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        for (Produto produto : produtos) {
            saida.append(produto.toString()).append("\n");
        }
        return saida.toString();
    }

}
