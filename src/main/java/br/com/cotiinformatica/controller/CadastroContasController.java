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
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoConta;
import br.com.cotiinformatica.models.CadastroContasModel;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class CadastroContasController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/cadastro-contas")
	public ModelAndView cadastroContas() {
		
		// WEB-INF/views/admin/cadastro-contas.jsp
		ModelAndView modelAndView = new ModelAndView("admin/cadastro-contas");
		modelAndView.addObject("model", new CadastroContasModel());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/cadastrar-conta", method = RequestMethod.POST)
	public ModelAndView cadastrarConta(CadastroContasModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/cadastro-contas");
		
		try {
			
			// Capturando o usuário autenticado na sessão
			UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuario");
			
			Conta conta = new Conta();
			conta.setUsuario(new Usuario());
			
			conta.setNome(model.getNome());
			conta.setValor(Double.parseDouble(model.getValor()));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(model.getData()));
			conta.setTipo(model.getTipo().equals("1") ? TipoConta.RECEBER : model.getTipo().equals("2") ? TipoConta.PAGAR : null);
			conta.setObservacoes(model.getObservacoes());
			conta.getUsuario().setIdUsuario(usuarioDTO.getIdUsuario());
			
			contaRepository.create(conta);
			
			model = new CadastroContasModel();
			modelAndView.addObject("mensagem", "Conta cadastrada com sucesso.");
		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao cadastrar conta: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
