package model;

import java.util.Base64;

public class Produto {
	
	private int id;
	private String nome;
	private String desc;
	private double preco;
    private int qtdeEstoque;
    private int quantidade;
    private byte[] imagem;
    private String base64Imagem;
	private int idFornecedor;
	private int idSubCategoria;
	private int idCategoria;
	
	public Produto() {
		this.quantidade = 1;
	}

	public Produto(int id) {
		this.id = id;
		this.quantidade = 1;
	}

	public Produto(
			int id, 
			String nome, 
			String desc, 
			double preco, 
			int qtdeEstoque,
			byte[] imagem,
			int idFornecedor, 
			int idSubCategoria, 
			int idCategoria) {
		this.id = id;
		this.nome = nome;
		this.desc = desc;
		this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
        this.imagem = imagem;
        this.base64Imagem = Base64.getEncoder().encodeToString(this.imagem);
        this.idFornecedor = idFornecedor;
        this.idSubCategoria = idSubCategoria;
        this.idCategoria = idCategoria;
        
        this.quantidade = 1;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
    
    public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
    
    public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	public int getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getBase64Imagem() {
		return this.base64Imagem;
	}
	
	public void setBase64Imagem() {
		this.base64Imagem = Base64.getEncoder().encodeToString(this.imagem);
	}
	
	public void setBase64Imagem(byte[] imagem) {
		this.base64Imagem = Base64.getEncoder().encodeToString(imagem);
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void aumentaQuantidade() {
		this.quantidade++;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + 
				", nome=" + nome + 
				", desc=" + desc + 
				", preco=" + preco + 
				", qtdeEstoque=" + qtdeEstoque + 
				", idFornecedor=" + idFornecedor + 
				", idSubCategoria=" + idSubCategoria + 
				", idCategoria=" + idCategoria + "]";
	}
	
}
