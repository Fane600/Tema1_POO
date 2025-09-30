package Tema1;

// Clasa pentru frauda care va fi folosita la optiunea 15
public class Frauda {
    private String CNP;
    private String nume;
    private String numeCircumscriptie;

    public Frauda (String CNP, String nume, String numeCircumscriptie) {
        this.CNP = CNP;
        this.nume = nume;
        this.numeCircumscriptie = numeCircumscriptie;
    }

    public String getCNP() {
        return CNP;
    }

    public String getNume() {
        return nume;
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
}
