package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.models.AlterarSenhaModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class DadosUsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/admin/dados-usuario")
	public ModelAndView dadosUsuario() {
		
		// WEB-INF/views/admin/dados-usuario.jsp
		ModelAndView modelAndView = new ModelAndView("admin/dados-usuario");
		modelAndView.addObject("model", new AlterarSenhaModel());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/atualizar-senha", method = RequestMethod.POST)
	public ModelAndView atualizarSenha(AlterarSenhaModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/dados-usuario");
		
		try {
			
			// Capturar o usuário autenticado em sessão
			UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
			
			// Atualizar a senha do usuário
			usuarioRepository.update(usuarioDTO.getIdUsuario(), model.getNovaSenha());
			
			modelAndView.addObject("mensagem_sucesso", "Senha de acesso atualizada com sucesso.");
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Erro ao alterar senha:  " + e.getMessage());
		}
		
		model = new AlterarSenhaModel();
		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
