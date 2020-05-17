package model;

import java.util.ArrayList;

import service.SubCategoriaService;

public class Categoria {
	
	private int id;
	private String nome;
	
	public Categoria() {}
	
	public Categoria(int id) {
		this.id = id;
	}
	
	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
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
	
	public ArrayList<SubCategoria> getSubCategorias() {
		ArrayList<SubCategoria> subCategorias;
		SubCategoriaService ss = new SubCategoriaService();
		subCategorias = ss.carregarSubCategoriasDaCategoria(id);
		return subCategorias;
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome +"]";
	}
	
}
