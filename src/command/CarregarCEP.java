package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarregarCEP implements Command{
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cepString = request.getParameter("cep");
				
		// api WebServiceCep
		WebServiceCep cep = WebServiceCep.searchCep(cepString);
		
		if(cep.wasSuccessful()) { // verifica se achow o cep
			// carrega os dados cep
			request.setAttribute("rua", cep.getLogradouroFull());
			request.setAttribute("bairro", cep.getBairro());
			request.setAttribute("cidade", cep.getCidade());
			request.setAttribute("estado", cep.getUf());
			request.setAttribute("cep", cepString);
			
			int valorFrete;
			
			// calcula o valor do frete
			if(cep.getCidade().equals("São Paulo")) {
				valorFrete = 0;
			} else if (cep.getUf().equals("SP")) {
				valorFrete = 20;
			} else if (cep.getUf().equals("RJ") || cep.getUf().equals("MG") || cep.getUf().equals("ES")) {
				valorFrete = 60;
			} else {
				valorFrete = 80;
			}
			
			request.setAttribute("valorFrete", valorFrete);
		} else { // se não, retorna o erro de CEP inválido
			request.setAttribute("erro", "CEP inváildo!");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("Carrinho.jsp");
		view.forward(request, response);
		
	}
	
}
