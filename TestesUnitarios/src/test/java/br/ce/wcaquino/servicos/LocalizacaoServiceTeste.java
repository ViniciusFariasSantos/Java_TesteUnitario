package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocalizacaoServiceTeste {

	private LocacaoService service;

	private static int co = 0;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Rule
	public ErrorCollector erro = new ErrorCollector();

	@Before
	public void setup() {
		System.out.println("Before");
		service = new LocacaoService();
		co++;
		System.out.println(co);
	}

	@After
	public void tearDown() {
		System.out.println("After");
	}

	@BeforeClass
	public static void setupClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("After Class");
	}

	@Test
	public void testeLocacao() throws Exception {
		// Cenário
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// Ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		// Verificação
		erro.checkThat(locacao.getValor(), is(5.0));
		erro.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erro.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}

	@Test(expected = Exception.class)
	public void testeLocacao_FilmeSemEstoque() throws Exception {
		// Cenário
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		System.out.println("Teste!");

		// Ação
		service.alugarFilme(usuario, filme);

	}

	@Test
	public void testeLocacao_FilmeSemEstoque_2() {
		// Cenário
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Ação
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lancado uma exececao");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}

	}

	@Test(expected = Exception.class)
	public void testeLocacao_FilmeSemEstoque_3() throws Exception {
		// Cenário
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");

		// Ação
		service.alugarFilme(usuario, filme);

	}

}
