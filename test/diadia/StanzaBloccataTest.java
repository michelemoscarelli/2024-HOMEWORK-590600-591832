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
	private String nomeStanzaIniziale = "bagno";
	private String nomeStanzaVincente = "cucina";

	@Before
	public void setUp(){
		
		labirintoBuilder = new LabirintoBuilder();
		trilocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanzaBloccata(nomeStanzaIniziale,"nord", "chiave").addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord").addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				//.addStanza("sgabuzzino").addAdiacenza("bagno", "sgabuzzino", "est").addAdiacenza("sgabuzzino","bagno" , "ovest")
				.getLabirinto();	
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
		this.trilocale.getStanzaIniziale().addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaIniziale().toString(),this.trilocale.getStanzaIniziale().getDescrizione());
	}
	
	@Test
	public void testgetDescrizione_StanzaSenzaChiave() {
		assertEquals("Stanza bloccata nella direzione: "+ "nord"+"\nPrendi l'attrezzo " + "chiave" + " e posalo nella stanza",this.trilocale.getStanzaIniziale().getDescrizione());
		
	}
	@Test
	public void testgetStanzaAdiacente_Sbloccata() {
		this.trilocale.getStanzaIniziale().addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaCorrente(),this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testgetStanzaAdiacente_DirezioneBloccata() {
		assertEquals(this.trilocale.getStanzaIniziale(),this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
	}
	/*@Test
	public void testgetStanzaAdiacente_DirezioneNonBloccata() {
		assertEquals(this.sgabuzzino,this.bagno.getStanzaAdiacente("est"));
	}*/
}
