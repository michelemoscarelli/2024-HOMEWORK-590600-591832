package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
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
		trilocale = labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanzaBloccata("sgabuzzino",Direzione.nord, "chiave")
				.addAdiacenza(nomeStanzaIniziale, "sgabuzzino", Direzione.nord).addAdiacenza("sgabuzzino", nomeStanzaIniziale, Direzione.sud)
				.addStanzaVincente(nomeStanzaVincente).addAdiacenza(nomeStanzaVincente, "sgabuzzino", Direzione.sud).addAdiacenza("sgabuzzino",nomeStanzaVincente , Direzione.nord)
				.getLabirinto();	
		this.chiave = new  Attrezzo("chiave", 2);
	}

	@Test
	public void testgetDescrizione_StanzaSbloccabile() {
		this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord),this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testgetDescrizione_StanzaSenzaChiave() {
		assertEquals("Stanza bloccata nella direzione: "+ "nord"+"\nPrendi l'attrezzo " + "chiave" + " e posalo nella stanza",this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getDescrizione());
		
	}
	@Test
	public void testgetStanzaAdiacente_Sbloccata() {
		this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).addAttrezzo(chiave);
		assertEquals(this.trilocale.getStanzaVincente(),this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testgetStanzaAdiacente_DirezioneBloccata() {
		assertEquals(this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord),this.trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getStanzaAdiacente(Direzione.nord));
	}
	@Test
	public void testgetStanzaAdiacente_DirezioneNonBloccata() {
		assertEquals(this.trilocale.getStanzaIniziale(),this.trilocale.getStanzaVincente().getStanzaAdiacente(Direzione.sud).getStanzaAdiacente(Direzione.sud));
	}
}
