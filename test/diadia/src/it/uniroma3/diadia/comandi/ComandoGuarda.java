package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	private IO io;

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().toString());
		this.io.mostraMessaggio("ed hai: " + partita.getGiocatore().getCfu() + " CFU");
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "Guarda";
		
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
