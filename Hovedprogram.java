import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

class Hovedprogram {
    public static void main(String[] args) throws IOException {
        BufferedReader tastatur = new BufferedReader(new InputStreamReader(System.in));
        Data databehandler = new Data();

        // Brukt copilot til å generere en .txt-fil med tilfeldige skyttere med fiktive
        // navn og adresser: Skyttere.txt og med fiktive resultater:
        // ResultaterSkyttere.txt (På formatet Navn-Historisk-Jakt-Langhold)

        // Ikke tatt hensyn til skrivefeil fra bruker
        System.out.println(
                "Velkommen til 'Mitt skytesystem'! \nDu kan nå legge inn en konkurranse. Hvor er konkurransen? ");
        String sted = tastatur.readLine();
        System.out.println("\nEr datoen i dag? Skriv 'Y'. Ellers skriv datoen på formatet YYYY-MM-DD ");
        String dato = tastatur.readLine();
        Konkurranse konkurranse;
        if (dato.equals("Y")) {
            konkurranse = new Konkurranse(sted, LocalDate.now());
        } else {
            konkurranse = new Konkurranse(sted, LocalDate.parse(dato));
        }
        System.out
                .println("En konkurranse i " + konkurranse.sted + " den " + konkurranse.hentDato()
                        + " er registrert.\n");

        System.out.println("Hvis du vil legge til deltagere i konkurransen, skriv filnavn (Eks. 'Skyttere.txt')");
        String filnavn = tastatur.readLine();
        ArrayList<Bueskytter> skyttere = databehandler.lesBueskytterinfoFraFil(filnavn);
        konkurranse.registrerDeltaker(skyttere);
        System.out.println(konkurranse.deltagere.size() + " skyttere er lagt til på konkurransen.");

        System.out.println("\nHvilke grener skal være i konkurransen? Skriv grenene, adskilt med komma. ");
        String grenerInput = tastatur.readLine();
        String[] grener = grenerInput.split(",");

        System.out.println("\nVil du generere tomme scorekort? skriv 'Y'");
        String svar = tastatur.readLine();
        if (svar.equals("Y")) {
            konkurranse.genererTommeScorekort(grener);
            konkurranse.skrivAlleScorekort(true);
        }

        System.out.println(
                "\nVil du registrere resultater fra fil? Skriv filnavn (Eks. Resultater.txt), ellers skriv 'N'");
        svar = tastatur.readLine();
        if (!svar.equals("N")) {
            databehandler.lesResultatFraFil(svar, konkurranse);
        }

        System.out.println("\nVil du skrive scorekort for alle skyttere? Skriv 'Y'");
        svar = tastatur.readLine();
        if (svar.equals("Y")) {
            konkurranse.skrivAlleScorekort(false);
        }

    }
}