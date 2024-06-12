package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Cane;
import it.uniroma3.diadia.giocatore.Mago;
import it.uniroma3.diadia.giocatore.Strega;

public class LabirintoBuilder {
	

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nome2stanza;

	public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = new Labirinto(labirinto);
		this.nome2stanza = new HashMap<>();
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza s = new Stanza(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(s);
		this.labirinto.setStanzaCorrente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza s = new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		Stanza s = new Stanza(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}	

	//		public LabirintoBuilder addPersonaggio(String nome, String presentazione) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	//			AbstractPersonaggio p = null;
	//
	//			StringBuilder nomeClasse
	//			= new StringBuilder("it.uniroma3.diadia.personaggi.");
	//			nomeClasse.append( nome.substring(1) ) ;
	//			p = (AbstractPersonaggio)Class.forName(nomeClasse.toString()).newInstance();
	//			p.setNome(nome);
	//			p.setPresentazione(presentazione);
	//			if(this.ultimaStanzaAggiunta==null)
	//				return this;
	//			this.ultimaStanzaAggiunta.setPersonaggio(p);
	//			return this;
	//		}

	public LabirintoBuilder  addMago(String nome, String presentazione, Attrezzo attrezzo) {
		Mago m = new Mago(nome, presentazione, attrezzo);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.setPersonaggio(m);
		return this;
	}

	public LabirintoBuilder  addCane(String nome, String presentazione,Attrezzo cibopreferito, Attrezzo attrezzotraidenti) {
		Cane c = new Cane(nome, presentazione, cibopreferito, attrezzotraidenti);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.setPersonaggio(c);
		return this;
	}

	public LabirintoBuilder  addStrega(String nome, String presentazione) {
		Strega s = new Strega(nome, presentazione);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.setPersonaggio(s);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.addAttrezzo(a);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, Direzione direzione) {
		Stanza c = this.nome2stanza.get(stanzaCorrente);
		Stanza a = this.nome2stanza.get(stanzaAdiecente);
		c.impostaStanzaAdiacente(direzione, a);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome, soglia);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, Direzione direzioneBloccata) {
		Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}

	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}
	
	public Map<String,Stanza> getListaStanze() {
		return this.nome2stanza;
	}
	
}
