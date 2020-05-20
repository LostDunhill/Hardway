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
import model.Produto;
import service.CarrinhoService;
import service.CompraService;

public class Comprar implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher view;
		
		String data = java.time.LocalDate.now().toString(); // pega a data e hoje em formato de String
		
		//pega os dados do endereço
		String rua = request.getParameter("rua");
		int numero = Integer.parseInt(request.getParameter("numero"));
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String complemento = request.getParameter("complemento");
		String cep = tratarCep(request.getParameter("cep"));
		double total = Double.parseDouble(request.getParameter("total")); // pega o valor total compra
		
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtosCarrinho");
		if (produtos == null) {
			produtos = new ArrayList<>();
		}
		ArrayList<Produto> produtosEmFalta = verificaDisponibilidadeProdutos(produtos); // verifica se o estoque contém a quantidade dos produtos escolhida pelo cliente
		
		if (produtos.size() == 0) { // se não tiver nenhum produto no carrinho ele retorna erro
			request.setAttribute("erroCompra", "Não há nenhum produto no carrinho");
			view = request.getRequestDispatcher("Carrinho.jsp");
		} else if (produtosEmFalta.size() != 0) { // se o array de produtos em falta for maior do que 0, ele mostra os produtos em falta
			String erroCompra = "Não há estoque suficiente para os seguintes produtos:\n";
			request.setAttribute("disponibilidadeProdutos", produtosEmFalta);
			request.setAttribute("erroCompra", erroCompra);
			view = request.getRequestDispatcher("Carrinho.jsp");
		} else { // se não ele segue com a compra normalmente
			Compra pedido = new Compra(0, data, rua, numero, bairro, cidade, estado, complemento, cep, total);
			session.setAttribute("pedido", pedido);
			
			if (cliente == null || cliente.getNome().equals("guest")) { // se o cliente estiver deslogado ele vai para a pagina de login
				request.setAttribute("pagina", "Pagamento.jsp");
				view = request.getRequestDispatcher("Login.jsp");
			} else { // se não ele vai para a pagina de pagamento direto
				view = request.getRequestDispatcher("Pagamento.jsp");
			}
		}
		view.forward(request, response);
	}

	private ArrayList<Produto> verificaDisponibilidadeProdutos(ArrayList<Produto> produtos) {
		ArrayList<Produto> produtosEmFalta = new ArrayList<>();
		for (Produto produto: produtos) {
			if (produto.getQuantidade() > produto.getQtdeEstoque()) {
				produtosEmFalta.add(produto);
			}
		}
		return produtosEmFalta;
	}

	private String tratarCep(String cep) {
		return cep.replaceAll("-", "");
	}
	
}
