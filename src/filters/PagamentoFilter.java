package filters;

import java.io.IOException;

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
import model.Compra;

/**
 * Servlet Filter implementation class PagamentoFilter
 */
@WebFilter("/Pagamento.jsp")
public class PagamentoFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PagamentoFilter() {
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
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		Compra pedido = (Compra) session.getAttribute("pedido");
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		
		// verifica se um cliente iniciou um pedido para acessar a página de pagamento
		if (pedido == null || (cliente == null || cliente.getNome().equals("guest"))) {
			((HttpServletResponse)response).sendRedirect("Index.jsp");
		} else {
			chain.doFilter(request, response);			
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
