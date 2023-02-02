package br.com.cotiinformatica.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.models.AutenticarModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class AutenticarController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/") // Raiz do projeto
	public ModelAndView autenticar() {
		// WEB-INF/views/autenticar.jsp
		ModelAndView modelAndView = new ModelAndView("autenticar");
		modelAndView.addObject("model", new AutenticarModel());

		return modelAndView;
	}

	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(AutenticarModel model, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("autenticar");

		try {
			
			// Consultar o usuário no banco de dados através do email e da senha
			Usuario usuario = usuarioRepository.findByEmailAndSenha(model.getEmail(), model.getSenha());
			
			// Verificar se o usuário foi encontrado
			if (usuario != null) {
				
				// Criando um objeto da classe DTO (DATA TRANSFER OBJECT)
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				
				usuarioDTO.setIdUsuario(usuario.getIdUsuario());
				usuarioDTO.setNome(usuario.getNome());
				usuarioDTO.setEmail(usuario.getEmail());
				usuarioDTO.setDataHoraAcesso(new Date());
				
				// Gravar os dados do usuário em sessão
				request.getSession().setAttribute("usuario", usuarioDTO);
				
				// Redirecionar para a página de boas vindas do sistema
				modelAndView.setViewName("redirect:/admin/dashboard");
			} else {
				modelAndView.addObject("mensagem_erro", "Acesso negado. Usuário não encontrado");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		modelAndView.addObject("model", model);
		return modelAndView;
	}

}
