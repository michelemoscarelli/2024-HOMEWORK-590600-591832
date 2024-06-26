package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String attrezzochiave;
	private Direzione direzioneBloccata;
	
	public StanzaBloccata(String nome,Direzione direzioneBloccata, String attrezzochiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzochiave = attrezzochiave;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		
		if(direzioneBloccata.equals(direzione)&&!hasAttrezzo(attrezzochiave))
           return this;
		return super.getStanzaAdiacente(direzione);
		
	}
	@Override
	public String getDescrizione() {
		String bloccata = "Stanza bloccata nella direzione: "+ direzioneBloccata+"\nPrendi l'attrezzo " + attrezzochiave + " e posalo nella stanza";

		if(!this.hasAttrezzo(attrezzochiave))
			return bloccata;
		return super.getDescrizione();
	}
}
