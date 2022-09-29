package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder utskrift = new StringBuilder();
        Node<T> current = hode;

        if (antall == 0){
            utskrift.append("[]");
            return utskrift.toString();
        }

        utskrift.append("[ " + current.verdi);
        for(int i = 0; i <= antall; i++){
            utskrift.append(", " + current.verdi);
            current = current.neste;
        }

        utskrift.append(" ]");
        return utskrift.toString();
    }

    public String omvendtString() {
        StringBuilder utskrift = new StringBuilder();
        Node<T> current = hale;

        if (antall == 0){
            utskrift.append("[]");
            return utskrift.toString();
        }

        utskrift.append("[ " + current.verdi);
        for(int i = antall; i > 0; i--){
            utskrift.append(", " + current.verdi);
            current = current.forrige;
        }

        utskrift.append(" ]");
        return utskrift.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


