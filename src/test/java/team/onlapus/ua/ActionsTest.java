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
        Assertions.assertEquals(2, Actions.convert(40.47, 1, 80.94));
    }
    @Test
    public void test_USD_to_UAH(){
        Assertions.assertEquals(80.94, Actions.convert(1, 40.47, 2));
    }
    @Test
    public void test_PLN_to_UAH(){
        Assertions.assertEquals(100.17, Actions.convert(4.04, 40.47, 10));
    }
    @Test
    public void test_GBR_to_SEK(){
        Assertions.assertEquals(67.24, Actions.convert(0.78, 10.49, 5));
    }
    @Test
    public void test_EUR_to_USD(){
        Assertions.assertEquals(53.76, Actions.convert(0.93, 1, 50));
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
