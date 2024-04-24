package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "borsa", "prendi", "posa", "guarda", "aiuto", "fine"};
	private IOConsole ioConsole;
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.ioConsole.mostraMessaggio(elencoComandi[i]+" ");
		this.ioConsole.mostraMessaggio("");
		
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
