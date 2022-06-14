package com.projetobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetobackend.controllers.exception.ObjectNotFoundException;
import com.projetobackend.dtos.ProdutoDTO;
import com.projetobackend.entities.Produto;
import com.projetobackend.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	/**
	 * Método para retornar uma lista de produtos
	 * @return lista de produtos.
	 */
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Método que retorna o produto encontrado pelo id
	 * @param id do produto que será localizado
	 * @return Retorna um produto caso tenha sido encontrado
	 */
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Produto não encontrado");
		}
		
		return obj.get();
	}
	
	/**
	 * Método para adicionar novo produto na lista
	 * @param produto a ser adicionado
	 * @return produto que foi adicionado na lista
	 */
	public Produto insert(Produto obj) {		
		return repository.save(obj);
	}
	
	public Produto fromDTO(ProdutoDTO objDto) {
		return new Produto(objDto.getId(), objDto.getNome(), objDto.getQuantidade(), objDto.getPreco(), objDto.getObservacao());
	}
	
	/**
	 * Método para deletar produto da lista através de seu id
	 * @param id do produto a ser deletado da lista
	 */
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	/**
	 * Metodo para atualizar um produto da lista, encontrado através do id
	 * @param produto que será atualizado
	 * @param id do produto a ser atualizado
	 * @return Retorna o produto atualizado, com o mesmo id
	 */
	public Produto update(Long id, Produto obj) {
		Produto entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setQuantidade(obj.getQuantidade());
		entity.setPreco(obj.getPreco());
		entity.setObservacao(obj.getObservacao());
		
	}
	
}
