package Tema1;

import java.util.ArrayList;
import java.util.List;

// Clasa pentru regiune care va fi folosita la optiunea 14
public class Regiune {
    private String numeRegiune;
    private int numarVoturi = 0;
    List<Candidat> candidati;

    public Regiune(){}

    public Regiune(String numeRegiune, int numarVoturi) {
        this.numeRegiune = numeRegiune;
        this.numarVoturi = numarVoturi;
        candidati = new ArrayList<>();
    }

    public String getNumeRegiune() {
        return numeRegiune;
    }

    public int getNumarVoturi() {
        return numarVoturi;
    }

    public void setNumarVoturi(int numarVoturi) {
        this.numarVoturi = numarVoturi;
    }

}
