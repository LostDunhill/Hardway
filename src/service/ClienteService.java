package service;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteService {
	
	private ClienteDAO dao;
	
	public ClienteService() {
		dao = new ClienteDAO();
	}
	
	public int incluir(Cliente cliente) {
		cliente.setId(dao.incluir(cliente));
		return cliente.getId();
	}
	
	public void atualizar(Cliente cliente) {
		dao.atualizar(cliente);
	}
	
	public void excluir(Cliente cliente) {
		dao.excluir(cliente);
	}
	
	public void excluir(int id) {
		dao.excluir(new Cliente(id));
	}
	
	public Cliente carregar(Cliente cliente) {
		return dao.carregar(cliente);
	}
	
	public Cliente carregar(int id) {
		return dao.carregar(new Cliente(id));
	}
	
	public Cliente carregaEmail(String email) {
		return dao.carregarEmail(email);
	}
	
	public boolean verificaDisponibilidadeEmail(String email) {
		return dao.carregarEmail(email).getNome() != null  ? false : true;
	}
	
	public boolean verificaDisponibilidadeCpf(String cpf) {
		return dao.carregarCpf(cpf).getNome() != null ? false : true;
	}
	
	public boolean verificaDisponibilidadeRg(String rg) {
		return dao.carregarRg(rg).getNome() != null ? false : true;
	}
	
	public String senhaToHash(String senha) {
		return dao.senhaToHash(senha);
	}
	
}
