import java.time.LocalDate;
import java.util.HashMap;

class Bueskytter extends Person implements Konkurranseskytter {
    static int skytternummerTeller = 1000; // Lager unikt skytternummer for alle skyttere
    private int skytternummer;
    private HashMap<String, Integer> resultater; // Resultater lagres i en HashMap, med skytegren som nøkkel og sum
                                                 // oppnådd som verdi

    {
        ++skytternummerTeller;
    }

    public Bueskytter(String navn, LocalDate fodselsdato, String adresse, String kjonn) {
        super(navn, fodselsdato, adresse, kjonn);
        this.skytternummer = skytternummerTeller;
    }

    @Override
    public void registrerResultater(HashMap<String, Integer> resultat) {
        resultater = resultat;
    }

    @Override
    public HashMap<String, Integer> hentResultater() {
        return this.resultater;
    }

    public String klasse() {
        // Antar alle deltar i klassen de strengt tatt tilhører
        String klasse;
        klasse = this.kjonn + "r";
        if (this.alderklasse() >= 16) {
            klasse += " senior";
        } else if (this.alderklasse() >= 13) {
            klasse = "Junior";
        } else {
            klasse = "Minijunior";
        }
        return klasse;
    }

    public int hentSkytternummer() {
        return this.skytternummer;
    }

}