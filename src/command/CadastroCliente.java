package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import service.ClienteService;

public class CadastroCliente  implements Command {

		@Override
		public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ClienteService cs = new ClienteService();
			
			// pega os dados que o cliente inseriu
			String nome = request.getParameter("cdnome");
			String sobrenome = request.getParameter("cdsobrenome");
			String cpf = request.getParameter("cdcpf");
			String rg = request.getParameter("cdrg");
			String email = request.getParameter("cdemail");
			String senha = request.getParameter("cdsenha");
			String cSenha = request.getParameter("cfsenha");
			
			// se ele tiver vindo da pagina do carrinho, vai ser carregada a pagina de pagamento, se não, ele carrega o index
			String pagina = request.getParameter("pagina");
			if (pagina.length() == 0) {
				pagina = "Index.jsp";
			}
			
			HttpSession session = request.getSession();
			
			// verifica senhas iguais
			if (senha.equals(cSenha)) { 
				// verifica se os dados já existem
				if (cs.verificaDisponibilidadeEmail(email)) { 
					if (cs.verificaDisponibilidadeCpf(cpf)) {
						if (cs.verificaDisponibilidadeRg(rg)) { 
							// inclui o cliente no banco e inicia a sessão dele
							Cliente cliente = new Cliente(0, nome + " " + sobrenome, cpf, rg, email, senha);
							int id = cs.incluir(cliente);
							cliente = cs.carregar(id);
							session.setAttribute("cliente", cliente);
							response.sendRedirect(pagina);
						} else {
							mostraErro("RG já cadastrado!", request, response);
						}
					} else {
						mostraErro("CPF já cadastrado!", request, response);
					}
				} else {
					mostraErro("E-mail já cadastrado!", request, response);
				}
			} else {
				mostraErro("Senhas não coíncidem!", request, response);
			}
			
			
		}
		
		private static void mostraErro(String erro, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			session.setAttribute("pagina", request.getParameter("pagina"));
 			
			request.setAttribute("erroCadastro", erro);
			RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
			view.forward(request, response);
		}
}
