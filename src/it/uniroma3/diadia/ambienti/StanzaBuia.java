package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoluce;

	public StanzaBuia(String nome,String attrezzoluce) {
		super(nome);
		this.attrezzoluce = attrezzoluce;
	}
	
	public StanzaBuia(String nome) {
		this(nome,null);
	}

	@Override
	public String getDescrizione() {
		if(!hasAttrezzo(attrezzoluce))
			return "qui c'è un buio pesto";
		return super.getDescrizione();
    }
	
	public String getAttrezzoluce(String attrezzoluce) {
		return attrezzoluce;
	}

	public void setAttrezzoluce(String attrezzoluce) {
		this.attrezzoluce = attrezzoluce;
	}
	
}
