package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.interfaces.Combattente;

public abstract class Personaggio implements Combattente {
    private static final int DANNO_MINIMO = 1;

    private final String nome;
    private int puntiVitaMax;
    private int puntiVita;
    private int attacco;
    private int difesa;

    public Personaggio(String nome, int puntiVitaMax, int attacco, int difesa) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (puntiVitaMax <= 0 || attacco < 0 || difesa < 0) {
            throw new IllegalArgumentException("Statistiche non valide");
        }
        this.nome = nome;
        this.puntiVitaMax = puntiVitaMax;
        this.attacco = attacco;
        this.difesa = difesa;
    }

}
