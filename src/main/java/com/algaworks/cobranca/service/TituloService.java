package com.algaworks.cobranca.service;

import java.util.List;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.filter.TituloFilter;

public interface TituloService {
	
	public void salvar(Titulo titulo);
	public void excluir(Long codigo);
	public String receber(Long codigo);
	public List<Titulo> filtrar(TituloFilter filtro);
	
}
