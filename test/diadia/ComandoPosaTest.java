package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPosaTest {
	private ComandoPosa comandoposa;
	private Partita partita;
	private Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		this.comandoposa = new ComandoPosa();
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("attrezzo_qualunque",2);
	}

	/* test esegui */
	@Test
	public void testEsegui_AttrezzoNull() {
		//assertSame("Parametro non valido", );
	}
	
	@Test
	public void testEsegui_AttrezzoNonEsiste() {
			
		fail("Not yet implemented");
	}
	
	@Test
	public void testEsegui_AttrezzoEsisteMaBorsaIsPiena() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEsegui_AttrezzoPreso() {
		fail("Not yet implemented");
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
