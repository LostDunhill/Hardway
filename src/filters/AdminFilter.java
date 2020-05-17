package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

import model.Compra;
import model.Produto;
import service.CompraService;
import service.ProdutoService;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({
	"/controller.do",
	"/Admin.jsp",
	"/CabecalhoAdmin.jsp",
	"/CadastrarCategoria.jsp",
	"/CadastrarFornecedor.jsp",
	"/CadastrarSubCategoria.jsp",
	"/CadastrarProduto",
	"/Categorias.jsp",
	"/SubCategoria.jsp",
	"/Fornecedores.jsp",
	"/VisualizarPedido.jsp"
})
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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
		
		String usuario = (String) session.getAttribute("usuario");
		String command = req.getParameter("command");
		String uri = req.getRequestURI();
		
		if (usuario == null && (checaUri(uri) || checaCommand(command))) {
			// verifica se o usuário é nulo e se ele está tentando acessar uma página
			// administrativa, ou realizar um comando administrativo
			res.sendRedirect("Login.jsp");
		} else {
			// se não ele simplesmente segue e carrega os ArrayLists necessários para o admin
			CompraService service = new CompraService();
			ProdutoService ps = new ProdutoService();
			
			ArrayList<Compra> pedidos = service.listar();
			ArrayList<Produto> produtos = ps.carregarProdutos();
			
			Collections.reverse(pedidos);
			
			session.setAttribute("produtos", produtos);
			session.setAttribute("pedidos", pedidos);
			chain.doFilter(request, response);			
		}
			
		
	}
	
	public static boolean checaCommand(String command) {
		// faz a verificação dos commandos administrativos
		if(command == null) return false;
		else if(command.equals("VisualizarPedido")) return true;
		else if(command.equals("VisualizarFornecedores")) return true;
		else if(command.equals("VisualizarCategorias")) return true;
		else if(command.equals("VisualizarSubCategorias")) return true;
		else if(command.startsWith("CarregarFormulario")) return true;
		else if(command.startsWith("Criar")) return true;
		else if(command.startsWith("Excluir")) return true;
		else if(command.startsWith("Editar")) return true;
		else if(command.equals("CadastroFornecedor")) return true;
		else if(command.equals("AlterarStatusPedido")) return true;
		return false;
	}
	
	public static boolean checaUri(String uri) {
		// faz a verificação das páginas de admin
		String [] paginasAdmin = { 
			"Admin.jsp",
			"CabecalhoAdmin.jsp",
			"CadastrarCategoria.jsp",
			"CadastrarFornecedor.jsp",
			"CadastrarSubCategoria.jsp",
			"CadastrarProduto",
			"Categorias.jsp",
			"SubCategoria.jsp",
			"Fornecedores.jsp",
			"VisualizarPedido.jsp"
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
