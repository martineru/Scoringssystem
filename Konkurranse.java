import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Konkurranse {
    String sted;
    LocalDate dato;
    ArrayList<Bueskytter> deltagere;
    Dommer hoveddommer;
    ArrayList<Dommer> meddommere;
    HashMap<Bueskytter, Integer> resultater;
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
            if (deltagere[j] != null) {
                this.deltagere.add(deltagere[j]);
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
        HashMap<String, Integer> grener = new HashMap<>();
        for (int i = 0; i < gren.length; i++) {
            grener.put(gren[i], 0);
        }
        ArrayList<Scorekort> scorekort = new ArrayList<>();
        if (this.deltagere.size() < 1) {
            System.out.println("Det er ingen registrerte deltagere i konkurransen. Ingen scorekort er skrevet.");
        } else {
            for (int i = 0; i < this.deltagere.size(); i++) {
                scorekort.add(new Scorekort(this.deltagere.get(i), this));
            }
            this.tommeScorekort = scorekort;
        }
        return scorekort;
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

    // Ønsker å kunne legge inn ett eller flere resultater i klassen

}
