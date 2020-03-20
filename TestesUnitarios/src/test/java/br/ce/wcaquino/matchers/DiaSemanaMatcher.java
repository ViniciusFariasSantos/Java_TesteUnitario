package br.ce.wcaquino.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

	private Integer diaSemana;
	
	
	public DiaSemanaMatcher (Integer diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	
	
	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	protected boolean matchesSafely(Date data) {
		// TODO Auto-generated method stub
		return DataUtils.verificarDiaSemana(data, diaSemana);
	}

}
