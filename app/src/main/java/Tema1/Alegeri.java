package Tema1;


import java.util.ArrayList;
import java.util.List;

public class Alegeri {
    private String idAlegeri;
    private String numeAlegeri;
    private String statusAlegeri = "NEINCEPUT";
    List<Candidat> candidati;
    List<Circumscriptie> circumscriptii;
    List<Frauda> fraude;

    public Alegeri() {
    }

    // Clasa pentru alegeri
    public Alegeri(String idAlegeri, String numeAlegeri) {
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        candidati = new ArrayList<>();
        circumscriptii = new ArrayList<>();
        fraude = new ArrayList<>();
        System.out.println("S-au creat alegerile " + numeAlegeri);
    }

    public void pornireAlegeri(Alegeri alegere) {
        // Verificam daca alegerile au inceput
        if (statusAlegeri.equals("NEINCEPUT")) {
            // Marcam alegerile ca in curs
            statusAlegeri = "IN_CURS";
            System.out.println("Au pornit alegerile " + alegere.numeAlegeri);
        } else {
            // Afisam un mesaj de eroare daca alegerile au inceput deja
            System.out.println("EROARE: Alegerile deja au inceput");
        }
    }

    public String getNumeAlegeri() {

        return numeAlegeri;
    }

    public String getIdAlegeri() {

        return idAlegeri;
    }

    public String getStatusAlegeri() {

        return statusAlegeri;
    }

    public void setStatusAlegeri(String statusAlegeri) {
        this.statusAlegeri = statusAlegeri;
    }

}
