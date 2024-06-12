package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai extends AbstractComando {

	private Direzione direzione;



	/**
	 * esecuzione del comando
	 */
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.getParametro() == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		}
		if(this.getParametro()!=null )// && (Direzione.valueOf(this.getParametro()).getClass() != Direzione.class))
			try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
			} catch(IllegalArgumentException e) {
				this.getIo().mostraMessaggio("Direzione inesistente");
				return;
			}
			
			if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
	}


	@Override
	public void setParametro(String direzione) {
		this.direzione = Direzione.valueOf(direzione);

	}


	@Override
	public String getNome() {
		return "Vai";
	}


	@Override
	public String getParametro() {
		return this.direzione.toString();
	}
	
}
