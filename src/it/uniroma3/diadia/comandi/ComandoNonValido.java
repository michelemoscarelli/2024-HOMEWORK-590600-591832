package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	private IO io;

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando sconosciuto");
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "ComandoNonValido";
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getParametro() {
		return null;	
	}
	
	@Override
	public void setIO(IO io) {
		this.io= io;
		
	}

}
