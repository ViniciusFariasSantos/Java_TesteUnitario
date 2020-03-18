package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;


import java.util.Date;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocalizacaoServiceTeste {
		
		@Rule
		public ErrorCollector  erro = new ErrorCollector();
		
	@Test
	public void testeLocacao() throws Exception {
		//Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario1");
		Filme filme =  new Filme("Filme 1", 0, 5.0);
		
		//Ação 
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//Verificação
		erro.checkThat(locacao.getValor(),  is(5.0));
		erro.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()),is(true));
		erro.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		
	}
	
}
