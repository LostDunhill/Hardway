package filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Produto;
import service.CarrinhoService;
import service.ProdutoService;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({
	"/controller.do",
	"/MeusPedidos.jsp",
	"/VisualizarPedidoCliente.jsp"
})
public class ClienteFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ClienteFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		int numPedido = -1;
		
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String command = req.getParameter("command");
		String numPedidoString = req.getParameter("NumPedido");
		if (numPedidoString != null) {
			numPedido = Integer.parseInt(numPedidoString);
		}
		String uri = req.getRequestURI();
		
		// verifica se o usuário deslogado está tentando realizar algum comando de cliente ou acessar alguma página diretamente
		if ((cliente == null || cliente.getNome().equals("guest"))  && (checaUri(uri) || checaCommand(command))) { 
			res.sendRedirect("Index.jsp");
		} 
		// aqui ele verifica se o cliente está tentando acessar alguma das páginas filtradas através da uri, ao invés dos botões
		else if (cliente != null && !cliente.getNome().equals("guest") && checaUri(uri)) {
			res.sendRedirect("Index.jsp");
		} 
		// por fim ele verifica se o cliente está tentando realizar algum comando no pedido de outro cliente
		else if (!(cliente == null) && 
				 !(cliente.getNome().equals("guest")) && 
				 !(checaPedido(cliente, numPedido)) && 
				 (command.equals("VisualizarPedidoCliente") || command.equals("CancelarPedidoCliente"))) {
			res.sendRedirect("Index.jsp");
		} 
		// se não ele simplesmente segue em frente
		else {
			chain.doFilter(request, response);			
		}
			
		
	}
	
	private boolean checaPedido(Cliente cliente, int numPedido) {
		if (cliente == null) return false;
		CarrinhoService service = new CarrinhoService();
		Cliente clientePedido = service.carregarClienteDoPedido(numPedido);
		if (cliente.getId() == clientePedido.getId()) return true;
		else return false;
	}

	private static boolean checaCommand(String command) {
		if(command == null) return false;
		else if(command.equals("VisualizarPedidoCliente")) return true;
		else if(command.equals("VisualizarPedidos")) return true;
		return false;
	}
	
	private static boolean checaUri(String uri) {
		String [] paginasAdmin = { 
			"VisualizarPedidos.jsp",
			"MeusPedidos.jsp"
		};
		
		for (String pagina : paginasAdmin) {
			if (uri.endsWith(pagina)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
