package com.algaworks.cobranca.model.enums;

import lombok.Getter;

public enum StatusTitulo {
	
	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	@Getter
	private String descricao;
	
	StatusTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	

}
