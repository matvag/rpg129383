package it.unicam.cs.mpgc.rpg129383.interfaces;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;

public interface Oggetto {
    String getNome();
    String getDescrizione();
    /**
     * Valore in monete d'oro
     */
    int getValore();
    /**
     * Applica l'effetto dell'oggetto all'eroe
     */
    void usa(Eroe eroe);
}
