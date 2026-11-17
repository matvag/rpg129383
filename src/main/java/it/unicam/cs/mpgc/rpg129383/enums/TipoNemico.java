package it.unicam.cs.mpgc.rpg129383.enums;

public enum TipoNemico {
    GOBLIN("Goblin", 40 , 10, 2, 25, 10),
    SCHELETRO("Scheletro", 55, 12, 4, 35, 15),
    ORCO("Orco", 80, 15, 6, 50, 25),
    DRAGO("Drago", 200, 24, 10, 200, 150);

    private final String nome;
    private final int puntiVita;
    private final int attacco;
    private final int difesa;
    private final int esperienza;
    private final int oro;

    TipoNemico(String nome, int puntiVita, int attacco, int difesa, int esperienza, int oro){
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.attacco = attacco;
        this.difesa = difesa;
        this.esperienza = esperienza;
        this.oro = oro;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getAttacco() {
        return attacco;
    }

    public int getDifesa() {
        return difesa;
    }

    public int getEsperienza() {
        return esperienza;
    }

    public int getOro() {
        return oro;
    }
}
