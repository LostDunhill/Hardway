package service;

import java.util.ArrayList;

import dao.CategoriaDAO;
import model.Categoria;

public class CategoriaService {
	
	private CategoriaDAO dao;
	
	public CategoriaService() {
		dao = new CategoriaDAO();
	}
	
	public int incluir(Categoria categoria) {
		categoria.setId(dao.incluir(categoria));
		return categoria.getId();
	}
	
	public void atualizar(Categoria categoria) {
		dao.atualizar(categoria);
	}
	
	public void excluir(Categoria categoria) {
		dao.excluir(categoria);
	}
	
	public void excluir(int id) {
		dao.excluir(new Categoria(id));
	}
	
	public Categoria carregar(Categoria categoria) {
		return dao.carregar(categoria);
	}
	
	public Categoria carregar(int id) {
		return dao.carregar(new Categoria(id));
	}
	
	public boolean verificarNome(String nome) {
		return dao.carregarNome(nome).getId() != 0 ? true : false;
	}
	
	public Categoria carregarNome(String nome) {
		return dao.carregarNome(nome);
	}
	
	public ArrayList<Categoria> carregarCategorias() {
		return dao.carregarCategorias();
	}
	
}
