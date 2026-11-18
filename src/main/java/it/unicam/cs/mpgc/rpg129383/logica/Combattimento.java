package it.unicam.cs.mpgc.rpg129383.logica;

import it.unicam.cs.mpgc.rpg129383.enums.StatoCombattimento;
import it.unicam.cs.mpgc.rpg129383.utils.Dado;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestisce un combattimento a turni tra l'eroe e un nemico
 * A ogni azione dell'eroe (attacco, oggetto, fuga) segue la risposta del nemico
 * Tutto ciò che accade viene scritto nel registro, che l'interfaccia grafica può mostrare
 */
public class Combattimento {


    private final Eroe eroe;
    private final Nemico nemico;
    private final Dado dado;
    private final List<String> registro;
    private StatoCombattimento stato;

    public Combattimento(Eroe eroe, Nemico nemico, Dado dado) {
        this.eroe = eroe;
        this.nemico = nemico;
        this.dado = dado;
        this.registro = new ArrayList<>();
        this.stato = StatoCombattimento.IN_CORSO;

    }


}
