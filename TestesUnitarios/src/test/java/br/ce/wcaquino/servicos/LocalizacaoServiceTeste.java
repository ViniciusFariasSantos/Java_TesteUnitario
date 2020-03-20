package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import  org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueExceptions;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;

public class LocalizacaoServiceTeste {

	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	@Test
	public void devaAlugarFilme() throws Exception {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SUNDAY));
		
		// cenario
		Usuario usuario = new Usuario("Leandro");
		List<Filme> filmes = Arrays.asList( new Filme("Titanic", 10, 5.0));

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	@Test(expected = FilmeSemEstoqueExceptions.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Leandro");
		List<Filme> filmes = Arrays.asList( new Filme("Titanic", 0, 4.0));

		// acao
		 service.alugarFilme(usuario, filmes);

	}

	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueExceptions {
		// cenario
		List<Filme> filmes = Arrays.asList( new Filme("Titanic", 10, 5.0));

		// Acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	@Test
	public void naodeveAlugarFilmeSemFilme() throws FilmeSemEstoqueExceptions, LocadoraException {
		// Cenário
		Usuario usuario = new Usuario("Leandro");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		// Ação
		service.alugarFilme(usuario, null);

	}
	
	@Test 
	public void deveDevolverFilmeNoDomingo () throws FilmeSemEstoqueExceptions, LocadoraException {
	
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SUNDAY));	
		//cenário
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes  = Arrays.asList(new Filme("Filme 1",2,5.0));
		
		//Ação
		Locacao retorno =service.alugarFilme(usuario,filmes);
		
		//Verificação
		boolean ehSegunda= DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
		Assert.assertThat(retorno.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
//		Assert.assertThat(retorno.getDataRetorno(), caiNaSegunda());
		
		
	}
}





