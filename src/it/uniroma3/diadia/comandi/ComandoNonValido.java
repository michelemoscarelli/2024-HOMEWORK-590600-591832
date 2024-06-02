package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Comando sconosciuto");
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "ComandoNonValido";
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getParametro() {
		return null;	
	}

}
