import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

class Scorekort {
    private Bueskytter skytter;
    private Konkurranse konkurranse;
    private HashMap<String, Integer> score;

    public Scorekort(Bueskytter skytter, Konkurranse konkurranse) {
        this.skytter = skytter;
        this.konkurranse = konkurranse;
        if (this.skytter.hentResultater() != null) {
            score = this.skytter.hentResultater();
        }
    }

    public void leggInnScore(String gren, int poengsum) {
        // For å kunne oppdatere sum underveis eller hvis feil. Ny score vil overskrive
        // gammel. Ellers legges ny gren inn.
        if (score.containsKey(gren)) {
            score.replace(gren, poengsum);
        } else {
            score.put(gren, poengsum);
        }
    }

    public HashMap<String, Integer> hentScore() {
        return score;
    }

    public int totalScore() {
        int s = 0;
        for (int i = 0; i < score.size(); i++) {
            s += (Integer) (score.values().toArray()[i]);
        }
        return s;
    }

    public void skrivScore(Boolean tommeKort) {
        String skytternavn = skytter.hentNavn();
        try {
            // Navngivning av fil tar ikke hensyn til om noen har samme navn.
            String filnavn = "Scorekort/" + skytternavn + ".md";
            if (tommeKort) {
                filnavn = "TommeScorekort/" + skytternavn + ".md";
            }
            filnavn = filnavn.replaceAll("\\s", "");
            filnavn = filnavn.replaceAll("å", "aa");
            filnavn = filnavn.replaceAll("æ", "ae");
            filnavn = filnavn.replaceAll("ø", "oe");

            OutputStream fil = new FileOutputStream(filnavn);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fil, "UTF-8"));
            pw.println("# Scorekort");
            pw.println("**Dato:** " + konkurranse.hentDato() + "\n");
            pw.println("**Sted:** " + konkurranse.sted + "\n");
            pw.println("**Skytter:** " + skytternavn + "\n");
            pw.println("**Klasse:** " + skytter.klasse() + "\n");
            pw.println("---");
            pw.println("|Gren|Poengsum|");
            pw.println("|----------|----------|");
            if (tommeKort) {
                for (int i = 0; i < score.size(); i++) {
                    pw.println("|" + score.keySet().toArray()[i] + "|  |");
                    pw.println("| **Total**|  |");

                }
            } else {
                for (int i = 0; i < score.size(); i++) {
                    pw.println("|" + score.keySet().toArray()[i] + "|" + score.values().toArray()[i] + "|");
                    pw.println("| **Total**|**" + totalScore() + "**|");
                }
            }

            pw.close();
            System.out.println("Scorekort skrevet for " + skytternavn);
        } catch (IOException e) {
            System.out.println("Klarer ikke skrive scorekort til fil for" + skytternavn);
        }
    }
}
