package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrinho;
import model.Cliente;
import model.Compra;
import model.Produto;
import service.CarrinhoService;
import service.CompraService;
import service.ProdutoService;

public class FinalizarCompra implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Pega o pedido, o cliente e os produtos comprados
		Compra pedido = (Compra) session.getAttribute("pedido");
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtosCarrinho");
		
		// Instancia os objetos das respectivas classes
		CarrinhoService serviceCarrinho = new CarrinhoService();
		CompraService serviceCompra = new CompraService();
		ProdutoService serviceProduto = new ProdutoService();
		
		// inclui o pedido no banco
		serviceCompra.incluir(pedido);
		
		// para cada produto ele retira a quantidade comprada do estoque
		for(Produto produto : produtos) {
			produto.setQtdeEstoque(produto.getQtdeEstoque() - produto.getQuantidade());
			serviceProduto.atualizar(produto);
			for (int i = 0; i < produto.getQuantidade(); i++) { 
				// para produto é feito registros na tabela carrinho
				// por exemplo: o cliente 5 comprou dois produtos 2, e o número do pedido é 50
				// então serão feitos dois registros assim:
				// | idCliente | idProduto | numPedido |
				// |         5 |         2 |        50 |
				// |         5 |         2 |        50 |
				Carrinho carrinho = new Carrinho(cliente.getId(), produto.getId(), pedido.getNumPedido());
				serviceCarrinho.incluir(carrinho);
			}
		}
		
		// é atribuido null ao atributo pedido da sessão, para que o filtro não deixe ele acessar a página de pagamento novamente
		session.setAttribute("pedido", null); 
		
		RequestDispatcher view = request.getRequestDispatcher("Sucesso.jsp");
		view.forward(request, response);
	}
	
}
