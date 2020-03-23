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
import org.junit.validator.PublicClassValidator;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueExceptions;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoVaolorLocacaoTest {
	
	private LocacaoService service;
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value= 1)
	public Double ValorLocacao;
	
	@Parameter(value= 2)
	public String cenario;
	
	@Before
	public void setup() {
		service = new LocacaoService();
		LocacaoDAO dao = new LocacaoDAOFake();
		service.setLocacaoDAO(dao);
	}
	
	
	
	private static Filme filme1 = umFilme().agora();
	private static Filme filme2 = umFilme().agora();
	private static Filme filme3 = umFilme().agora();
	private static Filme filme4 = umFilme().agora();
	private static Filme filme5 = umFilme().agora();
	private static Filme filme6 = umFilme().agora();
	private static Filme filme7 = umFilme().agora();
	
	@Parameters(name = "{2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][]{
			{ Arrays.asList(filme1, filme2),8.0, "2 Filmes Sem desconto"},
			{ Arrays.asList(filme1, filme2,filme3),11.0, "3 Filmes 25%"},
			{ Arrays.asList(filme1, filme2,filme3,filme4),13.0, "4 Filmes 50%"},
			{ Arrays.asList(filme1, filme2,filme3,filme4,filme5),14.0, "5 Filmes 75%"},	
			{ Arrays.asList(filme1, filme2,filme3,filme4,filme5,filme6),14.0, "6 Filmes 100%"},	
			{ Arrays.asList(filme1, filme2,filme3,filme4,filme5,filme6,filme7),18.0, "7 Filmes Sem desconto"}
			
		});
	}

	
	
	
	
	@Test 
	public void deveCalcularLocacaoConsiderandoDesconto() throws FilmeSemEstoqueExceptions, LocadoraException {
		//cen�rio
		Usuario usuario = usiBuilder().agora();;
				
		//A��o
		Locacao resultado = service.alugarFilme(usuario,filmes);
		
		//Verifica��o
		Assert.assertThat(resultado.getValor(), is(ValorLocacao));
		System.out.println("!");
	}
	
	@Test
	public void print () {
		System.out.println(ValorLocacao);
	}

	
	
	
}






