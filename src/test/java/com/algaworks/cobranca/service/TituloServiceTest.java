package com.algaworks.cobranca.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.model.enums.StatusTitulo;
import com.algaworks.cobranca.repository.TituloRepository;
import com.algaworks.cobranca.repository.filter.TituloFilter;
import com.algaworks.cobranca.service.impl.TituloServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TituloServiceTest {

	private static final String FILTRO_DESCRICAO = "101";

	@Mock
	private TituloRepository tituloRepository;

	@InjectMocks
	private TituloServiceImpl tituloService;
	
	@Test
	public void quandoPesquisarTituloPelaDescricaoDeveRetornarUmTitulo() throws ParseException {

		Titulo titulo = new Titulo();
		titulo.setDescricao("Apartamento 101");
		titulo.setValor(new BigDecimal(1000));
		titulo.setDataVencimento(getDataVencimento("30/06/2019"));
		titulo.setStatus(StatusTitulo.PENDENTE);
		
		when(tituloRepository.findByDescricaoContaining(FILTRO_DESCRICAO)).thenReturn(Arrays.asList(titulo));
		

		TituloFilter filtro = new TituloFilter(FILTRO_DESCRICAO);
		List<Titulo> titulos = tituloService.filtrar(filtro);
		
		assertEquals(1, titulos.size());
		
	}
	
	private Date getDataVencimento(String strDataVencimento) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.parse(strDataVencimento);
	}
		
}
