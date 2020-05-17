package model;

public class Compra {
	
	private int numPedido;
	private String dataCompra;
	private String statusCompra;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private String CEP;
	private double valorCompra;
	
	public Compra() {
		this.statusCompra = "Aguardando envio";
	}
	
	public Compra(int numPedido) {
		this.numPedido = numPedido;
		this.statusCompra = "Aguardando envio";
	}
	
	public Compra(
			int numPedido, 
			String dataCompra, 
			String rua,
			int numero,
			String bairro,
			String cidade,
			String estado,
			String complemento,
			String CEP,
			double valorCompra) {
		this.numPedido = numPedido;
		this.dataCompra = dataCompra;
		this.statusCompra = "Aguardando envio";
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.CEP = CEP;
		this.valorCompra = valorCompra;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public String getStatusCompra() {
		return statusCompra;
	}
	
	public void setStatusCompra(int statusCompra) {
		if (statusCompra == 0) this.statusCompra = "Cancelado";
		else if (statusCompra == 1) this.statusCompra = "Cancelado pelo Cliente";
		else if (statusCompra == 2) this.statusCompra = "Enviado";
		else this.statusCompra = "Aguardando Envio";
	}
	
	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	@Override
	public String toString() {
		return "Compra [numPedido=" + numPedido + 
				", dataCompra=" + dataCompra + 
				", rua=" + rua + 
				", numero=" + numero + 
				", bairro=" + bairro + 
				", cidade=" + cidade + 
				", estado=" + estado + 
				", complemento=" + complemento + 
				", cep=" + CEP + 
				", valorCompra=" + valorCompra + "]";
	}
}
