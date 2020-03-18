package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void test() {
		
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparacao",1, 1);
		Assert.assertEquals(0.512123, 0.51234, 0.001);
		
		int i = 5;
		Integer i2 = 5;
		
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bala", "bala");
		Assert.assertNotEquals("bala", "casa");
		Assert.assertTrue("bala".equalsIgnoreCase("Bala"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2= new Usuario("Usuario 1");
		Usuario u3= null;
				
		Assert.assertEquals(u1, u2);
		
		Assert.assertSame(u2, u2);
		
		Assert.assertNotSame(u1, u2);
		
		Assert.assertNull(u3);
		Assert.assertNotNull(u2);
		
		
	}

}






