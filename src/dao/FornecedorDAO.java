package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Fornecedor;

public class FornecedorDAO {

	public int incluir(Fornecedor fornecedor) {
		String sqlInsert = "INSERT INTO Fornecedor(Nome, CNPJ, Email, Telefone) VALUES(?,?,?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCnpj());
			stm.setString(3, fornecedor.getEmail());
			stm.setLong(4, fornecedor.getTelefone());
			
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					fornecedor.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fornecedor.getId();
	}
	
	public void atualizar(Fornecedor fornecedor) {
		String sqlUpdate = "UPDATE Fornecedor SET nome=?, CNPJ=?, Email=?, Telefone=? WHERE idFornecedor=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCnpj());
			stm.setString(3, fornecedor.getEmail());
			stm.setLong(4, fornecedor.getTelefone());
			stm.setInt(5, fornecedor.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Fornecedor fornecedor) {
		String sqlDelete = "DELETE FROM Fornecedor WHERE idFornecedor = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, fornecedor.getId());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Fornecedor carregar(Fornecedor fornecedor) {
		String sqlSelect = "SELECT * FROM Fornecedor WHERE idFornecedor = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, fornecedor.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					fornecedor.setId(rs.getInt("idFornecedor"));
					fornecedor.setNome(rs.getString("Nome"));
					fornecedor.setCnpj(rs.getString("CNPJ"));
					fornecedor.setEmail(rs.getString("Email"));
					fornecedor.setTelefone(rs.getLong("Telefone"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return fornecedor;
	}
	
	public Fornecedor carregarCnpj(String cnpj) {
		Fornecedor fornecedor = new Fornecedor();
		
		String sqlSelect = "SELECT * FROM Fornecedor WHERE CNPJ = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, cnpj);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					fornecedor.setId(rs.getInt("idFornecedor"));
					fornecedor.setNome(rs.getString("Nome"));
					fornecedor.setCnpj(rs.getString("CNPJ"));
					fornecedor.setEmail(rs.getString("Email"));
					fornecedor.setTelefone(rs.getLong("Telefone"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return fornecedor;
	}
	
	public ArrayList<Fornecedor> carregarFornecedores() {
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Fornecedor";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Fornecedor fornecedor = new Fornecedor();
					fornecedor.setId(rs.getInt("idFornecedor"));
					fornecedor.setNome(rs.getString("Nome"));
					fornecedor.setCnpj(rs.getString("CNPJ"));
					fornecedor.setEmail(rs.getString("Email"));
					fornecedor.setTelefone(rs.getLong("Telefone"));
					fornecedores.add(fornecedor);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return fornecedores;
	}
	
}
