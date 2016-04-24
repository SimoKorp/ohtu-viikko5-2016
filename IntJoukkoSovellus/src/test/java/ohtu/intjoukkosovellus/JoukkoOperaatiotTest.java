package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1, 2);
        IntJoukko toka = teeJoukko(3, 4);

        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }

        return joukko;
    }

    @Test
    public void leikkaus() {
        IntJoukko joukko1 = new IntJoukko();
        joukko1.lisaa(10);
        joukko1.lisaa(3);
        IntJoukko joukko2 = new IntJoukko();
        joukko2.lisaa(10);
        IntJoukko joukko3 = new IntJoukko();
        joukko3.lisaa(10);
        assertEquals(joukko3.toString(), IntJoukko.leikkaus(joukko1, joukko2).toString());
    }

    @Test
    public void erotus() {
        IntJoukko joukko1 = new IntJoukko();
        joukko1.lisaa(10);
        joukko1.lisaa(3);
        IntJoukko joukko2 = new IntJoukko();
        joukko2.lisaa(10);
        IntJoukko joukko3 = new IntJoukko();
        joukko3.lisaa(3);
        assertEquals(joukko3.toString(), IntJoukko.erotus(joukko1, joukko2).toString());
    }
}
