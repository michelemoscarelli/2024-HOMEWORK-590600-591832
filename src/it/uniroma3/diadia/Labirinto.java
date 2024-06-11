package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.Objects;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;

	public Labirinto() {
		creaStanze();
	}
	
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		 CaricatoreLabirinto c = 
		 new CaricatoreLabirinto(nomeFile);
		 c.carica();
		 this.stanzaIniziale = c.getStanzaIniziale();
		 this.stanzaVincente = c.getStanzaVincente();
		}


	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave",3);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio","nord","chiave");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza aulaN09 = new StanzaMagica("Aula N09");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		laboratorio.impostaStanzaAdiacente("nord", aulaN09);
		aulaN09.impostaStanzaAdiacente("sud", laboratorio);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);
		// il gioco comincia nell'atrio
        this.stanzaCorrente = atrio;  
		this.stanzaVincente = biblioteca;
    }
    
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	@Override
	public int hashCode() {
		return (this.stanzaCorrente.hashCode())+(this.stanzaVincente.hashCode())
				+(this.stanzaIniziale.hashCode());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Labirinto that = (Labirinto) o;
		return (this.stanzaCorrente.equals(that.getStanzaCorrente()))&&
				(this.stanzaVincente.equals(that.getStanzaVincente())) && 
				(this.stanzaIniziale.equals(that.getStanzaIniziale()));
	}


	
	
	
}
