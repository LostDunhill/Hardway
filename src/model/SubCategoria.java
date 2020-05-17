package model;

public class SubCategoria {

	private int id;
	private String nome;
	private int idCategoria;
	
	public SubCategoria() {}
	
	public SubCategoria(int id) {
		this.id = id;
	}
	
	public SubCategoria(int id, String nome, int idCategoria) {
		this.id = id;
		this.nome = nome;
		this.idCategoria = idCategoria;
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

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	@Override
	public String toString() {
		return "SubCategoria [id=" + id + ", nome=" + nome + ", idCategoria=" + idCategoria + "]";
	}
	
}
