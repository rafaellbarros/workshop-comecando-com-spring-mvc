package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.algaworks.cobranca.model.enums.StatusTitulo;

import lombok.Data;

@Entity
@Data
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
}
