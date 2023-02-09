package br.com.cotiinformatica.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EdicaoContasModel {

	private Integer idConta;
	private String nome;
	private String valor;
	private String data;
	private String tipo;
	private String observacoes;
}
