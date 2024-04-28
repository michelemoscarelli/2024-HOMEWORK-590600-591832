package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando{

	private String direzione;
	private IO io;



	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza prossimaStanza = null;

		if(direzione==null) {
			this.io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
			return;
		}


		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
			return;
		}

		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			this.io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}		 
	}


	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;

	}


	@Override
	public void getNome() {
		// TODO Auto-generated method stub

	}


	@Override
	public String getParametro() {
		return this.direzione;
	}


}
