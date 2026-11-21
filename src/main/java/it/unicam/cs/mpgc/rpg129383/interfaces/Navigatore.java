package it.unicam.cs.mpgc.rpg129383.interfaces;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;

public interface Navigatore {
    void mostraMenu();

    void mostraCreazioneEroe();

    void nuovaPartita(String nome, TipoEroe tipo);

    void caricaPartita();
}
