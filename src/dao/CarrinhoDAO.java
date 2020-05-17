package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Carrinho;
import model.Cliente;
import model.Compra;
import model.Produto;

public class CarrinhoDAO {
	
	public void incluir(Carrinho carrinho) {
		String sqlInsert = "INSERT INTO Carrinho(idCliente, idProduto, NumPedido) VALUES(?,?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
			stm.setInt(1, carrinho.getIdCliente());
			stm.setInt(2, carrinho.getIdProduto());
			stm.setInt(3, carrinho.getNumPedido());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void atualizar(Carrinho carrinho) {
		String sqlUpdate = "UPDATE Carrinho SET idCliente=?, idProduto=? WHERE NumPedido=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate)) {
			stm.setInt(1, carrinho.getIdCliente());
			stm.setInt(2, carrinho.getIdProduto());
			stm.setInt(3, carrinho.getNumPedido());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Carrinho carrinho) {
		String sqlDelete = "DELETE FROM Carrinho WHERE NumPedido = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, carrinho.getNumPedido());
		
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Produto> carregarProdutosDoPedido(Compra compra) {
		String sqlSelect = "SELECT * FROM Carrinho WHERE NumPedido = ?";
		
		ArrayList<Produto> produtos = new ArrayList<>();
		
		ProdutoDAO dao = new ProdutoDAO();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, compra.getNumPedido());
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					int idProduto = rs.getInt("idProduto");
					produtos.add(dao.carregar(new Produto(idProduto)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return produtos;
	}
	
	public Cliente carregarClienteDoPedido(Compra compra) {
		String sqlSelect = "SELECT * FROM Carrinho WHERE NumPedido = ?";
		
		ClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, compra.getNumPedido());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente = dao.carregar(new Cliente(rs.getInt("idCliente")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return cliente;
	}
	
	public ArrayList<Compra> carregarPedidosDoProduto(Produto produto) {
		String sqlSelect = "SELECT * FROM Carrinho WHERE idProduto = ?";
		
		CompraDAO dao =  new CompraDAO();
		
		ArrayList<Compra> pedidos =  new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, produto.getId());
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pedidos.add(dao.carregar(new Compra(rs.getInt("NumPedido"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return pedidos;
	}

	public ArrayList<Compra> carregarPedidosDoCliente(Cliente cliente) {
		String sqlSelect = "SELECT * FROM Carrinho WHERE idCliente = ?";
		
		CompraDAO dao =  new CompraDAO();
		
		ArrayList<Compra> pedidos =  new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, cliente.getId());
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pedidos.add(dao.carregar(new Compra(rs.getInt("NumPedido"))));
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
