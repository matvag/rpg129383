package it.unicam.cs.mpgc.rpg129383.persistenza;

import it.unicam.cs.mpgc.rpg129383.enums.TipoOggetto;
import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;
import it.unicam.cs.mpgc.rpg129383.model.Arma;
import it.unicam.cs.mpgc.rpg129383.model.Pozione;

/**
 * Copia semplice di un oggetto pronta per essere scritta nel file JSON
 */
public class DatiOggetto {

    private TipoOggetto tipo;
    private String nome;
    private int valore;
    private int effetto;


    private DatiOggetto() {
    }


    public DatiOggetto(Oggetto oggetto) {
        this.nome = oggetto.getNome();
        this.valore = oggetto.getValore();
        if (oggetto instanceof Pozione pozione) {
            this.tipo = TipoOggetto.POZIONE;
            this.effetto = pozione.getCura();
        } else if (oggetto instanceof Arma arma) {
            this.tipo = TipoOggetto.ARMA;
            this.effetto = arma.getBonusAttacco();
        } else {
            throw new IllegalArgumentException("Oggetto non salvabile: " + oggetto.getNome());
        }
    }

    public Oggetto creaOggetto() {
        return switch (tipo) {
            case POZIONE -> new Pozione(nome, effetto, valore);
            case ARMA -> new Arma(nome, effetto, valore);
        };
    }
}