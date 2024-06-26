package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private String nome;
	private List<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private Map<Direzione,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new ArrayList<>();
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		/* inserisce la coppia */
		Stanza v = this.stanzeAdiacenti.put(direzione, stanza);

		/* se alla chiave non era assegnato alcun valore, vuol dire che si sta impostando una
		 * direzione nuova, quindi incrementa il numeroStanzeAdiacenti */
		if (v==null) {
		}

		//if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {}	è da prendere in considerazione questa condizione?
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {

		/* se attrezzo è null o se è già contenuto nella stanza */
		if (attrezzo==null || hasAttrezzo(attrezzo.getNome())) return false;

		this.attrezzi.add(attrezzo);
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");

		Set<Direzione> direzioni = getDirezioni();
		for (Direzione direzione : direzioni)
			risultato.append(" " + direzione);

		risultato.append("\nAttrezzi nella stanza: ");
		/* elenca attrezzi nella stanza, se ci sono */
		for (Attrezzo a: this.attrezzi) {
			risultato.append(a.toString()+" ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo a1: this.attrezzi)
			if (a1.getNome().equals(nomeAttrezzo))
				a = a1;

		return a;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {		
		if (attrezzo==null) return false;	
		boolean out = false;
		Iterator<Attrezzo> it = this.attrezzi.iterator();

		while(it.hasNext()) {
			if(it.next().getNome().equals(attrezzo.getNome())) {
				out = true;
				it.remove();
			}
		}
		return out;	// se attrezzo non è stato trovato in this.attrezzi[]
	}


	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	public Map<Direzione,Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	public boolean isMagica() {
		return false;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Stanza that = (Stanza) o;
		return (this.nome.equals(that.getNome())) && (this.attrezzi.equals(that.getAttrezzi())) &&
				(this.numeroAttrezzi==that.numeroAttrezzi) &&(this.stanzeAdiacenti.equals(that.getMapStanzeAdiacenti())) && 
				(this.personaggio.equals(that.getPersonaggio()));
	}
	
	@Override
	public int hashCode() {
		return (this.nome.hashCode())+(this.attrezzi.hashCode())+this.numeroAttrezzi+
				(this.stanzeAdiacenti.hashCode())+(this.personaggio.hashCode());
	}
}