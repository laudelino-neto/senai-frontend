package br.com.senai.senaifrontend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transportadora {
	
	@EqualsAndHashCode.Include
	private Integer id;
	
	private String nomeFantasia;
	
	private String razaoSocial;
	
	private String cnpj;

}
