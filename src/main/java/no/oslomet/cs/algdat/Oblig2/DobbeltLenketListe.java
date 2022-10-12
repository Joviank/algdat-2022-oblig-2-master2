package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
        //Passer på at dersom det er ingen noder vil hale og hode være null
        hode = hale = null;
    }

    public DobbeltLenketListe(T[] a) {
        if (a == null){
            throw new NullPointerException("Tabellen a er null!");
        }
        int i = 0;
        for (; i < a.length; i++){
            if (a[i] != null) {
                hode = hale = new Node<>(a[i], null, null);
                antall++;
                break;
            }
        }
        if(i != a.length){ // dersom det er flere elementer igjen i listen skal vi gå gjennom de
            int j = i + 1; //legger i + 1 for at løkken ikke skal telle med head da den ellers vil bli telt to ganger
            for(; j < a.length; j++){
                if(a[j] != null){
                    hale = hale.neste = new Node<>(a[j], hale, null);
                    antall++;
                }
            }
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til, fra + til);
        DobbeltLenketListe <T> utskrift = new DobbeltLenketListe<>();
        for(int i = fra; i < til; i++){
            utskrift.leggInn(hent(i));
        }
        return utskrift;
    }

    private void fraTilKontroll(int fra, int til, int tabLengde){
        if(fra > til){
            throw new IllegalArgumentException();
        }
        if(fra < 0 || til > tabLengde){
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(antall == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Hei, verdien er null! Verdien må være større enn null! : )");
        if(hode == null && hale == null){
            Node N = new Node(verdi);
            hode = hale = N;
            antall++;
            endringer++;

            return true;
        }

        else if(hode != null && hale != null){
            Node N = new Node(verdi);
            Node q;
            q = hode;
            while(q != hale){
                q = q.neste;
            }
            q.neste = N;
            hale = N;
            hale.forrige = q;

            antall++;
            endringer++;
            return true;
        }
        return false;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Hei! Verdien MÅ være større eller lik 0!");
        if (indeks > antall){
            throw new IndexOutOfBoundsException("Indeks er større enn antall noder");
        } else if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeksen kan ikke være negativ");
        }
        //slutten eller hvis den er tom
        if (tom() || indeks == antall) {
            leggInn(verdi);
            return;
        }
        //hvis vi skal legg til en verdi på starten
        Node n = new Node (verdi);

        if (indeks == 0){
            n.neste = hode;
            hode.forrige = n;
            hode = n;
        }
        //Hvis vi skal legge til en verdi imellom
        else {
            Node<T> current = finnNode(indeks);
            Node<T> P = current.forrige;

            P.neste = n;
            current.forrige = n;
            n.neste = current;
            n.forrige = P;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) != -1){
            return true;
        }
        return false;
    }

    private Node<T> finnNode(int indeks){
        if(indeks < antall/2){
            Node<T> current = hode;
            int i = 0;
            while(i < indeks){
                current = current.neste;
                i++;
            }
            return current;
        }
        else {
            Node<T> current = hale;
            int i = antall - 1;
            while(i > indeks){
                current = current.forrige;
                i--;
            }
            return current;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> current = hode;
        int p = 0;

        while(current != null){
            if(current.verdi.equals(verdi)){
                return p;
            }
            p++;
            current = current.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi,"verdien kan ikke være null!");
        indeksKontroll(indeks,false);

        Node<T> gammel = finnNode(indeks);

        T gammelVerdi = gammel.verdi;
        gammel.verdi = nyverdi;
        endringer++;

        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }
        Node<T> current = hode;
        while(!current.verdi.equals(verdi)){
            current = current.neste;
            if(current.equals(hale)){
                break;
            }
        }
        if(verdi.equals(current.verdi)){
            fjernNode(current);
            return true;
        }
        return false;
    }
    //Lager en hjelpemetode som både kan brukes med indeks og verdi
    private void fjernNode(Node<T> P){
        //hvis det er kun en node
        if(P == hode && P == hale){
            hode = hale = null;
        }
        //legger til på starten hvis hode peker til neste node
        else if(P == hode){
            hode = hode.neste;
            hode.forrige = null;
        }
        //Hvis noden er kun hale
        else if(P == hale){
            hale = hale.forrige;
            hale.neste = null;
        }
        //Hvis noden er midt i
        else {
            P.neste.forrige = P.forrige;
            P.forrige.neste = P.neste;
        }
        antall--;
        endringer++;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);
        Node<T> current = finnNode(indeks);
        T lagretVerdi = current.verdi;

        fjernNode(current);
        return lagretVerdi;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder utskrift = new StringBuilder();
        Node<T> current = hode;

        if (current == null){
            utskrift.append("[]");
        }
        else {
            utskrift.append("[").append(current.verdi);
            current = current.neste;

            while(current != null){
                utskrift.append(", ").append(current.verdi);
                current = current.neste;
            }
            utskrift.append("]");
        }
        return utskrift.toString();
    }

    public String omvendtString() {
        StringBuilder utskrift = new StringBuilder();
        Node<T> current = hale;

        if (current == null){
            utskrift.append("[]");
        }
        else {
            utskrift.append("[").append(current.verdi);
            current = current.forrige;

            while(current != null){
            utskrift.append(", ").append(current.verdi);
            current = current.forrige;
            }
            utskrift.append("]");
        }
        return utskrift.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }
        //jeg koder omtrent likt som i koden over da kun Noden denne er den eneste forskjellen
        private DobbeltLenketListeIterator(int indeks) {
            denne = hode;
            int i = 0;
        //setter pekeren denne til den noden som hører til den oppgitte indeksen
            while(i < indeks){
                denne = denne.neste;
                i++;
            }
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("ikke lov å endre listen imens den blir gått gjennom");
            }
            if(!hasNext()){
                throw new NoSuchElementException("ikke flere noder igjen");
            }

            T lagrerDenne = denne.verdi;
            denne = denne.neste;
            fjernOK = true;
            return lagrerDenne;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


