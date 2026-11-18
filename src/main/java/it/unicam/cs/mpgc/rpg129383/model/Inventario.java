package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Inventario {
    public static final int CAPACITA_PREDEFINITA = 100;

    private final List<Oggetto> oggetti;
    private final int capacita;

    public Inventario(){
        this(CAPACITA_PREDEFINITA);
    }

    public Inventario(int capacita){
        if (capacita <= 0) {
            throw new IllegalArgumentException("La capacità deve essere positiva");
        }
        this.oggetti = new ArrayList<Oggetto>();
        this.capacita = capacita;
    }

    public boolean aggiungiOggetto(Oggetto oggetto){
        if (isPieno()) {
           return false;
        }
        return oggetti.add(oggetto);
    }

    public boolean rimuoviOggetto(Oggetto oggetto){
        return oggetti.remove(oggetto);
    }

    public boolean contiene(Oggetto oggetto) {
        return oggetti.contains(oggetto);
    }

    public boolean isPieno(){
        return oggetti.size() >= capacita;
    }

    public int getNumeroOggetti(){
        return oggetti.size();
    }

    public int getCapacita(){
        return capacita;
    }
    /**
     * copia non modificabile della lista degli oggetti
     */
    public List<Oggetto> getOggetti() {
        return Collections.unmodifiableList(oggetti);
    }
    /**
     *  solo le pozioni presenti nell'inventario
     */
    public List<Pozione> getPozioni() {
        return oggetti.stream()
                .filter(oggetto -> oggetto instanceof Pozione)
                .map(oggetto -> (Pozione) oggetto)
                .toList();
    }
    /**
     *  il valore complessivo in oro di tutti gli oggetti
     */
    public int getValoreTotale() {
        return oggetti.stream()
                .mapToInt(Oggetto::getValore)
                .sum();
    }
    /**
     * Raggruppa gli oggetti uguali contando quante copie ci sono di ognuno
     */
    public Map<Oggetto, Long> getOggettiRaggruppati() {
        return oggetti.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
