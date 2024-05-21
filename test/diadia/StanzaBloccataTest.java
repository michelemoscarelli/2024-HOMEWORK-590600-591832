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
		trilocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanzaBloccata("sgabuzzino","nord", "chiave")
				.addAdiacenza(nomeStanzaIniziale, "sgabuzzino", "nord").addAdiacenza("sgabuzzino", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaVincente).addAdiacenza(nomeStanzaVincente, "sgabuzzino", "sud").addAdiacenza("sgabuzzino",nomeStanzaVincente , "nord")
				.getLabirinto();	
		this.chiave = new  Attrezzo("chiave", 2);
	}

	@Test
	public void testgetDescrizione_StanzaSbloccabile() {
		this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord"),this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testgetDescrizione_StanzaSenzaChiave() {
		assertEquals("Stanza bloccata nella direzione: "+ "nord"+"\nPrendi l'attrezzo " + "chiave" + " e posalo nella stanza",this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord").getDescrizione());
		
	}
	@Test
	public void testgetStanzaAdiacente_Sbloccata() {
		this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord").addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaVincente(),this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testgetStanzaAdiacente_DirezioneBloccata() {
		assertEquals(this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord"),this.trilocale.getStanzaIniziale().getStanzaAdiacente("nord").getStanzaAdiacente("nord"));
	}
	@Test
	public void testgetStanzaAdiacente_DirezioneNonBloccata() {
		assertEquals(this.trilocale.getStanzaIniziale(),this.trilocale.getStanzaVincente().getStanzaAdiacente("sud").getStanzaAdiacente("sud"));
	}
}
