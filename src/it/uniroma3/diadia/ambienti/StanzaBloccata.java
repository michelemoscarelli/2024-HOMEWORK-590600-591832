package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String attrezzochiave;
	private String direzioneBloccata;
	public StanzaBloccata(String nome) {
		super(nome);
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if(!hasAttrezzo(attrezzochiave))
           return this;
		return super.getStanzaAdiacente(direzioneBloccata);
		
	}
	@Override
	public String getDescrizione() {
		String bloccata = "Stanza bloccata nella direzione: "+ direzioneBloccata+"\nPrendi il " + attrezzochiave + " e posalo nella stanza";

		if(!this.hasAttrezzo(attrezzochiave))
			return bloccata;
		return super.getDescrizione();
	}
}
