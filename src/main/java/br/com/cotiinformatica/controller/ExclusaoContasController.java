package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioDTO;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.models.ConsultaContasModel;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ExclusaoContasController {

	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value="/admin/exclusao-contas")
	public ModelAndView exclusaoContas(Integer id, HttpServletRequest request) {
		
		// WEB-INF/views/admin/consulta-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		try {
			
			// Capturando o usuário autenticado na sessão
			UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
			
			// Buscando o registro da conta no banco de dados
			Conta conta = contaRepository.findById(id, usuarioDTO.getIdUsuario());
			
			// Verificando se a conta foi encontrada
			if (conta != null) {
				
				contaRepository.delete(conta);
				
				modelAndView.addObject("mensagem", "Conta '" + conta.getNome() + "' excluída com sucesso.");
				
			} else {
				modelAndView.addObject("mensagem", "Conta não encontrada.");
			}
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao excluir a conta: " + e.getMessage());
		}
		
		modelAndView.addObject("model", new ConsultaContasModel());
		return modelAndView;
	}
}
