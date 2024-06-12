package team.onlapus.ua;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ActionsTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("testing Actions started");
    }
    @Test
    public void test_UAH_to_USD(){
        Assertions.assertEquals(2, Actions.convert(Main.UAH, 1, 80.94));
    }
    @Test
    public void test_USD_to_UAH(){
        Assertions.assertEquals(80.94, Actions.convert(Main.USD, Main.UAH, 2));
    }
    @Test
    public void test_PLN_to_UAH(){
        Assertions.assertEquals(100.17, Actions.convert(Main.PLN, Main.UAH, 10));
    }
    @Test
    public void test_GBR_to_SEK(){
        Assertions.assertEquals(67.24, Actions.convert(Main.GBR, Main.SEK, 5));
    }
    @Test
    public void test_EUR_to_USD(){
        Assertions.assertEquals(53.76, Actions.convert(Main.EUR, Main.USD, 50));
    }

    @Test
    public void test_getCurrencyValue_UAH() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.UAH, Actions.getCurrencyValue("UAH"));
    }
    @Test
    public void test_getCurrencyValue_PLN() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.PLN, Actions.getCurrencyValue("PLN"));
    }
    @Test
    public void test_getCurrencyValue_USD() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.USD, Actions.getCurrencyValue("USD"));
    }
    @Test
    public void test_getCurrencyValue_SEK() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.SEK, Actions.getCurrencyValue("SEK"));
    }
    @Test
    public void test_getCurrencyValue_EUR() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.EUR, Actions.getCurrencyValue("EUR"));
    }
    @Test
    public void test_getCurrencyValue_GBR() throws NoCurrencyFoundException {
        Assertions.assertEquals(Main.GBR, Actions.getCurrencyValue("GBR"));
    }
    @Test
    public void test_round(){
        Assertions.assertEquals(1.23, Actions.round(1.234, 2));
    }

    @AfterAll
    static void afterAll(){
        System.out.println("testing Actions ended");
    }


}
