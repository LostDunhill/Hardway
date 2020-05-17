package service;

import java.util.ArrayList;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorService {
	
	private FornecedorDAO dao;
	
	public FornecedorService() {
		dao = new FornecedorDAO();
	}
	
	public int incluir(Fornecedor fornecedor) {
		fornecedor.setId(dao.incluir(fornecedor));
		return fornecedor.getId();
	}
	
	public void atualizar(Fornecedor fornecedor) {
		dao.atualizar(fornecedor);
	}
	
	public void excluir(Fornecedor fornecedor) {
		dao.excluir(fornecedor);
	}
	
	public void excluir(int id) {
		dao.excluir(new Fornecedor(id));
	}
	
	public Fornecedor carregar(Fornecedor fornecedor) {
		return dao.carregar(fornecedor);
	}
	
	public Fornecedor carregar(int id) {
		return dao.carregar(new Fornecedor(id));
	}
	
	public boolean verificarCnpj(String cnpj) {
		return dao.carregarCnpj(cnpj).getNome() != null ? true : false;
	}
	
	public ArrayList<Fornecedor> carregarFornecedores() {
		return dao.carregarFornecedores();
	}
	
}
