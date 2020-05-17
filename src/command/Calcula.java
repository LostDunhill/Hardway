package command;

import java.util.ArrayList;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.SubCategoria;



public class Calcula {
	// calcula valor total dos produtos do carrinho
	public static double total(ArrayList<Produto> produtosCarrinho) {
		double total = 0;
		for (Produto produto : produtosCarrinho) {
			total += produto.getPreco() * produto.getQuantidade();
		}
		return total;
	}
	
	// retorna o indice do produto no ArrayList de produtos
	public static int indiceProduto(ArrayList<Produto> produtosCarrinho, Produto produto) {
		for (int i = 0; i < produtosCarrinho.size(); i++) {
			Produto p = produtosCarrinho.get(i);
			if(p.getId() == produto.getId()) {
				return i;
			}
		}
		return -1;
	}

	// retorna o indice da categoria no ArrayList de categorias
	public static int indiceCategoria(ArrayList<Categoria> categorias, Categoria categoria) {
		for (int i = 0; i < categorias.size(); i++) {
			Categoria c = categorias.get(i);
			if(c.getId() == categoria.getId()) {
				return i;
			}
		}
		return -1;
	}

	// retorna o indice da subCategoria no ArrayList de subCategorias
	public static int indiceSubCategoria(ArrayList<SubCategoria> subCategorias, SubCategoria subCategoria) {
		for (int i = 0; i < subCategorias.size(); i++) {
			SubCategoria s = subCategorias.get(i);
			if(s.getId() == subCategoria.getId()) {
				return i;
			}
		}
		return -1;
	}

	// retorna o indice do fornecedor no ArrayList de Fornecedores
	public static int indiceFornecedor(ArrayList<Fornecedor> fornecedores, Fornecedor fornecedor) {
		for (int i = 0; i < fornecedores.size(); i++) {
			Fornecedor f = fornecedores.get(i);
			if(f.getId() == fornecedor.getId()) {
				return i;
			}
		}
		return -1;
	}
}
