package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_SALUTATO = "Mi hai salutato? Ti mando nella stanza con più attrezzi";
	private static final String MESSAGGIO_NON_SALUTATO = "Brutto infame non mi saluti?"
			+ " Ora ti faccio vedere io. Ti mando nella stanza con meno attrezzi";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	public Stanza stanzaAttrezziMassimi(Stanza stanza_corrente) {
		/* iteriamo nell'insieme delle stanze adiacenti  */
		Stanza stanza_max = null;
		for (Stanza adiacente : stanza_corrente.getMapStanzeAdiacenti().values()) {
			if (stanza_max==null) stanza_max = adiacente;
			
			if (adiacente.getAttrezzi().size()>stanza_max.getAttrezzi().size())
				stanza_max = adiacente;
		}
		return stanza_max;
	}

	public Stanza stanzaAttrezziMinimi(Stanza stanza_corrente) {
		/* iteriamo nell'insieme delle stanze adiacenti  */
		Stanza stanza_min = null;
		for (Stanza adiacente : stanza_corrente.getMapStanzeAdiacenti().values()) {
			if (stanza_min==null) stanza_min = adiacente;
			
			if (adiacente.getAttrezzi().size()<stanza_min.getAttrezzi().size())
				stanza_min = adiacente;
		}
		return stanza_min;
	}

	@Override
	public String agisci(Partita partita) {
		
		if (haSalutato()) {
			partita.getLabirinto().
			setStanzaCorrente(stanzaAttrezziMassimi(partita.getLabirinto().getStanzaCorrente()));
			return MESSAGGIO_SALUTATO;
		}
		else {
			partita.getLabirinto().
			setStanzaCorrente(stanzaAttrezziMinimi(partita.getLabirinto().getStanzaCorrente()));
			return MESSAGGIO_NON_SALUTATO;
		}
	}

}
