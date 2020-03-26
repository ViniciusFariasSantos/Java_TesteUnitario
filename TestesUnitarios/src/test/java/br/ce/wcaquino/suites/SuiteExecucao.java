package br.ce.wcaquino.suites;



import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTeste;
import br.ce.wcaquino.servicos.CalculoVaolorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoService;


//@RunWith(Suite.class)
@SuiteClasses({ LocacaoService.class,CalculadoraTeste.class, CalculoVaolorLocacaoTest.class })
public class SuiteExecucao {
	

}
