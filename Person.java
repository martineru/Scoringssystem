import java.time.LocalDate;

public abstract class Person {
    String navn;
    LocalDate fodselsdato;
    String adresse;
    String kjonn;

    public Person(String navn, LocalDate fodselsdato, String adresse, String kjonn) {
        this.navn = navn;
        this.adresse = adresse;
        this.fodselsdato = fodselsdato;
        this.kjonn = kjonn;
    }

    public void byttAdresse(String adresseNy) {
        this.adresse = adresseNy;
    }

    /**
     * @return Hvor gammel personen blir i år, uavhengig av når på året personen er
     *         født.
     */
    public int aldersklasse() {
        LocalDate iDag = LocalDate.now();
        int alder = iDag.getYear() - this.fodselsdato.getYear();
        return alder;
    }

    public String hentNavn() {
        return this.navn;
    }

    public String hentKjonn() {
        return this.kjonn;
    }
}
