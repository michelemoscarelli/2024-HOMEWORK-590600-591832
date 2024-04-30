package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoPrendiTest {
	private ComandoPrendi comandoprendi;
	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Attrezzo pesante;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandoprendi = new ComandoPrendi();
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("attrezzo_qualunque",2);
		this.pesante = new Attrezzo("attrezzo_pesante",10);
		this.comandoprendi.setIO(io);
	}

	/* test esegui */
	
	@Test
	public void testEsegui_AttrezzoNonEsiste() {
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoEsisteMaBorsaIsPiena() {
		//aggiunge attrezzo ad una stanza
		this.partita.getGiocatore().getBorsa().addAttrezzo(pesante);
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoPreso() {
		//aggiunge attrezzo ad una stanza
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(partita);
		assertEquals(attrezzo,this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo_qualunque"));
	}
	
	
	/* test setParametro */
	@Test
	public void testSetParametro_OK() {
		this.comandoprendi.setParametro("attrezzo_qualunque");		//imposta il parametro al comando
		assertEquals("attrezzo_qualunque",this.comandoprendi.getParametro());
	}
	
	@Test
	public void testSetParametro_Null() {
		this.comandoprendi.setParametro(null);		//imposta il parametro al comando
		assertNull(this.comandoprendi.getParametro());
	}
}
