package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;



public class StanzaBuiaTest {
	
	Stanza bagno;
	private Attrezzo lanterna;
	
	@Before
	public void setUp(){;
	this.lanterna = new  Attrezzo("lanterna", 2);
	this.bagno = new StanzaBuia("bagno","lanterna");
	
	}

	@Test
	public void testgetDescrizione_StanzaBuiasi() {
		assertEquals("qui c'è un buio pesto",this.bagno.getDescrizione());
	}
	
	@Test
	public void testgetDescrizione_StanzaBuiano() {
		this.bagno.addAttrezzo(lanterna);
		assertEquals(this.bagno.toString(),this.bagno.getDescrizione());
		
	}

}
