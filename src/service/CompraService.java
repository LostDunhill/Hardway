package service;

import java.util.ArrayList;

import dao.CompraDAO;
import model.Compra;

public class CompraService {
	
	private CompraDAO dao;
	
	public CompraService() {
		dao = new CompraDAO();
	}
	
	public int incluir(Compra compra) {
		compra.setNumPedido(dao.incluir(compra));
		return compra.getNumPedido();
	}
	
	public void atualizar(Compra compra) {
		dao.atualizar(compra);
	}
	
	public void excluir(Compra compra) {
		dao.excluir(compra);
	}
	
	public void excluir(int id) {
		dao.excluir(new Compra(id));
	}
	
	public Compra carregar(Compra compra) {
		return dao.carregar(compra);
	}
	
	public Compra carregar(int id) {
		return dao.carregar(new Compra(id));
	}
	
	public ArrayList<Compra> listar() {
		return dao.listar();
	}
	
}
