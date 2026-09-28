import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
}
