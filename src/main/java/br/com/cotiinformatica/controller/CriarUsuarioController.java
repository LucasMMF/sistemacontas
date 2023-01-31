package br.com.cotiinformatica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.models.CriarUsuarioModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class CriarUsuarioController {

	@Autowired // Injeção de dependência (inicialização automática)
	private UsuarioRepository usuarioRepository;

	/*
	 * Método para abrir a página
	 */
	@RequestMapping(value = "/criar-usuario") // ROTA (navegação)
	public ModelAndView criarUsuario() {

		// WEB-INF/views/criar-usuario.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		modelAndView.addObject("model", new CriarUsuarioModel());

		return modelAndView;
	}

	/*
	 * Método para receber o SUBMIT POST do formulário
	 */
	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(CriarUsuarioModel model) {

		ModelAndView modelAndView = new ModelAndView("criar-usuario");

		try {

			// Verificar se já existe um usuário cadastrado no banco de dados
			// com o email informado na página.
			if (usuarioRepository.findByEmail(model.getEmail()) != null) {
				modelAndView.addObject("erro_email", "O email informado já está cadastrado, tente outro.");
			} else {
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(model.getNome());
				usuario.setEmail(model.getEmail());
				usuario.setSenha(model.getSenha());
				
				// Gravando o usuário no banco de dados
				usuarioRepository.create(usuario);
				
				modelAndView.addObject("mensagem_sucesso", "Parabéns! Sua conta de usuário foi criada com sucesso.");
				model = new CriarUsuarioModel();
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		modelAndView.addObject("model", model);
		return modelAndView;
	}

}
