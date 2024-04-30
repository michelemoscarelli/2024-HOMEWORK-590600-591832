package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoluce;
	
	public StanzaBuia(String nome) {
		super(nome);
	}

	@Override
	public String getDescrizione() {
		if(!hasAttrezzo(attrezzoluce))
			return "qui c'è un buio pesto";
		return super.getDescrizione();
    }
	
}
