package br.ce.wcaquino.servicos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import br.ce.wcaquino.runners.ParaleloRunner;


@RunWith(ParaleloRunner.class)
public class CalculadoraTeste {
	
	private static	Calculadora cal = new Calculadora(); 
	
	
	@Before
	public void setup() {
	cal = new Calculadora();
	System.out.println("Iniciando...");

	}
	
	@After
	public void tearDown() {
		System.out.println("Finalizando...");
		
	}
	
	@Test
	public void deveSomarDoisValores() {
	//Cen�rio 
	
		int a = 5;
		int b = 3;
	
		
	
		
	//A��o 
	int resultado = cal.somar(a, b);
	
	
	
		
	//Verifica��o	
	Assert.assertEquals(8, resultado);
	
	
	}
	
	@Test
	public void deveSubtrairDoisValores () {
		//Cen�rio
		
		int a = 8 ;
		int b =  5;
		
		
		
		//A��o
		int resultado = cal.subtrai(a, b);
		
		
		
		
		//Verifica��o 
		
		Assert.assertEquals(3, resultado);
		
		
		
	}
	
	@Test  
	public void deveDividirDoisvalores () throws NaoPodeDividirPorZeroException {
		
		//Cen�rio 
		int a = 6;
		int b  = 3;
	
		
		
		//A��o 
		
		int result = cal.dividir(a, b);
		
		
		//Verifica��o 
		
		Assert.assertEquals(2, result);
		
		
		
	}
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoDividirPorZero() throws NaoPodeDividirPorZeroException {
		//cenario
		int a = 10;
		int b = 0; 
				
		//A��o 
		cal.dividir(a, b);
		
		
		//Verifica��o
		
		
	}
	

	@Test
	public void deveDividir() {
		String a = "6";
		String b = "3";
		
		int result = cal.divide(a,b);
		
		Assert.assertEquals(2, result);
		
	}
	

	
	
	
}





