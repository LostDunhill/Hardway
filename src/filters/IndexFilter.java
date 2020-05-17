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
import javax.servlet.http.HttpSession;

import model.Produto;
import service.ProdutoService;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter("/Index.jsp")
public class IndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IndexFilter() {
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
		
		// carrega os produtos do banco
		ProdutoService ps = new ProdutoService();
		ArrayList<Produto> produtosBanco = ps.carregarProdutos();
		ArrayList<Produto> produtos = new ArrayList<>();
		
		// passa pro ArrayList "produtos" somente os produtos que tem no estoque
		for (int i = produtosBanco.size() - 1; i >= 0; i--) {
			Produto produto = produtosBanco.get(i);
			if (produto.getQtdeEstoque() > 0) {
				produtos.add(produto);
			}
		}
		
		session.setAttribute("produtos", produtos);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
