package service;

import java.util.ArrayList;

import dao.SubCategoriaDAO;
import model.SubCategoria;

public class SubCategoriaService {
	
	private SubCategoriaDAO dao;
	
	public SubCategoriaService() {
		dao = new SubCategoriaDAO();
	}
	
	public int incluir(SubCategoria subCategoria) {
		subCategoria.setId(dao.incluir(subCategoria));
		return subCategoria.getId();
	}
	
	public void atualizar(SubCategoria subCategoria) {
		dao.atualizar(subCategoria);
	}
	
	public void excluir(SubCategoria subCategoria) {
		dao.excluir(subCategoria);
	}
	
	public void excluir(int id) {
		dao.excluir(new SubCategoria(id));
	}
	
	public SubCategoria carregar(SubCategoria subCategoria) {
		return dao.carregar(subCategoria);
	}
	
	public SubCategoria carregar(int id) {
		return dao.carregar(new SubCategoria(id));
	}
	
	public boolean verificarNome(String nome) {
		return dao.carregarNome(nome).getId() !=0 ? true : false; 
	}
	
	public ArrayList<SubCategoria> carregarSubCategorias() {
		return dao.carregarSubCategorias();
	}
	
	public ArrayList<SubCategoria> carregarSubCategoriasDaCategoria(int id) {
		return dao.carregarSubCategorias(id);
	}
	
}
