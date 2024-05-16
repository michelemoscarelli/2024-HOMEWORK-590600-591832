package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	
	private StanzaMagica stanza;
	private Attrezzo attrezzo1;
	private Attrezzo restituito;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	
	@Before
	public void setUp() {
		this.stanza = new StanzaMagica("stanzaProva");
		this.attrezzo1 = new Attrezzo("1234", 4);
		this.attrezzo2 = new Attrezzo("5678", 4);
		this.attrezzo3 = new Attrezzo("abcd", 4);
		this.attrezzo4 = new Attrezzo("efgh", 4);
		this.restituito = stanza.modificaAttrezzo(attrezzo1);
	}
	
	/* test modificaAttrezzo */
	@Test
	public void testmodificaAttrezzo_OK() {
		assertEquals("4321", this.restituito.getNome());
		assertEquals(8, this.restituito.getPeso());

	}
	
	/* test modificaAttrezzo */
	@Test
	public void testAddAttrezzo_OK() {
		this.stanza.addAttrezzo(attrezzo1);
		this.stanza.addAttrezzo(attrezzo2);
		this.stanza.addAttrezzo(attrezzo3);
		this.stanza.addAttrezzo(attrezzo4);
		this.restituito = stanza.getAttrezzo(this.stanza.modificaAttrezzo(attrezzo4).getNome());
		assertEquals("hgfe", this.restituito.getNome());
		assertEquals(8, this.restituito.getPeso());
	}

}
