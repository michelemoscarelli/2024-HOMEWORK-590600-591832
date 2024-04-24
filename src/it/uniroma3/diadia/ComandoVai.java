package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comandoi{
	
	private String direzione;
	private IOConsole ioConsole;
	
	public ComandoVai(String direzione) {
	 this.direzione = direzione;
	}
	
	
	/**
	 * esecuzione del comando
	 */
	 @Override
	 public void esegui(Partita partita) {
		 Stanza prossimaStanza = null;
		 
		 if(direzione==null) {
			this.ioConsole.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
			return;
		 }
		 
			
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.ioConsole.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			this.ioConsole.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}		 
	}


}
