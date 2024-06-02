package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai extends AbstractComando {

	private String direzione;



	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza prossimaStanza = null;

		if(direzione==null) {
			this.getIo().mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
			return;
		}


		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}		 
	}


	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;

	}


	@Override
	public String getNome() {
		return "vai";
		// TODO Auto-generated method stub

	}


	@Override
	public String getParametro() {
		return this.direzione;
	}
	
}
