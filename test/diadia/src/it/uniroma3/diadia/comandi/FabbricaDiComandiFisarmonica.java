package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	private IO io;
	
	public FabbricaDiComandiFisarmonica(IO io){
		this.io = io;
	}
	
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
	    String parametro = null;
	    Comando comando = null;			

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();

		if (nomeComando == null) 
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else if (nomeComando.equals("borsa"))
			comando = new ComandoBorsa();
		else comando = new ComandoNonValido();
		
		comando.setParametro(parametro);
		comando.setIO(this.io);
		return comando;
	}
}
