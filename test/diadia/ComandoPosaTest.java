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
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	private ComandoPosa comandoposa;
	private LabirintoBuilder labirintoBuilder;
	private Partita partitamono;
	private Partita partitabi;
	private Attrezzo attrezzo;
	private IO io;

	@Before
	public void setUp() throws Exception {
		
		Labirinto monolocale = new LabirintoBuilder("test2.txt").getLabirinto();
		Labirinto monolocale1 = labirintoBuilder.addStanzaIniziale("salotto").addStanzaVincente("salotto").getLabirinto();
		this.partitamono = new Partita(monolocale);
		
//		Labirinto bilocale = labirintoBuilder
//				.addStanzaIniziale("salotto")
//				.addStanzaVincente("cucina")
//				.addAdiacenza("salotto","cucina", Direzione.nord)
//				.addAdiacenza("cucina", "salotto", Direzione.sud)
//				.getLabirinto();
//		this.partitabi = new Partita(bilocale);
		
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.comandoposa = new ComandoPosa();
		this.attrezzo = new Attrezzo("attrezzo_qualunque",2);
		this.comandoposa.setIO(this.io);
	}

	/* test esegui */

	
	@Test
	public void testEsegui_AttrezzoNonEsisteMono() {
		
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(this.partitamono);
		assertFalse(this.partitamono.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoNonEsisteBi() {
		
		
			
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(this.partitabi);
		assertFalse(this.partitabi.getLabirinto().getStanzaCorrente().hasAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoPosatoMono() {
		//aggiunge attrezzo alla borsa
		this.partitamono.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(this.partitamono);
		assertEquals(attrezzo,this.partitamono.getLabirinto().getStanzaCorrente().getAttrezzo("attrezzo_qualunque"));
	}
	
	@Test
	public void testEsegui_AttrezzoPosatoBi() {
		//aggiunge attrezzo alla borsa
		this.partitabi.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.comandoposa.setParametro("attrezzo_qualunque");
		this.comandoposa.esegui(this.partitabi);
		assertEquals(attrezzo,this.partitabi.getLabirinto().getStanzaCorrente().getAttrezzo("attrezzo_qualunque"));
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
