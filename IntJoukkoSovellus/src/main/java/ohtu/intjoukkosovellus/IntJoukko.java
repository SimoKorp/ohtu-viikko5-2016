package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (ljono.length <= alkioidenLkm) {
                kasvataKokoa();
            }
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int poistettava = etsiSijainti(luku, -1);
        if (poistettava != -1) {
            siirraVasemmalle(poistettava);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void siirraVasemmalle(int poistettava) {
        for (int i = poistettava; i < alkioidenLkm - 1; i++) {
            int apu = ljono[i];
            ljono[i] = ljono[i + 1];
            ljono[i + 1] = apu;
        }
    }

    private int etsiSijainti(int luku, int kohta) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                break;
            }
        }
        return kohta;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < alkioidenLkm; i++) {
            str.append(ljono[i]);
            if (alkioidenLkm - 1 > i) {
                str.append(", ");
            }
        }
        str.append("}");
        return str.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaJoukkoon(aTaulu, x);
        lisaaJoukkoon(bTaulu, x);
        return x;
    }

    private static void lisaaJoukkoon(int[] Taulu, IntJoukko x) {
        for (int i = 0; i < Taulu.length; i++) {
            if(!x.kuuluu(Taulu[i])) x.lisaa(Taulu[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        teeLeikkaus(aTaulu, bTaulu, y);
        return y;

    }

    private static void teeLeikkaus(int[] aTaulu, int[] bTaulu, IntJoukko y) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        IntJoukko c = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaJoukkoon(aTaulu, z);
        lisaaJoukkoon(bTaulu, z);
        teeLeikkaus(aTaulu, bTaulu, c);
        poistaJoukosta(c.toIntArray(), z);
        return z;
    }

    private static void poistaJoukosta(int[] bTaulu, IntJoukko z) {
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
    }

    private void kasvataKokoa() {
        int vanha[] = ljono;
        ljono = new int[this.ljono.length + OLETUSKASVATUS];
        kopioiTaulukko(vanha, ljono);
    }

}
