package br.ce.wcaquino.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTeste;
import br.ce.wcaquino.servicos.CalculoVaolorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoService;

@RunWith(Suite.class)
@SuiteClasses({ CalculadoraTeste.class, CalculoVaolorLocacaoTest.class, LocacaoService.class })
public class SuiteExecucao {
	@BeforeClass
	public static void before() {
		System.out.println("before");

	}
	@AfterClass
	public static void after() {
		
		System.out.println("After");
	}

}
