package it.unicam.cs.mpgc.rpg129383.enums;

public enum TipoEroe {
    GUERRIERO("Guerriero", "Molta vita e difesa, danno costante"),
    MAGO("Mago", "Poca vita ma grande danno"),
    LADRO("Ladro", "Molto veloce può infliggere colpi critici");

    private final String nome;
    private final String descrizione;

    TipoEroe(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

}
