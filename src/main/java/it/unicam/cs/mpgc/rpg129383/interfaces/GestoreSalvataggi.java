package it.unicam.cs.mpgc.rpg129383.interfaces;

import it.unicam.cs.mpgc.rpg129383.model.Eroe;

import java.util.Optional;

/**
 * Salva e carica l'eroe del giocatore
 */
public interface GestoreSalvataggi {

    void salva(Eroe eroe);

    /**
     * carica l'eroe salvato e se non c'è nessun salvataggio
     */
    Optional<Eroe> carica();

    boolean esisteSalvataggio();
}
