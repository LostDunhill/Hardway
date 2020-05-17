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

public class AlterarStatusPedido implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int numPedido = Integer.parseInt(request.getParameter("numPedido")); // pega o número do pedido
		int status = Integer.parseInt(request.getParameter("status")); // pega o status do pedido
		ArrayList<Produto> carrinho = (ArrayList<Produto>) session.getAttribute("carrinho"); // produtos que foram comprados
		
		CompraService cs = new CompraService(); // instancia CompraService
		Compra pedido = cs.carregar(numPedido); // carrega o pedido
		
		if (pedido.getStatusCompra().equals("Aguardando envio")) { // se o status do pedido for igual a Aguardando Envio
			if (status == 0) { // se foi cancelado acrescenta a quantidade que foi comprada dos produtos de volta no estoque
				ProdutoService ps = new ProdutoService();
				for (Produto produto : carrinho) { // adiciona os produtos no estoque
					produto.setQtdeEstoque(produto.getQtdeEstoque() + produto.getQuantidade());
					ps.atualizar(produto);
				}
			}
			pedido.setStatusCompra(status); // muda o status da compra e atualiza o pedido no banco
			cs.atualizar(pedido);
		}
		
		session.setAttribute("pedidoCliente", pedido);
		
		RequestDispatcher view = request.getRequestDispatcher("VisualizarPedido.jsp");
		view.forward(request, response);
	}
	
}
