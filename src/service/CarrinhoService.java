package service;

import java.util.ArrayList;

import dao.CarrinhoDAO;
import model.Carrinho;
import model.Cliente;
import model.Compra;
import model.Produto;

public class CarrinhoService {
	
	private CarrinhoDAO dao;
	
	public CarrinhoService() {
		dao = new CarrinhoDAO();
	}
	
	public void incluir(Carrinho carrinho) {
		dao.incluir(carrinho);
	}
	
	public void atualizar(Carrinho carrinho) {
		dao.atualizar(carrinho);
	}
	
	public void excluir(Carrinho carrinho) {
		dao.excluir(carrinho);
	}
	
	public void excluir(int numPedido) {
		Carrinho carrinho = new Carrinho();
		carrinho.setNumPedido(numPedido);
		
		dao.excluir(carrinho);
	}
	
	public ArrayList<Produto> carregarProdutosDoPedido(Compra compra) {
		return dao.carregarProdutosDoPedido(compra);
	}
	
	public ArrayList<Produto> carregarProdutosDoPedido(int numPedido) {
		return dao.carregarProdutosDoPedido(new Compra(numPedido));
	}
	
	public Cliente carregarClienteDoPedido(Compra compra) {
		return dao.carregarClienteDoPedido(compra);
	}
	
	public Cliente carregarClienteDoPedido(int numPedido) {		
		return dao.carregarClienteDoPedido(new Compra(numPedido));
	}
	
	public ArrayList<Compra> carregarPedidosDoProduto(Produto produto) {
		return dao.carregarPedidosDoProduto(produto);
	}
	
	public ArrayList<Compra> carregarPedidosDoProduto(int idProduto) {
		return dao.carregarPedidosDoProduto(new Produto(idProduto));
	}

	public ArrayList<Compra> carregarPedidosDoCliente(Cliente cliente) {
		return dao.carregarPedidosDoCliente(cliente);
	}
	
	public ArrayList<Compra> carregarPedidosDoCliente(int idCliente) {
		return dao.carregarPedidosDoCliente(new Cliente(idCliente));
	}
	
}
