package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Categoria;

public class CategoriaDAO {

	public int incluir(Categoria categoria) {
		String sqlInsert = "INSERT INTO Categoria(Nome) VALUES (?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, categoria.getNome());
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					categoria.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categoria.getId();
	}
	
	public void atualizar(Categoria categoria) {
		String sqlUpdate = "UPDATE Categoria SET nome=? WHERE idCategoria=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, categoria.getNome());
			stm.setInt(2, categoria.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Categoria categoria) {
		String sqlDelete = "DELETE FROM Categoria WHERE idCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, categoria.getId());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Categoria carregar(Categoria categoria) {
		String sqlSelect = "SELECT * FROM Categoria WHERE idCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, categoria.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					categoria.setNome(rs.getString("Nome"));
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return categoria;
	}
	
	public ArrayList<Categoria> carregarCategorias() {
		ArrayList<Categoria> categorias = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Categoria";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Categoria categoria = new Categoria();
					categoria.setId(rs.getInt("idCategoria"));
					categoria.setNome(rs.getString("Nome"));
					categorias.add(categoria);
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return categorias;
	}
	
	public Categoria carregarNome(String nome) {
		Categoria categoria = new Categoria();
		String sqlSelect = "SELECT * FROM Categoria WHERE Nome = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, nome);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					categoria.setId(rs.getInt("idCategoria"));
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return categoria;
	}
	
}
