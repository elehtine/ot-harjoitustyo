package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

	Kassapaate kassa;
	Maksukortti kortti;

	@Before
	public void setUp() {
		kassa = new Kassapaate();
		kortti = new Maksukortti(1000);
	}

	@Test
	public void luotuKassaOlemassa() {
		assertTrue(kassa != null);
	}

	@Test
	public void alussaRahaOikein() {
		assertTrue(kassa.kassassaRahaa() == 100000);
	}

	@Test
	public void alussaMaukkaitaOikeaMaara() {
		assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
	}

	@Test
	public void alussaedullisiaOikeaMaara() {
		assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
	}

	@Test
	public void rahamaaraKasvaaEdullisellaKateisostolla() {
		kassa.syoEdullisesti(240);
		assertTrue(kassa.kassassaRahaa() == 100240);
	}

	@Test
	public void rahamaaraKasvaaMaukkaallallaKateisostolla() {
		kassa.syoMaukkaasti(400);
		assertTrue(kassa.kassassaRahaa() == 100400);
	}

	@Test
	public void edullistenMaaraKasvaaKateisostolla() {
		kassa.syoEdullisesti(240);
		assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
	}

	@Test
	public void maukkaidenMaaraKasvaaKateisostolla() {
		kassa.syoMaukkaasti(400);
		assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
	}

	@Test
	public void palauttaaRahatEdullisesta() {
		assertTrue(kassa.syoEdullisesti(230) == 230);
	}

	@Test
	public void palauttaaRahatMaukkaasta() {
		assertTrue(kassa.syoMaukkaasti(390) == 390);
	}

	@Test
	public void eiLisaaEdullisiaLiianVahallaRahalla() {
		kassa.syoEdullisesti(230);
		assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
	}

	@Test
	public void eiLisaaMaukkaitaLiianVahallaRahalla() {
		kassa.syoMaukkaasti(390);
		assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
	}

	@Test
	public void eiLisaaRahaaEdullisistaLiianVahallaRahalla() {
		kassa.syoEdullisesti(230);
		assertTrue(kassa.kassassaRahaa() == 100000);
	}

	@Test
	public void eiLisaaRahaaMaukkaistaLiianVahallaRahalla() {
		kassa.syoMaukkaasti(390);
		assertTrue(kassa.kassassaRahaa() == 100000);
	}

	@Test
	public void kortiltaVeloitetaanEdullinen() {
		kassa.syoEdullisesti(kortti);
		assertTrue(kortti.saldo() == 760);
	}

	@Test
	public void kortiltaVeloitetaanMaukas() {
		kassa.syoMaukkaasti(kortti);
		assertTrue(kortti.saldo() == 600);
	}

	@Test
	public void kortillaSaaEdullisen() {
		assertTrue(kassa.syoEdullisesti(kortti));
	}

	@Test
	public void kortillaSaaMaukkaan() {
		assertTrue(kassa.syoMaukkaasti(kortti));
	}

	@Test
	public void kortillaLisataanEdullisia() {
		kassa.syoEdullisesti(kortti);
		assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
	}

	@Test
	public void kortillaLisataanMaukkaita() {
		kassa.syoMaukkaasti(kortti);
		assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
	}

	@Test
	public void kortillaEiVoiOstaaIlmanRahaa() {
		kassa.syoMaukkaasti(kortti);
		kassa.syoMaukkaasti(kortti);
		kassa.syoMaukkaasti(kortti);
		kassa.syoEdullisesti(kortti);
		assertTrue(kortti.saldo() == 200);
	}

	@Test
	public void kassanRahaEiMuutuKortilla() {
		kassa.syoEdullisesti(kortti);
		kassa.syoMaukkaasti(kortti);
		assertTrue(kassa.kassassaRahaa() == 100000);
	}

	@Test
	public void ladattaessaTuleeLisaaRahaa() {
		kassa.lataaRahaaKortille(kortti, 100);
		assertTrue(kortti.saldo() == 1100);
	}

	@Test
	public void negatiivinenEiLataa() {
		kassa.lataaRahaaKortille(kortti, -100);
		assertTrue(kortti.saldo() == 1000);
	}

}

