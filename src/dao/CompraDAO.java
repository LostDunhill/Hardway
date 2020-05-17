package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Compra;

public class CompraDAO {
	
	public int incluir(Compra compra) {
		String sqlInsert = "INSERT INTO Compra(DataCompra, Status, Rua, Numero, Bairro, Cidade, Estado, Complemento, CEP, ValorCompra) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setString(1, compra.getDataCompra());
			stm.setString(2, compra.getStatusCompra());
			stm.setString(3, compra.getRua());
			stm.setInt   (4, compra.getNumero());
			stm.setString(5, compra.getBairro());
			stm.setString(6, compra.getCidade());
			stm.setString(7, compra.getEstado());
			stm.setString(8, compra.getComplemento());
			stm.setString(9, compra.getCEP());
			stm.setDouble(10, compra.getValorCompra());
			
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					compra.setNumPedido(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return compra.getNumPedido();
	}
	
	public void atualizar(Compra compra) {
		String sqlUpdate = "UPDATE Compra SET DataCompra=?, Status=?, Rua=?, Numero=?, Bairro=?, Cidade=?, Estado=?, Complemento=?, CEP=?, ValorCompra=? WHERE NumPedido=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setString(1, compra.getDataCompra());
			stm.setString(2, compra.getStatusCompra());
			stm.setString(3, compra.getRua());
			stm.setInt   (4, compra.getNumero());
			stm.setString(5, compra.getBairro());
			stm.setString(6, compra.getCidade());
			stm.setString(7, compra.getEstado());
			stm.setString(8, compra.getComplemento());
			stm.setString(9, compra.getCEP());
			stm.setDouble(10, compra.getValorCompra());
			stm.setInt   (11, compra.getNumPedido());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Compra compra) {
		String sqlDelete = "DELETE FROM Compra WHERE NumPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, compra.getNumPedido());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Compra carregar(Compra compra) {
		String sqlSelect = "SELECT * FROM Compra WHERE NumPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, compra.getNumPedido());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					compra.setNumPedido(rs.getInt("NumPedido"));
					compra.setDataCompra(rs.getString("DataCompra"));
					compra.setStatusCompra(rs.getString("Status"));
					compra.setRua(rs.getString("Rua"));
					compra.setNumero(rs.getInt("Numero"));
					compra.setBairro(rs.getString("Bairro"));
					compra.setCidade(rs.getString("Cidade"));
					compra.setEstado(rs.getString("Estado"));
					compra.setComplemento(rs.getString("Complemento"));
					compra.setCEP(rs.getString("CEP"));
					compra.setValorCompra(rs.getDouble("ValorCompra"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return compra;
	}
	
	public ArrayList<Compra> listar() {
		ArrayList<Compra> pedidos = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM Compra";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Compra compra = new Compra();
					compra.setNumPedido(rs.getInt("NumPedido"));
					compra.setStatusCompra(rs.getString("Status"));
					compra.setDataCompra(rs.getString("DataCompra"));
					compra.setRua(rs.getString("Rua"));
					compra.setNumero(rs.getInt("Numero"));
					compra.setBairro(rs.getString("Bairro"));
					compra.setCidade(rs.getString("Cidade"));
					compra.setEstado(rs.getString("Estado"));
					compra.setComplemento(rs.getString("Complemento"));
					compra.setCEP(rs.getString("CEP"));
					compra.setValorCompra(rs.getDouble("ValorCompra"));
					pedidos.add(compra);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return pedidos;
	}
	
}
