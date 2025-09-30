package Tema1;

import java.util.ArrayList;
import java.util.List;

public class Circumscriptie {
    private String numeCircumscriptie;
    private String regiune;
    List<Votant> votanti;
    List<Candidat> candidati;

    public Circumscriptie(){}

    public Circumscriptie(String numeCircumscriptie, String regiune) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
        votanti = new ArrayList<>();
        candidati = new ArrayList<>();
        System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public String getRegiune() {
        return regiune;
    }
}
