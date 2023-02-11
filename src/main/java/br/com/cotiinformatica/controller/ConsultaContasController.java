package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.ConsultaContasModel;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ConsultaContasController {

	@Autowired // Inicialização automática (injeção de dependência)
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/consulta-contas")
	public ModelAndView consultaContas(HttpServletRequest request) {

		// WEB-INF/views/admin/consulta-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		ConsultaContasModel model = new ConsultaContasModel();
		
		try {
			
			model.setDataIni(DateHelper.getFirstDayOfMonth());
			model.setDataFim(DateHelper.getLastDayOfMonth());
			
			List<Conta> contas = obterConsultaDeContas(model, request);
			modelAndView.addObject("contas", contas);
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao consultar contas: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/consultar-contas", method = RequestMethod.POST)
	public ModelAndView consultarContas(ConsultaContasModel model, HttpServletRequest request) {
		
		// WEB-INF/views/admin/consulta-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		try {
			
			List<Conta> contas = obterConsultaDeContas(model, request);
			
			// Enviando a lista de contas para a página exibir
			modelAndView.addObject("contas", contas);
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao consultar contas: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);
		return modelAndView;
	}
	
	private List<Conta> obterConsultaDeContas(ConsultaContasModel model, HttpServletRequest request) throws Exception {
		
		// Capturando os dados do usuário gravado em sessão
		UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
		
		// Capturar as datas selecionadas no formulário
		Date dataIni = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDataIni());
		Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDataFim());
		
		// Consultar as contas do usuário no banco de dados
		return contaRepository.findByUsuarioAndData(usuarioDTO.getIdUsuario(), dataIni, dataFim);
		
	}
}
