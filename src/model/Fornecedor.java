package model;

public class Fornecedor {
	
	private int id;
	private String nome;
	private String cnpj;
	private String email;
	private long telefone;
	
	public Fornecedor() {
	}
	
	public Fornecedor(int id) {
		this.id = id;
	}
	
	public Fornecedor(int id, String nome, String cnpj, String email, long telefone) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + ", tel=" + telefone + "]";
	}
	
}
