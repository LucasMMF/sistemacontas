package br.com.cotiinformatica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.EmailDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.messages.EmailMessage;
import br.com.cotiinformatica.models.RecuperarSenhaModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RecuperarSenhaController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/recuperar-senha") // ROTA (navegação)
	public ModelAndView recuperarSenha() {
		// WEB-INF/views/recuperar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		modelAndView.addObject("model", new RecuperarSenhaModel());

		return modelAndView;
	}

	@RequestMapping(value = "/recuperacao-de-senha", method = RequestMethod.POST)
	public ModelAndView recuperacaoDeSenha(RecuperarSenhaModel model) {

		// WEB-INF/views/recuperar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");

		try {

			// Consultando o usuário no banco de dados através do email
			Usuario usuario = usuarioRepository.findByEmail(model.getEmail());

			// Verificando se o usuário foi encontrado
			if (usuario != null) {

				// Gerando uma nova senha para o usuário
				String novaSenha = new Faker().internet().password(8, 10);

				// Escrevendo a mensagem
				EmailDTO emailDTO = new EmailDTO();
				emailDTO.setMailTo(usuario.getEmail());
				emailDTO.setSubject("Recuperação de senha de acesso - Sistema Contas");
				emailDTO.setBody("Olá, " + usuario.getNome() 
						+ "\n\nUma nova senha de acesso foi gerada com sucesso!"
						+ "\nUtilize a senha: " + novaSenha
						+ "\n\nApós autenticar-se, você poderá alterar esta senha através do menu 'Dados do usuário'."
						+ "\n\nAtt\nEquipe Sistema Contas");

				// Enviando a mensagem por email
				EmailMessage.sendMessage(emailDTO);

				// Atualizando a senha no banco de dados
				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);

				modelAndView.addObject("mensagem_sucesso", "Recuperação de senha realizada com sucesso.");
				model = new RecuperarSenhaModel();

			} else {
				modelAndView.addObject("mensagem_erro", "Usuário não encontrado, verifique o email informado.");
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Erro: " + e.getMessage());
		}

		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
