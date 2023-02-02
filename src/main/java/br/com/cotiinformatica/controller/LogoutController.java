package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		// Apagando os dados do usuário gravado em sessão
		request.getSession().removeAttribute("usuario");
		
		// Redirecionar para a página de autenticação
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
	
}
