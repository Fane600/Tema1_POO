package Tema1;

import java.util.ArrayList;
import java.util.List;

public class Candidat {
    private String CNP;
    private int varsta;
    private String nume;
    private int numarVoturi = 0;
    List<Circumscriptie> circumscriptii;

    public Candidat() {}

    public Candidat(String CNP, int varsta, String nume) {
        this.CNP = CNP;
        this.varsta = varsta;
        this.nume = nume;
        System.out.println("S-a adaugat candidatul " + nume);
    }

    public String getCNP() {
        return CNP;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getNume() {
        return nume;
    }

    public int getNumarVoturi() {
        return numarVoturi;
    }

    public void setNumarVoturi(int numarVoturi) {
        this.numarVoturi = numarVoturi;
    }

}
