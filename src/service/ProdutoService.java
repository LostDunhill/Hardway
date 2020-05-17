package service;

import java.util.ArrayList;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoService {
	
	private ProdutoDAO dao;
	
	public ProdutoService() {
		dao = new ProdutoDAO();
	}
	
	public int incluir(Produto produto) {
		produto.setId(dao.incluir(produto));
		return produto.getId();
	}
	
	public void atualizar(Produto produto) {
		dao.atualizar(produto);
	}
	
	public void excluir(Produto produto) {
		dao.excluir(produto);
	}
	
	public void excluir(int id) {
		dao.excluir(new Produto(id));
	}
	
	public Produto carregar(Produto produto) {
		return dao.carregar(produto);
	}
	
	public Produto carregar(int id) {
		return dao.carregar(new Produto(id));
	}
	
	public ArrayList<Produto> carregarProdutos() {
		return dao.carregarProdutos();
	}
	
	public ArrayList<Produto> carregarProdutos(String chave) {
		return dao.carregarProdutos(chave);
	}
	
	public ArrayList<Produto> carregarProdutosDaSubCategoria(int id) {
		return dao.carregarProdutosDaSubCategoria(id);
	}
	
}
