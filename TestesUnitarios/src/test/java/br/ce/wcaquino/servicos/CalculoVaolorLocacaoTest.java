package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builders.UsuarioBuilder.usiBuilder;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueExceptions;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoVaolorLocacaoTest {
	@InjectMocks
	private LocacaoService service;
	
	@Mock
	private SPCService spc;
	
	@Mock
	private LocacaoDAO dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Parameter
	public List<Filme> filmes;
	@Parameter(value = 1)
	public Double valorLocacao;
	@Parameter(value = 2)
	public String cenario;

	private static Filme sonic = umFilme().agora();
	private static Filme frozeen2 = umFilme().agora();
	private static Filme clubeDaLuta = umFilme().agora();
	private static Filme senhorDosAneis = umFilme().agora();
	private static Filme harryPoter = umFilme().agora();
	private static Filme asBranquelas= umFilme().agora();
	private static Filme genteGrande = umFilme().agora();
	

	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] { 
				{ Arrays.asList(sonic,   frozeen2), 8.0, "2 Filmes: Sem desconto" },
				{ Arrays.asList(sonic,   frozeen2, clubeDaLuta), 11.0, "3 Filmes: 25%" },
				{ Arrays.asList(sonic,   frozeen2, clubeDaLuta, senhorDosAneis), 13.0, "4 Filmes: 50%" },
				{ Arrays.asList(sonic,   frozeen2, clubeDaLuta, senhorDosAneis, harryPoter), 14.0, "5 Filmes: 75%" },
				{ Arrays.asList(sonic,   frozeen2, clubeDaLuta, senhorDosAneis, harryPoter, asBranquelas), 14.0, "6 Filmes: 100%"},
				{ Arrays.asList(sonic,   frozeen2, clubeDaLuta, senhorDosAneis, harryPoter, asBranquelas, genteGrande), 18.0, "7 Filmes: Sem desconto" } });
	}

	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueExceptions, LocadoraException {
		// cenario
		Usuario usuario = usiBuilder().agora();

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(resultado.getValor(), is(valorLocacao));
		
	}
	
	
	
}






