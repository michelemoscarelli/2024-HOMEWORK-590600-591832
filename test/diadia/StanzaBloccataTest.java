package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private Labirinto trilocale;
	private LabirintoBuilder labirintoBuilder;
	private Attrezzo chiave;

	@Before
	public void setUp(){
		
		trilocale = labirintoBuilder.addStanzaIniziale("bagno").addStanzaBloccata("bagno","nord", "chiave").addStanzaVincente("cucina")
				.addAdiacenza("bagno", "cucina", "nord").addAdiacenza("cucina", "bagno", "sud").addStanza("sgabuzzino")
				.addAdiacenza("bagno", "sgabuzzino", "est").addAdiacenza("sgabuzzino","bagno" , "ovest").getLabirinto();	
		this.chiave = new  Attrezzo("chiave", 2);
		
		/*this.chiave = new  Attrezzo("chiave", 2);
		this.cucina = new Stanza("cucina");
		this.bagno = new StanzaBloccata("bagno","nord", "chiave");
		this.sgabuzzino = new Stanza("sgabuzzino");
		this.bagno.impostaStanzaAdiacente("nord",cucina);
		this.bagno.impostaStanzaAdiacente("est",sgabuzzino);
		this.sgabuzzino.impostaStanzaAdiacente("ovest", bagno);
		this.cucina.impostaStanzaAdiacente("sud",bagno);*/
	}

	@Test
	public void testgetDescrizione_StanzaSbloccabile() {
		this.trilocale.getEntrata().addAttrezzo(chiave);
		assertEquals(this.trilocale.getEntrata().toString(),this.trilocale.getEntrata().getDescrizione());
	}
	
	@Test
	public void testgetDescrizione_StanzaSenzaChiave() {
		assertEquals("Stanza bloccata nella direzione: "+ "nord"+"\nPrendi l'attrezzo " + "chiave" + " e posalo nella stanza",this.trilocale.getEntrata().getDescrizione());
		
	}
	/*@Test
	public void testgetStanzaAdiacente_Sbloccata() {
		this.trilocale.getEntrata().addAttrezzo(chiave);
		assertEquals(this.trilocale.get,this.trilocale.getEntrata().getStanzaAdiacente("nord"));
	}*/
	
	@Test
	public void testgetStanzaAdiacente_DirezioneBloccata() {
		assertEquals(this.trilocale.getEntrata(),this.trilocale.getEntrata().getStanzaAdiacente("nord"));
	}
	/*@Test
	public void testgetStanzaAdiacente_DirezioneNonBloccata() {
		assertEquals(this.sgabuzzino,this.bagno.getStanzaAdiacente("est"));
	}*/
}
