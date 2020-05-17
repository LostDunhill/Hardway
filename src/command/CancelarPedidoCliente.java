package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Compra;
import model.Produto;
import service.CompraService;
import service.ProdutoService;

public class CancelarPedidoCliente implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// mesma funcionalidade do alterar status do pedido, porém pro cliente
		// foi feito separado por conta da questão do filter, para o cliente não poder cancelar ou enviar os produtos como admin
		
		int numPedido = Integer.parseInt(request.getParameter("NumPedido")); // pega o número do pedido
		ArrayList<Produto> carrinho = (ArrayList<Produto>) session.getAttribute("carrinho"); // pega os produtos comprados
		
		CompraService cs = new CompraService();
		Compra pedido = cs.carregar(numPedido); // carrega os dados do pedido
		
		if (pedido.getStatusCompra().equals("Aguardando envio")) { // se o status for "Aguardando envio", ele cancela o pedido
			ProdutoService ps = new ProdutoService();
			for (Produto produto : carrinho) {
				produto.setQtdeEstoque(produto.getQtdeEstoque() + produto.getQuantidade()); // adiciona os produtos de volta ao estoque
				ps.atualizar(produto); // atualiza no banco
			}
			pedido.setStatusCompra(1); // muda status para 1 ("Cancelado pelo cliente")
			cs.atualizar(pedido);
		}
		
		session.setAttribute("pedidoCliente", pedido); 
		
		RequestDispatcher view = request.getRequestDispatcher("VisualizarPedidoCliente.jsp");
		view.forward(request, response);
	}

}
