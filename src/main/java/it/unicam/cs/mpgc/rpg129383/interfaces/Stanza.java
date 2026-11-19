package it.unicam.cs.mpgc.rpg129383.interfaces;

import it.unicam.cs.mpgc.rpg129383.enums.TipoStanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;

import java.util.Optional;

/**
 * Una stanza del dungeon. Ogni tipo di stanza decide cosa succede quando l'eroe entra.
 */
public interface Stanza {

    TipoStanza getTipo();


    String entra(Eroe eroe);

    /**
     * Optional : in tutte le stanze non c'è un nemico es. stanza riposo
     */

    default Optional<Nemico> getNemico() {
        return Optional.empty();
    }
}