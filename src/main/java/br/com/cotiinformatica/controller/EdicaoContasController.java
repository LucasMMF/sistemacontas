package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.enums.TipoConta;
import br.com.cotiinformatica.models.EdicaoContasModel;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class EdicaoContasController {

	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/edicao-contas")
	public ModelAndView edicaoContas(Integer id, HttpServletRequest request) {
		
		// WEB-INF/views/admin/edicao-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");
		
		try {
			
			// Capturar o usuário autenticado
			UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
			
			// Consultar a conta no banco de dados
			Conta conta = contaRepository.findById(id, usuarioDTO.getIdUsuario());
			
			// Verificando se a conta foi encontrada
			if (conta != null) {
				
				EdicaoContasModel model = new EdicaoContasModel();
				
				model.setIdConta(conta.getIdConta());
				model.setNome(conta.getNome());
				model.setValor(conta.getValor().toString());
				model.setData(new SimpleDateFormat("yyyy-MM-dd").format(conta.getData()));
				model.setTipo(conta.getTipo() == TipoConta.RECEBER ? "1" : conta.getTipo() == TipoConta.PAGAR ? "2" : null);
				model.setObservacoes(conta.getObservacoes());
				
				
				modelAndView.addObject("model", model);
				
			} else {
				modelAndView.addObject("mensagem", "Conta não encontrada.");
			}
			
		} catch (Exception e ) {
			modelAndView.addObject("mensagem", "Falha ao obter conta: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/atualizar-conta", method = RequestMethod.POST)
	public ModelAndView atualizarConta(EdicaoContasModel model, HttpServletRequest request) {
		
		// WEB-INF/views/admin/edicao-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");
		
		try {
			
			Conta conta = new Conta();
			
			conta.setIdConta(model.getIdConta());
			conta.setNome(model.getNome());
			conta.setValor(Double.parseDouble(model.getValor()));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(model.getData()));
			conta.setTipo(model.getTipo().equals("1") ? TipoConta.RECEBER : model.getTipo().equals("2") ? TipoConta.PAGAR : null);
			conta.setObservacoes(model.getObservacoes());
			
			contaRepository.update(conta);
			
			modelAndView.addObject("mensagem", "Conta atualizada com sucesso.");
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao atualizar conta: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
