package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_INTERAZIONE = "WOUF, WOUF, AHU. ORA TI HO MORSO"
			+ " E HAI PERSO CFU!";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu--);	
		return MESSAGGIO_INTERAZIONE;
	}

}
