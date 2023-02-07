package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConsultaContasController {

	@RequestMapping(value = "/admin/consulta-contas")
	public ModelAndView consultaContas() {
		
		// WEB-INF/views/admin/consulta-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		return modelAndView;
	}
}
