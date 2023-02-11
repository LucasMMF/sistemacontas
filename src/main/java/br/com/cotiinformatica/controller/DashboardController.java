package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.ConsultaContasModel;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class DashboardController {

	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		
		// WEB-INF/views/admin/dashboard.jsp
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		try {
			
			ConsultaContasModel model = new ConsultaContasModel();
			model.setDataIni(DateHelper.getFirstDayOfMonth());
			model.setDataFim(DateHelper.getLastDayOfMonth());
			
			modelAndView = consultaSomatorioContas(modelAndView, model, request);
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao carregar dashboard: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/filtrar-dashboard", method = RequestMethod.POST)
	public ModelAndView filtrarDashboard(ConsultaContasModel model, HttpServletRequest request) {
		
		// WEB-INF/views/admin/dashboard.jsp
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		try {
			
			modelAndView = consultaSomatorioContas(modelAndView, model, request);
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao carregar dashboard: " + e.getMessage());
		}
		return modelAndView;
	}
	
	private ModelAndView consultaSomatorioContas(ModelAndView modelAndView, ConsultaContasModel model, HttpServletRequest request) throws Exception {
		
		UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
		Date dataIni = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDataIni());
		Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDataFim());
		
		// Enviar as informações para a página
		modelAndView.addObject("model", model);
		modelAndView.addObject("total_contas_receber", contaRepository.sumByUsuarioAndData(usuarioDTO.getIdUsuario(), dataIni, dataFim, 1));
		modelAndView.addObject("total_contas_pagar", contaRepository.sumByUsuarioAndData(usuarioDTO.getIdUsuario(), dataIni, dataFim, 2));
		
		return modelAndView;
	}
}
