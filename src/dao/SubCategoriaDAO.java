package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SubCategoria;

public class SubCategoriaDAO {
	
	public int incluir(SubCategoria subCategoria) {
		String sqlInsert = "INSERT INTO SubCategoria(Nome, idCategoria) VALUES (?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, subCategoria.getNome());
			stm.setInt(2, subCategoria.getIdCategoria());
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					subCategoria.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return subCategoria.getId();
	}
	
	public void atualizar(SubCategoria subCategoria) {
		String sqlUpdate = "UPDATE SubCategoria SET nome=?, idCategoria=? WHERE idSubCategoria=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, subCategoria.getNome());
			stm.setInt(2, subCategoria.getIdCategoria());
			stm.setInt(3, subCategoria.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(SubCategoria subCategoria) {
		String sqlDelete = "DELETE FROM SubCategoria WHERE idSubCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, subCategoria.getId());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SubCategoria carregar(SubCategoria subCategoria) {
		String sqlSelect = "SELECT * FROM SubCategoria WHERE idSubCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, subCategoria.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					subCategoria.setNome(rs.getString("Nome"));
					subCategoria.setIdCategoria(rs.getInt("idCategoria"));
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return subCategoria;
	}
	
	public ArrayList<SubCategoria> carregarSubCategorias() {
		ArrayList<SubCategoria> subCategorias = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM SubCategoria";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					SubCategoria subCategoria = new SubCategoria();
					subCategoria.setId(rs.getInt("idSubCategoria"));
					subCategoria.setNome(rs.getString("Nome"));
					subCategoria.setIdCategoria(rs.getInt("idCategoria"));
					subCategorias.add(subCategoria);
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return subCategorias;
	}
	
	public ArrayList<SubCategoria> carregarSubCategorias(int id) {
		ArrayList<SubCategoria> subCategorias = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM SubCategoria WHERE idCategoria = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					SubCategoria subCategoria = new SubCategoria();
					subCategoria.setId(rs.getInt("idSubCategoria"));
					subCategoria.setNome(rs.getString("Nome"));
					subCategoria.setIdCategoria(rs.getInt("idCategoria"));
					subCategorias.add(subCategoria);
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return subCategorias;
	}
	
	public SubCategoria carregarNome(String nome) {
		SubCategoria subCategoria = new SubCategoria();
		
		String sqlSelect = "SELECT * FROM SubCategoria WHERE Nome = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, nome);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					subCategoria.setId(rs.getInt("idSubCategoria"));
					subCategoria.setNome(rs.getString("Nome"));
					subCategoria.setIdCategoria(rs.getInt("idCategoria"));
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return subCategoria;
	}
	
}
