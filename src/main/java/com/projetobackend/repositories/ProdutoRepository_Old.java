package com.projetobackend.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.projetobackend.entities.Produto;

@Repository
public class ProdutoRepository_Old {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Long ultimoId = (long) 0;
	
	/**
	 * Método para retornar uma lista de produtos
	 * @return lista de produtos.
	 */
	public List<Produto> obterTodos() {
		return produtos;
	}
	
	/**
	 * Método que retorna o produto encontrado pelo id
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso tenha sido encontrado
	 */
	public Optional<Produto> obterPorId(Long id) {
		return produtos
				.stream()
				.filter(produto -> produto.getId() == id)
				.findFirst(); 
	}	
	
	/**
	 * Método para adicionar novo produto na lista
	 * @param produto a ser adicionado
	 * @return produto que foi adicionado na lista
	 */
	public Produto adicionar(Produto produto) {
		ultimoId++;
		produto.setId(ultimoId);
		produtos.add(produto);
		
		return produto;
	}
	
	/**
	 * Método para deletar produto da lista através de seu id
	 * @param id do produto a ser deletado da lista
	 */
	public void deletar(Long id) {
		produtos.removeIf(produto -> produto.getId() == id);
	}
	
	/**
	 * Metodo para atualizar um produto da lista, encontrado através do id
	 * @param produto que será atualizado
	 * @return Retorna o produto atualizado, com o mesmo id
	 */
	public Produto atualizar(Produto produto) {
						
		deletar(produto.getId());	
		produtos.add(produto);
		
		return produto;
	}
	
}
