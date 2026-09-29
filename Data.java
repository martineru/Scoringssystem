import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

class Data {
    public ArrayList<Bueskytter> lesBueskytterinfoFraFil(String filnavn) throws IOException {
        File personfil = new File(filnavn);
        ArrayList<Bueskytter> skyttere = new ArrayList<>();
        try (BufferedReader filleser = new BufferedReader(
                new InputStreamReader(new FileInputStream(personfil), "UTF-8"))) {
            String linje = filleser.readLine();
            while (linje != null) {
                String info = linje;
                String[] infoDelt = info.split(":");
                Bueskytter nySkytter = new Bueskytter(infoDelt[0].trim(), LocalDate.parse(infoDelt[1].trim()),
                        infoDelt[3].trim(),
                        infoDelt[2].trim());
                skyttere.add(nySkytter);
                linje = filleser.readLine();
            }
        } catch (IOException e) {
            System.out.println("Klarer ikke lese filen med navn " + filnavn);
        }

        return skyttere;

    }

    public void lesResultatFraFil(String filnavn, Konkurranse konkurranse)
            throws IOException {
        File resultatfil = new File(filnavn);
        ArrayList<String> grener = konkurranse.grener;
        try (BufferedReader filleser = new BufferedReader(
                new InputStreamReader(new FileInputStream(resultatfil), "UTF-8"))) {
            String linje = filleser.readLine();
            String[] info = linje.split(":");

            // Ikke optimalisert kode
            while (linje != null) {
                for (int i = 0; i < konkurranse.deltagere.size(); i++) {
                    HashMap<String, Integer> resultater = new HashMap<>();
                    resultater.put(grener.get(0), Integer.parseInt(info[1]));
                    resultater.put(grener.get(1), Integer.parseInt(info[2]));
                    resultater.put(grener.get(2), Integer.parseInt(info[3]));

                    if (info[0].trim().equals(konkurranse.deltagere.get(i).navn)) {
                        konkurranse.deltagere.get(i).registrerResultater(resultater);
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Klarer ikke lese filen med navn " + filnavn);
        }
    }
}
