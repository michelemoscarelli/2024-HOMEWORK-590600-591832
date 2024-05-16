package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "borsa", "prendi", "posa", "guarda", "aiuto", "fine"};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		StringBuilder aiuto = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) 
			aiuto.append(elencoComandi[i]+" ");
		this.io.mostraMessaggio(aiuto.toString());
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "aiuto";
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
