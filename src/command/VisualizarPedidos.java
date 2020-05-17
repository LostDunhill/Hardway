package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Compra;
import service.CarrinhoService;

public class VisualizarPedidos implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// carrega a tabela de pedidos do cliente
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		CarrinhoService service = new CarrinhoService();
		ArrayList<Compra> pedidosBanco = service.carregarPedidosDoCliente(cliente);
		ArrayList<Compra> pedidos = new ArrayList<>();
		
		for (int i = 0; i < pedidosBanco.size(); i++) {
			Compra pedido = pedidosBanco.get(i);
			if (checaPedido(pedido,  pedidos)) { // impede que o pedido fique duplicado na tabela
				pedidos.add(pedido);
			}
		}
		
		session.setAttribute("pedidos", pedidos);
		RequestDispatcher view = request.getRequestDispatcher("MeusPedidos.jsp");
		view.forward(request, response);
	}

	private boolean checaPedido(Compra pedido, ArrayList<Compra> pedidos) {
		for (Compra p : pedidos) {
			if(pedido.getNumPedido() == p.getNumPedido()) {
				return false;
			}
		}
		return true;
	}
	
}
