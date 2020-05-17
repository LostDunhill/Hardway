package model;

public class Cliente {
	
	private int id;
	private String nome;
	private String cpf;
	private String rg;
	private String email;
	private String senha;
	
	public Cliente() {
	}
	
	public Cliente(int id) {
		this.id = id;
	}

	public Cliente(int id, String nome, String cpf, String rg, String email,
			String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.senha = senha;
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
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", email=" + email + ", senha=" + senha + "]";
	}
	
}
