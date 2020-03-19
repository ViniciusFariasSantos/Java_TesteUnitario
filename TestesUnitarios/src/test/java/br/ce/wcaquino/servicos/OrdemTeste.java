package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTeste {
	
	public static int contator = 0;
	
	@Test
	public void inicia() {
		contator = 1;
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, contator);
	}
	
	
}
