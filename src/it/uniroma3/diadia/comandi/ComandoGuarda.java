package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	private IOConsole ioConsole;

	@Override
	public void esegui(Partita partita) {
		this.ioConsole.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().toString());
		this.ioConsole.mostraMessaggio("ed hai: " + partita.getGiocatore().getCfu());
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getParametro() {
		// TODO Auto-generated method stub
		
	}

}
