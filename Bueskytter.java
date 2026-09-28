import java.time.LocalDate;
import java.util.HashMap;

class Bueskytter extends Person implements Konkurranseskytter {
    static int skytternummerTeller = 1000; // Lager unikt skytternummer for alle skyttere
    private int skytternummer;
    private HashMap<String, Integer> resultater; // Resultater lagres i en HashMap, med skytegren som nøkkel og sum
                                                 // oppnådd som verdi
    private Scorekort scorekort;

    {
        ++skytternummerTeller;
    }

    public Bueskytter(String navn, LocalDate fodselsdato, String adresse, String kjonn) {
        super(navn, fodselsdato, adresse, kjonn);
        this.skytternummer = skytternummerTeller;
        this.resultater = new HashMap<>();
    }

    @Override
    public void registrerResultater(HashMap<String, Integer> resultat) {
        resultater = resultat;
    }

    @Override
    public HashMap<String, Integer> hentResultater() {
        return this.resultater;
    }

    public void registrertScorekort(Scorekort sc) {
        this.scorekort = sc;
    }

    public int hentTotalscore() {
        return this.scorekort.totalScore();
    }

    public String klasse() {
        // Antar alle deltar i klassen de strengt tatt tilhører
        String klasse;
        klasse = this.kjonn + "r";
        if (this.aldersklasse() >= 16) {
            klasse += " senior";
        } else if (this.aldersklasse() >= 13) {
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