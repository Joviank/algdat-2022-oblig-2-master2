# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Jovia Namayanja Kaggwa, S364526, s364526@oslomet.no


# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Jeg har gjort oppgave 1-6 + 8

# Oppgavebeskrivelse

* oppgave 1

I oppgave 1 tenkte var det lurest å løpe gjennom arrayen og først definere hodet og hale. Etter
dette tenkte jeg at det var lurt å bruke en if statment som passer på at selv om vi har funnet hode og hale
dersom det er flere elementer i arrayen så ønsker vi å fortsette å lette etter eventuelle verdier.
I denne løkken hopper halen for hver gang et nytt element blir lagt inn. 

Dette var koden som passet best i oppgave 1, da jeg kom på flere ulike løsninger
men som fikk f.eks oppgave 2 til å ikke funke

* oppgave 2

I oppgave 2a passer vi på at dersom listen er tom så returnerer vi "[]"
ellers så bygger vi en streng ved hjelp av en while løkke. Til slutt returnerer vi strengen.

Oppgave 2b hvis hale og hode er null ønsker vi at den verdien som blir lagt inn skal da være hode og hale ellers
så lager vi en hjelpe variabel som går gjennom listen og legg til verdien på slutten og den blir da til hale.

* oppgave 3

oppgave 3a
lager en private metode som går gjennom en løkke avhengig av om indeks er større enn antall/2 eller ikke.

hent metoden returner current sin verdi.

oppdater kaller finnnode for å finn hvilken den skal erstatte
vi lagrer den gamle verdien da det er den vi skal returnere, også erstatter vi den med den nye verdien.

oppgave 3b oppretter vi en ny liste ut ifra den opprinnelige listen vi bruker en for løkke da vi vet at vi skal slutte på til
også legger vi til elementer i den nye listen ved å kalle legginn som tar å kaller på hent inn hvor hent inn tar inn verdien på i

* oppgave 4

Ganske rett frem, vi lager en variabel som teller posisjonen også benytter vi en while løkke, dersom vi finner verdien returnerer vi posisjonen til den verdien hvis ikke 
blir -1 returnert

* oppgave 5

Opprinnglig i oppgave 5 var alle utfallene for seg selv men forrige løsningen ble ignorent når jeg kjørte den. Måtte derfor
bytte ut den gamle koden med en ny kode som jeg fikk hjelp av Erling med å opprette.

* oppgave 6

Samme problem som i oppgave 5, men fikk tips av Øystein om å opprette en hjelpemetode da både boolean og indeks metoden benytter samme kode når vi ønsker å fjerne et element.
Satt opp alle spesiall tilfellene i hjelpemetoden også kaller jeg heller på hjelpemetoden i boolean og indeks.

* oppgave 8

I oppgave 8 gikk jeg bare frem som teksten sa at jeg skulle gå frem. I opprettet Exceptions, passet på å lagre verdien før den går over på den neste verdien også returnerer jeg den verdien som ble lagret
I oppgave 8c tok jeg inspirasjon DobbeltLenketListeIterator() og så hvordan den var kodet og gjorde det samme men får og finne noden i den oppgitte indeksen bruker jeg en while løkke. Resten av koden gjorde lik som den andre private metoden.


