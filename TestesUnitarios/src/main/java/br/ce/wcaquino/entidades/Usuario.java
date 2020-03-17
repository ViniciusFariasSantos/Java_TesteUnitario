package br.ce.wcaquino.entidades;

public class Usuario {
	
	//Variavel
	private String nome;
	
	//Método
	public Usuario() {}
	
	
	//Construtor 
	public Usuario(String nome) {
		this.nome = nome;
	}

	//Getter e Setter 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}