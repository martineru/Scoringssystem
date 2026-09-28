import java.io.IOException;
import java.util.ArrayList;

class Demonstrasjon {
    public static void main(String[] args) throws IOException {
        Data databehandler = new Data();
        // Brukt copilot til å generere en .txt-fil med tilfeldige skyttere med fiktive
        // navn og adresser
        ArrayList<Bueskytter> skyttere = databehandler.lesBueskytterinfoFraFil("Skyttere.txt");

        for (int i = 0; i < skyttere.size(); i++) {
            System.out.println(skyttere.get(i));
        }
    }
}