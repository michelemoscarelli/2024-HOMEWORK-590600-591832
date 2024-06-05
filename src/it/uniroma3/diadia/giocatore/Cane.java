package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_INTERAZIONE = "WOUF, WOUF, AHU. ORA TI MORDO"
			+ " E HAI PERSO CFU!";
	private static final String MESSAGGIO_CIBO_ATTREZZO = "WOUF, WOUF, WOUF!! (Ringrazio umilmente Vossignoria dallo squisito dono"+
			" sono per cui onorato di donorle questo povero attrezzo";
	private static final String MESSAGGIO_CIBO_NO_ATTREZZO = "WOUF, WOUF, WUFF(sorry boss non non ho più roba da darti, grazie lo stesso tho";
	Attrezzo cibopreferito;
	Attrezzo attrezzotraidenti;
	boolean hasAttrezzo;

	public Cane(String nome, String presentaz, Attrezzo cibopreferito, Attrezzo attrezzotraidenti) {
		super(nome, presentaz);
		this.cibopreferito = cibopreferito;
		this.attrezzotraidenti = attrezzotraidenti;
		this.hasAttrezzo = true;
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu--);	
		return MESSAGGIO_INTERAZIONE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.equals(cibopreferito)&&hasAttrezzo==true) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzotraidenti);
			this.hasAttrezzo = false;
			return MESSAGGIO_CIBO_ATTREZZO;
		}
		if(attrezzo.equals(cibopreferito)&&hasAttrezzo==true)
			return MESSAGGIO_CIBO_NO_ATTREZZO;
		
		return agisci(partita);
	}

}
