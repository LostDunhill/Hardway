package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;

public class ClienteDAO {

	public int incluir(Cliente cliente) {
		String sqlInsert = "INSERT INTO Cliente(Nome, CPF, RG, Email, Senha) VALUES(?,?,?,?,PASSWORD(?))";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpf());
			stm.setString(3, cliente.getRg());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, cliente.getSenha());
			
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cliente.getId();
	}
	
	public void atualizar(Cliente cliente) {
		String sqlUpdate = "UPDATE Cliente SET nome=?, CPF=?, RG=?, Email=?, Senha=PASSWORD(?) WHERE idCliente=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpf());
			stm.setString(3, cliente.getRg());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, cliente.getSenha());
			stm.setInt(6, cliente.getId());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Cliente cliente) {
		String sqlDelete = "DELETE FROM Cliente WHERE idCliente = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, cliente.getId());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Cliente carregar(Cliente cliente) {
		String sqlSelect = "SELECT * FROM Cliente WHERE idCliente = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, cliente.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setCpf(rs.getString("CPF"));
					cliente.setRg(rs.getString("RG"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setSenha(rs.getString("Senha"));
				}
			} 
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return cliente;
	}
	
	public Cliente carregarEmail(String email) {
		Cliente cliente = new Cliente();
		
		String sqlSelect = "SELECT * FROM Cliente WHERE Email = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, email);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setCpf(rs.getString("CPF"));
					cliente.setRg(rs.getString("RG"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setSenha(rs.getString("Senha"));
				}
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return cliente;
	}
	
	public Cliente carregarCpf(String cpf) {
		Cliente cliente = new Cliente();
		
		String sqlSelect = "SELECT * FROM Cliente WHERE CPF = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, cpf);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setCpf(rs.getString("CPF"));
					cliente.setRg(rs.getString("RG"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setSenha(rs.getString("Senha"));
				}
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return cliente;
	}
	
	public Cliente carregarRg(String rg) {
		Cliente cliente = new Cliente();
		
		String sqlSelect = "SELECT * FROM Cliente WHERE RG = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setString(1, rg);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setCpf(rs.getString("CPF"));
					cliente.setRg(rs.getString("RG"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setSenha(rs.getString("Senha"));
				}
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return cliente;
	}
	
	public String senhaToHash(String senha) {
		String sqlHash = "SELECT PASSWORD(?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlHash)) {
			stm.setString(1, senha);
			try (ResultSet rs = stm.executeQuery();) {
				if(rs.next()) {
					return rs.getString(1);
				}
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return null;
	}
	
}
