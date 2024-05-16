package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	Stanza bagno;
	Stanza cucina;
	Stanza sgabuzzino;
	private Attrezzo chiave;

	@Before
	public void setUp(){
		this.chiave = new  Attrezzo("chiave", 2);
		this.cucina = new Stanza("cucina");
		this.bagno = new StanzaBloccata("bagno","nord", "chiave");
		this.sgabuzzino = new Stanza("sgabuzzino");
		this.bagno.impostaStanzaAdiacente("nord",cucina);
		this.bagno.impostaStanzaAdiacente("est",sgabuzzino);
		this.sgabuzzino.impostaStanzaAdiacente("ovest", bagno);
		this.cucina.impostaStanzaAdiacente("sud",bagno);
	}

	@Test
	public void testgetDescrizione_StanzaSbloccabile() {
		this.bagno.addAttrezzo(chiave);
		assertEquals(this.bagno.toString(),this.bagno.getDescrizione());
	}
	
	@Test
	public void testgetDescrizione_StanzaSenzaChiave() {
		assertEquals("Stanza bloccata nella direzione: "+ "nord"+"\nPrendi l'attrezzo " + "chiave" + " e posalo nella stanza",this.bagno.getDescrizione());
		
	}
	@Test
	public void testgetStanzaAdiacente_Sbloccata() {
		this.bagno.addAttrezzo(chiave);
		assertEquals(this.cucina,this.bagno.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testgetStanzaAdiacente_DirezioneBloccata() {
		assertEquals(this.bagno,this.bagno.getStanzaAdiacente("nord"));
	}
	@Test
	public void testgetStanzaAdiacente_DirezioneNonBloccata() {
		assertEquals(this.sgabuzzino,this.bagno.getStanzaAdiacente("est"));
	}
}
