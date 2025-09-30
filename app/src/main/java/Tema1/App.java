package Tema1;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Am creat un array list in care adaugam toate alegerile pe parcurs
        ArrayList<Alegeri> alegeri = new ArrayList<>();

        // O bucla infinita care se va opri doar cand se va introduce 18, adica exit
        while(true) {
            // Variabila optiune care va fi citita din input
            int optiune = scanner.nextInt();
            // Sarim peste endline
            scanner.nextLine();
            if (optiune == 0) {
                boolean ok = true;

                // Citim id-ul si numele alegerilor din input
                String idAlegeri = scanner.next();
                String numeAlegeri = scanner.nextLine().trim();

                // Verificam daca exista deja alegeri cu acelasi id
                for (int i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        System.out.println("EROARE: Deja exista alegeri cu id " + idAlegeri);
                        ok = false;
                    }
                }

                // Daca nu exista alegeri cu acelasi id, le adaugam in array list
                if (ok == true) {
                    alegeri.add(new Alegeri(idAlegeri, numeAlegeri));
                }

            } else if (optiune == 1) {

                // Citim id-ul alegerilor
                String idAlegeri = scanner.next();
                Alegeri alegere = new Alegeri();

                // Cautam alegerile cu id-ul respectiv
                int i;
                for(i = 0; i < alegeri.size(); i++) {
                    if(alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        alegere = alegeri.get(i);
                        break;
                    }
                }

                // Daca nu exista alegeri cu id-ul respectiv, afisam un mesajul de eroare
                // altfel pornim alegerile
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                } else {
                    alegere.pornireAlegeri(alegere);
                }

            } else if(optiune == 2) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();
                String regiune = scanner.nextLine().trim();

                // Verificam daca exista alegeri cu id-ul respectiv
                int i,j;
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                }

                // Verificam daca exista deja o circumscriptie cu acelasi nume
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                        return;
                    }
                }

                // Verificam daca alegerile sunt IN_CURS, si adaugam circumscriptia in caz afirmativ
                for (j = 0; j < alegeri.size(); j++) {
                    if (alegeri.get(j).getIdAlegeri().equals(idAlegeri)) {
                        if(alegeri.get(j).getStatusAlegeri().equals("NEINCEPUT")) {
                            System.out.println("EROARE: Nu este perioada de votare");
                        } else {
                            Circumscriptie circumscriptie = new Circumscriptie(numeCircumscriptie, regiune);
                            alegeri.get(i).circumscriptii.add(circumscriptie);
                            break;
                        }
                    }
                }

            } else if (optiune == 3) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();

                // Cautam alegerile cu id-ul respectiv
                int i , j, temp = -1;
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                }

                // Verificam daca alegerile sunt in curs
                for (j = 0; j < alegeri.size(); j++) {
                    if (alegeri.get(j).getIdAlegeri().equals(idAlegeri)) {
                        if (alegeri.get(j).getStatusAlegeri().equals("NEINCEPUT")) {
                            System.out.println("EROARE: Nu este perioada de votare");
                            return;
                        }
                    }
                }

                // Verificam daca exista o circumscriptie cu numele respectiv
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        temp = j;
                        break;
                    }
                }

                // Daca nu exista o circumscriptie cu numele respectiv, afisam un mesaj de eroare
                // altfel stergem circumscriptia
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                } else {
                    alegeri.get(i).circumscriptii.remove(temp);
                    System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
                    break;
                }
            } else if (optiune == 4) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String CNP = scanner.next();
                int varsta = scanner.nextInt();
                String nume = scanner.nextLine().trim();

                // Cautam alegerile cu id-ul respectiv
                int i, j;
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Verificam daca exista alegeri cu id-ul respectiv
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else {
                    // Verificam daca alegerile sunt in curs
                    if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                        System.out.println("EROARE: Nu este perioada de votare");
                        return;
                    }
                }

                if (CNP.length() != 13) {
                    System.out.println("EROARE: CNP invalid");
                    return;
                }

                if (varsta < 35) {
                    System.out.println("EROARE: Varsta invalida");
                    return;
                }

                // Verificam daca exista un candidat cu acelasi CNP
                for (j = 0; j < alegeri.get(i).candidati.size(); j++) {
                    if (alegeri.get(i).candidati.get(j).getCNP().equals(CNP)) {
                        System.out.println("EROARE: Candidatul " + alegeri.get(i).candidati.get(j).getNume() + " are deja acelasi CNP");
                        return;
                    }
                }

                // Adaugam candidatul in lista de candidati
                Candidat candidat = new Candidat(CNP, varsta, nume);
                alegeri.get(i).candidati.add(candidat);

            } else if (optiune == 5) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String CNP = scanner.next().trim();

                // Cautam alegerile cu id-ul respectiv
                int i, j, temp = -1;
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Verificam daca exista alegeri cu id-ul respectiv si daca au inceput
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }

                // Verificam daca exista un candidat cu acelasi CNP
                for (j = 0; j < alegeri.get(i).candidati.size(); j++) {
                    if (alegeri.get(i).candidati.get(j).getCNP().equals(CNP)) {
                        temp = j;
                        break;
                    }
                }

                // Daca nu exista un candidat cu acelasi CNP, afisam un mesaj de eroare
                // altfel stergem candidatul
                if (j == alegeri.get(i).candidati.size()) {
                    System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
                    return;
                } else {
                    System.out.println("S-a sters candidatul " + alegeri.get(i).candidati.get(temp).getNume());
                    alegeri.get(i).candidati.remove(temp);
                }
            } else if (optiune == 6) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();
                String CNP = scanner.next().trim();
                int varsta = scanner.nextInt();
                String neindemanatic = scanner.next().trim();
                String nume = scanner.nextLine().trim();

                int i, j;

                if (CNP.length() != 13) {
                    System.out.println("EROARE: CNP invalid");
                    return;
                }

                if (varsta < 18) {
                    System.out.println("EROARE: Varsta invalida");
                    return;
                }

                // Cautam alegerile cu id-ul respectiv
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Verificam daca exista alegeri cu id-ul respectiv si daca au inceput
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                }

                // Verificam daca exista o circumscriptie cu numele respectiv
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        break;
                    }
                }

                // Daca nu exista o circumscriptie cu numele respectiv, afisam un mesaj de eroare
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                }

                // Verificam daca exista un votant cu acelasi CNP
                for (int k = 0; k < alegeri.get(i).circumscriptii.get(j).votanti.size() ; k++) {
                    if (alegeri.get(i).circumscriptii.get(j).votanti.get(k).getCNP().equals(CNP)) {
                        System.out.println("EROARE: Votantul " + alegeri.get(i).circumscriptii.get(j).votanti.get(k).getNume() + " are deja acelasi CNP");
                        return;
                    }
                }

                // Adaugam votantul in lista de votanti
                Votant votant = new Votant(CNP, nume, varsta, neindemanatic);
                alegeri.get(i).circumscriptii.get(j).votanti.add(votant);


            } else if (optiune == 7) {
                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i;

                // Cautam alegerile cu id-ul respectiv
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Daca nu exista alegeri cu id-ul sau nu au inceput, afisam un mesaj de eroare
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                    return;
                }

                // Daca nu exista candidati, afisam un mesaj de eroare
                if(alegeri.get(i).candidati.size() == 0) {
                    System.out.println("GOL: Nu sunt candidati");
                    return;
                } else {
                    // Altfel, afisam candidatii
                    System.out.println("Candidatii:");
                    for (int j = 0; j < alegeri.get(i).candidati.size(); j++) {
                        System.out.println(alegeri.get(i).candidati.get(j).getNume() + " " + alegeri.get(i).candidati.get(j).getCNP() + " " + alegeri.get(i).candidati.get(j).getVarsta());
                    }
                }

            } else if (optiune == 8) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();

                int i, j;

                // Cautam alegerile cu id-ul respectiv
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Daca nu exista alegeri cu id-ul sau nu au inceput, afisam un mesaj de eroare
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                    return;
                }

                // Verificam daca exista o circumscriptie cu numele respectiv
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        break;
                    }
                }

                // Daca nu exista o circumscriptie cu numele respectiv, afisam un mesaj de eroare
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                }

                // Daca nu exista votanti, afisam un mesaj de eroare
                if(alegeri.get(i).circumscriptii.get(j).votanti.size() == 0) {
                    System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
                    return;
                } else {
                    // Altfel, afisam votantii
                    System.out.println("Votantii din " + numeCircumscriptie + ":");
                    for (int k = 0; k < alegeri.get(i).circumscriptii.get(j).votanti.size(); k++) {
                        System.out.println(alegeri.get(i).circumscriptii.get(j).votanti.get(k).getNume() + " " + alegeri.get(i).circumscriptii.get(j).votanti.get(k).getCNP() + " " + alegeri.get(i).circumscriptii.get(j).votanti.get(k).getVarsta());
                    }
                }

            } else if (optiune == 9) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();
                String CNP_votant = scanner.next().trim();
                String CNP_candidat = scanner.next().trim();
                String numeVotant = "";

                int i, j, k, l;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Inca nu au inceput alegerile");
                    return;
                }

                // Verificarea pentru circumscriptii
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        break;
                    }
                }
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                }

                boolean ok = false;

                // Verificam daca exista un votant cu CNP-ul respectiv in toate circumscriptiile
                for (int m = 0; m < alegeri.get(i).circumscriptii.size(); m++) {
                    for (k = 0; k < alegeri.get(i).circumscriptii.get(m).votanti.size(); k++) {
                        if (alegeri.get(i).circumscriptii.get(m).votanti.get(k).getCNP().equals(CNP_votant)) {
                            ok = true;
                            numeVotant = alegeri.get(i).circumscriptii.get(m).votanti.get(k).getNume();
                            break;
                        }
                    }
                }
                if (ok == false) {
                    System.out.println("EROARE: Nu exista un votant cu CNP-ul " + CNP_votant);
                    return;
                }

                boolean ok2 = false;

                // Verificam daca exista candidatul in circumscriptie
                for (int o = 0; o < alegeri.get(i).circumscriptii.get(j).candidati.size(); o++) {
                    if (alegeri.get(i).circumscriptii.get(j).candidati.get(o).getCNP().equals(CNP_candidat)) {
                        ok2 = true;
                        break;
                    }
                }

                // Daca nu exista candidatul in circumscriptie, il adaugam
                if (ok2 == false) {
                    for (l = 0; l < alegeri.get(i).candidati.size(); l++) {
                        if (alegeri.get(i).candidati.get(l).getCNP().equals(CNP_candidat)) {
                            Candidat candidat = new Candidat(alegeri.get(i).candidati.get(l).getCNP(), alegeri.get(i).candidati.get(l).getVarsta(), alegeri.get(i).candidati.get(l).getNume());
                            alegeri.get(i).circumscriptii.get(j).candidati.add(candidat);
                            break;
                        }
                    }

                    // Daca nu exista niciun candidat cu acel CNP in alegeri, afisam un mesaj de eroare
                    if (l == alegeri.get(i).candidati.size()) {
                        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP_candidat);
                        return;
                    }
                } else {
                    // Daca ok2 a fost true, cautam pozitia candidatului in lista de candidati
                    for (l = 0; l < alegeri.get(i).candidati.size(); l++) {
                        if (alegeri.get(i).candidati.get(l).getCNP().equals(CNP_candidat)) {
                            break;
                        }
                    }
                }

                // Cautam pozitia votantului in lista de votanti
                for (k = 0; k < alegeri.get(i).circumscriptii.get(j).votanti.size(); k++) {
                    if (alegeri.get(i).circumscriptii.get(j).votanti.get(k).getCNP().equals(CNP_votant)) {
                        break;
                    }
                }
                // Frauda cand votantul nu este in circumscriptie
                if (k == alegeri.get(i).circumscriptii.get(j).votanti.size()) {
                    System.out.println("FRAUDA: Votantul cu CNP-ul " + CNP_votant + " a incercat sa comita o frauda. Votul a fost anulat");
                    Frauda frauda = new Frauda(CNP_votant, numeVotant, numeCircumscriptie);
                    alegeri.get(i).fraude.add(frauda);
                } else {
                    // Frauda cand votantul incearca sa voteze de mai multe ori
                    if (alegeri.get(i).circumscriptii.get(j).votanti.get(k).getFrauda().equals("da")) {
                        System.out.println("FRAUDA: Votantul cu CNP-ul " + alegeri.get(i).circumscriptii.get(j).votanti.get(k).getCNP() + " a incercat sa comita o frauda. Votul a fost anulat");
                        Frauda frauda = new Frauda(CNP_votant, numeVotant, numeCircumscriptie);
                        alegeri.get(i).fraude.add(frauda);
                    } else {
                        // Contorizarea voturilor. Se adauga daca neindemanaticul este "nu", altfel doar afisam mesajul de succes
                        // fara a adauga votul si setam frauda la "da" NOTE: Frauda putea sa fie un boolean
                        if (alegeri.get(i).circumscriptii.get(j).votanti.get(k).getNeindemanatic().equals("da")) {
                            System.out.println(alegeri.get(i).circumscriptii.get(j).votanti.get(k).getNume() + " a votat pentru " + alegeri.get(i).candidati.get(l).getNume());
                            alegeri.get(i).circumscriptii.get(j).votanti.get(k).setFrauda("da");
                        } else {
                            System.out.println(alegeri.get(i).circumscriptii.get(j).votanti.get(k).getNume() + " a votat pentru " + alegeri.get(i).candidati.get(l).getNume());
                            alegeri.get(i).circumscriptii.get(j).votanti.get(k).setFrauda("da");
                            int m;
                            for (m = 0; m < alegeri.get(i).circumscriptii.get(j).candidati.size(); m++) {
                                if (alegeri.get(i).circumscriptii.get(j).candidati.get(m).getCNP().equals(CNP_candidat)) {
                                    break;
                                }
                            }
                            alegeri.get(i).candidati.get(l).setNumarVoturi(alegeri.get(i).candidati.get(l).getNumarVoturi() + 1);
                            alegeri.get(i).circumscriptii.get(j).candidati.get(m).setNumarVoturi(alegeri.get(i).circumscriptii.get(j).candidati.get(m).getNumarVoturi() + 1);
                        }
                    }
                }

            } else if (optiune == 10) {

                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i;

                // Cautam alegerile cu id-ul respectiv
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Daca nu exista alegeri cu id-ul sau nu au inceput, afisam un mesaj de eroare
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("NEINCEPUT")) {
                    System.out.println("EROARE: Nu este perioada de votare");
                    return;
                } else {
                    // Daca alegerile sunt in curs, le marcam ca terminat
                    alegeri.get(i).setStatusAlegeri("TERMINAT");
                    System.out.println("S-au terminat alegerile " + alegeri.get(i).getNumeAlegeri());
                }
            } else if (optiune == 11) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();

                int i, j;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("IN_CURS")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }

                // Verificarea pentru circumscriptii
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        break;
                    }
                }
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                }

                // Daca nu exista candidati in lista de candidati din circumscriptii, afisam un mesaj de eroare,
                // deoarece inseamna ca nimeni nu a votat in circumscriptie
                if (alegeri.get(i).circumscriptii.get(j).candidati.size() == 0) {
                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie);
                    return;
                } else {
                    // Sortarea candidatilor in ordine descrescatoare dupa numarul de voturi sau dupa CNP in caz de egalitate
                    Collections.sort(alegeri.get(i).circumscriptii.get(j).candidati, (c1, c2) -> {
                        int cmp = Integer.compare(c2.getNumarVoturi(), c1.getNumarVoturi());
                        if (cmp == 0) {
                            return c2.getCNP().compareTo(c1.getCNP());
                        }
                        return cmp;
                    });
                    // Afisarea candidatilor
                    System.out.println("Raport voturi " + numeCircumscriptie + ":");
                    for (int k = 0; k < alegeri.get(i).circumscriptii.get(j).candidati.size(); k++) {
                        System.out.println(alegeri.get(i).circumscriptii.get(j).candidati.get(k).getNume() + " " + alegeri.get(i).circumscriptii.get(j).candidati.get(k).getCNP() + " - " + alegeri.get(i).circumscriptii.get(j).candidati.get(k).getNumarVoturi());
                    }
                }


            } else if (optiune == 12) {

                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i, j;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("IN_CURS")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }

                // Facem numarul national de voturi
                int nrVoturi = 0;
                for (j = 0; j < alegeri.get(i).candidati.size(); j++) {
                    nrVoturi = nrVoturi + alegeri.get(i).candidati.get(j).getNumarVoturi();
                }

                // Daca nu exista voturi, afisam un mesaj de eroare
                if (nrVoturi == 0) {
                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                    return;
                } else {
                    // Sortarea candidatilor in ordine descrescatoare dupa numarul de voturi sau dupa CNP in caz de egalitate
                    Collections.sort(alegeri.get(i).candidati, (c1, c2) -> {
                        int cmp = Integer.compare(c2.getNumarVoturi(), c1.getNumarVoturi());
                        if (cmp == 0) {
                            return c2.getCNP().compareTo(c1.getCNP());
                        }
                        return cmp;
                    });
                    // Afisarea candidatilor
                    System.out.println("Raport voturi Romania: ");
                    for (int k = 0; k < alegeri.get(i).candidati.size(); k++) {
                        System.out.println(alegeri.get(i).candidati.get(k).getNume() + " " + alegeri.get(i).candidati.get(k).getCNP() + " - " + alegeri.get(i).candidati.get(k).getNumarVoturi());
                    }
                }

            } else if (optiune == 13) {

                // Citim argumentele din input
                String idAlegeri = scanner.next();
                String numeCircumscriptie = scanner.next().trim();

                int i, j;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("IN_CURS")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }

                // Verificarea pentru circumscriptii
                for (j = 0; j < alegeri.get(i).circumscriptii.size(); j++) {
                    if (alegeri.get(i).circumscriptii.get(j).getNumeCircumscriptie().equals(numeCircumscriptie)) {
                        break;
                    }
                }
                if (j == alegeri.get(i).circumscriptii.size()) {
                    System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
                    return;
                }

                // Calcularea numarului de voturi national
                int nrVoturiNational = 0;
                for (int k = 0; k < alegeri.get(i).candidati.size(); k++) {
                    nrVoturiNational = nrVoturiNational + alegeri.get(i).candidati.get(k).getNumarVoturi();
                }

                // Calcularea numarului de voturi din circumscriptie
                int nrVoturi = 0;
                for (int k = 0; k < alegeri.get(i).circumscriptii.get(j).candidati.size(); k++) {
                    nrVoturi = nrVoturi + alegeri.get(i).circumscriptii.get(j).candidati.get(k).getNumarVoturi();
                }

                // Daca nu exista voturi, afisam un mesaj de eroare
                if (nrVoturi == 0) {
                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie);
                    return;
                }

                // Afisarea ca in cerinta
                System.out.println("In " + numeCircumscriptie + " au fost " + nrVoturi + " voturi din " + nrVoturiNational + ". Adica " + (nrVoturi * 100) / nrVoturiNational + "%. Cele mai multe voturi au fost stranse de " + alegeri.get(i).circumscriptii.get(j).candidati.get(0).getCNP() + " " + alegeri.get(i).circumscriptii.get(j).candidati.get(0).getNume() + ". Acestea constituie " + (alegeri.get(i).circumscriptii.get(j).candidati.get(0).getNumarVoturi() * 100) / nrVoturi + "% din voturile circumscriptiei.");

            } else if (optiune == 14) {

                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i, o;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("IN_CURS")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }

                // Calcularea numarului de voturi national
                int nrVoturiNational = 0;
                for (int k = 0; k < alegeri.get(i).candidati.size(); k++) {
                    nrVoturiNational = nrVoturiNational + alegeri.get(i).candidati.get(k).getNumarVoturi();
                }

                // Daca nu exista voturi, afisam un mesaj de eroare
                if (nrVoturiNational == 0) {
                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                    return;
                }

                // Calcularea voturilor pentru fiecare regiune si in acelasi timp construirea listei de regiuni
                ArrayList<Regiune> regiuni = new ArrayList<>();

                for (int k = 0; k < alegeri.get(i).circumscriptii.size(); k++) {
                    int nrVoturi = 0;
                    for (int l = 0; l < alegeri.get(i).circumscriptii.get(k).candidati.size(); l++) {
                        nrVoturi = nrVoturi + alegeri.get(i).circumscriptii.get(k).candidati.get(l).getNumarVoturi();
                    }
                    for (o = 0; o < regiuni.size(); o++) {
                        if (regiuni.get(o).getNumeRegiune().equals(alegeri.get(i).circumscriptii.get(k).getRegiune())) {
                            regiuni.get(o).setNumarVoturi(regiuni.get(o).getNumarVoturi() + nrVoturi);
                            break;
                        }
                    }
                    if (o == regiuni.size()) {
                        Regiune regiune = new Regiune(alegeri.get(i).circumscriptii.get(k).getRegiune(), nrVoturi);
                        regiuni.add(regiune);
                    }
                }

                // Calcularea voturilor pentru fiecare candidat in fiecare regiune
                for (int k = 0; k < alegeri.get(i).circumscriptii.size(); k++) {
                    for (int l = 0; l < regiuni.size(); l++) {
                        if (regiuni.get(l).getNumeRegiune().equals(alegeri.get(i).circumscriptii.get(k).getRegiune())) {
                            for (int m = 0; m < alegeri.get(i).circumscriptii.get(k).candidati.size(); m++) {
                                int n;
                                for (n = 0; n < regiuni.get(l).candidati.size(); n++) {
                                    if (regiuni.get(l).candidati.get(n).getCNP().equals(alegeri.get(i).circumscriptii.get(k).candidati.get(m).getCNP())) {
                                        regiuni.get(l).candidati.get(n).setNumarVoturi(regiuni.get(l).candidati.get(n).getNumarVoturi() + alegeri.get(i).circumscriptii.get(k).candidati.get(m).getNumarVoturi());
                                        break;
                                    }
                                }
                                if (n == regiuni.get(l).candidati.size()) {
                                    Candidat candidat = new Candidat(alegeri.get(i).circumscriptii.get(k).candidati.get(m).getCNP(), alegeri.get(i).circumscriptii.get(k).candidati.get(m).getVarsta(), alegeri.get(i).circumscriptii.get(k).candidati.get(m).getNume());
                                    candidat.setNumarVoturi(alegeri.get(i).circumscriptii.get(k).candidati.get(m).getNumarVoturi());
                                    regiuni.get(l).candidati.add(candidat);
                                }

                            }
                        }
                    }
                }

                // Sortarea regiunilor in ordine alfabetica
                Collections.sort(regiuni, (r1, r2) -> r1.getNumeRegiune().compareTo(r2.getNumeRegiune()));

                for (int k = 0; k < regiuni.size(); k++) {
                    // Sortarea candidatilor in ordine descrescatoare dupa numarul de voturi sau dupa CNP in caz de egalitate
                    // in fiecare regiune
                    Collections.sort(regiuni.get(k).candidati, (c1, c2) -> {
                        int cmp = Integer.compare(c2.getNumarVoturi(), c1.getNumarVoturi());
                        if (cmp == 0) {
                            return c2.getCNP().compareTo(c1.getCNP());
                        }
                        return cmp;
                    });
                }

                // Afisarea ca in cerinta
                System.out.println("In Romania au fost " + nrVoturiNational + " voturi.");
                for (int k = 0; k < regiuni.size(); k++) {
                    System.out.println("In " + regiuni.get(k).getNumeRegiune() + " au fost " + regiuni.get(k).getNumarVoturi() + " voturi din " + nrVoturiNational +
                            ". Adica " + (regiuni.get(k).getNumarVoturi() * 100) / nrVoturiNational + "%. Cele mai multe voturi au fost stranse de " +
                            regiuni.get(k).candidati.get(0).getCNP() + " " + regiuni.get(k).candidati.get(0).getNume() + ". Acestea constituie "
                            + (regiuni.get(k).candidati.get(0).getNumarVoturi() * 100) / regiuni.get(k).getNumarVoturi() + "% din voturile regiunii.");

                }


            } else if (optiune == 15) {

                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i, j;

                // Verificarea pentru alegeri
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                } else if (alegeri.get(i).getStatusAlegeri().equals("IN_CURS")) {
                    System.out.println("EROARE: Inca nu s-a terminat votarea");
                    return;
                }

                // Afisarea fraudelor daca exista in ordinea last in first out ca in cerinta
                if (alegeri.get(i).fraude.size() == 0) {
                    System.out.println("GOL: Romanii sunt cinstiti");
                } else {
                    System.out.println("Fraude comise: ");
                    for (j = alegeri.get(i).fraude.size() - 1; j >= 0; j--) {
                        System.out.println("In " + alegeri.get(i).fraude.get(j).getNumeCircumscriptie() + ": " + alegeri.get(i).fraude.get(j).getCNP() + " " + alegeri.get(i).fraude.get(j).getNume());
                    }
                }

            } else if (optiune == 16) {

                // Citim argumentul din input
                String idAlegeri = scanner.next();

                int i;

                // Cautam alegerile cu id-ul respectiv
                for (i = 0; i < alegeri.size(); i++) {
                    if (alegeri.get(i).getIdAlegeri().equals(idAlegeri)) {
                        break;
                    }
                }

                // Daca nu exista alegeri cu id-ul respectiv, afisam un mesaj de eroare
                if (i == alegeri.size()) {
                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                    return;
                }

                // Stergem alegerea
                System.out.println("S-au sters alegerile " + alegeri.get(i).getNumeAlegeri());
                alegeri.remove(i);

            } else if (optiune == 17) {
                // Daca exista alegeri le afisam, altfel afisam un mesaj de eroare
                if (alegeri.size() == 0) {
                    System.out.println("GOL: Nu sunt alegeri");
                } else {
                    System.out.println("Alegeri:");
                    for (int i = 0; i < alegeri.size(); i++) {
                        System.out.println(alegeri.get(i).getIdAlegeri() + " " + alegeri.get(i).getNumeAlegeri());
                    }
                }

            } else if (optiune == 18) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}