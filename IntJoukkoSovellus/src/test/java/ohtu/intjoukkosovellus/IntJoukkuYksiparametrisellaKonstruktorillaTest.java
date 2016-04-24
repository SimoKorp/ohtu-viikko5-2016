package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {

    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(3);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }

    @Test
    public void negatiivinenParametri() {
        IntJoukko joukko1 = new IntJoukko(-1);
        assertEquals(0, joukko1.mahtavuus());
    }

    // perii kaikki testit luokasta IntJoukkoTest
}
