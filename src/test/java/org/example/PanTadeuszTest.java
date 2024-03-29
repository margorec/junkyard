package org.example;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

public class PanTadeuszTest {

    PanTadeusz panTadeusz = new PanTadeusz();

    @Test
    public void testLiczbaWystapienSlowaTadeuszWDowolnejFormie() {
        long actual = panTadeusz.liczbaWystapienSlowaTadeuszWDowolnejFormie();
        long expected = 176;
        assertEquals(expected, actual);
    }

    @Test
    public void testLiczbaUnikalnychWystapienSlowaTadeuszWDowolnejFormie() {
        long actual = panTadeusz.liczbaUnikalnychWystapienSlowaTadeuszWDowolnejFormie();
        long expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void testDrugeNajdluzszeSlowoWCalymUtworze() {
        String wynik = panTadeusz.drugeNajdluzszeSlowoWCalymUtworze();
        assertEquals(wynik, "Podkomorzy-Marszałek");
    }

    @Test
    public void testProcentWystapienSlowaTadeuszWPoszczegolnychFormachDo2mpp() throws IOException {
        long start = currentTimeMillis();
        Map<String, BigDecimal> wynik = panTadeusz.procentWystapienSlowaTadeuszWPoszczegolnychFormachDo2mpp();
        System.out.println("Execution time = " + (currentTimeMillis() - start) + "ms");
        assertEquals(wynik.get("Tadeuszka"), BigDecimal.valueOf(1.14).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszek"), BigDecimal.valueOf(0.57).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszem"), BigDecimal.valueOf(1.14).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszkowi"), BigDecimal.valueOf(0.57).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszowi"), BigDecimal.valueOf(2.86).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeusza"), BigDecimal.valueOf(26.86).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeusz"), BigDecimal.valueOf(60.57).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszku"), BigDecimal.valueOf(2.86).setScale(2, RoundingMode.HALF_UP));
        assertEquals(wynik.get("Tadeuszu"), BigDecimal.valueOf(3.43).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testSredniaLiczbaZnakowAlfabetycznychPrzypadajacychNaNiepustyWersWKsiedzeIV() throws IOException {
        int wynik = panTadeusz.sredniaLiczbaZnakowAlfabetycznychPrzypadajacychNaNiepustyWersWKsiedzeIV();
        int expected = 32;
        assertEquals(expected, wynik);
    }

    @Test
    public void testTytulILiczbaWersowKazdejZKsiagZZachowaniemKolejnosci() throws IOException {
        Map<String, Long> wynik = panTadeusz.tytulOrazLiczbaWersowKazdejKsiegiPrzyZachowaniuKolejnosci();
        assertEquals(wynik.get("Księga pierwsza"), Long.valueOf(985));
        assertEquals(wynik.get("Księga druga"), Long.valueOf(856));
        assertEquals(wynik.get("Księga trzecia"), Long.valueOf(794));
        assertEquals(wynik.get("Księga czwarta"), Long.valueOf(1005));
        assertEquals(wynik.get("Księga piąta"), Long.valueOf(914));
        assertEquals(wynik.get("Księga szósta"), Long.valueOf(622));
        assertEquals(wynik.get("Księga siódma"), Long.valueOf(559));
        assertEquals(wynik.get("Księga ósma"), Long.valueOf(808));
        assertEquals(wynik.get("Księga dziewiąta"), Long.valueOf(767));
        assertEquals(wynik.get("Księga dziesiąta"), Long.valueOf(911));
        assertEquals(wynik.get("Księga jedenasta"), Long.valueOf(686));
        assertEquals(wynik.get("Księga dwunasta"), Long.valueOf(1003));
    }



}