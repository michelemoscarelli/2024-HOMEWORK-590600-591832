package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoPrendiTest {
	private ComandoPrendi comandoprendi;
	private Partita partita;
	private Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		this.comandoprendi = new ComandoPrendi();
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
		this.comandoprendi.setParametro("attrezzo_qualunque");		//imposta il parametro al comando
		assertEquals("attrezzo_qualunque",this.comandoprendi.getParametro());
	}
	
	@Test
	public void testSetParametro_Null() {
		this.comandoprendi.setParametro(null);		//imposta il parametro al comando
		assertNull(this.comandoprendi.getParametro());
	}
}
