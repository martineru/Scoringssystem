import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Konkurranse {
    String sted;
    LocalDate dato;
    ArrayList<Bueskytter> deltagere;
    Dommer hoveddommer;
    ArrayList<Dommer> meddommere;
    ArrayList<String> grener;
    HashMap<Bueskytter, Integer> resultater;
    ArrayList<Scorekort> tommeScorekort;
    ArrayList<Scorekort> alleScorekortUtfylt;
    Data databehandler;

    public Konkurranse(String sted, LocalDate dato) {
        this.sted = sted;
        this.dato = dato;
        this.deltagere = new ArrayList<>();
        this.alleScorekortUtfylt = new ArrayList<>();
        this.databehandler = new Data();
    }

    /**
     * @return Returnerer en mer utskriftsvennlig dato med norskt format (String).
     */
    public String hentDato() {
        String datoFormattert = dato.getDayOfMonth() + "." + dato.getMonthValue() + "." + dato.getYear();
        return datoFormattert;
    }

    public void registrerDeltaker(Bueskytter deltager) {
        this.deltagere.add(deltager);
    }

    public void registrerDeltaker(Bueskytter[] deltagere) {
        int j = deltagere.length - 1;
        while (j > 0) {
            if (deltagere[j] != null) {
                this.deltagere.add(deltagere[j]);
            }
            j--;
        }
    }

    public void registrerDeltaker(ArrayList<Bueskytter> deltagere) {
        int j = deltagere.size() - 1;
        while (j >= 0) {
            if (deltagere.get(j) != null) {
                this.deltagere.add(deltagere.get(j));
            }
            j--;
        }
    }

    /**
     * @param gren array med alle grener i konkurransen
     * @return returnerer ArrayList med tomme scorekort for alle deltagere
     *         registrert i konkurransen
     */
    public ArrayList<Scorekort> genererTommeScorekort(String[] gren) {
        grener = new ArrayList<>();
        Collections.addAll(grener, gren);
        int[] nullScore = new int[gren.length];

        for (int i = 0; i < nullScore.length; i++) {
            nullScore[i] = 0;
        }
        ArrayList<Scorekort> scorekort = new ArrayList<>();
        if (this.deltagere.size() < 1) {
            System.out.println("Det er ingen registrerte deltagere i konkurransen. Ingen scorekort er skrevet.");
        } else {
            for (int i = 0; i < this.deltagere.size(); i++) {
                Scorekort nyttScorekort = new Scorekort(this.deltagere.get(i), this);
                nyttScorekort.leggInnScore(gren, nullScore);
                scorekort.add(nyttScorekort);
            }
            this.tommeScorekort = scorekort;
        }
        return scorekort;
    }

    public void skrivAlleScorekort(boolean tomme) {
        if (tomme) {
            for (int i = 0; i < tommeScorekort.size(); i++) {
                tommeScorekort.get(i).skrivScore(true);
                ;
            }
        } else {
            for (int i = 0; i < alleScorekortUtfylt.size(); i++) {
                alleScorekortUtfylt.get(i).skrivScore(false);
                ;
            }
        }
    }

    public ArrayList<Bueskytter> hentDeltakere() {
        return this.deltagere;
    }

    public HashMap<Bueskytter, Integer> hentResultatlisteForKlasse(String klasse) {
        HashMap<Bueskytter, Integer> returResultater = new HashMap<>();
        for (int i = 0; i < deltagere.size(); i++) {
            if (deltagere.get(i).klasse().equals(klasse)) {
                returResultater.put(deltagere.get(i), deltagere.get(i).hentTotalscore());
            }
        }
        return returResultater;
    }

    public void skrivResultatliste(String klasse) {
        Map<Bueskytter, Integer> resultater = hentResultatlisteForKlasse(klasse);

        List<Map.Entry<Bueskytter, Integer>> liste = new ArrayList<>(resultater.entrySet());
        liste.sort(Map.Entry.comparingByValue());
        Collections.reverse(liste);
        databehandler.skrivResultatliste(liste, klasse);
    }

    public void registrerResultat(Bueskytter skytter) {
        if (this.resultater == null) {
            this.resultater = new HashMap<>();
        }
        this.resultater.put(skytter, skytter.hentTotalscore());
    }

    public void registrerResultat(ArrayList<Bueskytter> skyttere) {
        for (int i = 0; i < skyttere.size(); i++) {
            registrerResultat(skyttere.get(i));
        }
    }

}
