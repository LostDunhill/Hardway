package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {
	
	public int incluir(Produto produto) {
		String sqlInsert = "INSERT INTO Produto(Nome, Descricao, Preco, QtdeEstoque, imagem, idFornecedor, idSubCategoria, idCategoria) VALUES(?,?,?,?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDesc());
			stm.setDouble(3, produto.getPreco());
			stm.setInt(4, produto.getQtdeEstoque());
			stm.setBytes(5, produto.getImagem());
			stm.setInt(6,  produto.getIdFornecedor());
			stm.setInt(7, produto.getIdSubCategoria());
			stm.setInt(8, produto.getIdCategoria());
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					produto.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return produto.getId();
	}
	
	public void atualizar(Produto produto) {
		String sqlUpdate = "UPDATE Produto SET nome=?, Descricao=?, Preco=?, QtdeEstoque=?, imagem=?, idFornecedor=?, idSubCategoria=?, idCategoria=? WHERE idProduto=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDesc());
			stm.setDouble(3, produto.getPreco());
			stm.setInt(4, produto.getQtdeEstoque());
			stm.setBytes(5, produto.getImagem());
			stm.setInt(6, produto.getIdFornecedor());
			stm.setInt(7, produto.getIdSubCategoria());
			stm.setInt(8, produto.getIdCategoria());
			stm.setInt(9, produto.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Produto produto) {
		String sqlDelete = "DELETE FROM Produto WHERE idProduto = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, produto.getId());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Produto carregar(Produto produto) {
		String sqlSelect = "SELECT * FROM Produto WHERE idProduto = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, produto.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					produto.setId(rs.getInt("idProduto"));
					produto.setNome(rs.getString("Nome"));
					produto.setDesc(rs.getString("Descricao"));
					produto.setPreco(rs.getDouble("Preco"));
					produto.setQtdeEstoque(rs.getInt("QtdeEstoque"));
					produto.setImagem(rs.getBytes("imagem"));
					produto.setBase64Imagem(rs.getBytes("imagem"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
					produto.setIdSubCategoria(rs.getInt("idSubCategoria"));
					produto.setIdCategoria(rs.getInt("idCategoria"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return produto;
	}
	
	public ArrayList<Produto> carregarProdutos() {
		ArrayList<Produto> produtos = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Produto";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setId(rs.getInt("idProduto"));
					produto.setNome(rs.getString("Nome"));
					produto.setDesc(rs.getString("Descricao"));
					produto.setPreco(rs.getDouble("Preco"));
					produto.setQtdeEstoque(rs.getInt("QtdeEstoque"));
					produto.setImagem(rs.getBytes("imagem"));
					produto.setBase64Imagem(rs.getBytes("imagem"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
					produto.setIdSubCategoria(rs.getInt("idSubCategoria"));
					produto.setIdCategoria(rs.getInt("idCategoria"));
					produtos.add(produto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return produtos;
	}
	
	public ArrayList<Produto> carregarProdutos(String chave) {
		ArrayList<Produto> produtos = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Produto WHERE upper(Nome) like ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1,"%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setId(rs.getInt("idProduto"));
					produto.setNome(rs.getString("Nome"));
					produto.setDesc(rs.getString("Descricao"));
					produto.setPreco(rs.getDouble("Preco"));
					produto.setQtdeEstoque(rs.getInt("QtdeEstoque"));
					produto.setImagem(rs.getBytes("imagem"));
					produto.setBase64Imagem(rs.getBytes("imagem"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
					produto.setIdSubCategoria(rs.getInt("idSubCategoria"));
					produto.setIdCategoria(rs.getInt("idCategoria"));
					produtos.add(produto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return produtos;
	}
	
	public ArrayList<Produto> carregarProdutosDaSubCategoria(int id) {
		ArrayList<Produto> produtos = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Produto WHERE idSubCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setId(rs.getInt("idProduto"));
					produto.setNome(rs.getString("Nome"));
					produto.setDesc(rs.getString("Descricao"));
					produto.setPreco(rs.getDouble("Preco"));
					produto.setQtdeEstoque(rs.getInt("QtdeEstoque"));
					produto.setImagem(rs.getBytes("imagem"));
					produto.setBase64Imagem(rs.getBytes("imagem"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
					produto.setIdSubCategoria(rs.getInt("idSubCategoria"));
					produto.setIdCategoria(rs.getInt("idCategoria"));
					produtos.add(produto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return produtos;
	}
}
