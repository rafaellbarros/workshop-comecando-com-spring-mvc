package com.algaworks.cobranca.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
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
	private static final String FILTRO_SEM_DESCRICAO = "";
	
	@Mock
	private TituloRepository tituloRepository;

	@InjectMocks
	private TituloService tituloService = new TituloServiceImpl();
	
	private Titulo titulo1;
	
	private Titulo titulo2;
	
	private Titulo titulo3;
	
	@Before
	public void setUp() throws ParseException {
		titulo1 = Titulo.builder().descricao("Apartamento 101")
				.valor(new BigDecimal(1000))
				.dataVencimento(getDataVencimento("30/06/2019"))
				.status(StatusTitulo.PENDENTE)
				.build();
		
		titulo2 = Titulo.builder().descricao("Apartamento 101")
				.valor(new BigDecimal(5000))
				.dataVencimento(getDataVencimento("15/06/2019"))
				.status(StatusTitulo.RECEBIDO)
				.build();
		
		titulo3 = Titulo.builder().descricao("Apartamento 101")
				.valor(new BigDecimal(100000))
				.dataVencimento(getDataVencimento("16/06/2019"))
				.status(StatusTitulo.PENDENTE)
				.build();
	}
	
	@Test
	public void salvarTitulo() {	
		tituloService.salvar(titulo1);
		verify(tituloRepository, times(1)).save(titulo1);
	}
	
	@Test
	public void quandoPesquisarTituloPelaDescricaoDeveRetornarUmTitulo()  {
		
		when(tituloRepository.findByDescricaoContaining(FILTRO_DESCRICAO))
			.thenReturn(Arrays.asList(titulo1));
		
		List<Titulo> titulos = getServiceTitulos(FILTRO_DESCRICAO);
		
		assertEquals(1, titulos.size());
		assertThat(titulos.get(0).getDescricao()).isEqualTo(titulo1.getDescricao());
		verify(tituloRepository, times(1)).findByDescricaoContaining(FILTRO_DESCRICAO);
		
	}
	
	@Test
	public void quandoPesquisarTituloPelaDescricaoDeveRetornarTitulos() {
		
		when(tituloRepository.findByDescricaoContaining(FILTRO_DESCRICAO))
			.thenReturn(getTitulos());
		
		List<Titulo> titulos = getServiceTitulos(FILTRO_DESCRICAO);
		
		assertEquals(3, titulos.size());
		verify(tituloRepository, times(1)).findByDescricaoContaining(FILTRO_DESCRICAO);
		
	}
	
	@Test
	public void quandoPesquisarTituloSemDescricaoNaoDeveRetornarTitulos()   {
		
		when(tituloRepository.findByDescricaoContaining(FILTRO_SEM_DESCRICAO))
			.thenReturn(Collections.emptyList());
		
		List<Titulo> titulos = getServiceTitulos(FILTRO_SEM_DESCRICAO);
		
		assertEquals(0, titulos.size());
		verify(tituloRepository, times(1)).findByDescricaoContaining(FILTRO_SEM_DESCRICAO);
		
	}
	
	@Test
	public void quandoPesquisarTituloSemDescricaoDeveRetornarTitulos()   {
		
		when(tituloRepository.findByDescricaoContaining(FILTRO_SEM_DESCRICAO))
			.thenReturn(getTitulos());
		
		List<Titulo> titulos = getServiceTitulos(FILTRO_SEM_DESCRICAO);
		
		assertEquals(3, titulos.size());
		verify(tituloRepository, times(1)).findByDescricaoContaining(FILTRO_SEM_DESCRICAO);
		
	}
	
	private List<Titulo> getServiceTitulos(String filtro) {
		return tituloService.filtrar(new TituloFilter(filtro));
	}
	
	private List<Titulo>  getTitulos() {
		return Arrays.asList(titulo1, titulo2, titulo3);
	}
	
	private Date getDataVencimento(String strDataVencimento) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.parse(strDataVencimento);
	}
		
}
