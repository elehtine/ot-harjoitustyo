package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void alussaSaldoOikein() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void saldoKasvaa() {
		kortti.lataaRahaa(250);
        assertEquals("saldo: 12.50", kortti.toString());
    }

    @Test
    public void saldonVaheneminenPalauttaaTrue() {
		assertTrue(kortti.otaRahaa(250));
    }

    @Test
    public void eiVoiOttaaRahaaPalauttaaFalse() {
		assertFalse(kortti.otaRahaa(1500));
    }

    @Test
    public void saldoVahenee() {
		kortti.otaRahaa(250);
        assertEquals("saldo: 7.50", kortti.toString());
    }

    @Test
    public void eiNegatiivinenSaldo() {
		kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }

}
