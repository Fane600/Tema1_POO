[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3CLMYIcM)

# Tema 1

## Optiunea 0
Am citit argumentele din input si am verifcat daca exista alegerea in lista de alegeri, in caz afirmativ am afisat mesajul de eroare, altfel am adaugat alegerea in lista.

## Optiunea 1
Ne folosim de metoda pornire alegeri din clasa Alegeri, dupa ce verifcam daca nu exista erori.

## Optiunea 2
Adaugam circumscriptia in lista de circumscriptii pentru alegerea cu id-ul respectiv din lista de alegeri.

## Optiunea 3
Cautam alegerea si circumscriptia, apoi daca exista circumscriptia o eliminam.

## Optiunea 4
Adaugam candidatul in lista de candidati din alegerea cu id-ul specificat in input

## Optiunea 5
Cautam alegerea si candidatul, daca exista candidatul il eliminam.

## Optiunea 6
Adaugam votantul in circumsciptie, dupa ce verificam daca exista circumsciptia si nu exista alt votant cu CNP-ul votantului pe care vrem sa il adaugam.

## Optiunea 7
Afisam candidatii din lista de candidati pentru alegerea cu id-ul precizat.

## Optiunea 8
Afisam votantii din lista de votanti, din circumsciptia si alegerea precizate in input.

## Optiunea 9
Aici am adaugat voturile in 2 liste diferite de candidati pentru a ma ajuta la optiunile viitoare. Una pe care o aveam deja in alegeri, iar cealalta pe care o aveam in fiecare circumsciptie. De fiecare data cand aveam un vot valid si candidatul nu exista in lista de candidati din circumscriptie, il adaugam. Daca exista deja ii incrementam numarul de voturi si ii incrementam numarul de voturi si in lista din alegeri. Am facut acest lucru pentru a putea calcula numarul de voturi national si numarul de voturi per circumscriptie.

## Optiunea 10
Verificam daca exista alegerea si daca era IN_CURS si o marcam ca si TERMINAT.

## Optiunea 11
Avem tot ce ne trebuie in lista de candidati din circumscriptie. Cautam alegerea si circumsciptia, apoi sortam lista de candidati si ii afisam asa cum este precizat in cerinta.

## Optiunea 12
Aici calculam numarul de voturi national si ne folosim de lista de candidati din alegere.

## Optiunea 13
Afisam o analiza per circumscriptie in care ne folosim de lista de candidati din circumscriptie pentru voturile din circumscriptie si lista de candidati din alegere pentru votul national.

## Optiunea 14
Aici ne folosim de calsa Regiune unde vom avea din nou o noua lista de candidati. Prima oara calculam numarul de voturi national, apoi creeam
o lista de regiuni si incepem sa parcurgem circumscriptiile si sa construim lista de regiuni. In acelasi timp calculam si numarul total de voturi per regiune. Apoi in lista construita parcurgem candidatii din fiecare circumscriptie, gasim regiunea lor si adaugam datele lor in lista de candidati din regiune.
In final sortam alfabetic regiunile si apoi candidatii dupa voturi sau CNP in caz de egalitale si afisam ca in cerinta.

## Optiunea 15
Aici afisam fraudele care au fost tinute in lista de fraude din alegeri. Acestea au fost adaugate la optiunea 9, unde au fost contorizate voturile si in acelasi timp verificam daca se comit fraude.

## Optiunea 16
Cautam alegerea dupa id si o stergem.

## Optiunea 17
Afisam toate alegerile din lista de alegeri.

## Optiunea 18
Un simplu return pentru a iesi din bucla infinita si a termina programul.

## Precizare generala pentru toate optiunile
In toate optiunile au fost tratate cazurile de eroare precizate.

# Folosirea AI
Am folosit generarea cu AI doar pentru comparatoare atunci cand sortam diferite liste, deoarece la momentul acela nu eram foarte familiarizat cu sortarile de ArrayList-uri.

# BONUS

## Cazuri limita

Votanti duplicat in circumscriptii diferite:

Un votant incearca sa se inregistreze in mai multe circumscriptii folosind acelasi CNP. Ar trebui sa detectem si sa prevenim aceasta situatie.
Rezolvare: Validare suplimentara la nivel de sesiune de alegeri pentru unicitatea CNP-urilor in toate circumscriptiile.

CNP cu caractere invalide:

Se introduce un CNP care are lungimea buna dar nu este format din cifre.
Rezolvare: Adaugam o verificare in plus care sa verifice daca CNP-ul are litere sau alte simboluri invalide.

Varsta mult prea mare:

Introducerea unei varste care depasete durata de viata a unui om.
Rezolvare: Aruncam o eroare daca varsta depaseste o anumita limita. 150 de ani ar fi mai mult decat suficient.

## Refactorizare comenzi

Refactorizarea comenzilor pentru gestionarea erorilor:

In loc sa returnezi un mesaj de eroare generic, fiecare comanda ar putea oferi informatii suplimentare despre ce anume a cauzat eroarea. De exemplu:
In cazul unui CNP invalid, nu doar sa se returneze "CNP invalid", ci si sa se explice care sunt conditiile specifice care trebuie indeplinite (ex: "CNP-ul trebuie sa fie format din 13 caractere numerice").
Exemplu: "EROARE: CNP invalid. Asigurati-va ca CNP-ul este format din 13 caractere numerice."

Imbunatatirea comenzii pentru listarea raporturilor de vot:

In loc de a afisa doar un simplu raport in format text, ar putea fi introdusa o structura de date (cum ar fi un obiect de tip „Raport”) care sa permita un format mai structurat pentru rezultate. Acest lucru va face ca raporturile sa fie mai usor de citit si mai usor de manipulat in aplicatie (pentru export sau generare de statistici suplimentare).
Exemplu: In loc de "Raport voturi Arges", ar putea fi un obiect JSON sau o tabela clara care include numarul total de voturi, numarul de voturi valabile, si top 3 candidati cu cel mai mare numar de voturi.

Refactorizarea comenzilor pentru prevenirea erorilor din cauza starii alegerilor:

In loc de a returna un mesaj de eroare doar cand alegerile nu sunt in starea corecta, se poate implementa un sistem de validare centralizat care va fi invocat la fiecare comanda pentru a verifica daca alegerile sunt in starea corecta. Astfel, nu ar mai fi necesar ca fiecare comanda sa contina verificari de stare.
Exemplu: Comanda "Pornire alegeri" ar putea invoca un validor care sa verifice daca starea alegerilor este "NEINCEPUT", iar daca nu, sa returneze "EROARE: Alegerile nu sunt in stadiul corect pentru a incepe." Acelasi principiu poate fi aplicat si pentru alte comenzi.

