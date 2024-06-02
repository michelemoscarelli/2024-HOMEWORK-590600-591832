package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().toString());
		this.getIo().mostraMessaggio("ed hai: " + partita.getGiocatore().getCfu() + " CFU");
		
	}

	@Override
	public String getNome() {
		return "Guarda";
		
	}


	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
