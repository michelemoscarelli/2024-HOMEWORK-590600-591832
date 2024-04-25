package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "borsa", "prendi", "posa", "guarda", "aiuto", "fine"};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");
		this.io.mostraMessaggio("");
		
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
