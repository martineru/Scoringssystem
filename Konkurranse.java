import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Konkurranse {
    String sted;
    LocalDate dato;
    ArrayList<Bueskytter> deltagere;
    Dommer hoveddommer;
    ArrayList<Dommer> meddommere;
    HashMap<Bueskytter, HashMap<String, Integer>> resultater;
    ArrayList<Scorekort> tommeScorekort;
    ArrayList<Scorekort> alleScorekortUtfylt;

    public Konkurranse(String sted, LocalDate dato) {
        this.sted = sted;
        this.dato = dato;
        this.deltagere = new ArrayList<>();
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
            this.deltagere.add(deltagere[j]);
            j--;
        }
    }

    public ArrayList<Scorekort> genererTommeScorekort(String[] gren) {
        HashMap<String, Integer> grener = new HashMap<>();
        for (int i = 0; i < gren.length; i++) {
            grener.put(gren[i], 0);
        }
        ArrayList<Scorekort> scorekort = new ArrayList<>();
        for (int i = 0; i < this.deltagere.size(); i++) {
            scorekort.add(new Scorekort(this.deltagere.get(i), this));
        }

        return scorekort;
    }

    public ArrayList<Bueskytter> hentDeltakere() {
        return this.deltagere;
    }

    public HashMap<Bueskytter, HashMap<String, Integer>> hentResultaterForKlasse(String klasse) {
        HashMap<Bueskytter, HashMap<String, Integer>> returResultater = new HashMap<>();
        for (int i = 0; i < deltagere.size(); i++) {
            if (deltagere.get(i).klasse().equals(klasse)) {
                returResultater.put(deltagere.get(i), deltagere.get(i).hentResultater());
            }
        }
        return returResultater;
    }

    // Ønsker å kunne legge inn ett eller flere resultater i klassen

}
