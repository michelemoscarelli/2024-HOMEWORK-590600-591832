package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private static final String MESSAGGIO_CHI_REGALO = "A chi dovrei reagalare?...";
	private static final String MESSAGGIO_ATTREZZO_MANCATO = "Attrezzo non trovato in borsa";
	private String messaggio;
	String attrezzo;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {

			if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo)) {
				this.messaggio = personaggio.riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo), partita);
				this.getIo().mostraMessaggio(this.messaggio);
			}
			else
				this.getIo().mostraMessaggio(MESSAGGIO_ATTREZZO_MANCATO);

		} else this.getIo().mostraMessaggio(MESSAGGIO_CHI_REGALO);

	}

	@Override
	public void setParametro(String attrezzo) {
		this.attrezzo = attrezzo;
	}

	@Override
	public String getNome() {
		return "Regala";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
