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
        Node current = hode = hale;
        int j = 0;
        for (int i = 0; i < a.length; i++){
            if (a[i] != null) {
                current = new Node<>(a[i], null, null);
                antall++;
                j = i + 1; //legger i + 1 for at løkken ikke skal telle med head da den ellers vil bli telt to ganger
                break;
            }
        }
            for(; j < a.length; j++){
                if(a[j] != null){
                    current = new Node<>(a[j], hale, null);
                    current = current.neste;
                    antall++;
                }
            }
            hale = current;
    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til, fra + til);
        DobbeltLenketListe <T> utskrift = new DobbeltLenketListe<>();
        for(int i = fra; i < til; i++){
            utskrift.leggInn(hent(i));
        }
        return utskrift;
    }

    private void fraTilKontroll(int fra, int til, int antall){
        if(fra > til){
            throw new IllegalStateException();
        }
        else if(fra < 0 || til > antall){
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) != -1){
            return true;
        }
        return false;
    }

    private Node<T> finnNode(int indeks){
        indeksKontroll(indeks,false);

        if(indeks < antall/2){
            Node<T> current = hode;
            int i=0;
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
        Node<T> gammel = finnNode(indeks);
        T gammelVerdi = gammel.verdi;
        gammel.verdi = nyverdi;
        endringer++;

        return gammelVerdi;
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


