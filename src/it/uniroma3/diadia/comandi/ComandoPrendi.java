package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private String nomeAttrezzo;
	

	@Override
	public void esegui(Partita partita) {
		/* il nome dell'attrezzo è null, stampa messaggio di errore e ritorna */
		if (nomeAttrezzo==null) {
			this.getIo().mostraMessaggio("Parametro non valido");
			return;
		}
		/* il nome dell'attrezzo non è null, cercalo tra gli attrezzi della stanza */
		Attrezzo attrezzo_da_prendere;
		// se non esiste, vale null
		attrezzo_da_prendere = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		/* esiste l'attrezzo: mettilo in borsa se si può, toglilo dalla stanza e ritorna */
		if (attrezzo_da_prendere!=null) {
			/* mettilo in borsa */
			boolean preso_in_borsa = partita.getGiocatore().getBorsa().addAttrezzo(attrezzo_da_prendere);
			
			/* borsa è piena */
			if ( !(preso_in_borsa) ) {
				this.getIo().mostraMessaggio("Impossibile prendere l'attrezzo"+" "+nomeAttrezzo+":"+" "+"borsa piena o già troppo pesante.");
				return;
			}
			/* borsa non è piena: attrezzo preso */
			this.getIo().mostraMessaggio("Attrezzo"+" "+nomeAttrezzo+" "+"preso.");
			
			/* toglilo dalla stanza */
			/* se removeAttrezzo restituisce false anziché true, stampa messaggio di errore,
			 * altrimenti l'elemento è rimosso da stanza */
			if ( !(partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo_da_prendere)) ) {
				this.getIo().mostraMessaggio("Errore: rimozione di"+" "+nomeAttrezzo+" "+"da stanza non riuscita.");
			}
			return;
		}
		/* non esiste, stampa messaggio di errore e ritorna */
		this.getIo().mostraMessaggio("Attrezzo non trovato nella stanza.");
		return;
		
	}

	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo=nomeAttrezzo;
		
	}

	@Override
	public String getNome() {
		return "Prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
		
	}

}
