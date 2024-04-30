package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPosaTest {
	private ComandoPosa comandoposa;
	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Attrezzo pesante;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandoposa = new ComandoPosa();
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("attrezzo_qualunque",2);
		this.pesante = new Attrezzo("attrezzo_pesante",10);
		this.comandoposa.setIO(io);
	}

	/* test esegui */

	
	@Test
	public void testEsegui_AttrezzoNonEsiste() {
			
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoEsisteMaStanzaIsPiena() {
		//aggiunge attrezzo alla borsa
		for (int i=0; i<10; i++)		// 10 è il numero massimo di attrezzi contenibili in Stanza
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.pesante);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoPosato() {
		//aggiunge attrezzo alla borsa
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(partita);
		assertEquals(attrezzo,this.partita.getLabirinto().getStanzaCorrente().getAttrezzo("attrezzo_qualunque"));
	}
	
	/* test setParametro */
	@Test
	public void testSetParametro_OK() {
		this.comandoposa.setParametro("attrezzo_qualunque");		//imposta il parametro al comando
		assertEquals("attrezzo_qualunque",this.comandoposa.getParametro());
	}
	
	@Test
	public void testSetParametro_Null() {
		this.comandoposa.setParametro(null);		//imposta il parametro al comando
		assertNull(this.comandoposa.getParametro());
	}
}
