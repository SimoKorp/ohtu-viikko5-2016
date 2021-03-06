
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    public void setUp() {
        joukko = new IntJoukko(4, 2);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    @Test
    public void negatiivisetArvot(){
        boolean test = false;
        try{
            joukko = new IntJoukko(-4, 4);            
        }catch (Exception e){
            test = true;
        }
        assertTrue(test);
        
        test = false;
        try{
            joukko = new IntJoukko(4, -4);            
        }catch (Exception e){
            test = true;
        }
        assertTrue(test);
    }
}
