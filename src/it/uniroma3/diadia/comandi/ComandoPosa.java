package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;
	private IOConsole ioConsole;
	
	
	
	@Override
	public void esegui(Partita partita) {
		
		/* il nome dell'attrezzo è null, stampa messaggio di errore e ritorna */
		if (nomeAttrezzo==null) {
			this.ioConsole.mostraMessaggio("Parametro non valido");
			return;
		}
		
		
		
		/* il nome dell'attrezzo non è null, cercalo tra gli attrezzi della borsa*/
		Attrezzo attrezzo_da_posare;
		// se non esiste, vale null
		attrezzo_da_posare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		/* esiste l'attrezzo, viene aggiunto nella stanza se non è piena, viene rimosso dalla borsa e ritorna */
		if (attrezzo_da_posare!=null) {
			/* mettilo nella stanza */
			boolean posato_in_stanza = partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo_da_posare);
			
			/* stanza è piena */
			if ( !(posato_in_stanza) ) {
				this.ioConsole.mostraMessaggio("Impossibile posare l'attrezzo"+" "+nomeAttrezzo+":"+" "+"stanza piena.");
				return;
			}
			
			/* stanza non è piena: attrezzo posato */
			this.ioConsole.mostraMessaggio("Attrezzo"+" "+nomeAttrezzo+" "+"posato.");
			
			/* toglilo dalla borsa */
			/* se removeAttrezzo non restituisce l'elemento eliminato, stampa messaggio di errore,
			 * altrimenti l'elemento è rimosso da borsa */
			if ( partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo_da_posare.getNome())==null ) {
				this.ioConsole.mostraMessaggio("Errore: rimozione di"+" "+nomeAttrezzo+" "+" da borsa non riuscita.");
			}
			return;
		}
		/* non esiste, stampa messaggio di errore e ritorna */
		this.ioConsole.mostraMessaggio("Attrezzo non trovato in borsa.");
		return;
		
	}

	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo=nomeAttrezzo;
		
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
