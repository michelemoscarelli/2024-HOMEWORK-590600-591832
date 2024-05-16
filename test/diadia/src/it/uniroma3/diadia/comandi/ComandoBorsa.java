package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoBorsa implements Comando{
	private IO io;

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "borsa";
		
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
