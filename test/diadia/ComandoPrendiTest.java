package diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	private ComandoPrendi comandoprendi;
	private Partita partita;
	private LabirintoBuilder labirintoBuilder;
	private Attrezzo attrezzo;
	private IO io;
	private Attrezzo pesante;

	@Before
	public void setUp() throws Exception {
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.labirintoBuilder = new LabirintoBuilder();
		this.comandoprendi = new ComandoPrendi();
		Labirinto bilocale = labirintoBuilder.addStanzaIniziale("salotto").addStanzaVincente("cucina")
				.addAdiacenza("salotto", "cucina", Direzione.nord).addAdiacenza("cucina", "salotto", Direzione.sud).getLabirinto();
		this.partita = new Partita(bilocale);
		this.attrezzo = new Attrezzo("attrezzo_qualunque",2);
		this.pesante = new Attrezzo("attrezzo_pesante",10);
		this.comandoprendi.setIO(this.io);
	}

	/* test esegui */
	
	@Test
	public void testEsegui_AttrezzoNonEsiste() {
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoEsisteMaBorsaIsPiena() {
		//aggiunge attrezzo ad una stanza
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.pesante);
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoPreso() {
		//aggiunge attrezzo ad una stanza
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.comandoprendi.setParametro("attrezzo_qualunque");
		this.comandoprendi.esegui(this.partita);
		assertEquals(this.attrezzo,this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo_qualunque"));
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
