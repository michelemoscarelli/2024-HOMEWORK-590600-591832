package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Map<String, Stanza> stanze;
	private Stanza ultimaAggiunta;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanze = new HashMap<String, Stanza>();
	}
	
	
	

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza s = new Stanza(nomeStanzaIniziale);
		this.setUltimaStanzaAggiunta(s);
		this.labirinto.setStanzaIniziale(s);
		this.labirinto.setStanzaCorrente(s);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza s = new Stanza(nomeStanzaVincente);
		this.setUltimaStanzaAggiunta(s);
		this.labirinto.setStanzaVincente(s);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String string, int i) {
		Attrezzo a = new Attrezzo(string, i);
		if (this.ultimaAggiunta==null) return this;
		
		this.ultimaAggiunta.addAttrezzo(a);
		return this;
	}

	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza s = new Stanza(nomeStanza);
		this.setUltimaStanzaAggiunta(s);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String nomeStanzaA, String nomeStanzaB, String direzione) {
		Stanza sA = this.stanze.get(nomeStanzaA);
		Stanza sB = this.stanze.get(nomeStanzaB);
		sA.impostaStanzaAdiacente(direzione, sB);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata, String nomeAttrezzoChiave) {
		Stanza s = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, nomeAttrezzoChiave);
		this.setUltimaStanzaAggiunta(s);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int soglia) {
		Stanza s = new StanzaMagica(nomeStanzaMagica, soglia);
		this.setUltimaStanzaAggiunta(s);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoLuce) {
		Stanza s = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoLuce);
		this.setUltimaStanzaAggiunta(s);
		return this;
	}

	
	
	public void setUltimaStanzaAggiunta(Stanza stanza) {
		this.ultimaAggiunta = stanza;
		this.stanze.put(stanza.getNome(), stanza);
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Map<String,Stanza> getListaStanze() {
		return this.stanze;
	}
	
	
	
}
