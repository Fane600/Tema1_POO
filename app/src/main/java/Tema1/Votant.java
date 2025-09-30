package Tema1;

public class Votant {
    private String CNP;
    private String nume;
    private int varsta;
    private String neindemanatic;
    private String frauda = "nu";

    public Votant() {}

    public Votant(String CNP, String nume, int varsta, String neindemanatic) {
        this.CNP = CNP;
        this.nume = nume;
        this.varsta = varsta;
        this.neindemanatic = neindemanatic;
        System.out.println("S-a adaugat votantul " + nume);
    }

    public String getCNP() {
        return CNP;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getNeindemanatic() {
        return neindemanatic;
    }

    public String getFrauda() {
        return frauda;
    }

    public void setFrauda(String frauda) {
        this.frauda = frauda;
    }
}

