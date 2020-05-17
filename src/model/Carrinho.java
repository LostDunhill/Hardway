package model;

public class Carrinho {
	
	private int idCliente;
	private int idProduto;
	private int numPedido;
	
	public Carrinho() {}
	
	public Carrinho(int idCliente, int idProduto, int numPedido) {
		this.idCliente = idCliente;
		this.idProduto = idProduto;
		this.numPedido = numPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}	
}
