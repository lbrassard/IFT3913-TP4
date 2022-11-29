package ua.karatnyk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.OfflineJsonWorker;

public class CurrencyConvertorTest {
    private CurrencyConversion CC;

    @Before
    public void init(){
        OfflineJsonWorker manager = new OfflineJsonWorker();
        CC = manager.parser();
    }


    //boite noire

    //tests sur les valeurs frontieres
    @Test
    public void USDtoCADUnder(){
        try {
            CurrencyConvertor.convert(-1, "USD", "CAD", CC);
            fail("le montant n'est pas dans [0,10000] et on \"convert\" quand meme");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void USDtoCADInLim(){
        try {
            CurrencyConvertor.convert(100, "USD", "CAD", CC);
        } catch (Exception e) {
            fail("exeption unexpected");
        }
    }
    @Test
    public void USDtoGBPOver(){
        try {
            CurrencyConvertor.convert(10001, "USD", "GBP", CC);
            fail("le montant n'est pas dans [0,10000] et on \"convert\" quand meme");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void USDtoEURLowLim(){
        try {
            CurrencyConvertor.convert(0, "USD", "EUR", CC);
        } catch (Exception e) {
            fail("exeption unexpected");
        }
    }
    @Test
    public void USDtoCHFHighLim(){
        try {
            CurrencyConvertor.convert(10000, "USD", "CHF", CC);
        } catch (Exception e) {
            fail("exeption unexpected");
        }
    }

    //tests sur les devises
    @Test
    public void USDtoLVL(){
        try {
            CurrencyConvertor.convert(5000, "USD", "CHF", CC);
            fail("devises ne devraient pas etre accepte");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void MXNtoUSD(){
        try {
            CurrencyConvertor.convert(5000, "USD", "CHF", CC);
            fail("devises ne devraient pas etre accepte");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void UGXtoZAR(){
        try {
            CurrencyConvertor.convert(5000, "USD", "CHF", CC);
            fail("devises ne devraient pas etre accepte");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void UDSXtoCADX(){
        try {
            CurrencyConvertor.convert(5000, "USDx", "CADX", CC);
            fail("devises ne devraient pas etre accepte");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    //boite blanche

    //couverture des intructions
    @Test
    public void intoIF(){
        try {
            CurrencyConvertor.convert(5000, "", "", CC);
            fail("error expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void notIntoIF(){
        try {
            CurrencyConvertor.convert(5000, "USD", "CAD", CC);

        } catch (Exception e) {
            fail("exception unexpected");
        }
    }

    //couverture des arcs du graphe de flot de contrôle
    //serait la meme chose que la couverture des instructions

    //Critère de couverture des chemins indépendants du graphe de flot de contrôle
    //aussi la meme chose que la couverture des instructions car pas de boucle.

    //couverture des conditions
    @Test
    public void trueTrue(){
        try {
            CurrencyConvertor.convert(5000, "USD", "CAD", CC);
            fail("error expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void trueFalse(){
        try {
            CurrencyConvertor.convert(5000, "USD", "", CC);
            fail("error expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void falseTrue(){
        try {
            CurrencyConvertor.convert(5000, "", "USD", CC);
            fail("error expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    @Test
    public void falseFalse(){
        try {
            CurrencyConvertor.convert(5000, "", "", CC);
            fail("error expected");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
    //couverture des i-chemins
    //pas de boucle donc rien de plus a tester
}
